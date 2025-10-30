package TestCases;

import org.testng.annotations.Test;

import PageObject.Login_QMS_Page;
import PageObject.QMS_Dashboard;
import testBase.BaseClassQMS;

public class QMS_001_Login_Dashboard extends BaseClassQMS{

	
	@Test
    public void doLogin() throws Exception
    {
    	testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
    }
	
}
