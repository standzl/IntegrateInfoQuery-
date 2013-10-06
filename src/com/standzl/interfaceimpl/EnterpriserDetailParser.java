package com.standzl.interfaceimpl;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.ParserException;

import com.standzl.base.ISystemEntity;
import com.standzl.base.ParserRuleAdapter;
import com.standzl.doman.EnterpriserBean;

public class EnterpriserDetailParser extends ParserRuleAdapter {

	/**
	 * 解析企业详细信息HTML文本
	 */
	public ISystemEntity parserHtmlTextToEntity(String htmlText) {
		Parser parser=null;
		EnterpriserBean bean=new EnterpriserBean();
		try {
			parser = new Parser(htmlText);
			AndFilter filter = new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("id", "lhTitleO"));
			Node  contentTableParentNode=parser.parse(filter).elementAt(0).getParent();
			parser=new Parser(contentTableParentNode.toHtml());
			TableTag contentTable=(TableTag) parser.parse(new TagNameFilter("table")).elementAt(2);
			TableRow[] rows= contentTable.getRows();
			for (int i = 0; i < rows.length; i++) {
				TableColumn[] columns= rows[i].getColumns();
				switch (i) {
					case 0:
						//注册号
						bean.setEnterPriserRegNo(columns[1].toPlainTextString().trim());
						//企业类型
						bean.setEnterPriserType(columns[3].toPlainTextString().trim());
						break;
					case 1:
						//主体名称
						bean.setEnterPriserName(columns[1].toPlainTextString().trim());
						break;
					case 2:
						//法定代表人
						bean.setEnterPriserPerson(columns[1].toPlainTextString().trim());
						//行政区划
						bean.setAdministrativeDivision(columns[3].toPlainTextString().trim());
						break;
					case 3:
						//成立日期
						bean.setEnterPriserCreateDate(columns[1].toPlainTextString().trim());
						//注册资本
						bean.setRegeditMenoy(columns[3].toPlainTextString().trim());
						break;
					case 4:
						//经营期限自
						bean.setOperatPeriodStart(columns[1].toPlainTextString().trim());
						//经营期限至
						bean.setOperatPeriodEnd(columns[3].toPlainTextString().trim());
						break;
					case 5:
						//登记机关
						bean.setRegisterBody(columns[1].toPlainTextString().trim());
						//企业状态
						bean.setEnterPriserStatus(columns[3].toPlainTextString().trim());
						break;
					case 6:
						//地址/住所
						bean.setEnterPriserAddr(columns[1].toPlainTextString().trim());
						break;
					case 7:
						//经营范围
						bean.setOperationRang(columns[1].toPlainTextString().trim());
						break;
					case 8:
						if(columns.length==4){//说明企业未注销
							//年检年度
							bean.setCheckYear(columns[1].toPlainTextString().trim());
							//年检结果
							bean.setCheckResult(columns[3].toPlainTextString().trim());
						}else{//说明企业已经注销
							//注销日期
							bean.setCancleDate(columns[1].toPlainTextString().trim());
						}
						break;
				}
			}
			} catch (ParserException e) {
				e.printStackTrace();
			}
		return bean;
	}
}
