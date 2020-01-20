import common.TestUtilities;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.Child;
import models.RoomRequest;
import org.testng.annotations.Test;
import pages.RoomsSearchingPage;

import java.time.LocalDate;
import java.util.Arrays;

public class RoomsSortingTest extends BaseRoomSearchTest {

    @Test
    @Step("roomsSortingByPriceTest started")
    @Description(value = "The test checks sorting rooms searching results by price (lowest first)")
    public void roomsSortingByPriceTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = TestUtilities.getInWeeksDate(2);
        RoomRequest roomRequest = new RoomRequest("Paris", checkIn, checkOut, 2,
                Arrays.asList(new Child(5)), 1, false);
        RoomsSearchingPage roomsSearchingPage = getRoomSearchResults(roomRequest);
        roomsSearchingPage.sortByPrice();
        assertions.assertSortingByPriceIsCorrect(roomsSearchingPage.getPriceList());
    }

    @Test
    @Step("roomsSortingByTypeTest started")
    @Description(value = "The test checks sorting rooms searching results by type (show homes first)")
    public void roomsSortingByTypeTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = TestUtilities.getInWeeksDate(2);
        RoomRequest roomRequest = new RoomRequest("Amsterdam", checkIn, checkOut, 2,
                Arrays.asList(), 1, false);
        RoomsSearchingPage roomsSearchingPage = getRoomSearchResults(roomRequest);
        roomsSearchingPage.sortByPrice();
        assertions.assertSortingByPriceIsCorrect(roomsSearchingPage.getPriceList());
    }
}
