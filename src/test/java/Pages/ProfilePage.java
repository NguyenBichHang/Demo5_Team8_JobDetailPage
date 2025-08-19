package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProfilePage {
    WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath ="(//div[@class='gigs_card'])[2]")
    private WebElement jobList;

    @FindBy(xpath ="(//div[@class='gigs_card_img'])[1]")
    private WebElement jobListImage;

    @FindBy(xpath ="(//a[contains(text(),'View detail')])[1]")
    private WebElement viewDetailButton;

    @FindBy(xpath ="(//button[contains(text(),'DEL')])[1]")
    private WebElement delButton;

    public void verifyJobDisplay(){
        Assert.assertTrue(jobList.isDisplayed(), "Job đã thuê không hiển thị");
//        String srcJobListImage = jobListImage.getAttribute("src");
    }

    public void verifyViewDetailButton(){
        viewDetailButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("jobDetail/2"), "FAIL. Vẫn ở trang Profile");
    }

    public void verifyDelButton(){
        delButton.click();
//        Assert.assertTrue(driver.getCurrentUrl().contains("jobDetail/2"), "FAIL. Vẫn ở trang Profile");
    }
}
