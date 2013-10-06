package com.standzl.util;

import java.util.ArrayList;
import java.util.List;

import com.standzl.doman.BaseEnterPriserBean;
import com.standzl.doman.EnterpriserBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {

		private SQLiteUtil sqliteUtil;
		
		private String[] overViewInfo=new String[]{
				Constant.TABLE_FILED_ENTERNAME,
				Constant.TABLE_FILED_ENTERREGNO,
				Constant.TABLE_FILED_ENTERPERSON,
				Constant.TABLE_FILED_ENTERCREATEDATE,
				Constant.TABLE_FILED_ENTERSTATUS,
				};
		
		private String[] detailInfo=new String[]{
				Constant.TABLE_FILED_ENTERNAME,
				Constant.TABLE_FILED_ENTERREGNO,
				Constant.TABLE_FILED_ENTERPERSON,
				Constant.TABLE_FILED_ENTERCREATEDATE,
				Constant.TABLE_FILED_ENTERSTATUS,
				Constant.TABLE_FILED_ENTERTYPE,
				Constant.TABLE_FILED_ENTERDIVISION,
				Constant.TABLE_FILED_ENTERREGMENOY,
				Constant.TABLE_FILED_ENTERPERIODSTART,
				Constant.TABLE_FILED_ENTERPERIODEND,
				Constant.TABLE_FILED_REGISTERBODY,
				Constant.TABLE_FILED_ENTERPRISERADDR,
				Constant.TABLE_FILED_OPERATIONRANG,
				Constant.TABLE_FILED_CHECKYEAR,
				Constant.TABLE_FILED_CHECKRESULT,
				Constant.TABLE_FILED_CANCLEDATE
				};		
		
		public DBUtil(SQLiteUtil sqliteUtil){
				this.sqliteUtil=sqliteUtil;
		}
		
		/**
		 * 保存从网络上查询出来的企业信息
		 * @param bean
		 */
		public void saveEnterPriser(EnterpriserBean bean){
				ContentValues  value=new ContentValues();
				value.put(Constant.TABLE_FILED_ENTERNAME,bean.getEnterPriserName());
				value.put(Constant.TABLE_FILED_ENTERREGNO,bean.getEnterPriserRegNo());
				value.put(Constant.TABLE_FILED_ENTERPERSON,bean.getEnterPriserPerson());
				value.put(Constant.TABLE_FILED_ENTERCREATEDATE,bean.getEnterPriserCreateDate());
				value.put(Constant.TABLE_FILED_ENTERSTATUS,bean.getEnterPriserStatus());
				value.put(Constant.TABLE_FILED_ENTERTYPE,bean.getEnterPriserType());
				value.put(Constant.TABLE_FILED_ENTERDIVISION,bean.getAdministrativeDivision());
				value.put(Constant.TABLE_FILED_ENTERREGMENOY,bean.getRegeditMenoy());
				value.put(Constant.TABLE_FILED_ENTERPERIODSTART,bean.getOperatPeriodStart());
				value.put(Constant.TABLE_FILED_ENTERPERIODEND,bean.getOperatPeriodEnd());
				value.put(Constant.TABLE_FILED_REGISTERBODY,bean.getRegisterBody());
				value.put(Constant.TABLE_FILED_ENTERPRISERADDR,bean.getEnterPriserAddr());
				value.put(Constant.TABLE_FILED_OPERATIONRANG,bean.getOperationRang());
				value.put(Constant.TABLE_FILED_CHECKYEAR,bean.getCheckYear());
				value.put(Constant.TABLE_FILED_CHECKRESULT,bean.getCheckResult());
				value.put(Constant.TABLE_FILED_CANCLEDATE,bean.getCancleDate());
				SQLiteDatabase db=sqliteUtil.getWritableDatabase();
				db.insert(Constant.TABLENAME,"enterPriserName", value);
		}
		
		/**
		 * 根据企业名称查询企业的概况信息
		 * @param enterPriserName
		 * @return
		 */
	public List<BaseEnterPriserBean> queryEnterPriserAboutInfo(
			String enterPriserName) {
		SQLiteDatabase db=sqliteUtil.getReadableDatabase();
		Cursor cursor = db.query(Constant.TABLENAME, this.overViewInfo,
				Constant.TABLE_FILED_ENTERNAME + " like ?",
				new String[] {"%"+enterPriserName+"%"}, null, null, null);
		List<BaseEnterPriserBean> list = new ArrayList<BaseEnterPriserBean>();
		BaseEnterPriserBean bean = null;
		while (cursor.moveToNext()) {
			bean = new BaseEnterPriserBean();
			bean.setEnterPriserName(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERNAME)));
			bean.setEnterPriserRegNo(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERREGNO)));
			bean.setEnterPriserCreateDate(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERCREATEDATE)));
			bean.setEnterPriserPerson(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERPERSON)));
			bean.setEnterPriserStatus(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERSTATUS)));
			list.add(bean);
		}
		cursor.close();
		return list;
	}
		
		/**
		 * 根据注册号查询企业的详细信息
		 * @param enterPriserRegNo
		 * @return
		 */
	public EnterpriserBean queryEnterPriserDetailInfo(String enterPriserRegNo) {
		SQLiteDatabase db=sqliteUtil.getReadableDatabase();
		Cursor cursor = db.query(Constant.TABLENAME, this.detailInfo,
				Constant.TABLE_FILED_ENTERREGNO + "=?",
				new String[] { enterPriserRegNo }, null, null, null);
		EnterpriserBean bean = null;
		while (cursor.moveToNext()) {
			bean = new EnterpriserBean();
			bean.setEnterPriserName(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERNAME)));
			bean.setEnterPriserRegNo(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERREGNO)));
			bean.setEnterPriserCreateDate(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERCREATEDATE)));
			bean.setEnterPriserPerson(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERPERSON)));
			bean.setEnterPriserStatus(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERSTATUS)));
			bean.setRegeditMenoy(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERREGMENOY)));
			bean.setEnterPriserType(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERTYPE)));
			bean.setAdministrativeDivision(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERDIVISION)));
			bean.setOperatPeriodStart(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERPERIODSTART)));
			bean.setOperatPeriodEnd(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERPERIODEND)));
			bean.setRegisterBody(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_REGISTERBODY)));
			bean.setEnterPriserAddr(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_ENTERPRISERADDR)));
			bean.setOperationRang(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_OPERATIONRANG)));
			bean.setCheckYear(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_CHECKYEAR)));
			bean.setCheckResult(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_CHECKRESULT)));
			bean.setCancleDate(cursor.getString(cursor
					.getColumnIndex(Constant.TABLE_FILED_CANCLEDATE)));
		}
		cursor.close();
		return bean;
	}
}
