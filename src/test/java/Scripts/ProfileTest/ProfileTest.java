package Scripts.ProfileTest;

import Pages.DetailJobPage;
import Pages.ProfilePage;
import Scripts.BaseTest.LoggedInBaseTest;
import org.testng.annotations.Test;

public class ProfileTest extends LoggedInBaseTest {
    @Test
    public void testJobDisplay() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyJobDisplay();
    }

    @Test
    public void testJobDisplayEmpty() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyJobDisplayEmpty();
    }

    @Test
    public void testJobDetailContent() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyJobDetailContent();
    }

    @Test
    public void testDelButtonDisplay() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyDelButtonDisplay();
    }
}
