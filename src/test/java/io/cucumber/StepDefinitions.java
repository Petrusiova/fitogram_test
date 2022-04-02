package io.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static io.cucumber.BasePage.checkExpectedUrl;

public class StepDefinitions {

    private MainPage mainPage;

    @Given("User opens start page")
    public void openStartPage(){
        mainPage = new MainPage();
    }

    @Given("User enters {} as login")
    public void enterLogin(String login){
        if (login.equals("nothing")){
            mainPage.enterLogin("");
        } else {
            mainPage.enterLogin(login);
        }
    }

    @Then("User enters {} as password")
    public void enterPassword(String password){
        if (password.equals("nothing")) {
            mainPage.enterPassword("");
        } else {
            mainPage.enterPassword(password);
        }
    }

    @And("{} is opened")
    public void checkUrl(String url){
        checkExpectedUrl(url);
    }

    @And("{} is displayed")
    public void checkErrorMessage(String message){
        mainPage.checkErrorMessageIsDisplayed(message);
    }

    @Then("User closes pop-up")
    public void closePopUp() {
        mainPage.closePopUpWindow();
    }


    @Then("User clicks Sign In button")
    public void clickSignIn() {
        mainPage.clickSignIn();
    }

    @After()
    public void closeBrowser(){
        mainPage.afterScenario();
    }
}
