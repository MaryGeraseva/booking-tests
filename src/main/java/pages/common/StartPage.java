package pages.common;

import io.qameta.allure.Step;
import models.accommodation.Child;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.accommodation.AccommodationSearchingPage;
import pages.flights.FlightsStartPage;

import java.time.LocalDate;
import java.util.List;

public class StartPage extends BasePageObject {

    private String ulr = "https://www.booking.com";
    //localisation menu
    private By languageLink = By.xpath("//a[@class='popover_trigger']");
    private By englishUKLink = By.cssSelector("li.lang_en-gb  a");
    //search box
    private By destinationInputField = By.id("ss");
    private By datePicker = By.cssSelector("div.xp__dates");
    private By currentMonth = By.xpath("//div[@class='bui-calendar__wrapper'][1]//tbody");
    private By nextMonth = By.xpath("//div[@class='bui-calendar__wrapper'][2]//tbody");
    private By guestsPicker = By.cssSelector("div.xp__guests");
    private By adultsStepperDisplay = By.cssSelector("div.sb-group__field-adults span.bui-stepper__display");
    private By adultsStepperAdd = By.cssSelector("div.sb-group__field-adults button.bui-stepper__add-button");
    private By adultsStepperSubtract = By.cssSelector("div.sb-group__field-adults button.bui-stepper__subtract-button");
    private By childrenStepperDisplay = By.cssSelector("div.sb-group-children span.bui-stepper__display");
    private By childrenStepperSubtract = By.cssSelector("div.sb-group-children button.bui-stepper__subtract-button");
    private By childrenStepperAdd = By.cssSelector("div.sb-group-children button.bui-stepper__add-button");
    private By roomStepperDisplay = By.cssSelector("div.sb-group__field-rooms span.bui-stepper__display");
    private By roomStepperSubtract = By.cssSelector("div.sb-group__field-rooms button.bui-stepper__subtract-button");
    private By roomStepperAdd = By.cssSelector("div.sb-group__field-rooms button.bui-stepper__add-button");
    private By ageDropdown = By.cssSelector("select[name=age]");
    private By forWorkTravelCheckbox = By.cssSelector("div.xp__travel-purpose");
    private By searchButton = By.cssSelector("button.sb-searchbox__button");
    //additional services
    private By flightsLink = By.xpath("//a[@data-decider-header=\"flights\"]");

    public void open() {
        openPage(ulr);
    }

    @Step("set English language")
    public void setEnglishLanguage() {
        click(languageLink);
        click(englishUKLink);
    }

    @Step("set the destination")
    public void setDestination(String destination) {
        type(destinationInputField, destination);
    }

    @Step("set checkIn/checkOut dates")
    public void setDates(LocalDate checkInDate, LocalDate checkOutDate) {
        click(datePicker);
        setDate(checkInDate);
        setDate(checkOutDate);
        log.info(String.format("set checkIn %s >> checkOut %s", checkInDate, checkOutDate));
    }

    private void setDate(LocalDate date) {
        String locator = String.format("//td[@data-date='%s']", date);
        click(By.xpath(locator));
    }

    @Step("set the adults quantity")
    public void setAdults(int adults) {
        click(guestsPicker);
        //default number of adults is [2]
        if (adults > 2) {
            multipleClicks(adultsStepperAdd, adults - 2);
        } else if (adults < 2) {
            multipleClicks(adultsStepperSubtract, 1);
        }
    }

    @Step("set children quantity and age")
    public void setChildren(List<Child> children) {
        //default number of children is [0]
        multipleClicks(childrenStepperAdd, children.size());

        List<WebElement> childrenAgeDropdownList = findAllElements(ageDropdown);
        int childrenNumber = 0;

        for (WebElement childrenAgeDropdown : childrenAgeDropdownList) {
            click(childrenAgeDropdown);
            //children age options [0...17] start from index [1]
            selectOptionByIndex(childrenAgeDropdown, children.get(childrenNumber).getAge() + 1);
            childrenNumber++;
        }
    }

    @Step("set rooms quantity")
    public void setRooms(int rooms) {
        //default number of rooms is [1]
        multipleClicks(roomStepperAdd, rooms - 1);
    }

    @Step("set travel for work option")
    public void selectTravelForWork(Boolean travelForWork) {
        if (travelForWork) {
            selectCheckbox(forWorkTravelCheckbox);
        }
    }

    @Step("launched accommodation search")
    public AccommodationSearchingPage launchSearch() {
        click(searchButton);
        return new AccommodationSearchingPage();
    }

    public String getUlr() {
        return ulr;
    }

    public FlightsStartPage openFlightsPage() {
        setEnglishLanguage();
        click(flightsLink);
        return new FlightsStartPage();
    }
}
