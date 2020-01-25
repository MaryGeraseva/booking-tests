package common.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome extends Browser {

    Chrome() {
        super("webdriver.chrome.driver");
    }

    @Override
    public WebDriver getDriver() {
        return new ChromeDriver();
    }

    @Override
    public Capabilities getOptions() {
        return new ChromeOptions();
    }
}

