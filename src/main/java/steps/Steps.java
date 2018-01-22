package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import runners.Runner;
import webdriver.WebDriverManager;

public class Steps extends Runner {
    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriverManager.setWebDriver(new ChromeDriver(chromeOptions));
    }

    @Then("^Browser should be closed$")
    public void browserShouldBeClosed() {
        WebDriverManager.getDriver().quit();
    }

    @Given("^Open chrome browser and open google page$")
    public void openChromeBrowserAndOpenGooglePage() {
        WebDriverManager.getDriver().manage().window().maximize();
        WebDriverManager.getDriver().get("http://www.google.com");
    }

    @When("^I enter \"([^\"]*)\" search request$")
    public void iEnterSearchRequest(String searchFor) {
        WebDriverManager.getDriver().findElement(By.xpath("//input[@type='text']")).sendKeys(searchFor);
        WebDriverManager.getDriver().findElement(By.xpath("//input[@type='text']")).submit();
    }

    @Then("^Search results should contain \"([^\"]*)\"$")
    public void searchResultsShouldContain(String expect) {
        Assert.assertFalse(WebDriverManager.getDriver().findElements(By.xpath("//div[@id='search']//*[contains(text(), '" + expect + "')]")).isEmpty());
    }
}
