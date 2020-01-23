package accommodationTests;

import common.TestUtilities;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.accommodstion.AccommodationRequest;
import models.accommodstion.Child;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.accommodation.AccommodationSearchingPage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AccommodationFiltrationTests extends BaseAccommodationSearchingTest {

    @DataProvider(name = "accommodationReviewFilters")
    private static Object[][] data() {
        LocalDate currentDate = LocalDate.now();
        LocalDate checkIn = TestUtilities.getInWeeksDate(1);
        LocalDate checkOut = TestUtilities.getInWeeksDate(2);
        return new Object[][]{
                {"1", new AccommodationRequest("Barcelona", currentDate, checkOut, 1,
                        Arrays.asList(), 1, true), 0, "Superb: 9+"},
                {"2", new AccommodationRequest("Rome", currentDate, checkOut, 4,
                        Arrays.asList(), 2, false), 1, "Very good: 8+"},
                {"3", new AccommodationRequest("Minsk", checkIn, checkOut, 2,
                        Arrays.asList(new Child(5)), 1, false), 2, "Good: 7+"},
                {"4", new AccommodationRequest("Milan", currentDate, checkOut, 3,
                        Arrays.asList(), 3, true), 3, "Pleasant: 6+"},
                {"5", new AccommodationRequest("Riga", currentDate, checkOut, 2,
                        Arrays.asList(), 1, false), 4, "No rating"}
        };
    }

    @Test(dataProvider = "accommodationReviewFilters")
    @Step("accommodationFiltrationByReviewTest started")
    @Description(value = "The test checks filtration accommodation by review score")
    public void accommodationFiltrationByReviewTest(String testId, AccommodationRequest accommodationRequest,
                                                    int indexReviewInList, String reviewScoreLabel) {
        AccommodationSearchingPage accommodationSearchingPage = getAccommodationSearchingResults(accommodationRequest);
        accommodationSearchingPage.setReviewScore(indexReviewInList);

        if (!reviewScoreLabel.equals("No rating")) {
            List<Double> reviewBadgeList = accommodationSearchingPage.getReviewBadgeList();
            assertions.assertFiltrationByReviewScoreIsCorrect(reviewBadgeList, reviewScoreLabel);
        } else {
            assertions.assertFiltrationByReviewScoreIsCorrect(accommodationSearchingPage.isAccommodationReviewLabelPresent(),
                    reviewScoreLabel);
        }
    }
}
