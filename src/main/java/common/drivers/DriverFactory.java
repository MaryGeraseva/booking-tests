package common.drivers;

import common.browsers.Browser;
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

    private String hubUrl = System.getProperty("hub.url");

    public WebDriver createDriver() {
        if (hubUrl != null) {
            try {
                driver = new RemoteWebDriver(new URL(hubUrl), new BrowserFactory().getBrowser().getOptions());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            log.info("started webDriver on the selenium grid server");
            log.info("server URL: " + hubUrl);
        } else {
            Browser browser = new BrowserFactory().getBrowser();
            driver = browser.getDriver();
            log.info("started local webDriver: " + browser.getDriverProperty());
        }
        return driver;
    }
}
