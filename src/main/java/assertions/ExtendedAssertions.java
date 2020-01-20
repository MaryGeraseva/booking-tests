package assertions;

import common.drivers.Driver;
import common.logger.LogInstance;
import io.qameta.allure.Step;
import models.RoomRequest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtendedAssertions {

    protected Logger log = LogInstance.getLogger();
    protected WebDriver driver = Driver.getDriver();

    @Step("checked the page by current url")
    public void url(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl),
                String.format("the expected page with url %s wasn't opened, current url is %s", expectedUrl, actualUrl));
        log.info(String.format("checked the page by url %s", actualUrl));
    }

    @Step("checked parameters on the searching result screen")
    public void assertSearchParametersShownCorrect(RoomRequest expectedParameters, RoomRequest actualParameters) {
        Assert.assertEquals(expectedParameters, actualParameters,
                String.format("expected parameters aren't shown correct %s, actual parameters are %s",
                        expectedParameters, actualParameters));
        log.info(String.format("are shown correct parameters\n expected parameters are: %s\n actual parameters are:   %s",
                expectedParameters, actualParameters));
    }

    @Step("checked parameters on the searching result screen")
    public void assertSortingByPriceIsCorrect(List<Integer> actualList) {
        List<Integer> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList);
        Assert.assertEquals(expectedList, actualList,
                String.format("sorting isn't correct, expected list: %s\n actual list: %s",
                        expectedList, actualList));
        log.info(String.format("sorting was made correctly\n expected list: %s\n actual list:   %s",
                expectedList, actualList));
    }
}
