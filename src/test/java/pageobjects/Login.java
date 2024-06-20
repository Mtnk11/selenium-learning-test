package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends BasePage {
    By loginForm = By.id("login");
    By usernameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By submitLocator = By.cssSelector("#login > button");
    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");

    public Login(WebDriver driver) throws Exception{
        super(driver);
        visit("/login");
        if(!isDisplayed(loginForm)) {
            throw new Exception("Login form is not displayed");
        }
    }
    public void with(String username, String password) {
        type(usernameLocator,username);
        type(passwordLocator,password);
        click(submitLocator);
    }
    public Boolean successLoginMessage() {
        return isDisplayed(successMessageLocator);
    }
    public Boolean failureLoginMessage() {
        return  isDisplayed(failureMessageLocator);
    }
}
