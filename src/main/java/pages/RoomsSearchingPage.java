package pages;

import io.qameta.allure.Step;
import models.Child;
import models.RoomRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RoomsSearchingPage extends BasePageObject {

    private By destinationInput = By.id("ss");
    private By checkInInput = By.cssSelector("div.--checkin-field div.sb-date-field__display");
    private By checkOutInput = By.cssSelector("div.--checkout-field div.sb-date-field__display");
    private By adultsInput = By.id("group_adults");
    private By childrenInput = By.id("group_children");
    private By roomsInput = By.id("no_rooms");
    private By ageDropdown = By.cssSelector("select[name=age]");
    private By travelForCheckbox = By.cssSelector("input[name=sb_travel_purpose]");
    private By accommodationBlock = By.cssSelector("div.sr_property_block");
    private By accommodationPriceLabel = By.cssSelector("div.bui-price-display__value");
    private By sortByPriceTab = By.xpath("//a[@data-category=\"price\"]");
    private By accommodationTypeLabel = By.cssSelector("span.bh-property-type");
    private By sortByTypeTab = By.xpath("//a[@data-category=\"upsort_bh\"]");


    @Step("got the search parameters on the searching result screen")
    public RoomRequest getSearchParameters() {
        List<Child> children = new ArrayList<>();
        if (!getSelectedOptionText(childrenInput).contains("No children")) {
            children = getChildrenList();
        }
        return new RoomRequest(getDestination(), getDate(checkInInput), getDate(checkOutInput),
                getAdultsQuantity(adultsInput), children, getRoomsQuantity(), isCheckboxSelected(travelForCheckbox));
    }

    private int getRoomsQuantity() {
        return Integer.parseInt(splitAdditionalText(getSelectedOptionText(roomsInput)));
    }

    private List<Child> getChildrenList() {
        List<WebElement> childrenAgeDropdownList = findAllElements(ageDropdown);
        List<Child> children = new ArrayList<>();
        for (WebElement childrenAgeDropdown : childrenAgeDropdownList) {
            int age = Integer.parseInt(splitAdditionalText(getSelectedOptionText(childrenAgeDropdown)));
            children.add(new Child(age));
        }
        return children;
    }

    private int getAdultsQuantity(By adultsInput) {
        return Integer.parseInt(splitAdditionalText(getSelectedOptionText(adultsInput)));
    }

    private String splitAdditionalText(String text) {
        return text.split(" ")[0];
    }

    private LocalDate getDate(By locator) {
        String checkInString = getText(locator);
        String checkInDate = checkInString.substring(checkInString.indexOf(" ") + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(checkInDate, formatter);
        return date;
    }

    private String getDestination() {
        return findElement(destinationInput).getAttribute("value");
    }

    public void sortByPrice() {
        click(sortByPriceTab);
    }

    public void sortByType() {
        click(sortByTypeTab);
    }

    public List<Integer> getPriceList() {
        List<WebElement> accommodationPrice = getAccommodationParametersAsList(accommodationPriceLabel);
        List<Integer> prices = new ArrayList<>();
        for (WebElement accommodation: accommodationPrice) {
            prices.add(Integer.parseInt(accommodation.getText().replaceAll("[^0-9.]", "")));
        }
        System.out.println(prices);
        return prices;
    }

    public List<String> getAccommodationTypeList() {
        List<WebElement> accommodationTypes = getAccommodationParametersAsList(accommodationTypeLabel);
        List<String> types = new ArrayList<>();
        for (WebElement accommodation: accommodationTypes) {
            types.add(accommodation.getText());
        }
        System.out.println(types);
        return types;
    }

    private List<WebElement> getAccommodationParametersAsList(By parametersLocator) {
        waitForVisibilityOf(By.cssSelector("div.sr-usp-overlay__loading"), 10);
        waitForInvisibilityOf(By.cssSelector("div.sr-usp-overlay__loading"), 10);
        waitForVisibilityOf(parametersLocator, 12);
        return findAllElements(parametersLocator);
    }
}
