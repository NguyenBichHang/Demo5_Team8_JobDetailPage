package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DetailJobPage {
    WebDriver driver;
    private JavascriptExecutor js;
    private Actions actions;

    public DetailJobPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //  Locator phần Navigation
    @FindBy(xpath ="//a[contains(text(),'Graphics & Design')]")
    private WebElement navigation1;

    @FindBy(xpath ="//a[contains(text(),'Logo & Brand Identity')]")
    private WebElement navigation2;

    @FindBy(xpath ="//a[contains(text(),'Logo Desgin')]")
    private WebElement navigation3;

    //  Locator phần Job Description
    @FindBy(xpath ="//h1[@class='job-title']")
    private WebElement jobTitle;

    @FindBy(xpath ="//h2[normalize-space()='About This Gig']")
    private WebElement jobDescriptionTitle;

    @FindBy(xpath ="//div[@class='description']")
    private WebElement jobDescription;

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
    @FindBy(xpath ="(//div[@class='FAQ mt-5']")
    private WebElement faqSection;

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

    //  Locator textbox search
    @FindBy(xpath ="//input[@type='text']")
    private WebElement textboxSearch;

    @FindBy(xpath ="//form[contains(@class,'search-form d-flex')]//button")
    private WebElement searchButton;

    //  Locator phần comment
    @FindBy(xpath ="//div[contains(text(),'Leave some comments')]")
    private WebElement commentTitle;

    @FindBy(xpath ="//textarea[@name='noiDung']")
    private WebElement commentTextarea;

    @FindBy(xpath ="//button[@class='comment-submit']")
    private WebElement commentButton;

//    ****Navigation****
    public void verifyNavigationLinkDisplayed(){
        Assert.assertTrue(navigation1.isDisplayed(), "Link Navigation không hiển thị");
        Assert.assertTrue(navigation2.isDisplayed(), "Link Navigation không hiển thị");
        Assert.assertTrue(navigation3.isDisplayed(), "Link Navigation không hiển thị");
//        String imageSrc = jobImage.getAttribute("src");
//        Assert.assertFalse(imageSrc.isEmpty(), "Image source không được để trống");
    }

//    ****Job Detail****
    public void verifyJobDetail(){
        Assert.assertTrue(jobTitle.isDisplayed(), "Job title không được hiển thị");
        Assert.assertTrue(jobDescriptionTitle.isDisplayed(), "Job Description title không được hiển thị");
        Assert.assertTrue(jobDescription.isDisplayed(), "Job Description không được hiển thị");
    }

    public void verifyImageDisplayed(){
        Assert.assertTrue(jobImage.isDisplayed(), "Service image không hiển thị");
        String imageSrc = jobImage.getAttribute("src");
        Assert.assertFalse(imageSrc.isEmpty(), "Image source không được để trống");
    }

    public void verifyImageHoverTransition(){
        
        js = (JavascriptExecutor) driver;

        String initialTransform = js.executeScript(
                "return window.getComputedStyle(arguments[0]).transform", jobImage).toString();
        actions.moveToElement(jobImage).perform();
        String hoverTransform = js.executeScript(
                "return window.getComputedStyle(arguments[0]).transform", jobImage).toString();
        Assert.assertNotEquals(hoverTransform, initialTransform,
                "Image không có hover transition effect - Transform không thay đổi");        actions.moveByOffset(100, 100).perform();
        String finalTransform = js.executeScript(
                "return window.getComputedStyle(arguments[0]).transform", jobImage).toString();
        System.out.println("Final transform after mouse leave: " + finalTransform);
    }

    //    ****About The Seller****
    public void verifyContactMeButtonNotLoggedIn(){
        contactMeButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "FAIL. Vẫn ở trang Job Detail");
    }

    public void verifyContactMeButtonLoggedIn(){
        contactMeButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "FAIL. Vẫn ở trang Job Detail");
    }

    //    ****Package****
    public void verifyContinueButtonNotLoggedIn(){
        continueButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "FAIL. Vẫn ở trang Job Detail");
    }

    public void verifyContinueButtonLoggedIn(){
        continueButton.click();
//        Assert.assertEquals(driver.findElement(errorMessageEmailInvalidChar).getText(), "使用できない文字が含まれています。", "Không đúng nội dung error message");
    }

    public void verifyCompareButtonNotLoggedIn(){
        continueButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "FAIL. Vẫn ở trang Job Detail");
    }

    public void verifyCompareButtonLoggedIn(){
        continueButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("compare"), "FAIL. Vẫn ở trang Job Detail");
    }
    //    ****Seacrh****
    public void inputSearch(String search){
        textboxSearch.sendKeys(search);
        searchButton.click();
    }

    //    ****FAQ****
    public void FAQ() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqSection);
    }

    //    ****Comment****
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
