package accommodationTests;

import common.utils.DateTimeUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.accommodation.AccommodationRequest;
import models.accommodation.Child;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.accommodation.AccommodationSearchingPage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class AccommodationSearchingTests extends BaseAccommodationSearchingTest {

    @DataProvider(name = "accommodationRequests")
    private static Object[][] data() {
        LocalDate currentDate = LocalDate.now();
        LocalDate checkIn = DateTimeUtils.getInWeeksDate(1);
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);

        return new Object[][]{
                {"1", new AccommodationRequest("Barcelona", currentDate, checkOut, 1,
                        Collections.emptyList(), 1, true)},
                {"2", new AccommodationRequest("Rome", currentDate, checkOut, 4,
                        Collections.emptyList(), 2, true)},
                {"3", new AccommodationRequest("Minsk", checkIn, checkOut, 2,
                        Collections.singletonList(new Child(5)), 1, false)},
                {"4", new AccommodationRequest("Milan", checkIn, checkOut, 2,
                        Arrays.asList(new Child(0), new Child(17)), 2, false)}
        };
    }

    @Test(dataProvider = "accommodationRequests")
    @Step("searchParametersInputTest started")
    @Description(value = "The test checks that search parameters are correctly shown on the searching result screen")
    public void searchParametersInputTest(String testId, AccommodationRequest request) {
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        assertions.assertSearchParametersShownCorrect(request, searchingPage.getSearchParameters());
    }

    @Test
    @Step("childrenPlacementValidationTest started")
    @Description(value = "The test checks the possibility of placing children in separate rooms without adults")
    public void childrenPlacementValidationTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);
        AccommodationRequest request = new AccommodationRequest("Madrid", checkIn, checkOut, 1,
                Arrays.asList(new Child(2), new Child(16)), 2, false);
        AccommodationRequest expectedResult = new AccommodationRequest("Madrid", checkIn, checkOut, 1,
                Arrays.asList(new Child(2), new Child(16)), 1, false);

        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        assertions.assertSearchParametersShownCorrect(expectedResult, searchingPage.getSearchParameters());
    }
}
