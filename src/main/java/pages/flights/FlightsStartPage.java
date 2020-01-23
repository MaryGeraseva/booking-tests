package pages.flights;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.common.BasePageObject;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class FlightsStartPage extends BasePageObject {

    private String url = "https://booking.kayak.com/";

    private By departureInput = By.xpath("//input[@aria-label='Flight origin input'][@placeholder='From where?']");
    private By arrivalInput = By.xpath("//input[@aria-label='Flight destination input'][@placeholder='To where?']");
    private By datePicker = By.cssSelector("div.Common-Widgets-Datepicker-DateRangeInput.noBorderRemoval");
    private By searchButton = By.xpath("//div[@class='fieldBlock buttonBlock']/button");

    public String getUrl() {
        return url;
    }

    public FlightsSearchingPage searchFlights(String departure, String arrival,
                                              LocalDate departureDate, LocalDate arrivalDate) {
        setDeparture(departure);
        setArrival(arrival);
        setDates(departureDate, arrivalDate);
        clickSearchButton();
        return new FlightsSearchingPage();
    }

    private void setDeparture(String departure) {
        click(departureInput);
        type(departureInput, departure);
        sendKey(departureInput, Keys.ENTER);
    }

    private void setArrival(String arrival) {
        click(arrivalInput);
        type(arrivalInput, arrival);
        sendKey(arrivalInput, Keys.ENTER);
    }

    private void setDates(LocalDate departureDate, LocalDate arrivalDate) {
        click(datePicker);
        setDate(departureDate);
        click(datePicker);
        setDate(arrivalDate);
    }

    private void setDate(LocalDate date) {
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String day = Integer.toString(date.getDayOfMonth());
        String locator = String.format("//div[@aria-label='%s %s']", month, day);
        System.out.println(locator);
        click(By.xpath(locator));
    }

    private void clickSearchButton() {
        click(searchButton);
    }
}
