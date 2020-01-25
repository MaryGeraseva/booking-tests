package common.browsers;

import common.logger.LogInstance;
import org.apache.log4j.Logger;

public class BrowserFactory {

    private Browser browser;

    public BrowserFactory() {
        String propertyBrowser = System.getProperty("browser");
        Logger log = LogInstance.getLogger();
        if (propertyBrowser == null) {
            browser = new Chrome();
            log.info("no browser type defined, started default browser Chrome");
        } else if (propertyBrowser.equals("firefox")) {
            browser = new Firefox();
            log.info("Browser: Firefox");
        } else if (propertyBrowser.equals("chrome")) {
            browser = new Chrome();
            log.info("Browser: Chrome");
        } else {
            browser = new Chrome();
            log.warn("unknown browser type, only Chrome and Firefox supported, started default browser chrome");
        }
    }

    public Browser getBrowser() {
        return browser;
    }
}
