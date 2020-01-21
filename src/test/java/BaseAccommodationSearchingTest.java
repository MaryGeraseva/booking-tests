import models.AccommodationRequest;
import pages.AccommodationSearchingPage;
import pages.StartPage;

public class BaseAccommodationSearchingTest extends BaseTest {

    protected AccommodationSearchingPage getAccommodationSearchingResults(AccommodationRequest accommodationRequest) {
        StartPage startPage = new StartPage();
        startPage.open();
        assertions.urlIsCorrect(startPage.getUlr());
        startPage.setEnglishLanguage();
        startPage.setDestination(accommodationRequest.getDestination());
        startPage.setDates(accommodationRequest.getCheckInDate(), accommodationRequest.getCheckOutDate());
        startPage.setAdults(accommodationRequest.getAdults());
        if (!accommodationRequest.getChildren().isEmpty()) {
            startPage.setChildren(accommodationRequest.getChildren());
        }
        startPage.setRooms(accommodationRequest.getRooms());
        startPage.selectTravelForWork(accommodationRequest.isTravelForWork());
        return startPage.launchSearch();
    }
}