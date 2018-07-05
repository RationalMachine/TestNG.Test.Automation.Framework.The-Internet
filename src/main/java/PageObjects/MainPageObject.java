package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPageObject extends BasePageObject{

    private WebDriver driver;

    public MainPageObject(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(linkText = "Basic Auth")
    public WebElement basicAuthLink;

    @FindBy(linkText = "Broken Images")
    public WebElement brokenImageLink;

    @FindBy(linkText = "Checkboxes")
    public WebElement checkBoxLink;

    @FindBy(linkText = "Drag and Drop")
    public WebElement dragAndDropLink;

    public BasicAuthPageObject openAuthPage(){
        waitForElementAndClick(basicAuthLink);
        return new BasicAuthPageObject(driver);
    }

    public BrokenImagePageObject openBrokenImagePage(){
        waitForElementAndClick(brokenImageLink);
        return new BrokenImagePageObject(driver);
    }

    public CheckBoxPageObject openCheckBoxPage(){
        waitForElementAndClick(checkBoxLink);
        return new CheckBoxPageObject(driver);
    }

    public DragAndDropPageObject openDragPage(){
        waitForElementAndClick(dragAndDropLink);
        return new DragAndDropPageObject(driver);
    }
}
