package common.browsers;

import common.logger.LogInstance;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class Chrome extends Browser {

    @Override
    public WebDriver getDriver() {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("selenium.driver"));
        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\chromedriver.exe");
        return new ChromeDriver();
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return DesiredCapabilities.chrome();
    }
}

