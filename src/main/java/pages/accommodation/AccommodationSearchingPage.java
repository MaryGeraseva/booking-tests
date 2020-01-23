package pages.accommodation;

import io.qameta.allure.Step;
import models.accommodstion.AccommodationRequest;
import models.accommodstion.Child;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.common.BasePageObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccommodationSearchingPage extends BasePageObject {

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
    private By reviewScoreCheckbox = By.cssSelector("div#filter_review div.bui-checkbox__label");
    private By reviewScoreBadge = By.cssSelector("div.bui-review-score__badge");
    private By placementRecommendationLabel = By.cssSelector("h4.sr-group-recommendation__title");
    private By recommendedSortingTab = By.xpath("//a[@data-category=\"popularity\"]");
    private By availabilityCheckbox = By.cssSelector("div#filter_out_of_stock div.bui-checkbox__label");
    private By seeAvailabilityButton = By.cssSelector("a.sr_cta_button");


    @Step("got the search parameters on the searching result screen")
    public AccommodationRequest getSearchParameters() {
        List<Child> children = new ArrayList<>();
        if (!getSelectedOptionText(childrenInput).contains("No children")) {
            children = getChildrenList();
        }
        return new AccommodationRequest(getDestination(), getDate(checkInInput), getDate(checkOutInput),
                getAdultsQuantity(adultsInput), children, getRoomsQuantity(), isCheckboxSelected(travelForCheckbox));
    }

    //getting search parameters methods
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
        return getAttribute(destinationInput, "value");
    }

    //sorting test methods
    @Step("clicked sort by price tab")
    public void sortByPrice() {
        click(sortByPriceTab);
    }

    @Step("clicked show home first tab")
    public void sortByType() {
        click(sortByTypeTab);
    }

    @Step("got the list of presented prices")
    public List<Integer> getPriceList() {
        List<WebElement> accommodationPrice = getAccommodationParametersAsList(accommodationPriceLabel);
        List<Integer> prices = new ArrayList<>();
        for (WebElement accommodation: accommodationPrice) {
            prices.add(Integer.parseInt(accommodation.getText().replaceAll("[^0-9.]", "")));
        }
        log.info(String.format("got list of accommodation prices: \n%s", prices));
        return prices;
    }

    @Step("got the list of presented accommodation types")
    public List<String> getTypeList() {
        List<WebElement> accommodationTypes = getAccommodationParametersAsList(accommodationTypeLabel);
        List<String> types = new ArrayList<>();
        for (WebElement accommodation: accommodationTypes) {
            types.add(accommodation.getText());
        }
        log.info(String.format("got list of accommodation types: \n%s", types));
        return types;
    }

    private List<WebElement> getAccommodationParametersAsList(By parametersLocator) {
        waitUpdating(parametersLocator);
        return findAllElements(parametersLocator);
    }

    private void waitUpdating(By locator) {
        waitForVisibilityOf(By.cssSelector("div.sr-usp-overlay__loading"), 10);
        waitForInvisibilityOf(By.cssSelector("div.sr-usp-overlay__loading"), 10);
        waitForVisibilityOf(locator, 12);
    }

    //filtration tests methods
    private List<WebElement> getReviewCheckboxesList() {
        return findAllElements(reviewScoreCheckbox);
    }


    @Step("got the list of presented accommodation review scores")
    public List<Double> getReviewBadgeList() {
        List<WebElement> reviewBadgeList = getAccommodationParametersAsList(reviewScoreBadge);
        List<Double> reviewBadgeValue = new ArrayList<>();
        for (WebElement reviewBadge: reviewBadgeList) {
            reviewBadgeValue.add(Double.parseDouble(reviewBadge.getText()));
        }
        log.info(String.format("got list of review scores: \n%s", reviewBadgeValue));
        return reviewBadgeValue;
    }

    @Step("click on the review score checkbox")
    public void setReviewScore(int indexReviewInList) {
        click(getReviewCheckboxesList().get(indexReviewInList));
    }

    @Step("checked is accommodation review scores presented in \"No rating\" category")
    public boolean isAccommodationReviewLabelPresent() {
        waitUpdating(accommodationBlock);
        if (driver.findElements(reviewScoreBadge).size() == 0) {
            return true;
        }
        return false;
    }

    @Step("click on the \"Only show available properties\" checkbox")
    public void filterByAvailability() {
        click(availabilityCheckbox);
    }

    //recommended sorting methods
    @Step("got recommended sorting title")
    public String getRecommendedSorting() {
        return getText(recommendedSortingTab).trim();
    }

    @Step("got placement recommendation labels")
    public List<String> getRecommendationLabels() {
        List<WebElement> recommendationLabels = findAllElements(placementRecommendationLabel);
        List<String> labels = new ArrayList<>();
        for (WebElement recommendationLabel: recommendationLabels) {
            labels.add(recommendationLabel.getText().trim());
        }
        log.info(String.format("got list of recommendation labels: \n%s", labels));
        return labels;
    }

    //booking offers methods
    private void clickSeeAvailabilityButton(int offerIndex) {
        List<WebElement> buttonList = findAllElements(seeAvailabilityButton);
        click(buttonList.get(offerIndex));
    }

    public AccommodationBookingPage getOfferPage(int offerIndex) {
        clickSeeAvailabilityButton(offerIndex);
        switchToTab(getWindowHeader());
        return new AccommodationBookingPage();
    }

    public AccommodationBookingPage waitUpdatingAndGetOfferPage(int offerIndex) {
        waitUpdating(accommodationBlock);
        return getOfferPage(offerIndex);
    }
}