package common.browsers;

import common.logger.LogInstance;
import org.apache.log4j.Logger;

public class BrowserFactory {

    private Logger log = LogInstance.getLogger();
    private Browser browser;

    public BrowserFactory() {
        String propertyBrowser = System.getProperty("browser");
        if (propertyBrowser == null) {
            browser = new Chrome();
            log.info("no browser type defined, started default browser chrome");
        } else if (propertyBrowser.equals("firefox")) {
            browser = new Firefox();
        } else if (propertyBrowser.equals("chrome")) {
            browser = new Chrome();
        } else {
            browser = new Chrome();
            log.info("unknown browser type, only chrome and firefox supported, started default browser chrome");
        }
        log.info("created webDriver: " + browser.getBrowserProperty());
    }

    public Browser getBrowser() {
        return browser;
    }
}
