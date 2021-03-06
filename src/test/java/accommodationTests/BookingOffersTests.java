package accommodationTests;

import common.utils.DateTimeUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.accommodation.AccommodationRequest;
import models.accommodation.Child;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.accommodation.AccommodationBookingPage;
import pages.accommodation.AccommodationSearchingPage;

import java.time.LocalDate;
import java.util.Collections;

public class BookingOffersTests extends BaseAccommodationSearchingTest {

    @DataProvider(name = "bookingOffers")
    private static Object[][] data() {
        LocalDate currentDate = LocalDate.now();
        LocalDate checkIn = DateTimeUtils.getInWeeksDate(1);
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);
        return new Object[][]{
                {"1", new AccommodationRequest("Barcelona", currentDate, checkOut, 1,
                        Collections.emptyList(), 1, true), 0},
                {"2", new AccommodationRequest("Rome", currentDate, checkOut, 4,
                        Collections.emptyList(), 2, false), 1},
                {"3", new AccommodationRequest("Riga", checkIn, checkOut, 2,
                        Collections.singletonList(new Child(5)), 2, false), 2}
        };
    }

    @Test(dataProvider = "bookingOffers")
    @Step("bookingOffersParametersTest started")
    @Description(value = "The test checks that accommodation are available for desired searching parameters: dates, price")
    public void bookingOffersParametersTest(String testId, AccommodationRequest request, int offerIndex) {
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        searchingPage.filterByAvailability();
        double expectedPrice = searchingPage.getPriceList().get(offerIndex);
        AccommodationBookingPage bookingPage = searchingPage.getOfferPage(offerIndex);
        assertions.assertDates(request, bookingPage.getCheckIn(), bookingPage.getCheckOut());
        assertions.assertPrice(request, bookingPage.getPriceList(), expectedPrice);
    }

    @Test(dataProvider = "bookingOffers")
    @Step("reservationNotAllowedTest started")
    @Description(value = "The test checks that reservation couldn't be started if quantity of rooms not selected")
    public void reservationNotAllowedTest(String testId, AccommodationRequest request, int offerIndex) {
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        searchingPage.filterByAvailability();
        AccommodationBookingPage bookingPage = searchingPage.waitUpdatingAndGetOfferPage(offerIndex);
        bookingPage.clickReserveButton();
        assertions.assertReserveButtonIsDisable(bookingPage.reserveButtonIsDisable());
    }
}
