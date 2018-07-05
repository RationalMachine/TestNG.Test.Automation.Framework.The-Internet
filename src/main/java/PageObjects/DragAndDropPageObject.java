package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DragAndDropPageObject extends BasePageObject{

    private WebDriver driver;

    public DragAndDropPageObject(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(id = "column-a")
    public WebElement leftElement;

    @FindBy(id = "column-b")
    public WebElement rightColumn;

    @FindBy(xpath = "//div[@id='column-a']/header")
    public WebElement columnAHeader;


}
