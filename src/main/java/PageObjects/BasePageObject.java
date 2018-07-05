package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageObject {

    private WebDriver driver;
    private WebDriverWait wait;

    public BasePageObject(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,15);
        PageFactory.initElements(driver,this);
    }

    protected void waitForElementAndClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }



}
