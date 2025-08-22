package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ProfilePage {
    WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath ="//div[@class='gigs_card_bottom']")
    private WebElement jobListSection;

    @FindBy(xpath ="//div[@class='gigs_card']")
    private List<WebElement> jobItem;

    @FindBy(xpath ="(//div[@class='gigs_card'])[2]")
    private WebElement firstJobItem;

    @FindBy(xpath ="(//div[@class='gigs_card'])[2]//h1")
    private WebElement firstJobTitle;

    @FindBy(xpath ="(//div[@class='gigs_card'])[2]//p")
    private WebElement firstJobDescription;

    @FindBy(xpath ="(//div[@class='gigs_card'])[2]//span[@class='saoCV']")
    private WebElement firstJobStar;

    @FindBy(xpath ="(//div[@class='gigs_card'])[2]//span[@class='danhGia']")
    private WebElement firstJobRating;

    @FindBy(xpath ="(//div[@class='gigs_card'])[2]//p[@class='giaTien']")
    private WebElement firstJobPrice;

    @FindBy(xpath ="(//div[@class='gigs_card'])[2]//div[@class='gigs_card_img']")
    private WebElement firstJobImage;

    @FindBy(xpath ="(//a[contains(text(),'View detail')])[1]")
    private WebElement viewDetailButton;

    @FindBy(xpath ="(//button[contains(text(),'DEL')])[1]")
    private WebElement delButton;

    @FindBy(xpath ="//div[@id='jy2rdrn']")
    private WebElement delAlert;

    @FindBy(xpath ="//*[name()='path' and contains(@d,'M12 0a12 1')]")
    private WebElement alertIcon;

    @FindBy(xpath ="//button[@aria-label='close']//*[name()='svg']")
    private WebElement alertCloseButton;

    //div[contains(text(),'Xóa thành công!')]

    public void verifyJobDisplayEmpty(){
        Assert.assertTrue(jobListSection.isDisplayed(), "Job đã thuê không hiển thị");
        Assert.assertTrue(jobItem.isEmpty(),"Có Job được hiển thị");
    }

    public void verifyJobDisplay(){
        Assert.assertFalse(jobListSection.isDisplayed(), "Job đã thuê được hiển thị");
        Assert.assertTrue(getJobItemsCount() > 0,
                "At least one job should be displayed in the list");

        System.out.println("Found " + getJobItemsCount() + " job items");
//        String srcJobListImage = jobListImage.getAttribute("src");
    }

    public void verifyJobDetailContent(){
        Assert.assertTrue(firstJobTitle.isDisplayed(), "Job title should be visible");
        Assert.assertFalse(firstJobTitle.getText().trim().isEmpty(),
                "Job title should not be empty");
        Assert.assertTrue(firstJobDescription.isDisplayed(),
                "Job description should be visible");
        Assert.assertTrue(firstJobStar.isDisplayed(),
                "Job star rating should be visible");
        Assert.assertTrue(firstJobRating.isDisplayed(),
                "Job rating should be visible");
        Assert.assertTrue(firstJobPrice.isDisplayed(),
                "Job price should be visible");
        Assert.assertTrue(firstJobImage.isDisplayed(),
                "Job image should be visible");
    }

    public int getJobItemsCount() {
        return jobItem.size();
    }

    public void verifyViewDetailButton(){
        viewDetailButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("jobDetail/2"), "FAIL. Vẫn ở trang Profile");
    }

    public void verifyDelButtonDisplay(){
        Assert.assertTrue(delButton.isDisplayed(),
                "DEL button should be visible");
        delButton.click();
        Assert.assertTrue(delAlert.isDisplayed(),
                "DEL alert should be visible");
        Assert.assertTrue(alertIcon.isDisplayed(),
                "Alert icon should be visible");
        Assert.assertTrue(alertCloseButton.isDisplayed(),
                "Alert close button should be visible");
        String DelAlertText = delButton.getText();
        Assert.assertTrue(DelAlertText.contains("Xóa thành công!"),
                "Alert message is not correct. Actual: " + DelAlertText);
    }
}
