package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    WebDriver driver;
    private WebDriverWait wait;

    // PageFactory Locators
    @FindBy(xpath = "//input[@id='email']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@name='rememberMe']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath= "//a[normalize-space()='Forgot Password?']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//button[@aria-label='Show Password']//*[name()='svg']")
    private WebElement showPasswordIcon;

    // Constructor 
    public LoginPage (WebDriver driver)
	{
		super (driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
        PageFactory.initElements(driver, this);
	}

    //Actions
    public void setUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
    	 WebElement loginBtn= wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public void toggleShowPassword() {
    	 WebElement toggleBtn= wait.until(ExpectedConditions.elementToBeClickable(showPasswordIcon));
    	 toggleBtn.click();
    }

    public void checkRememberMe() {
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    public void uncheckRememberMe() {
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    public boolean isRememberMeSelected() {
        wait.until(ExpectedConditions.visibilityOf(rememberMeCheckbox));
        return rememberMeCheckbox.isSelected();
    }
    
    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
       // forgotPasswordLink.click();
    }

    // Reusable login method
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }
    
    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getRememberMeCheckbox() {
        return rememberMeCheckbox;
    }

    public WebElement getForgotPasswordLink() {
        return forgotPasswordLink;
    }

    public WebElement getShowPasswordIcon() {
        return showPasswordIcon;
    }


}
