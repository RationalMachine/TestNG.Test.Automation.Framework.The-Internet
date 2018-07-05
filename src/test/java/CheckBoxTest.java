import PageObjects.BasicAuthPageObject;
import PageObjects.CheckBoxPageObject;
import PageObjects.MainPageObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBoxTest extends DriverInitializer{

    @Test
    public void checkBoxTest(){
        MainPageObject mainPageObject=new MainPageObject(driver);
        CheckBoxPageObject checkBoxPageObject=mainPageObject.openCheckBoxPage();
        checkBoxPageObject.checkUnCheckBoxes();
        Assert.assertTrue(isCheckBoxSelected(checkBoxPageObject.checkboxOne));
        Assert.assertFalse(isCheckBoxSelected(checkBoxPageObject.checkboxTwo));
    }
}
