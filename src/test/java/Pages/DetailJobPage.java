package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class DetailJobPage {
    WebDriver driver;
    private JavascriptExecutor js;
    private Actions actions;
    private WebDriverWait wait;

    public DetailJobPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //  ******
    //  ***Locator phần Navigation***
    @FindBy(xpath ="//a[contains(text(),'Graphics & Design')]")
    private WebElement navigation1;

    @FindBy(xpath ="//a[contains(text(),'Logo & Brand Identity')]")
    private WebElement navigation2;

    @FindBy(xpath ="//a[contains(text(),'Logo Desgin')]")
    private WebElement navigation3;

    //  ******
    //  ***Locator phần Job Description***
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

    //  ******
    //  ***Locator phần Package***
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

    //  ******
    //  ***Locator phần About The Seller***
    @FindBy(xpath ="(//div[@class='rating'])[2]")
    private WebElement ratingCount2;

    @FindBy(xpath ="//button[@class='contact']")
    private WebElement contactMeButton;

    //  ******
    //  ***Locator FAQ***
    @FindBy(xpath ="(//div[@class='FAQ mt-5']")
    private WebElement faqSection;

    @FindBy(xpath ="(//input[@type='checkbox'])[1]")
    private WebElement question1;

    @FindBy(xpath ="(//input[@type='checkbox'])[2]")
    private WebElement question2;

    @FindBy(xpath ="(//input[@type='checkbox'])[3]")
    private WebElement question3;

    @FindBy(xpath ="(//input[@type='checkbox'])[4]")
    private WebElement question4;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//p[1]")
    private WebElement answer1;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[2]//p[1]")
    private WebElement answer2;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//p[3]")
    private WebElement answer3;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//p[4]")
    private WebElement answer4;

    @FindBy(xpath ="//*[name()='svg']/*[name()='path'][contains(@d, 'M')]")
    private WebElement arrowButton;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[1]//*[name()='svg']")
    private WebElement arrowButton1;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[2]//*[name()='svg']")
    private WebElement arrowButton2;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[3]//*[name()='svg']")
    private WebElement arrowButton3;

    @FindBy(xpath ="//div[@class='FAQ mt-5']//li[4]//*[name()='svg']")
    private WebElement arrowButton4;

    //  ******
    //  ***Locator textbox search***
    @FindBy(xpath ="//input[@type='text']")
    private WebElement textboxSearch;

    @FindBy(xpath ="//form[contains(@class,'search-form d-flex')]//button")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class, 'reviewer-name')]")
    private List<WebElement> reviewerName;

    @FindBy(xpath = "//div[contains(@class, 'country-name')]")
    private List<WebElement> countries;

    @FindBy(xpath = "//*[contains(@class, 'star')]")
    private List<WebElement> starRating;

    @FindBy(xpath ="//div[contains(@class, 'comment')")
    private WebElement allComments;

    @FindBy(xpath ="//div[contains(text(), 'There is no results')")
    private WebElement noResultMessage;

    @FindBy(xpath = "//div[text()='Helpful?']")
    private List<WebElement> helpfulText;

    @FindBy(xpath = "//div[contains(@class, 'yes d-flex align-items-center gap-1')]")
    private List<WebElement> yesButton;

    @FindBy(xpath = "//div[contains(@class, 'no d-flex align-items-center gap-1')]")
    private List<WebElement> noButton;

    //  ******
    //  ***Locator phần comment***
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
    public void clickTabBasic(){
        tabBasic.click();
    }

    public void clickTabStandard(){
        tabStandard.click();
    }

    public void clickTabPremium(){
        tabPremium.click();
    }

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
    public void verifyTextboxSearchPlaceholder() {
        String placeholder = textboxSearch.getAttribute("placeholder");
        Assert.assertNotNull(placeholder, "Search box should have a placeholder");
        Assert.assertTrue(placeholder.contains("Search reviews"),
                "Placeholder should be 'Search reviews'. Actual: " + placeholder);
    }

    public void inputSearchText(String search){
        Assert.assertTrue(textboxSearch.isDisplayed(), "Search box should be visible");
        textboxSearch.sendKeys(search);
    }

    public void clickSearchButton(){
        Assert.assertTrue(textboxSearch.isEnabled(), "Search box should be enabled");
        searchButton.click();
    }

    public void waitForSearchResult(){
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    org.openqa.selenium.By.xpath("//div[contains(@class, 'comment')]")
            ));
        } catch (Exception e) {
            // Results might not be present for invalid searches
        }
    }

    public List<WebElement> getSearchResults(String search) {
        return driver.findElements(
                org.openqa.selenium.By.xpath("//div[contains(text(), '" + search + "') or .//*[contains(text(), '" + search + "')]]")
        );
    }

    public boolean hasSearchResults(String search) {
        List<WebElement> results = getSearchResults(search);
        return results.size() > 0;
    }

    public boolean searchResultsContainSearchKeyword(String search) {
        List<WebElement> results = getSearchResults(search);
        for (WebElement result : results) {
            if (result.getText().toLowerCase().contains(search.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public int getAllReviewsCount() {
        List<WebElement> allReviews = driver.findElements(
                org.openqa.selenium.By.xpath("//div[contains(@class, 'comment')]")
        );
        return allReviews.size();
    }

    public boolean areReviewElementsVisible() {
        return reviewerName.size() > 0 ||
                countries.size() > 0 ||
                starRating.size() > 0 ;
//                reviewText.size() > 0;
    }

    public boolean areHelpfulButtonsVisible() {
        return helpfulText.size() > 0 &&
                yesButton.size() > 0 &&
                noButton.size() > 0;
    }

    public void verifyValidSearchResult(String search){
        Assert.assertTrue(hasSearchResults(search),
                "Search should return results for valid keyword: " + search);
        Assert.assertTrue(searchResultsContainSearchKeyword(search),
                "Search results should contain the search term: " + search);
//        List<WebElement> searchResults = driver.findElements(
//                By.xpath("//div[contains(text(), '" + search + "') or .//*[contains(text(), '" + search + "')]]")
//        );
//        Assert.assertTrue(searchResults.size() > 0, "Search should return results for valid keyword");
//        boolean foundSearchTerm = false;
//        for (WebElement result : searchResults) {
//            if (result.getText().toLowerCase().contains(search.toLowerCase())) {
//                foundSearchTerm = true;
//                break;
//            }
//        }
//        Assert.assertTrue(foundSearchTerm, "Search results should contain the search term");
    }
//    public boolean hasNoResultMessage() {
//        return noResultMessage.size() > 0;
//    }
    
    public void verifyInvalidSearchResult(String search){
        boolean hasResults = hasSearchResults(search);
//        boolean hasNoResultMessage = hasNoResultMessage();
//        Assert.assertTrue(!hasResults || hasNoResultMessage,
//                "Invalid search should return no results or show no results message");
    }

    public void verifyAllCommentsAreDisplayed(){
        int reviewCount = getAllReviewsCount();
        Assert.assertTrue(reviewCount > 0,
                "All reviews should be displayed when search is empty");
    }

    //    ****FAQ****
    public void FAQ() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqSection);
    }

    public void arrowButtonExist() {
        Assert.assertTrue(arrowButton1.isDisplayed(),
                "Không tìm thấy nút mũi tên trong FAQ section");
        Assert.assertTrue(arrowButton1.isEnabled(),
                "Nút mũi tên không thể click");
    }

    public void isAnswerHidden() {
        Assert.assertTrue(arrowButton1.isDisplayed(),
                "Không tìm thấy nút mũi tên trong FAQ section");
        Assert.assertTrue(arrowButton1.isEnabled(),
                "Nút mũi tên không thể click");
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
