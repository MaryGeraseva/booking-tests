package common.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Firefox extends Browser {

    @Override
    public WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("selenium.driver"));
        return new FirefoxDriver();
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return DesiredCapabilities.firefox();
    }

}
