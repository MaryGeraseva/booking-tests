package common.drivers;

import common.browsers.BrowserFactory;
import common.logger.LogInstance;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static WebDriver driver;
    private Logger log = LogInstance.getLogger();

    private String hubURL = System.getProperty("hub.url");

    public WebDriver createDriver() {
        if (hubURL != null) {
            try {
                driver = new RemoteWebDriver(new URL(hubURL), new BrowserFactory().getBrowser().getCapabilities());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            log.info("started webDriver on selenium grid server");
            log.info("server URL: " + hubURL);
        } else {
            driver = new BrowserFactory().getBrowser().getDriver();
        }
        return driver;
    }
}
