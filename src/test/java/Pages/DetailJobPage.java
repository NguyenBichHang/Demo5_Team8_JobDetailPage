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
    //  Locator phần Job Description
    @FindBy(xpath ="(//div[@class='rating'])[1]")
    private WebElement ratingCount1;

    @FindBy(xpath ="//img[@class='img-fluid w-100']")
    private WebElement jobImage;

    //  Locator phần Package
    @FindBy(xpath ="//button[contains(text(),'Basic')]")
    private WebElement tabBasic;

    @FindBy(xpath ="//button[contains(text(),'Standard')]")
    private WebElement tabStandard;

    @FindBy(xpath ="//button[contains(text(),'Premium')]")
    private WebElement tabPremium;

    @FindBy(xpath ="//button[@class='submit']")
    private WebElement continueButton;

    @FindBy(xpath ="//a[@class='compare']")
    private WebElement compareButton;

    //  Locator phần About The Seller
    @FindBy(xpath ="(//div[@class='rating'])[2]")
    private WebElement ratingCount2;

    @FindBy(xpath ="//button[@class='contact']")
    private WebElement contactMeButton;

    //  Locator FAQ
    @FindBy(xpath ="(//h3[contains(text(),'There are many passages but the majority?')])[1]")
    private WebElement question1;

    @FindBy(xpath ="(//h3[contains(text(),'There are many passages but the majority?')])[2]")
    private WebElement question2;

    @FindBy(xpath ="(//h3[contains(text(),'There are many passages but the majority?')])[3]")
    private WebElement question3;

    @FindBy(xpath ="(//h3[contains(text(),'There are many passages but the majority?')])[4]")
    private WebElement question4;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//p[1]")
    private WebElement answer1;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//p[2]")
    private WebElement answer2;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//p[3]")
    private WebElement answer3;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//p[4]")
    private WebElement answer4;

    @FindBy(xpath ="div[@class='FAQ mt-5']//li[1]//*[name()='svg']")
    private WebElement arrowButton1;

    //  Locator phần comment
    @FindBy(xpath ="//div[contains(text(),'Leave some comments')]")
    private WebElement commentTitle;

    @FindBy(xpath ="//textarea[@name='noiDung']")
    private WebElement commentTextarea;

    @FindBy(xpath ="//button[@class='comment-submit']")
    private WebElement commentButton;

    //div[@class='FAQ mt-5']//li[1]//*[name()='svg']
    public void isTextareaEmpty() {
        String value = commentTextarea.getAttribute("value");
        Assert.assertTrue(value == null || value.trim().isEmpty(), "Default value của textbox không đúng. Actual" + value);
    }

    public void inputComment(String comment) {
        commentTextarea.sendKeys(comment);
        commentButton.click();
    }

    public void verifyCommentFailBecauseNotLoggedIn(){
//        commentTextarea.sendKeys(comment);
//        commentButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "FAIL. Vẫn ở trang Detail Job");
    }

    public void verifyTextareaRequired(){
        String requiredAttr = commentTextarea.getAttribute("required");
        Assert.assertNotNull(requiredAttr, "Textarea phai co thuoc tinh required");
    }

}
