package accommodationTests;

import common.DateTimeUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.accommodation.AccommodationRequest;
import models.accommodation.Child;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.accommodation.AccommodationSearchingPage;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AccommodationFiltrationTests extends BaseAccommodationSearchingTest {

    @DataProvider(name = "accommodationFilters")
    private static Object[][] data() {
        LocalDate checkIn = DateTimeUtils.getInWeeksDate(1);
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);
        return new Object[][]{
                {"1", new AccommodationRequest("Barcelona", checkIn, checkOut, 1,
                        Collections.emptyList(), 1, true), 0},
                {"2", new AccommodationRequest("Rome", checkIn, checkOut, 4,
                        Collections.emptyList(), 2, false), 1},
                {"3", new AccommodationRequest("Minsk", checkIn, checkOut, 2,
                        Collections.singletonList(new Child(5)), 1, false), 2},
                {"4", new AccommodationRequest("Milan", checkIn, checkOut, 3,
                        Collections.emptyList(), 3, true), 3},
                {"5", new AccommodationRequest("Riga", checkIn, checkOut, 2,
                        Collections.emptyList(), 1, false), 4}
        };
    }

    @Test(dataProvider = "accommodationFilters")
    @Step("accommodationFiltrationByReviewTest started")
    @Description(value = "The test checks filtration accommodation by review score")
    public void accommodationFiltrationByReviewTest(String testId, AccommodationRequest request,
                                                    int indexReviewInList) {
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        String reviewScoreLabel = searchingPage.getReviewScoreCategory(indexReviewInList);
        searchingPage.setReviewScore(indexReviewInList);
        if (!reviewScoreLabel.equals("No rating")) {
            List<Double> reviewBadgeList = searchingPage.getReviewBadgeList();
            assertions.assertFiltrationByReviewScoreIsCorrect(reviewBadgeList, reviewScoreLabel);
        } else {
            assertions.assertFiltrationByReviewScoreIsCorrect(searchingPage.isAccommodationReviewLabelPresent(),
                    reviewScoreLabel);
        }
    }

    @Test(dataProvider = "accommodationFilters")
    @Step("accommodationFiltrationByPriceTest started")
    @Description(value = "The test checks filtration accommodation by price")
    public void accommodationFiltrationByPriceTest(String testId, AccommodationRequest request,
                                                   int indexReviewInList) {
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        double[] priceRange = searchingPage.getPriceRange(indexReviewInList);
        searchingPage.setPriceRange(indexReviewInList);
        searchingPage.filterByAvailability();
        List<Double> priceList = searchingPage.getPriceList();
        assertions.assertFiltrationByPriceIsCorrect(priceList, priceRange,
                request.getCheckInDate(), request.getCheckOutDate());
    }
}
