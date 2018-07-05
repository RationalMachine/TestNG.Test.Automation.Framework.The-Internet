package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BrokenImagePageObject extends BasePageObject {

    private WebDriver driver;

    public BrokenImagePageObject(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(xpath = "//div[@class='example']")
    public WebElement imageListElement;

    public int findTotalBrokenImages(){
        int c=0;
        List<WebElement> elements=driver.findElements(By.xpath("//div[@class='example']/img"));
        for(WebElement element:elements) {
            if (element.getAttribute("naturalWidth").equals("0")) c++;
        }
        return c;
    }

    public int findValidImages(){
        int c=0;
        List<WebElement> elements=driver.findElements(By.xpath("//div[@class='example']/img"));
        for(WebElement element:elements) {
            if (element.getAttribute("naturalWidth").equals("160")) c++;
        }
        return c;
    }

}
