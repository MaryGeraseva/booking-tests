package common;

import assertions.ExtendedAssertions;
import common.drivers.Driver;
import common.logger.LogInstance;
import io.qameta.allure.Allure;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners({TestListener.class})
public class BaseTest {

    private WebDriver driver;
    protected Logger log;
    protected ExtendedAssertions assertions;

    @BeforeMethod
    public void setUp(Method method, ITestContext context, Object[] testData) {
        log = LogInstance.setContext(context, method);
        driver = Driver.getDriver();
        assertions = new ExtendedAssertions();
//        maximizeWindow();
        log.info(String.format("setUp %s", method.getName()));
    }

    @AfterMethod
    public void tearDown(Method method, ITestContext context) {
        log.info(String.format("tearDown %s", method.getName()));
        attachLog(getLogPath(context));
        Driver.resetDriver();
        LogInstance.resetLog();
        driver.quit();
    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    private void attachLog(String path) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            Allure.addAttachment("Logs", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLogPath(ITestContext context) {
        return  String.format("%s\\target\\logs\\methods\\%s\\%d#%s.log",
                System.getProperty("user.dir"), context.getCurrentXmlTest().getName(),
                LogInstance.getTestCaseId(context), Thread.currentThread().getName());
    }
}
