package com.standzl.doman;

import com.standzl.base.ISystemEntity;
import com.standzl.util.Tools;

public class BaseEnterPriserBean implements ISystemEntity{
	
	// 企业名称
	protected String enterPriserName;

	// 企业注册号
	protected String enterPriserRegNo;

	// 企业法定代表人姓名
	protected String enterPriserPerson;

	// 企业成立日期
	protected String enterPriserCreateDate;

	// 企业状态
	protected String enterPriserStatus;

	// checkDetail串(附加到基础请求地址后以构成详细查看URL)
	protected String detailStr;
	
	protected Tools tools=Tools.getInstance();

	public String getEnterPriserName() {
		return enterPriserName;
	}

	public void setEnterPriserName(String enterPriserName) {
		this.enterPriserName =tools.filterStr(enterPriserName) ;
	}

	public String getEnterPriserRegNo() {
		return enterPriserRegNo;
	}

	public void setEnterPriserRegNo(String enterPriserRegNo) {
		this.enterPriserRegNo =tools.filterStr(enterPriserRegNo);
	}

	public String getEnterPriserPerson() {
		return enterPriserPerson;
	}

	public void setEnterPriserPerson(String enterPriserPerson) {
		this.enterPriserPerson =tools.filterStr(enterPriserPerson);
	}

	public String getEnterPriserCreateDate() {
		return enterPriserCreateDate;
	}

	public void setEnterPriserCreateDate(String enterPriserCreateDate) {
		this.enterPriserCreateDate =tools.filterStr(enterPriserCreateDate);
	}

	public String getEnterPriserStatus() {
		return enterPriserStatus;
	}

	public void setEnterPriserStatus(String enterPriserStatus) {
		this.enterPriserStatus =tools.filterStr(enterPriserStatus);
	}

	public String getDetailStr() {
		return detailStr;
	}

	public void setDetailStr(String detailStr) {
		this.detailStr = detailStr;
	}
}
