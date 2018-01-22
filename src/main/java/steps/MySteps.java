package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class MySteps {
    private WebDriver driver;

    @Then("^Browser should be closed$")
    public void browserShouldBeClosed() {
        driver.quit();
    }

    @Given("^Open chrome browser and open google page$")
    public void openChromeBrowserAndOpenGooglePage() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
    }

    @When("^I enter \"([^\"]*)\" search request$")
    public void iEnterSearchRequest(String searchFor) {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(searchFor);
        driver.findElement(By.xpath("//input[@type='text']")).submit();
    }

    @Then("^Search results should contain \"([^\"]*)\"$")
    public void searchResultsShouldContain(String expect) {
        Assert.assertFalse(driver.findElements(By.xpath("//div[@id='search']//*[contains(text(), '" + expect + "')]")).isEmpty());
    }
}
