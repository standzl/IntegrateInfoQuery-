package com.standzl.enterpriser.activtiy;

import java.util.List;

import com.standzl.doman.BaseEnterPriserBean;
import com.standzl.doman.EnterpriserBean;
import com.standzl.util.DBUtil;
import com.standzl.util.SQLiteUtil;

import android.test.AndroidTestCase;
import android.util.Log;

public class DBTest extends AndroidTestCase {
	
	private static final String Tag="MyDBTest";
	
	 public void testSave() throws Throwable{
		 	DBUtil DbUtil=new DBUtil(new SQLiteUtil(this.getContext()));
		 	
		 	EnterpriserBean bean=new EnterpriserBean();
		 	
		 	bean.setEnterPriserName("东方飞扬bbccaaddeeff");
		 	bean.setEnterPriserRegNo("110108001432655");
		 	bean.setEnterPriserPerson("侯标");
		 	bean.setEnterPriserCreateDate("2000-07-04");
		 	bean.setEnterPriserStatus("开业");
		 	
		 	bean.setEnterPriserType("股份有限公司(非上市、自然人投资或控股)");
		 	bean.setAdministrativeDivision("海淀区");
		 	bean.setRegeditMenoy("2812.5 万");
		 	bean.setOperatPeriodStart("2000-07-04");
		 	bean.setOperatPeriodEnd("2118-08-10");
		 	bean.setRegisterBody("北京市工商行政管理局海淀分局");
		 	bean.setEnterPriserAddr("北京市海淀区西小口路66号A区1号楼二层");
		 	bean.setOperationRang("软件技术开发、咨询、服务、转让；" +
		 			"承接计算机网络工程；" +
		 			"零售开发后的产品、" +
		 			"计算机软硬件及外围设备、" +
		 			"电子元器件、机械电器设备；" +
		 			"档案整理、档案数据处理；" +
		 			"工程技术咨询；工程监理；" +
		 			"计算机软件技术培训；" +
		 			"设备租赁；仓储服务。 ");
		 	bean.setCheckYear("2012 ");
		 	bean.setCheckResult("通过");
		 	DbUtil.saveEnterPriser(bean);
	 }
	 
	 public void testOverViewQueryByName() throws Throwable{
		 	DBUtil DbUtil=new DBUtil(new SQLiteUtil(this.getContext()));
		 	List<BaseEnterPriserBean> list=DbUtil.queryEnterPriserAboutInfo("东方飞扬");
		 	for(BaseEnterPriserBean bean:list){
		 		//	System.out.println(bean.getEnterPriserRegNo());
		 		Log.v(Tag, bean.getEnterPriserRegNo());
		 	}
	 }
	 
	 public void testDetailQueryByRegNo() throws Throwable{
		 	DBUtil DbUtil=new DBUtil(new SQLiteUtil(this.getContext()));
		 	EnterpriserBean bean=DbUtil.queryEnterPriserDetailInfo("110108001432655");
		 	//System.out.println(bean.getRegeditMenoy());
		 	Log.v(Tag, bean.getRegeditMenoy());
	 }	 
}
