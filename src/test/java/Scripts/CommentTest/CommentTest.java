package Scripts.CommentTest;

import Listener.SimpleListener;
import Pages.DetailJobPage;
import Scripts.BaseTest.NotLoggedInBaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(SimpleListener.class)
public class CommentTest extends NotLoggedInBaseTest {
    @Test
    public void testDefaultValue() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.isTextareaEmpty();
    }

    @Test
    public void testCommentWithoutLoggedIn() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.inputComment("test");
        detailJobPage.verifyCommentFailBecauseNotLoggedIn();
    }

    @Test
    public void testCommentWithoutInput() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.verifyTextareaRequired();
    }

    //    Test Image
    @Test
    public void testImageDisplay() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.verifyImageDisplayed();
    }
}
