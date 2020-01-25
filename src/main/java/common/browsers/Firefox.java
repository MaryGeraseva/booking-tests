package common.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Firefox extends Browser {

    Firefox() {
        super("webdriver.gecko.driver");
    }

    @Override
    public WebDriver getDriver() {
        return new FirefoxDriver();
    }


    @Override
    public Capabilities getOptions() {
        return new FirefoxOptions();
    }

}
