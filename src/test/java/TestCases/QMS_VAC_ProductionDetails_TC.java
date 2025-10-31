package TestCases;

import org.testng.annotations.Test;

import PageObject.Production_Details_QMS;
import PageObject.Production_Order_VAC_Dashboard;
import PageObject.QMS_Dashboard;
import PageObject.VAC_Dashboard_QMS;
import testBase.BaseClassQMS;

public class QMS_VAC_ProductionDetails_TC extends BaseClassQMS{

	@Test
	public void productiondetails() throws Exception
	{
		logger.info("********** Starting QMS_VAC_Production_Details_TC **********");
	       
        testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
        QMS_Dashboard qms = new QMS_Dashboard(driver);
        VAC_Dashboard_QMS vac = new VAC_Dashboard_QMS(driver);
        
        Production_Details_QMS pdetails= new Production_Details_QMS(driver);
        
        qms.clickVAC();
        logger.info("Clicked on VAC module");
        
        vac.clickProductionDetails();
        pdetails.clickrecordTab();
        pdetails.clickgraphTab();
        pdetails.searchProduct("Mouse");
	}
}
