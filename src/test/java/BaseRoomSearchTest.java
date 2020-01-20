import models.RoomRequest;
import pages.RoomsSearchingPage;
import pages.StartPage;

public class BaseRoomSearchTest extends BaseTest {

    protected RoomsSearchingPage getRoomSearchResults(RoomRequest roomRequest) {
        StartPage startPage = new StartPage();
        startPage.open();
        assertions.url(startPage.getUlr());
        startPage.setEnglishLanguage();
        startPage.setDestination(roomRequest.getDestination());
        startPage.setDates(roomRequest.getCheckInDate(), roomRequest.getCheckOutDate());
        startPage.setAdults(roomRequest.getAdults());
        if (!roomRequest.getChildren().isEmpty()) {
            startPage.setChildren(roomRequest.getChildren());
        }
        startPage.setRooms(roomRequest.getRooms());
        startPage.selectTravelForWork(roomRequest.isTravelForWork());
        return startPage.launchSearch();
    }
}
