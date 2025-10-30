package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_QMS_Page extends BasePage {
	
	private WebDriverWait wait;
 public Login_QMS_Page(WebDriver driver) {
	 super(driver);
}
 
 @FindBy(id="email")
 WebElement email;
 
 @FindBy(id="password")
 WebElement password;
 
 @FindBy(xpath="//button[text()='Login']")
 WebElement btnLogin;
 
 // Action Methods
 
 public void setUsername(String username) {
     wait.until(ExpectedConditions.visibilityOf(email));
     email.clear();
     email.sendKeys(username);
 }
 
 public void setPassword(String pwd) {
     wait.until(ExpectedConditions.visibilityOf(password));
     password.clear();
     password.sendKeys(pwd);
 }
 
 public void clickLogin() {
	 WebElement loginBtn= wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
    loginBtn.click();
}
 
}
 
 




