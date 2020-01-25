package accommodationTests;

import common.utils.DateTimeUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.accommodation.AccommodationRequest;
import models.accommodation.Child;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.accommodation.AccommodationSearchingPage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class PlacementRecommendationTests extends BaseAccommodationSearchingTest {

    @DataProvider(name = "recommendedSorting")
    private static Object[][] sortingData() {
        LocalDate currentDate = LocalDate.now();
        LocalDate checkIn = DateTimeUtils.getInWeeksDate(1);
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);
        return new Object[][]{
                {"1", new AccommodationRequest("Prague", currentDate, checkOut, 1,
                        Collections.emptyList(), 1, true), "Top picks for business travellers"},
                {"2", new AccommodationRequest("Rome", currentDate, checkOut, 2,
                        Collections.emptyList(), 1, false), "Our top picks"},
                {"3", new AccommodationRequest("Vilnius", checkIn, checkOut, 2,
                        Collections.singletonList(new Child(5)), 1, false),
                        "Top picks for families"},
                {"4", new AccommodationRequest("Vienna", currentDate, checkOut, 4,
                        Collections.emptyList(), 2, false),
                        "Top picks for groups"}
        };
    }

    @Test(dataProvider = "recommendedSorting")
    @Step("recommendedSortingTest started")
    @Description(value = "The test checks that recommended sorting by search parameters is shown correctly")
    public void recommendedSortingTest(String testId, AccommodationRequest request,
                                       String expectedRecommendedSorting) {
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        assertions.assertRecommendationIsCorrect(searchingPage.getRecommendedSorting(), expectedRecommendedSorting);
    }

    @DataProvider(name = "placementRecommendationLabels")
    private static Object[][] labelsData() {
        LocalDate currentDate = LocalDate.now();
        LocalDate checkIn = DateTimeUtils.getInWeeksDate(1);
        LocalDate checkOut = DateTimeUtils.getInWeeksDate(2);
        return new Object[][]{
                {"1", new AccommodationRequest("Vilnius", checkIn, checkOut, 2,
                        Collections.singletonList(new Child(5)), 1, false),
                        "Recommended for 2 adults, 1 child"},
                {"2", new AccommodationRequest("Vilnius", checkIn, checkOut, 1,
                        Arrays.asList(new Child(3), new Child(7)), 1, false),
                        "Recommended for 1 adult, 2 children"},
                {"3", new AccommodationRequest("Vienna", currentDate, checkOut, 4,
                        Collections.emptyList(), 2, false),
                        "Recommended for 4 adults"},
                {"4", new AccommodationRequest("Vienna", currentDate, checkOut, 3,
                        Collections.emptyList(), 2, false),
                        "Recommended for 3 adults"}
        };
    }

    @Test(dataProvider = "placementRecommendationLabels")
    @Step("recommendationLabelTest started")
    @Description(value = "The test checks that accommodation blocks have appropriate recommendation labels")
    public void recommendationLabelTest(String testId, AccommodationRequest request,
                                        String expectedRecommendationLabel) {
        AccommodationSearchingPage searchingPage = getAccommodationSearchingResults(request);
        assertions.assertRecommendationIsCorrect(searchingPage.getRecommendationLabels(), expectedRecommendationLabel);
    }

}