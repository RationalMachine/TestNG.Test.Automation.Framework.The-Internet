import PageObjects.BrokenImagePageObject;
import PageObjects.MainPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrokenImageTest extends DriverInitializer{

    @Test
    public void twoBrokenImagesValidate(){
        BrokenImagePageObject brokenImagePageObject = getBrokenImagePageObject();
        waitForSomeTime(3);
        int totalBrokenImagesFound = brokenImagePageObject.findTotalBrokenImages();
        Assert.assertEquals(2,totalBrokenImagesFound);
    }

    @Test
    public void validImagesValidate(){
        BrokenImagePageObject brokenImagePageObject = getBrokenImagePageObject();
        waitForSomeTime(3);
        int totalImagesFound = brokenImagePageObject.findValidImages();
        Assert.assertEquals(1,totalImagesFound);
    }

    private BrokenImagePageObject getBrokenImagePageObject() {
        MainPageObject mainPageObject=new MainPageObject(driver);
        return mainPageObject.openBrokenImagePage();
    }

}
