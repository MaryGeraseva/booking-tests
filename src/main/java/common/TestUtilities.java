package common;

import common.drivers.Driver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class TestUtilities {

    public void getScreenshotFile(String path) {
        WebDriver driver = Driver.getDriver();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    public byte[] getScreenshotByte() {
        WebDriver driver = Driver.getDriver();
        return  ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    public static String getTodayDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    public static LocalDate getInWeeksDate(int weeks) {
        return LocalDate.now().plus(weeks, ChronoUnit.WEEKS);
    }

    public String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }

    public List<LogEntry> getBrowserLogs() {
        WebDriver driver = Driver.getDriver();
        LogEntries logs = driver.manage().logs().get("browser");
        return logs.getAll();
    }
}
