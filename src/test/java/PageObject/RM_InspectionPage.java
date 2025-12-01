package PageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.ExtentReportManager;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RM_InspectionPage extends BasePage {

	WebDriverWait wait;
	Random random = new Random();

	public RM_InspectionPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@FindBy(xpath = "//span[normalize-space()='RM INSPECTION']")
	WebElement rmInspectionPage;
	
	  @FindBy(xpath = "//table//tbody/tr")
	    private List<WebElement> tableRows;
	  
	    // ANY column (dynamic use)
	    @FindBy(xpath = "//table//tbody/tr/td")
	    private List<WebElement> allCells;

	@FindBy(xpath = "//label[contains(normalize-space(.), 'Section')]/following::input[1]")
	WebElement txtSection;

	@FindBy(xpath = "//label[contains(normalize-space(.), 'I/O No - Mis No')]/following::input[1]")
	WebElement txtIONo;

	@FindBy(xpath = "//button[normalize-space()='Graph']")
	WebElement btnGraph;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-vzn9ow-MuiButtonBase-root-MuiIconButton-root']//*[@data-testid='CloseIcon']")
	WebElement btnCloseGraph;

	@FindBy(xpath = "//span[@data-label='Parameter Wise']")
	WebElement ParameterWiseTab;
	
	@FindBy(xpath = "//label[@role='button']//*[name()='svg']")
	WebElement btnAttachments;

	@FindBy(xpath = "//td[5]")
	public   
	List<WebElement> txtSampleSize;

	@FindBy(xpath = "//input[contains(@data-testid,'reading-input')]")
	List<WebElement> txtReadings;

	@FindBy(xpath = "//table//tbody//tr")
	List<WebElement> parameterRows1;

	// @FindBy(xpath = "//table//td[normalize-space()='LENGTH']/following::td[1]")
	// WebElement txtDimensionalLimits;

	@FindBy(xpath = "//table//span[contains(text(),'-')]")
	List<WebElement> txtDimensionalLimits;

	// @FindBy(xpath = "//input[@data-testid='machine_id_for_row_202']")

	@FindBy(xpath = "//input[contains(@data-testid, 'machine-select-')]")
	List<WebElement>  MachineDropdown;

	
	@FindBy(xpath = "//span[normalize-space()='Capture Reading']")
	WebElement btnCaptureReading;

	@FindBy(xpath = "//label[contains(normalize-space(.), 'QC Machine')]/following::input[1]")
	WebElement qcMachineInput;

	@FindBy(xpath = "//label[contains(normalize-space(.), 'QC Machine')]/following::input[1]/li[normalize-space(text())='\" + machine + \"']")
	List<WebElement> qcMachineOptions;

	@FindBy(xpath = "//table//tr[contains(@class,'MuiTableRow-root')]")
	List<WebElement> parameterRows;

	@FindBy(xpath = "//p[normalize-space(text())='Accepted']/following::input[1]")
	WebElement txtAccepted;

	@FindBy(xpath = "//p[normalize-space(text())='Rejected']/following::input[1]")
	WebElement txtRejected;

	@FindBy(xpath = "//input[@placeholder='Enter remarks ']")
	List<WebElement> txtRemarks;

	@FindBy(xpath = "//div[contains(@data-testid,'remarks')]//input[@placeholder='Enter remarks ']")
	List<WebElement> txtRemarksList;

	@FindBy(xpath = "//button[normalize-space()='Save & Next Parameter >']")
	WebElement btnSaveNext;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement btnSave;

	@FindBy(xpath = "//div[contains(text(),'Data Saved Successfully') or contains(text(),'Success')]")
	WebElement toastSuccess;

	public int getTotalReadings() {
		return driver.findElements(By.xpath("//input[contains(@data-testid,'reading-input')]")).size();
	}

	public void navigateToRmInspectionPage() {

		wait.until(ExpectedConditions.elementToBeClickable(rmInspectionPage)).click();
		ExtentReportManager.logInfo("Navigated to RM Inspection Management");
	}

	public int getTotalParameters() {
		return parameterRows.size();
	}

	public void parameterWiseInspectionTab() {

		wait.until(ExpectedConditions.elementToBeClickable(ParameterWiseTab)).click();
		ExtentReportManager.logInfo("Navigated to parameter Wise Inspection Tab");
	}

	public void BtnGraph() {

		wait.until(ExpectedConditions.elementToBeClickable(btnGraph)).click();
		ExtentReportManager.logInfo("Clicked on Graph Button");
		
	}

	public void BtnAttachments() {

		wait.until(ExpectedConditions.elementToBeClickable(btnAttachments)).click();
		ExtentReportManager.logInfo("Clicked on Attachments Button");
	}

	public void BtnCloseGraph() {

		wait.until(ExpectedConditions.elementToBeClickable(btnCloseGraph)).click();
		ExtentReportManager.logInfo("Clicked on Close Graph Button");
	}

	public void BtnCaptureReading() {

		wait.until(ExpectedConditions.elementToBeClickable(btnCaptureReading)).click();
		ExtentReportManager.logInfo("Clicked on Capture Reading Button");
	}

	public void enterSection(String section) {
		wait.until(ExpectedConditions.elementToBeClickable(txtSection)).click();

		txtSection.clear();
		txtSection.sendKeys(section);

		WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//li[contains(@role,'option') and normalize-space()='" + section + "']")));

		option.click();

		wait.until(ExpectedConditions.elementToBeClickable(txtIONo));
	}

	public void enterIO(String IO) {

		wait.until(ExpectedConditions.elementToBeClickable(txtIONo)).click();

		txtIONo.clear();
		txtIONo.sendKeys(IO);

		WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//li[contains(@role,'option') and normalize-space()='" + IO + "']")));

		option.click();

		//wait.until(ExpectedConditions.elementToBeClickable(txtRemarks));
	}

	public void selectQCMachines(List<String> qcMachines) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (String machine : qcMachines) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(qcMachineInput));

				qcMachineInput.click();
				Thread.sleep(800);

				qcMachineInput.sendKeys(machine);
				Thread.sleep(1000);

				WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//ul[@role='listbox']//li[normalize-space()='" + machine + "']")));
				option.click();
				Thread.sleep(800);

				System.out.println(" Selected: " + machine);
			} catch (Exception e) {
				System.out.println(" Option not found or not clickable for: " + machine);
			}
		}
	}

	public void enterAccepted(int accepted) {
		txtAccepted.clear();
		txtAccepted.sendKeys(String.valueOf(accepted));
	}

	public void enterRejected(int rejected) {
		txtRejected.clear();
		txtRejected.sendKeys(String.valueOf(rejected));
	}

	
	 public void enterRemarks(int rowIndex, String remark) {
	        txtRemarks.get(rowIndex).clear();
	        txtRemarks.get(rowIndex).sendKeys(remark);
	    }
	  
	  public void BtnMachineInput(int rowIndex,String Machine) throws InterruptedException {
		  MachineDropdown.get(rowIndex).clear();
	        MachineDropdown.get(rowIndex).sendKeys(Machine); }
	  
	// -----------------------------------------------------
	    //  GET DIMENSIONAL LIMITS (Already provided by you)
	    // -----------------------------------------------------
	    public double[] getDimensionalRange(int rowIndex) {
	        String limits = txtDimensionalLimits.get(rowIndex).getText().trim(); // Example: "44.7 - 45"
	        String[] parts = limits.split("-");

	        double min = Double.parseDouble(parts[0].trim());
	        double max = Double.parseDouble(parts[1].trim());

	        System.out.println("Row " + rowIndex + " → Dimensional Limits: " + min + " - " + max);
	        return new double[]{min, max};
	    }

	    // -----------------------------------------------------
	    //  GENERATE RANDOM READING BETWEEN LSL - USL
	    // -----------------------------------------------------
	    public String generateRandomReading(double lsl, double usl) {
	        double value = lsl + Math.random() * (usl - lsl);
	        return String.format("%.2f", value);
	    }

	    // -----------------------------------------------------
	    //  GENERATE READINGS FOR ONE ROW
	    // -----------------------------------------------------
	    public List<String> generateReadingsForRow(int rowIndex) {

	        int sampleCount = Integer.parseInt(txtSampleSize.get(rowIndex).getText().trim());

	        double[] range = getDimensionalRange(rowIndex);
	        double lsl = range[0];
	        double usl = range[1];

	        List<String> readings = new ArrayList<>();

	        for (int i = 0; i < sampleCount; i++) {
	            readings.add(generateRandomReading(lsl, usl));
	        }

	        return readings;
	    }

	    // -----------------------------------------------------
	    // GET READING INPUT ELEMENT BY DYNAMIC TEST ID
	    // -----------------------------------------------------
	    public WebElement getReadingInput(String testId) {
	        return driver.findElement(By.xpath("//input[@data-testid='" + testId + "']"));
	    }

	    // -----------------------------------------------------
	    // FILL ALL ROWS AUTOMATICALLY
	    // -----------------------------------------------------
	    public void fillAllRowsAuto(List<String> remarksList, List<String> machineList) throws Exception {

	        int rowCount = txtSampleSize.size();

	        for (int row = 0; row < rowCount; row++) {

	            System.out.println("=== Filling Row " + (row + 1) + " ===");

	            // ---- Generate readings dynamically ----
	            List<String> readings = generateReadingsForRow(row);

	            for (int col = 0; col < readings.size(); col++) {

	                String testId = "reading-input-" + (202 + row) + "-0-" + col;

	                WebElement input = getReadingInput(testId);
	                wait.until(ExpectedConditions.visibilityOf(input)).clear();
	                input.sendKeys(readings.get(col));
	            }

	            // ----- Remarks -----
	            txtRemarks.get(row).clear();
	            txtRemarks.get(row).sendKeys(remarksList.get(row));

	            // ----- QC Machine Selection -----
	            MachineDropdown.get(row).clear();
	            MachineDropdown.get(row).sendKeys(remarksList.get(row));

	            // ----- Navigation: Save & Next OR Final Save -----
	            if (isElementPresent(btnSaveNext)) {
	                btnSaveNext.click();
	                Thread.sleep(800);
	            } else if (isElementPresent(btnSave)) {
	                btnSave.click();
	            }
	        }
	    }
	    
	    public boolean isElementPresent(WebElement element){
	        try {
	            element.isDisplayed();
	            return true;
	        } catch (Exception e){
	            return false;
	        }
	    }
	
	public void clickSaveNext() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSaveNext);
		btnSaveNext.click();
	}

	public void clickSave() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSave);
		btnSave.click();
	}

	public boolean isSaveSuccessful() {
		try {
			wait.until(ExpectedConditions.visibilityOf(toastSuccess));
			return toastSuccess.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
