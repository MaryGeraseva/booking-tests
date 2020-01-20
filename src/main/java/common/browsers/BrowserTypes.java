package common.browsers;

public enum BrowserTypes {

    FIREFOX("firefox"),
    FIREFOX_HEADLESS("firefox.headless"),
    CHROME("chrome"),
    CHROME_HEADLESS("chrome.headless"),
    CHROME_MOBILE("chrome.mobile");

    private String value;

    BrowserTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
