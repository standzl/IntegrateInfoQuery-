package com.standzl.interfaceimpl;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.standzl.base.ISystemEntity;
import com.standzl.base.ParserRuleAdapter;
import com.standzl.doman.BaseEnterPriserBean;

public class EnterpriserOverViewParser extends ParserRuleAdapter {

	/**
	 * 解析企业概览HTML文本，生成enterPre对象
	 * @param htmlText
	 * @return
	 */
	public List<? extends ISystemEntity> parserHtmlTextToArray(String htmlText) {
		List<BaseEnterPriserBean> beans=new ArrayList<BaseEnterPriserBean>();
		BaseEnterPriserBean bean=null;
		Parser parser = null;
		try {
			parser = new Parser(htmlText);
			AndFilter filter = new AndFilter(new TagNameFilter("table"),new HasAttributeFilter("id", "ec_table"));
			NodeList nodeList = parser.parse(filter);
			TableTag tableTag = (TableTag) nodeList.elementAt(0);
			TableRow[] rows = tableTag.getRows();
			if (rows.length > 1) {
				for (int i = 1; i < rows.length; i++) {
					bean=new BaseEnterPriserBean();
					TableRow row = rows[i];
					TableColumn[] tableColumn = row.getColumns();
					TableColumn enterpriseName = tableColumn[1];
					parser=Parser.createParser(enterpriseName.toHtml(),"UTF-8");
					NodeList eNameChild=parser.parse(new TagNameFilter("a"));
					LinkTag linkTag=(LinkTag) eNameChild.elementAt(0);
					//链接指向
					bean.setDetailStr(linkTag.getAttribute("href"));
					//企业名称
					bean.setEnterPriserName(linkTag.childAt(2).getText());
					TableColumn enterpriseNo = tableColumn[2];
					//注册号
					bean.setEnterPriserRegNo(enterpriseNo.getStringText());
					TableColumn enterprisePersonName = tableColumn[3];
					//法定代表人名称
					bean.setEnterPriserPerson(enterprisePersonName.getStringText());
					TableColumn enterpriseCreateTiem = tableColumn[4];
					//创建时间
					bean.setEnterPriserCreateDate(enterpriseCreateTiem.getStringText());
					TableColumn enterpriseStatus = tableColumn[5];
					//企业状态
					bean.setEnterPriserStatus(enterpriseStatus.getStringText());
					beans.add(bean);
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return beans;
	}
}
