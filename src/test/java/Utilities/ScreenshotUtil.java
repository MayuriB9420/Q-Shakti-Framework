package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

   /* public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destPath = "./screenshots/" + screenshotName + "_" + timestamp + ".png";
        Files.createDirectories(new File("./screenshots/").toPath());
        File destFile = new File(destPath);
        Files.copy(srcFile.toPath(), destFile.toPath());
        return destPath;
    }
}
*/

public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    
    String folderPath = System.getProperty("user.dir") + "./screenshots/";
    Files.createDirectories(new File(folderPath).toPath());
    
    String destPath = folderPath + screenshotName + "_" + timestamp + ".png";
    File destFile = new File(destPath);
    Files.copy(srcFile.toPath(), destFile.toPath());
    
    return destFile.getAbsolutePath(); //  Always absolute
}}
