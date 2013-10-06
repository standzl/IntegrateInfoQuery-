package com.standzl.enterpriser.activtiy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.standzl.base.IParserRuleService;
import com.standzl.doman.BaseEnterPriserBean;
import com.standzl.doman.EnterpriserBean;
import com.standzl.interfaceimpl.EnterpriserDetailParser;
import com.standzl.interfaceimpl.EnterpriserOverViewParser;
import com.standzl.util.Constant;
import com.standzl.util.DBUtil;
import com.standzl.util.SQLiteUtil;
import com.standzl.util.Tools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText enterPriserName;

	private EditText enterPriserRegNo;

	private EditText enterPriserPersonName;

	private Button queryBtn;

	private Button resetBtn;

	private WebView webView;

	public static final int HANDLER_MESSAGE_NO_NETWORK = 116;

	public static final int HANDLER_MESSAGE_VAILDATOR_ALERT = 117;

	public static final int HANDLER_MESSAGE_GET_DATA_OK = 118;

	public static final int HANDLER_MESSAGE_VAILDATOR_OK = 119;

	public static final int HANDLER_MESSAGE_SHOW_DETAIL = 120;

	public List<BaseEnterPriserBean> beanList;

	public WebViewObj wvObj = null;

	private Tools tool = Tools.getInstance();

	private IParserRuleService parserService;

	private IParserRuleService DetailparserService;

	private EnterpriserBean detailBean;

	private AlertDialog.Builder detailDialog;
	
	private AlertDialog.Builder networkStatusDialog;
	
	private ProgressDialog dialog;
	
	private DBUtil  dbUtil;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_MESSAGE_NO_NETWORK:
				networkStatusDialog.setMessage("当前无可用的网络连接,请检查设置");
				networkStatusDialog.setPositiveButton("确定",null);
				networkStatusDialog.show();
				break;
			case HANDLER_MESSAGE_VAILDATOR_ALERT:
				Toast.makeText(MainActivity.this, "公司名称，注册号，法人代表三项不能都为空", 1200)
						.show();
				break;
			case HANDLER_MESSAGE_VAILDATOR_OK:
				dialog.show();
				break;
			case HANDLER_MESSAGE_GET_DATA_OK:
				wvObj.showData();
				dialog.cancel();
				break;
			case HANDLER_MESSAGE_SHOW_DETAIL:
				detailDialog.setMessage("实体名称："
						+ detailBean.getEnterPriserName() + "\r\n" + "行政区划："
						+ detailBean.getAdministrativeDivision() + "\r\n"
						+ "经营期限至：" + detailBean.getOperatPeriodEnd() + "\r\n"
						+ "企业状态：" + detailBean.getEnterPriserStatus() + "\r\n"
						+ "企业类型：" + detailBean.getEnterPriserType() + "\r\n"
						+ "注册资金：" + detailBean.getRegeditMenoy() + "\r\n"
						+ "地址：" + detailBean.getEnterPriserAddr() + "\r\n"
						+ "登记机关：" + detailBean.getRegisterBody() + "\r\n"
						+ "年检年度：" + detailBean.getCheckYear() + "\r\n"
						+ "年检结果：" + detailBean.getCheckResult());
				detailDialog.show();
				break;
			}
		}
	};
	
	Handler handler2=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_MESSAGE_NO_NETWORK:
				Toast.makeText(MainActivity.this,"Handler2_没有网络",Toast.LENGTH_LONG).show();
				break;
			case HANDLER_MESSAGE_VAILDATOR_ALERT:
				Toast.makeText(MainActivity.this,"Handler2_查询属性校验失败",Toast.LENGTH_LONG).show();
				break;
			case HANDLER_MESSAGE_VAILDATOR_OK:
				Toast.makeText(MainActivity.this,"Handler2_校验通过",Toast.LENGTH_LONG).show();
				break;
			case HANDLER_MESSAGE_GET_DATA_OK:
				Toast.makeText(MainActivity.this,"Handler2_得到列表数据",Toast.LENGTH_LONG).show();
				break;
			case HANDLER_MESSAGE_SHOW_DETAIL:
				Toast.makeText(MainActivity.this,"Handler2_显示详细",Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		enterPriserName = (EditText) findViewById(R.id.eNameText);
		enterPriserRegNo = (EditText) findViewById(R.id.eRegNoText);
		enterPriserPersonName = (EditText) findViewById(R.id.ePersonNameText);
		wvObj = new WebViewObj();
		webView = (WebView) findViewById(R.id.webview01);
		WebSettings settings = webView.getSettings();
		settings.setDefaultTextEncodingName("GBK");
		settings.setSupportZoom(false);
		settings.setSavePassword(false);
		settings.setSaveFormData(false);
		settings.setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/table.html");
		webView.addJavascriptInterface(wvObj, "androidObj");
		
		queryBtn = (Button) findViewById(R.id.queryBtn);
		resetBtn = (Button) findViewById(R.id.resetBtn);
		queryBtn.setOnClickListener(this);
		resetBtn.setOnClickListener(this);
		
		//数据库工具
		dbUtil=new DBUtil(new SQLiteUtil(this));
		
		//数据加载进度条
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setMessage("数据正在查询中,请稍后.........");
		
		parserService = new EnterpriserOverViewParser();//企业概览数据解析服务
		DetailparserService = new EnterpriserDetailParser();//企业详细数据解析服务
		
		//企业详细信息显示框
		detailDialog = new AlertDialog.Builder(this);
		detailDialog.setTitle("企业详细信息");
		
		networkStatusDialog=new AlertDialog.Builder(this);
		networkStatusDialog.setTitle("网络状态检查");
		//检测是否有网络
		new Thread(new Runnable() {
			@Override
			public void run() {
					boolean hasNetwork=tool.checkNetworkStatus(MainActivity.this);
					if(!hasNetwork){
						Message OKmessage = new Message();
						OKmessage.what = HANDLER_MESSAGE_NO_NETWORK;
						handler.sendMessage(OKmessage);						
					}
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.queryBtn:
			new Thread(new Runnable() {
				@Override
				public void run() {
					final String eNameStr = enterPriserName.getText()
							.toString();
					final String eRegNo = enterPriserRegNo.getText().toString();
					final String ePersonName = enterPriserPersonName.getText()
							.toString();
					//输入框为空检测
					if (tool.isEmpty(eNameStr) && tool.isEmpty(eRegNo)
							&& tool.isEmpty(ePersonName)) {
						Message message = new Message();
						message.what = HANDLER_MESSAGE_VAILDATOR_ALERT;
						handler.sendMessage(message);
						return;
					}
					
					//从数据库中检测,如果数据库中有,就从本地加载
					Message message = new Message();
					message.what = HANDLER_MESSAGE_GET_DATA_OK;
					String enterPriserName=eNameStr.toString();
					List<BaseEnterPriserBean> beans=dbUtil.queryEnterPriserAboutInfo(enterPriserName);
					if(beans.size()>0){//本地数据库中有记录
						beanList=beans;
						handler.sendMessage(message);						
					}else{//如果本地数据库没有,则从网络上加载
						//网络检测
						boolean hasNetwork=tool.checkNetworkStatus(MainActivity.this);
						if(!hasNetwork){
							Message networkMessage = new Message();
							networkMessage.what = HANDLER_MESSAGE_NO_NETWORK;
							handler.sendMessage(networkMessage);
							return;
						}						
						// 显示进度条
						Message OKmessage = new Message();
						OKmessage.what = HANDLER_MESSAGE_VAILDATOR_OK;
						handler.sendMessage(OKmessage);						
						Map<String, String> param = new HashMap<String, String>();
						param.put(Constant.FIELD_ENTERPRISE_NAME, eNameStr);
						param.put(Constant.FIELD_ENTERPRISE_REGEDIT_NO, eRegNo);
						param.put(Constant.FIELD_ENTERPRISE_CORP_RPT, ePersonName);
						try {
							beanList = (List<BaseEnterPriserBean>) parserService
									.parserHtmlTextToArray(tool.sendRequestAsPost(new URL(Constant.REQUEST_HOST_ABOUT), param));
							handler.sendMessage(message);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}						
					}
				}
			}).start();
			break;
		case R.id.resetBtn:
			enterPriserName.setText("");
			enterPriserRegNo.setText("");
			enterPriserPersonName.setText("");
			wvObj.clearContent();
			break;
		}
	}

	private class WebViewObj {

		public void showData() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					String jsonStr = getJsonStr();
					webView.loadUrl("javascript:show('" + jsonStr + "')");
				}
			});
		}

		public void showDetail(final String link,final String regNo) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					new Thread(new Runnable() {
						@Override
						public void run() {
							Message OKmessage = new Message();
							OKmessage.what = HANDLER_MESSAGE_SHOW_DETAIL;
							EnterpriserBean bean=dbUtil.queryEnterPriserDetailInfo(regNo.trim());
							if(bean!=null){//说明本地数据库中有数据
								detailBean=bean;
								handler.sendMessage(OKmessage);
							}else{//否则从网络上加载
								//网络检测
								boolean hasNetwork=tool.checkNetworkStatus(MainActivity.this);
								if(!hasNetwork){
									Message networkMessage = new Message();
									networkMessage.what = HANDLER_MESSAGE_NO_NETWORK;
									handler.sendMessage(networkMessage);
									return;
								}
								//从网络上加载数据
								try {
									String text = tool.sendRequestAsGet(new URL(
											Constant.REQUEST_HOST_BASE + link));
									detailBean = (EnterpriserBean) DetailparserService
											.parserHtmlTextToEntity(text);
									dbUtil.saveEnterPriser(detailBean);//保存到本地数据库中
									handler.sendMessage(OKmessage);
								} catch (MalformedURLException e) {
									e.printStackTrace();
								}
							}
						}
					}).start();
				}
			});
		}

		public void clearContent() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:clearTableContent()");
				}
			});
		}

		private String getJsonStr() {
			JSONArray arrays = new JSONArray();
			JSONObject jsonObj = null;
			for (BaseEnterPriserBean bean : beanList) {
				jsonObj = new JSONObject();
				try {
					jsonObj.put("enterPriserName", bean.getEnterPriserName());
					jsonObj.put("enterPriserRegNo", bean.getEnterPriserRegNo());
					jsonObj.put("enterPriserPerson",
							bean.getEnterPriserPerson());
					jsonObj.put("enterPriserCreateDate",
							bean.getEnterPriserCreateDate());
					jsonObj.put("enterPriserStatus",
							bean.getEnterPriserStatus());
					jsonObj.put("enterPriserDetailStr", bean.getDetailStr());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				arrays.put(jsonObj);
			}
			return arrays.toString();
		}
	}
}
