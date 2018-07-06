import PageObjects.BrokenImagePageObject;
import PageObjects.MainPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrokenImageTest extends DriverInitializer{

    @Test(description = "Validating that there are two broken images present")
    public void twoBrokenImagesValidate(){
        extentTest=extentReports.createTest("Regression Test");
        BrokenImagePageObject brokenImagePageObject = getBrokenImagePageObject();
        waitForSomeTime(3);
        int totalBrokenImagesFound = brokenImagePageObject.findTotalBrokenImages();
        Assert.assertEquals(2,totalBrokenImagesFound);
    }

    @Test(description = "Validating that there is at least one correct image")
    public void validImagesValidate(){
        extentTest=extentReports.createTest("Regression Test");
        BrokenImagePageObject brokenImagePageObject = getBrokenImagePageObject();
        waitForSomeTime(3);
        int totalImagesFound = brokenImagePageObject.findValidImages();
        Assert.assertEquals(2,totalImagesFound);
    }

    private BrokenImagePageObject getBrokenImagePageObject() {
        MainPageObject mainPageObject=new MainPageObject(driver);
        return mainPageObject.openBrokenImagePage();
    }

}
