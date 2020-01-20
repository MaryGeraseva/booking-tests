import common.TestUtilities;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.Child;
import models.RoomRequest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RoomsSearchingPage;

import java.time.LocalDate;
import java.util.Arrays;

public class RoomSearchPositiveTest extends BaseRoomSearchTest {

    @DataProvider(name = "roomRequests")
    private static Object[][] files(){
        LocalDate currentDate = LocalDate.now();
        LocalDate checkIn = TestUtilities.getInWeeksDate(1);
        LocalDate checkOut = TestUtilities.getInWeeksDate(2);

        return new Object[][]{
                {"1", new RoomRequest("Barcelona", currentDate, checkOut, 1,
                Arrays.asList(), 1, true)},
                {"2", new RoomRequest("Rome", currentDate, checkOut, 4,
                        Arrays.asList(), 2, true)},
                {"3", new RoomRequest("Minsk", checkIn, checkOut, 2,
                        Arrays.asList(new Child(5)), 1, false)},
                {"4", new RoomRequest("Milan", checkIn, checkOut, 2,
                        Arrays.asList(new Child(0), new Child(17)), 2, false)}
        };
    }

    @Test(dataProvider = "roomRequests")
    @Step("searchPositiveTest started")
    @Description(value = "The test checks that search parameters are correctly shown on the searching result screen")
    public void searchPositiveTest(String testId, RoomRequest roomRequest) {
        RoomsSearchingPage roomsSearchingPage = getRoomSearchResults(roomRequest);
        assertions.assertSearchParametersShownCorrect(roomRequest, roomsSearchingPage.getSearchParameters());
    }

    @Test
    @Step("childrenPlacementValidationTest started")
    @Description(value = "The test checks the possibility of placing children in separate rooms without adults")
    public void childrenPlacementValidationTest() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = TestUtilities.getInWeeksDate(2);
        RoomRequest roomRequest = new RoomRequest("Madrid", checkIn, checkOut, 1,
                Arrays.asList(new Child(2), new Child(16)), 2, false);
        RoomRequest expectedResult = new RoomRequest("Madrid", checkIn, checkOut, 1,
                Arrays.asList(new Child(2), new Child(16)), 1, false);

        RoomsSearchingPage roomsSearchingPage = getRoomSearchResults(roomRequest);
        assertions.assertSearchParametersShownCorrect(expectedResult, roomsSearchingPage.getSearchParameters());
    }
}
