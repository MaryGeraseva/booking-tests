package common.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class Browser {

    private String browserProperty;

    Browser(String browserProperty) {
        this.browserProperty = browserProperty;
        if (System.getenv(browserProperty) == null) {
            throw new RuntimeException(String.format("driver didn't find, set system environment variable: %s",
                    browserProperty));
        }
        System.setProperty(browserProperty, System.getenv(browserProperty));
    }

    public abstract WebDriver getDriver();

    public abstract DesiredCapabilities getCapabilities();

    String getBrowserProperty() {
        return browserProperty;
    }
}
