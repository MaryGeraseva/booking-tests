package common.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class Browser {

    public static String driver = System.getProperty("selenium.driver");
    public static String browser = System.getProperty("selenium.browser");

    public abstract WebDriver getDriver();

    public abstract DesiredCapabilities getCapabilities();
}
