package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DetailJobPage {
    WebDriver driver;
    public DetailJobPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//div[contains(text(),'Leave some comments')]")
    private WebElement commentTitle;

    @FindBy(xpath ="//textarea[@name='noiDung']")
    private WebElement commentTextarea;

    @FindBy(xpath ="//button[@class='comment-submit']")
    private WebElement commentButton;

    public boolean getTextareaValue() {
        String value = commentTextarea.getAttribute("value");
        if (value == null) {
            System.out.print("Gia tri la trong");
        }
        else {
        System.out.print("Gia tri la" + value);}
        return false;
    }

    public void isTextareaEmpty() {
        Assert.assertTrue(getTextareaValue(), "Textbox password không trống");
    }

    public void inputComment(String comment) {
        commentTextarea.sendKeys(comment);
    }
}
