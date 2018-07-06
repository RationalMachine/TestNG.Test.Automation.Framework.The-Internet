import PageObjects.MainPageObject;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverInitializer{

    public WebDriver driver;
    File reporterPathBuilder=new File("ExtentReport.html");
    private final String REPORTER_PATH=reporterPathBuilder.getAbsolutePath();
    File screenshotPathBuilder=new File("Screenshots/");
    private final String SCREENSHOT_ROOT = screenshotPathBuilder.getAbsolutePath();
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    static{
        File logbackPathBuilder=new File("src/main/resources/logback.xml");
        System.setProperty("logback.configurationFile", logbackPathBuilder.getAbsolutePath());
        }
    public static final Logger logger= LoggerFactory.getLogger(DriverInitializer.class);


    @BeforeSuite
    public void initializing(){
        logger.info("Initializing testing...");

        htmlReporter=new ExtentHtmlReporter(REPORTER_PATH);
        extentReports=new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        extentReports.setSystemInfo("OS","MAC");
        extentReports.setSystemInfo("QA: ","MACOS");
        extentReports.setSystemInfo("Environment","Test");

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeMethod
    public void beforeMethod(){
        System.setProperty("webdriver.chrome.driver",getDriverAbsolutePath());
        getChromeBrowserWithOptions();
        logger.info("Driver has been invoked");
        driver.manage().window().maximize();
        logger.info("Driver has been maximized");
        driver.get("http://admin:admin@the-internet.herokuapp.com/");
        logger.info("The website {} has been opened",driver.getCurrentUrl());
    }

    @AfterSuite
    public void flushing(){
        logger.info("Flushing...");
        extentReports.flush();
    }

    @AfterMethod
    public void reportingTheResult(ITestResult iTestResult){
        if(iTestResult.getStatus()==ITestResult.FAILURE){
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName()+" has failed"+" due to",ExtentColor.AMBER));
            extentTest.fail(iTestResult.getThrowable());
            TakesScreenshot ts=(TakesScreenshot)driver;
            try{
                File src=ts.getScreenshotAs(OutputType.FILE);
                String screenPath=SCREENSHOT_ROOT+iTestResult.getName()+".png";
                FileUtils.copyFile(src, new File(screenPath));
                extentTest.addScreenCaptureFromPath(screenPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(iTestResult.getStatus()==ITestResult.SUCCESS){
            extentTest.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName()+" has passed",ExtentColor.LIME));
        }else{
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName()+" has skipped",ExtentColor.ORANGE));
            extentTest.skip(iTestResult.getThrowable());
        }
        driver.quit();
    }

    private void getChromeBrowserWithOptions() {
        ChromeOptions options=new ChromeOptions();
        Map<String, Object> preferences = new HashMap<String, Object>();
        preferences.put("credentials_enable_service", false);
        preferences.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs",preferences);
        options.addArguments("disable-infobars");
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver=new ChromeDriver(options);
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

    public String getDriverAbsolutePath(){
        String OSname=System.getProperty("os.name").toUpperCase();
        String CHROMEDRIVER_PATH="";
        if(OSname.contains("MAC")) {
            File file2=new File("src/main/resources/"+"chromedriver_mac");
            CHROMEDRIVER_PATH=file2.getAbsolutePath();
            logger.info("Chromedriver for MAC has been invoked");
        }else if(OSname.contains("LINUX")){
            File file3=new File("src/main/resources/"+"chromedriver_linux");
            CHROMEDRIVER_PATH=file3.getAbsolutePath();
            logger.info("Chromedriver for Linux has been invoked");
        }
        return CHROMEDRIVER_PATH;
    }


}
