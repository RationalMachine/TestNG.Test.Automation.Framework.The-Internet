import PageObjects.BasePageObject;
import PageObjects.BasicAuthPageObject;
import PageObjects.MainPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest extends DriverInitializer{

    private BasicAuthPageObject basicAuthPageObject;

    @Test
    public void basicAuthenticationTest() throws InterruptedException {
        MainPageObject mainPageObject=new MainPageObject(driver);
        BasicAuthPageObject basicAuthPageObject=mainPageObject.openAuthPage();
        waitForSomeTime(2);
        String contentText=basicAuthPageObject.content.getText().replaceAll("\n","");
        String expectedText="Basic AuthCongratulations! You must have the proper credentials.";
        Assert.assertEquals(expectedText,contentText);
    }
}
