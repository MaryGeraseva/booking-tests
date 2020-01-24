package pages.accommodation;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.BasePageObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccommodationBookingPage extends BasePageObject {

    private By priceLabel = By.cssSelector("div.bui-price-display__value");
    private By checkInLabel = By.id("av-summary-checkin");
    private By checkOutLabel = By.id("av-summary-checkout");
    private By reserveButton = By.xpath("//span[contains(text(),\"I'll reserve\")]");

    @Step("got the list of presented prices")
    public List<Double> getPriceList() {
        List<WebElement> priceLabels = findAllElements(priceLabel);
        List<Double> prices = new ArrayList<>();
        for (WebElement price : priceLabels) {
            String text = price.getText().replaceAll("[^0-9.]", "");
            if (!text.isEmpty()) {
                prices.add(Double.parseDouble(text));
            }
        }
        log.info(String.format("got list of booking prices: \n%s", prices));
        return prices;
    }

    public LocalDate getCheckIn() {
        return getDate(checkInLabel);
    }

    public LocalDate getCheckOut() {
        return getDate(checkOutLabel);
    }

    private LocalDate getDate(By locator) {
        String checkInString = getText(locator);
        String checkInDate = checkInString.substring(checkInString.indexOf(" ") + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(checkInDate, formatter);
        System.out.println(date);
        return date;
    }

    @Step("clicked on the reserve button")
    public void clickReserveButton() {
        click(reserveButton);
    }

    @Step("checked is the reserve button disabled")
    public boolean reserveButtonIsDisable() {
        return findElement(By.xpath("//button[@data-title='Select your accommodation first']")).isEnabled();
    }
}
