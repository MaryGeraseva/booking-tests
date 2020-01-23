package accommodationTests;

import common.BaseTest;
import models.accommodstion.AccommodationRequest;
import pages.accommodation.AccommodationSearchingPage;
import pages.common.StartPage;

public class BaseAccommodationSearchingTest extends BaseTest {

    protected AccommodationSearchingPage getAccommodationSearchingResults(AccommodationRequest request) {
        StartPage startPage = new StartPage();
        startPage.open();
        assertions.urlIsCorrect(startPage.getUlr());
        startPage.setEnglishLanguage();
        startPage.setDestination(request.getDestination());
        startPage.setDates(request.getCheckInDate(), request.getCheckOutDate());
        startPage.setAdults(request.getAdults());
        if (!request.getChildren().isEmpty()) {
            startPage.setChildren(request.getChildren());
        }
        startPage.setRooms(request.getRooms());
        startPage.selectTravelForWork(request.isTravelForWork());
        return startPage.launchSearch();
    }
}
