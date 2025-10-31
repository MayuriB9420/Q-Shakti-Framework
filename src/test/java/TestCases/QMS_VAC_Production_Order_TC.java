package TestCases;

import org.testng.annotations.Test;

import PageObject.Checklist_Control_Plan_VAC_Dashboard_QMS;
import PageObject.Process_Work_Flow_VAC_QMS_PO;
import PageObject.Production_Order_VAC_Dashboard;
import PageObject.QMS_Dashboard;
import PageObject.VAC_Dashboard_QMS;
import testBase.BaseClassQMS;

public class QMS_VAC_Production_Order_TC extends BaseClassQMS{

	@Test
	public void production() throws Exception
	{
		logger.info("********** Starting QMS_VAC_Process_Work_Flow_TC **********");
	       
        testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
        QMS_Dashboard qms = new QMS_Dashboard(driver);
        VAC_Dashboard_QMS vac = new VAC_Dashboard_QMS(driver);
        Production_Order_VAC_Dashboard po = new Production_Order_VAC_Dashboard(driver);

        qms.clickVAC();
        logger.info("Clicked on VAC module");
        
        vac.clickProductionOrder();
        logger.info("Clicked on Production Order module");
        
        po.clickAddProductionOrder();
        po.clickPOProduct("Aluminum Rods");
        po.clickPOCustomer("Infosys");
        po.enterItemCode("IT01");
        po.clickAddPO();
        po.clickStart();
	}
}
