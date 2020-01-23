package common.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Firefox extends Browser {

    Firefox() {
        super("webdriver.gecko.driver");
    }

    @Override
    public WebDriver getDriver() {
        return new FirefoxDriver();
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return DesiredCapabilities.firefox();
    }

}
