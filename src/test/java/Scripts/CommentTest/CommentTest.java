package Scripts.CommentTest;

import Pages.DetailJobPage;
import Scripts.BaseTest.BaseTest;
import org.testng.annotations.Test;

public class CommentTest extends BaseTest {
    @Test
    public void testDefaultValue() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.getTextareaValue();
    }

    @Test
    public void testComment() {
        DetailJobPage detailJobPage = new DetailJobPage(driver);
        detailJobPage.inputComment("test");
        detailJobPage.getTextareaValue();
    }
}
