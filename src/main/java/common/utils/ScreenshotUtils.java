package common.utils;

import common.drivers.Driver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public void getScreenshotFile(String path) {
        WebDriver driver = Driver.getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
