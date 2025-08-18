package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="(//a[contains(text(),'View detail')])[1]")
    private WebElement viewDetailButton;

    @FindBy(xpath ="(//button[contains(text(),'DEL')])[1]")
    private WebElement delButton;

}
