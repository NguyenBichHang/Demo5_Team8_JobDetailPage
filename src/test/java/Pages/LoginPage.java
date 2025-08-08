package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    private WebElement emailAddress;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css="button[type='submit']")
    private WebElement loginButton;

    public void login(String email, String password) {
        emailAddress.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
