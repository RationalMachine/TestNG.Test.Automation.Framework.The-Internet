package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPageObject extends BasePageObject {

    private WebDriver driver;

    public BasicAuthPageObject(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(xpath = "//div[@class='example']")
    public WebElement content;

}
