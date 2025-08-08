package Scripts.CommentTest;

import Pages.DetailJobPage;
import org.testng.annotations.Test;
import Scripts.BaseTest.LoggedInBaseTest;

public class CommentTestLoggedIn extends LoggedInBaseTest{
    @Test
    public void testDefaultValue() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.isTextareaEmpty();
    }

    @Test
    public void testCommentSuccess() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.inputComment("hangtest");
    }

    @Test
    public void testCommentWithoutInput() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.verifyTextareaRequired();
    }
}
