import Helpers.DragAndDropJSHelper;
import PageObjects.DragAndDropPageObject;
import PageObjects.MainPageObject;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends DriverInitializer {



    @Test
    public void dragAndDropTestLeftToRight(){
        MainPageObject mainPageObject=new MainPageObject(driver);
        DragAndDropPageObject dragAndDropPageObject=mainPageObject.openDragPage();
        DragAndDropJSHelper ddh = new DragAndDropJSHelper();
        ddh.dragAndDrop(driver,"#column-a", "#column-b");
        waitForSomeTime(5);
        Assert.assertEquals("B",dragAndDropPageObject.columnAHeader.getText());
        analyzeLog();
    }

}
