package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QMS_Dashboard extends BasePage {

	public QMS_Dashboard(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//div[@class='MuiBox-root css-pwbuwj']")
	WebElement QMSDashboardPage;

	@FindBy(xpath = "//h6[text()='Master Data Management (MDM)']")
    WebElement btnMDM;

    @FindBy(xpath = "//h6[text()='Screw Compressor Assembly (SCA)']")
    WebElement btnSCA;

    @FindBy(xpath = "//h6[text()='Vapor Absorption Chiller (VAC)']")
    WebElement btnVAC;

    @FindBy(xpath = "//h6[text()='Process Audit']")
    WebElement btnProcessAudit;

    @FindBy(xpath = "//h6[text()='Account & Security']")
    WebElement btnAccountAndSecurity;

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOut;
    
//    Action Methods
    public void clickQMSDashboardPage() {
		QMSDashboardPage.click();
	}

	public void clickMDM() {
		btnMDM.click();
	}

	public void clickSCA() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSCA)).click();
	}

	public void clickVAC() {
		wait.until(ExpectedConditions.elementToBeClickable(btnVAC)).click();
	}

	public void clickProcessAudit() {
		wait.until(ExpectedConditions.elementToBeClickable(btnProcessAudit)).click();
	}

	public void clickAccountAndSecurity() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAccountAndSecurity)).click();	
	}

	public void clickSignOut() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSignOut)).click();	
	}
}
