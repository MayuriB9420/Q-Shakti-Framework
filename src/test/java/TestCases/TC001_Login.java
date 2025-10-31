package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.LoginPage;
import testBase.BaseClass;
//import Utilities.ScreenshotUtil;
//import Utilities.ExtentReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//@Listeners(Utilities.ExtentReportManager.class)
public class TC001_Login extends BaseClass {

    WebDriverWait wait;

    @Test(priority = 1)
    public void testShowPasswordToggle() {
        logger.info("=== Starting Show/Hide Password Toggle Test ===");

        try {
            LoginPage lp = new LoginPage(driver);
            WebElement passwordInput = lp.getPasswordField();

            String initialType = passwordInput.getAttribute("type");
            Assert.assertEquals(initialType, "password", "Password should be hidden initially");

            lp.setPassword(p.getProperty("password"));
            Thread.sleep(1500);
            lp.toggleShowPassword();
            Thread.sleep(1500);

            String toggledType = passwordInput.getAttribute("type");
            Assert.assertEquals(toggledType, "text", "Password should be visible after toggle");

            logger.info("=== Toggle Password Test Passed ===");

        } catch (Exception e) {
            logger.error("Toggle Password Test FAILED", e);
            Assert.fail("Toggle password test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 2)
    public void doLogin() throws InterruptedException
    {
    	testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
    	Thread.sleep(1500);
    }


    /*@Test(priority = 2)
    public void testLoginFunctionality() {
        logger.info("=== Starting Login Functionality Test ===");

        try {
            LoginPage lp = new LoginPage(driver);

            lp.setUsername(p.getProperty("email"));

            String inputType = lp.getPasswordField().getAttribute("type");
            Assert.assertEquals(inputType, "password", "Password should be hidden while typing");

            lp.setPassword(p.getProperty("password"));
            Thread.sleep(1500);
            lp.clickLogin();

            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement dashboardElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Dashboard']"))
            );

            Assert.assertTrue(dashboardElement.isDisplayed(), "Dashboard should be displayed");

            logger.info("=== Login Test Passed ===");

        } catch (Exception e) {
            logger.error("Login Test FAILED", e);
            Assert.fail("Login test failed: " + e.getMessage());
        }
    }*/

    @Test(priority = 3)
    public void testRememberMe() {
        logger.info("=== Starting Remember Me Checkbox Test ===");

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement checkboxInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='rememberMe']"))
            );
              
            Thread.sleep(1000);
            Assert.assertFalse(checkboxInput.isSelected(), "Checkbox should be unchecked by default");
            logger.info("Default state verified: Remember Me is unchecked");
            
            Thread.sleep(1000);

            if (!checkboxInput.isDisplayed() || !checkboxInput.isEnabled()) {
                WebElement label = driver.findElement(By.xpath("//label[contains(@class,'MuiFormControlLabel-root')]"));
                label.click();
                Thread.sleep(1000);

            } else {
                checkboxInput.click();
                Thread.sleep(1500);
            }

            Assert.assertTrue(checkboxInput.isSelected(), "Checkbox should be selected after click");

            logger.info("=== Remember Me Checkbox Test Passed ===");

        } catch (Exception e) {
            logger.error("Remember Me Test FAILED", e);
            Assert.fail("Remember Me test failed: " + e.getMessage());
        }
    }
}
