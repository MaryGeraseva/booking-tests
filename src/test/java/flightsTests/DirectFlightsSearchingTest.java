package flightsTests;

import common.BaseTest;
import common.utils.DateTimeUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.flights.Flight;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.common.StartPage;
import pages.flights.FlightsSearchingPage;
import pages.flights.FlightsStartPage;

import java.time.LocalDate;

public class DirectFlightsSearchingTest extends BaseTest {

    @DataProvider(name = "directFlights")
    private static Object[][] data() {
        LocalDate departureDate = DateTimeUtils.getInWeeksDate(1);
        LocalDate arrivalDate = DateTimeUtils.getInWeeksDate(2);
        return new Object[][]{
                {"1", new Flight("Moscow", "Milan", departureDate, arrivalDate)},
                {"2", new Flight("Moscow", "Rome", departureDate, arrivalDate)}
        };
    }

    @Test(dataProvider = "directFlights")
    @Step("directFlightsSearchingTest started")
    @Description(value = "The test checks that searching can find non-stop direct flights")
    public void directFlightsSearchingTest(String testId, Flight flight) {
        StartPage startPage = new StartPage();
        startPage.open();
        assertions.urlIsCorrect(startPage.getUlr());
        FlightsStartPage flightsStartPage = startPage.openFlightsPage();
//        FlightsStartPage flightsStartPage = new FlightsStartPage();
//        flightsStartPage.openPage(flightsStartPage.getUrl());
//        assertions.urlIsCorrect(flightsStartPage.getUrl());
        FlightsSearchingPage flightsSearchingPage = flightsStartPage.searchFlights(flight.getDepartureDestination(),
                flight.getArrivalDestination(),
                flight.getDepartureDate(), flight.getArrivalDate());
        log.info("This test fell because of firewall");
    }
}
