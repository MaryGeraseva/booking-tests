package accommodationTests;

import common.utils.DateTimeUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.accommodation.AccommodationRequest;
import models.accommodation.Child;
import org.testng.annotations.Test;
import pages.accommodation.AccommodationSearchingPage;

import java.time.LocalDate;
import java.util.Collections;

public class AccommodationSortingTests extends BaseAccommodationSearchingTest {

    @Test
    @Step("accommodationSortingByPriceTest started")
    @Description(value = "The test checks sorting accommodation by price (lowest first)")
    public void accommodationSortingByPriceTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);
        AccommodationRequest request = new AccommodationRequest("Paris", checkIn, checkOut, 2,
                Collections.singletonList(new Child(5)), 1, false);
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        searchingPage.sortByPrice();
        assertions.assertSortingByPriceIsCorrect(searchingPage.getPriceList());
    }

    @Test
    @Step("accommodationSortingByTypeTest started")
    @Description(value = "The test checks sorting accommodation by type (show homes first)")
    public void accommodationSortingByTypeTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);
        AccommodationRequest request = new AccommodationRequest("London", checkIn, checkOut, 2,
                Collections.emptyList(), 1, false);
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        searchingPage.sortByType();
        assertions.assertSortingByTypeIsCorrect(searchingPage.getTypeList());
    }
}
