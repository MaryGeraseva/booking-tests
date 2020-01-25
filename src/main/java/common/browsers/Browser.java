package common.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public abstract class Browser {

    private String driverProperty;

    Browser(String driverProperty) {
        this.driverProperty = driverProperty;
        if (System.getenv(driverProperty) == null) {
            throw new RuntimeException(String.format("driver didn't find, set system environment variable: %s",
                    driverProperty));
        }
        System.setProperty(driverProperty, System.getenv(driverProperty));
    }

    public abstract WebDriver getDriver();

    public abstract Capabilities getOptions();

    public String getDriverProperty() {
        return driverProperty;
    }
}
