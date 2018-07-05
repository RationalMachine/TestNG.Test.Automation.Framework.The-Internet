import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.slf4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverInitializer {

    public WebDriver driver;
    public static final File file2=new File("src/main/resources/chromedriver");
    public static final String CHROMEDRIVER_PATH=file2.getAbsolutePath();
    public static final Logger logger= LoggerFactory.getLogger(DriverInitializer.class);

    @BeforeMethod
    public void setUp(){

        String userHome=System.getProperty("user.home");
        System.setProperty("webdriver.chrome.driver",CHROMEDRIVER_PATH);
        ChromeOptions options=new ChromeOptions();
        Map<String, Object> preferences = new HashMap<String, Object>();
        preferences.put("credentials_enable_service", false);
        preferences.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs",preferences);
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        driver=new ChromeDriver(options);
        logger.info("Driver has been invoked");
        driver.manage().window().maximize();
        logger.info("Driver has been maximized");
        driver.get("http://admin:admin@the-internet.herokuapp.com/");
        logger.info("The website {} has been opened",driver.getCurrentUrl());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public void waitForSomeTime(int timeInSeconds){
        try {
            Thread.sleep(timeInSeconds*1000);
        } catch (InterruptedException e) {
            logger.info("Failed to wait due to {}",e);
        }
        logger.info("Waited for {} seconds",timeInSeconds);
    }

    public boolean isCheckBoxSelected(WebElement element){
        return element.isSelected();
    }

    public void dragAndDrop(WebElement elementOne, WebElement elementTwo){
        waitForSomeTime(3);
        Actions actions=new Actions(driver);
        actions.clickAndHold(elementOne).moveByOffset(getXOffset(elementTwo),getYOffset(elementTwo)).moveToElement(elementTwo).release().build().perform();
        logger.info("Element has been dragged");
        //actions.clickAndHold(elementOne).moveByOffset(getXOffset(elementOne),getXOffset(elementTwo)).release().build().perform();
    }


    public String getText(WebElement element){
        waitForSomeTime(3);
        return element.getText();
    }

    public int getXOffset(WebElement element){
        waitForSomeTime(3);
        Point point=element.getLocation();
        return point.getX();
    }

    public int getYOffset(WebElement element){
        waitForSomeTime(3);
        Point point=element.getLocation();
        return point.getY();
    }

    public void analyzeLog(){
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for(LogEntry entry:logEntries)
            logger.info("This was logged at {} with the level {} : {}",entry.getTimestamp(),entry.getLevel(),entry.getMessage());
    }

    public void reportLog(String message){
        logger.info("Message: {}",message);
    }

}
