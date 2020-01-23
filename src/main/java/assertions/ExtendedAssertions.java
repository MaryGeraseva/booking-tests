package assertions;

import common.drivers.Driver;
import common.logger.LogInstance;
import io.qameta.allure.Step;
import models.accommodstion.AccommodationRequest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ExtendedAssertions {

    protected Logger log = LogInstance.getLogger();
    protected WebDriver driver = Driver.getDriver();
    protected SoftAssert softAssert;

    @Step("checked the page by current url")
    public void urlIsCorrect(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl),
                String.format("the expected page with url %s wasn't opened, current url is %s", expectedUrl, actualUrl));
        log.info(String.format("checked the page by url %s", actualUrl));
    }

    @Step("checked parameters on the searching result screen")
    public void assertSearchParametersShownCorrect(AccommodationRequest expectedParameters, AccommodationRequest actualParameters) {
        Assert.assertEquals(expectedParameters, actualParameters,
                String.format("expected parameters aren't shown correct %s, actual parameters are %s",
                        expectedParameters, actualParameters));
        log.info(String.format("were shown correct parameters\n expected parameters are: %s\n actual parameters are:   %s",
                expectedParameters, actualParameters));
    }

    @Step("checked sorting accommodation by price")
    public void assertSortingByPriceIsCorrect(List<Double> actualList) {
        List<Double> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList);
        Assert.assertEquals(expectedList, actualList,
                String.format("sorting wasn't correct, expected list: %s\n actual list: %s",
                        expectedList, actualList));
        log.info(String.format("sorting was made correctly\n expected list: %s\n actual list:   %s",
                expectedList, actualList));
    }

    @Step("checked sorting accommodation by type")
    public void assertSortingByTypeIsCorrect(List<String> actualList) {
        for (String type: actualList) {
            Assert.assertTrue(type.contains("Apart"),
                    String.format("sorting wasn't correct, got: %s", type));
        }
        log.info(String.format("sorting by type was correct"));
    }

    @Step("checked filtration accommodation by review score")
    public void assertFiltrationByReviewScoreIsCorrect(List<Double> actualList, String reviewScoreLabel) {
        Integer reviewScoreInt = Integer.parseInt(reviewScoreLabel.replaceAll("[^0-9]", ""));
        Double reviewScoreCategory = Double.parseDouble(reviewScoreInt.toString());
        for (Double review: actualList) {
            Assert.assertTrue(review >= reviewScoreCategory,
                    String.format("filtration wasn't correct, actual review score: %s doesn't match to the category: %s",
                            review, reviewScoreLabel));
        }
        log.info(String.format("sorting by type was correct, all review scores match to the category: %s",
                reviewScoreLabel));
    }

    @Step("checked filtration accommodation by review score")
    public void assertFiltrationByReviewScoreIsCorrect(boolean isReviewBadgePresent, String reviewScoreLabel) {
        Assert.assertTrue(isReviewBadgePresent,
                String.format("filtration wasn't correct, actual review score doesn't match to the category: %s",
                        reviewScoreLabel));
        log.info(String.format("sorting by type was correct, all review scores match to the category: %s",
                reviewScoreLabel));
    }

    @Step("checked filtration accommodation by price")
    public void assertFiltrationByPriceIsCorrect(List<Double> priceList, double[] priceRange,
                                                 LocalDate checkIn, LocalDate checkOut) {
        for (double price : priceList) {
            System.out.println(DAYS.between(checkIn, checkOut));
            double pricePerDay = price/DAYS.between(checkIn, checkOut);
            Assert.assertTrue(isPriceInRange(pricePerDay, priceRange),
                    String.format("filtration wasn't correct, price %s doesn't match in range %s",
                            pricePerDay, priceRange));
        }
        log.info(String.format("sorting by type was correct, all review scores match to the range: %s",
                Arrays.toString(priceRange)));
    }

    private boolean isPriceInRange(double price, double[] priceRange) {
        if (priceRange.length == 1 && price >= priceRange[0]) return true;
        if (price >= priceRange[0] && price <= priceRange[1]) return true;
        return false;
    }

    @Step("checked recommendation by search parameters")
    public void assertRecommendationIsCorrect(String actualRecommendation, String expectedRecommendation) {
        Assert.assertEquals(actualRecommendation, expectedRecommendation,
                String.format("recommendation wasn't correct, expected: %s, but got %s",
                        expectedRecommendation, actualRecommendation));
        log.info(String.format("recommendation was correct, got: %s",
                actualRecommendation));
    }

    @Step("checked recommendation by search parameters")
    public void assertRecommendationIsCorrect(List<String> actualRecommendationList, String expectedRecommendation) {
        for (String recommendation: actualRecommendationList) {
            Assert.assertTrue(recommendation.contains(expectedRecommendation),
                    String.format("expected recommendation wasn't got,\n expected: %s, but got: %s",
                            expectedRecommendation, recommendation));
        }
        log.info(String.format("recommendation was correct, got: %s",
                expectedRecommendation));
    }

    @Step("checked checkIn/checkOut dates")
    public void assertDates(AccommodationRequest accommodationRequest, LocalDate actualCheckIn, LocalDate actualCheckOut) {
        softAssert = new SoftAssert();
        LocalDate expectedCheckIn = accommodationRequest.getCheckInDate();
        LocalDate expectedCheckOut = accommodationRequest.getCheckOutDate();
        softAssert.assertEquals(expectedCheckIn, actualCheckIn,
                String.format("got wrong checkIn date %s instead of %s", actualCheckIn, expectedCheckIn));
        softAssert.assertEquals(expectedCheckIn, actualCheckIn,
                String.format("got wrong checkOut date %s instead of %s", actualCheckOut, expectedCheckOut));
        log.info(String.format("checkIn/checkOut dates was shown correctly, checkIn: %s checkOut: %s",
                actualCheckIn, actualCheckOut));
    }

    @Step("checked booking price")
    public void assertPrice(AccommodationRequest accommodationRequest, List<Double> priceList, double expectedPrice) {
        softAssert = new SoftAssert();
        softAssert.assertTrue(priceList.contains(expectedPrice),
                String.format("didn't got expected price: %s", expectedPrice));
        log.info(String.format("price was offered correctly, got expected price: %s",
                expectedPrice));
    }

    @Step("checked reservation couldn't be started")
    public void assertReserveButtonIsDisable(boolean isButtonDisable) {
        Assert.assertTrue(isButtonDisable, "didn't get expected result, reserve button is enable");
        log.info("get expected result, reserve button is disable");
    }
}
