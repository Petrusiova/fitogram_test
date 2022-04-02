package io.cucumber;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class BasePage {

    private static ChromeDriver driver;
    public static final String MAIN_PAGE_URL = "https://app.fitogram.pro";
    Actions actions;

    public BasePage() {
        initWebDriver();
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public static ChromeDriver getChromeDriver() {
        return driver;
    }

    private void initWebDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver");
            driver = new ChromeDriver();
        }
    }

    @AfterEach
    @Step("Close browser")
    public void afterScenario() {
        driver.close();
        driver.quit();
        driver = null;
    }

    @Step("Check url equals to expected: {expectedUrl}")
    public static void checkExpectedUrl(String expectedUrl){
        FluentWait wait = new FluentWait(getChromeDriver());
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(250));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "Wrong page url");
    }

    @Step("Click the element")
    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (WebDriverException e) {
            JavascriptExecutor executor = (JavascriptExecutor) getChromeDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            executor.executeScript("arguments[0].click()", element);
        }
    }
}
