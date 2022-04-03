package io.cucumber;


import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class MainPage extends BasePage {

    @FindBy(className = "ot-sdk-container")
    private WebElement popUp;

    @FindBy(xpath = "//button[contains(text(), 'That’s OK')]")
    private WebElement closePopUpButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signIn;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(id = "fm-signin-error")
    private WebElement signInError;


    public MainPage() {
        goToMainPage();
    }

    @Step("User opens start page")
    public void goToMainPage() {
        getChromeDriver().get(MAIN_PAGE_URL);
    }

    @Step("User clicks Sign In button")
    public void clickSignIn() {
        signIn.click();
    }

    @Step("Enter login: {0}")
    public void enterLogin(String login) {
        email.click();
        email.sendKeys("");
        email.sendKeys(login);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String pass) {
        password.click();
        password.sendKeys("");
        password.sendKeys(pass);
    }

    @Step("Check Error Message Is Displayed: {0}")
    public void checkErrorMessageIsDisplayed(String errorMessage) {
        waitForElementToBeDisplayed(signInError, 10, 250, true);
        Assertions.assertTrue(signInError.isDisplayed(),
                "Error is not displayed");
        Assertions.assertEquals(errorMessage, signInError.getText(),
                "Error message is not equal to expected");
    }

    @Step("User closes pop-up")
    public void closePopUpWindow() {
        waitForElementToBeDisplayed(popUp, 10, 250, true);
            closePopUpButton.click();
        waitForElementToBeDisplayed(closePopUpButton, 10, 250, false);

        Assertions.assertFalse(closePopUpButton.isDisplayed(),
                "Pop-up window is still displayed");
    }

    private void waitForElementToBeDisplayed(WebElement element, int duration, int polling, boolean shouldVisible) {
        var wait = setTimeOut(duration, polling);
        wait.ignoring(NoSuchElementException.class);
        if (!shouldVisible) {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } else {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }
}
