package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import webdriver.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/java/features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-html-report"})
public class Runner extends AbstractTestNGCucumberTests {

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            File imageFile = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            String failureImageFileName = result.getMethod().getMethodName()
                    + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
            File failureImageFile = new File("target/screenshots/" + failureImageFileName);
            FileUtils.copyFile(imageFile, failureImageFile);
        }
    }
}
