package common.browsers;

public enum MobileDeviceName {

    IPAD("iPad"),
    IPAD_PRO("iPad Pro"),
    IPHONE_X("iPhone X"),
    IPHONE_8_PLUS("iPhone 6/7/8 Plus"),
    IPHONE_8("iPhone 6/7/8"),
    PIXEL_2("Pixel 2"),
    PIXEL_2_XL("Pixel 2 XL"),
    GALAXY_S5("Galaxy S5");

    private String value;

    MobileDeviceName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
