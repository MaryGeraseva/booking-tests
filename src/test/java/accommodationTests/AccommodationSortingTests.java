package accommodationTests;

import common.TestUtilities;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.accommodstion.Child;
import models.accommodstion.AccommodationRequest;
import org.testng.annotations.Test;
import pages.accommodation.AccommodationSearchingPage;

import java.time.LocalDate;
import java.util.Arrays;

public class AccommodationSortingTests extends BaseAccommodationSearchingTest {

    @Test
    @Step("accommodationSortingByPriceTest started")
    @Description(value = "The test checks sorting accommodation by price (lowest first)")
    public void accommodationSortingByPriceTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = TestUtilities.getInWeeksDate(2);
        AccommodationRequest accommodationRequest = new AccommodationRequest("Paris", checkIn, checkOut, 2,
                Arrays.asList(new Child(5)), 1, false);
        AccommodationSearchingPage accommodationSearchingPage = getAccommodationSearchingResults(accommodationRequest);
        accommodationSearchingPage.sortByPrice();
        assertions.assertSortingByPriceIsCorrect(accommodationSearchingPage.getPriceList());
    }

    @Test
    @Step("accommodationSortingByTypeTest started")
    @Description(value = "The test checks sorting accommodation by type (show homes first)")
    public void accommodationSortingByTypeTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = TestUtilities.getInWeeksDate(2);
        AccommodationRequest accommodationRequest = new AccommodationRequest("Lida", checkIn, checkOut, 2,
                Arrays.asList(), 1, false);
        AccommodationSearchingPage accommodationSearchingPage = getAccommodationSearchingResults(accommodationRequest);
        accommodationSearchingPage.sortByType();
        assertions.assertSortingByTypeIsCorrect(accommodationSearchingPage.getTypeList());
    }
}
