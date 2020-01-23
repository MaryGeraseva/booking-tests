package common.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Chrome extends Browser {

    Chrome() {
        super("webdriver.chrome.driver");
    }

    @Override
    public WebDriver getDriver() {
        return new ChromeDriver();
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return DesiredCapabilities.chrome();
    }
}

