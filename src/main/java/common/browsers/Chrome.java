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

    private Logger log = LogInstance.getLogger();

    @Override
    public WebDriver getDriver() {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("selenium.driver"));
        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\chromedriver.exe");
        WebDriver driver;
        if (browserHaveOptions(browser)) {
            driver = new ChromeDriver(getBrowserOptions());
        } else {
            driver = new ChromeDriver();
        }
        return driver;
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        if (browserHaveOptions(browser)) {
            capabilities.setCapability(ChromeOptions.CAPABILITY, getBrowserOptions());
        }
        return capabilities;
    }

    private ChromeOptions getBrowserOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if ((browser.contains("headless"))) {
            chromeOptions.addArguments("--headless");
        } else if ((browser.contains("mobile"))) {
            String deviceName = (device != null) ? device : MobileDeviceName.PIXEL_2.getValue();
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", deviceName);
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            log.info("started chrome browser with " + deviceName + " emulation");
        }
        return chromeOptions;
    }
}

