package com.standzl.doman;

public class EnterpriserBean extends BaseEnterPriserBean{
		
		//法定代表人证件类型
		private String CredentialsType;
		
		//证件号码
		private String CredentialsNo;
		
		//住所
		private String EnterPriserPersonAddr;
		
		//企业类型
		private String enterPriserType;
		
		//行政区划
		private String administrativeDivision;
		
		//注册资本
		private String regeditMenoy;
		
		//起始经营期限
		private String operatPeriodStart;
		
		//结束经营期限
		private String operatPeriodEnd;
		
		//登记机关
		private String registerBody;
		
		//企业地址
		private String enterPriserAddr;
		
		//经营范围
		private String operationRang;
		
		//年检年度
		private String checkYear;
		
		//年检结果
		private String checkResult;
		
		//注销日期
		private String cancleDate;
		
		public String getCredentialsType() {
			return CredentialsType;
		}

		public void setCredentialsType(String credentialsType) {
			CredentialsType =tools.filterStr(credentialsType);
		}

		public String getCredentialsNo() {
			return CredentialsNo;
		}

		public void setCredentialsNo(String credentialsNo) {
			CredentialsNo =tools.filterStr(credentialsNo);
		}

		public String getEnterPriserPersonAddr() {
			return EnterPriserPersonAddr;
		}

		public void setEnterPriserPersonAddr(String enterPriserPersonAddr) {
			EnterPriserPersonAddr = tools.filterStr(enterPriserPersonAddr);
		}

		public String getEnterPriserType() {
			return enterPriserType;
		}

		public void setEnterPriserType(String enterPriserType) {
			this.enterPriserType = tools.filterStr(enterPriserType);
		}

		public String getAdministrativeDivision() {
			return administrativeDivision;
		}

		public void setAdministrativeDivision(String administrativeDivision) {
			this.administrativeDivision = tools.filterStr(administrativeDivision);
		}

		public String getRegeditMenoy() {
			return regeditMenoy;
		}

		public void setRegeditMenoy(String regeditMenoy) {
			this.regeditMenoy = tools.filterStr(regeditMenoy);
		}

		public String getOperatPeriodStart() {
			return operatPeriodStart;
		}

		public void setOperatPeriodStart(String operatPeriodStart) {
			this.operatPeriodStart = tools.filterStr(operatPeriodStart);
		}

		public String getOperatPeriodEnd() {
			return operatPeriodEnd;
		}

		public void setOperatPeriodEnd(String operatPeriodEnd) {
			this.operatPeriodEnd = tools.filterStr(operatPeriodEnd);
		}

		public String getRegisterBody() {
			return registerBody;
		}

		public void setRegisterBody(String registerBody) {
			this.registerBody = tools.filterStr(registerBody);
		}

		public String getEnterPriserAddr() {
			return enterPriserAddr;
		}

		public void setEnterPriserAddr(String enterPriserAddr) {
			this.enterPriserAddr = tools.filterStr(enterPriserAddr);
		}

		public String getOperationRang() {
			return operationRang;
		}

		public void setOperationRang(String operationRang) {
			this.operationRang = operationRang;
		}

		public String getCheckYear() {
			return checkYear;
		}

		public void setCheckYear(String checkYear) {
			this.checkYear = tools.filterStr(checkYear);
		}

		public String getCheckResult() {
			return checkResult;
		}

		public void setCheckResult(String checkResult) {
			this.checkResult = tools.filterStr(checkResult);
		}

		public String getCancleDate() {
			return cancleDate;
		}

		public void setCancleDate(String cancleDate) {
			this.cancleDate = tools.filterStr(cancleDate);
		}
}

