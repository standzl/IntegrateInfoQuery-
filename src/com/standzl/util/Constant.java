package com.standzl.util;

public class Constant {

	/**
	 * 企业名称
	 */
	public static final String FIELD_ENTERPRISE_NAME = "zhcxModel.ent_name";

	/**
	 * 注册号
	 */
	public static final String FIELD_ENTERPRISE_REGEDIT_NO = "zhcxModel.lic_reg_n";

	/**
	 * 法定代表人名称
	 */
	public static final String FIELD_ENTERPRISE_CORP_RPT = "zhcxModel.corp_rpt";

	/**
	 * 法定代表人证件类型
	 */
	public static final String FIELD_ENTERPRISE_CER_TYPE = "zhcxModel.cer_type";

	/**
	 * 法定代表人证件号码
	 */
	public static final String FIELD_ENTERPRISE_CER_NO = "zhcxModel.cer_no";

	/**
	 * 住所
	 */
	public static final String FIELD_ENTERPRISE_CORP_RPT_ADDR = "zhcxModel.dom";

	/**
	 * Host头的值
	 */
	public static final String HTTP_HEAD_HOSTADDR="211.94.187.236";
	
	
	/**
	 * Referer头的值
	 */
	public static final String HTTP_HEAD_REFERER="211.94.187.236";
	
	/**
	 * Connection
	 */
	public static final String HTTP_HEAD_CONNECTION="keep-alive";
	
	/**
	 * User-Agent
	 */
	public static final String HTTP_HEAD_USER_AGENT="Mozilla/5.0 (Windows NT 6.1; rv:23.0) Gecko/20100101 Firefox/23.0";
	
	/**
	 * Accept
	 */
	public static final String HTTP_HEAD_ACCEPT="text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	
	/**
	 * accept_encoding
	 */
	public static final String HTTP_HEAD_ACCEPT_ENCODING="gzip, deflate";
	
	/**
	 * ACCEPT_LANGUAGE
	 */
	public static final String HTTP_HEAD_ACCEPT_LANGUAGE="zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3";
	
	/**
	 * 基础请求地址
	 */
	public static final String REQUEST_HOST_BASE="http://211.94.187.236";
	
	/**
	 * 企业概览请求地址
	 */
	public static final String  REQUEST_HOST_ABOUT="http://211.94.187.236/zhcx/zhcxAction!list.dhtml";
	
	/**
	 * 数据库表名
	 */
	public static final String  TABLENAME="enterpriserInfo";
	
	//数据库字段
	public static final String TABLE_FILED_ENTERNAME="enterPriserName";
	public static final String TABLE_FILED_ENTERREGNO="enterPriserRegNo";
	public static final String TABLE_FILED_ENTERPERSON="enterPriserPerson";
	public static final String TABLE_FILED_ENTERCREATEDATE="enterPriserCreateDate";
	public static final String TABLE_FILED_ENTERSTATUS="enterPriserStatus";
	public static final String TABLE_FILED_ENTERTYPE="enterPriserType";
	public static final String TABLE_FILED_ENTERDIVISION="administrativeDivision";
	public static final String TABLE_FILED_ENTERREGMENOY="regeditMenoy";
	public static final String TABLE_FILED_ENTERPERIODSTART="operatPeriodStart";
	public static final String TABLE_FILED_ENTERPERIODEND="operatPeriodEnd";
	public static final String TABLE_FILED_REGISTERBODY="registerBody";
	public static final String TABLE_FILED_ENTERPRISERADDR="enterPriserAddr";
	public static final String TABLE_FILED_OPERATIONRANG="operationRang";
	public static final String TABLE_FILED_CHECKYEAR="checkYear";
	public static final String TABLE_FILED_CHECKRESULT="checkResult";
	public static final String TABLE_FILED_CANCLEDATE="cancleDate";
}
