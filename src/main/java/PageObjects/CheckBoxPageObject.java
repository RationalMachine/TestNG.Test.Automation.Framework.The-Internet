package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class CheckBoxPageObject extends BasePageObject{

    private WebDriver driver;

    public CheckBoxPageObject(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBys(@FindBy(css = "input[type='checkbox']"))
    public List<WebElement> checkboxes;

    @FindBy(css="input[type='checkbox']:nth-child(1)")
    public WebElement checkboxOne;

    @FindBy(css="input[type='checkbox']:nth-child(3)")
    public WebElement checkboxTwo;

    public void checkUnCheckBoxes(){
        for(WebElement element:checkboxes)
            element.click();
    }

}
