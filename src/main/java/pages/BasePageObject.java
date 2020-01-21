package pages;

import common.drivers.Driver;
import common.logger.LogInstance;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePageObject {

    protected Logger log = LogInstance.getLogger();
    protected WebDriver driver = Driver.getDriver();

    @Step("opened the page")
    public void openPage(String url) {
        try {
            driver.get(url);
            log.info(String.format("opened the page with url: %s", url));
        } catch (Exception e) {
            log.info("url didn't find, the page didn't open");
            log.error(e.getMessage());
        }
    }

    @Step("got the web element text")
    public String getText(By locator) {
        waitForVisibilityOf(locator, 12);
        String text = driver.findElement(locator).getText();
        log.info(String.format("got web element text %s", text));
        return text;
    }

    public WebElement findElement(By locator) {
        waitForVisibilityOf(locator, 12);
        return driver.findElement(locator);
    }

    public List<WebElement> findAllElements(By locator) {
        waitForVisibilityOf(locator, 12);
        return driver.findElements(locator);
    }

    public void click(By locator) {
        waitForVisibilityOf(locator, 12);
        findElement(locator).click();
        log.info(String.format("clicked on the web element %s", locator.toString()));
    }

    @Step("typed in the field")
    public void type(By locator, String text) {
        waitForVisibilityOf(locator, 12);
        findElement(locator).sendKeys(text);
        log.info(String.format("in the field typed: %s", text));
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    private void waitForBooleanCondition(ExpectedCondition<Boolean> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    public void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
                log.error(String.format("the element is stale %s", e.getMessage()));
            }
            attempts++;
        }
    }

    public void waitForInvisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitForBooleanCondition(ExpectedConditions.invisibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
                log.error(String.format("the element is stale %s", e.getMessage()));
            }
            attempts++;
        }
    }

    public boolean isVisible(By locator) {
        return findElement(locator).isDisplayed();
    }

    @Step("the field was cleaned")
    public void clear(By locator) {
        findElement(locator).clear();
        log.info("the field was cleaned");
    }

    @Step("sent the key")
    public void sendKey(By locator, Keys key) {
        findElement(locator).sendKeys(key);
    }

    @Step("sent the key with the action")
    public void sendKeyWithAction(Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
        log.info("sent the key with the action");
    }

    public List<WebElement> getColumns(By locator) {
        return driver.findElement(locator).findElements(By.tagName("td"));
    }

    private void clickDate(By locator, int date) {
        List<WebElement> columns = getColumns(locator);
        for (WebElement cell : columns) {
            if (cell.getText().equals(Integer.toString(date))) {
                cell.click();
            }
        }
    }

    @Step("picked checkIn/checkOut")
    public void pickDates(By currentMonthLocator, By nextMonthLocator, int checkIn, int checkOut) {
        clickDate(currentMonthLocator, checkIn);
        if (checkOut < checkIn) {
            clickDate(nextMonthLocator, checkOut);
        } else {
            clickDate(currentMonthLocator, checkOut);
        }
    }

    @Step("made multiple clicks on the stepper")
    public void multipleClicks(By locator, int clicksCount) {
        for (int i = 0; i < clicksCount; i++) {
            click(locator);
        }
        log.info(String.format("made multiple clicks on the stepper: %s times", clicksCount));
    }

    @Step("selected the dropdown option by text")
    public void selectOptionByText(By dropdownLocator, String expectedText) {
        WebElement dropdownElement = findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(expectedText);
        log.info(String.format("the dropdown option with text %s is selected", expectedText));
    }

    @Step("selected the dropdown option by index")
    public void selectOptionByIndex(By dropdownLocator, int index) {
        WebElement dropdownElement = findElement(dropdownLocator);
        selectOptionByIndex(dropdownElement, index);
        log.info(String.format("the dropdown option with index %s is selected", index));
    }

    @Step("selected the dropdown option by index")
    public void selectOptionByIndex(WebElement dropdownElement, int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
        log.info(String.format("the dropdown option with index %s and text %s is selected",
                index, dropdown.getFirstSelectedOption().getText().trim()));
    }

    @Step("checked the dropdown option selection")
    public boolean checkSelectedOption(String expectedText, By dropdownLocator) {
        WebElement dropdownElement = findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        if (!dropdown.getFirstSelectedOption().getText().equals(expectedText)) {
            log.info(String.format("the dropdown option with text %s isn't selected", expectedText));
            return false;
        }
        log.info(String.format("the dropdown option with text %s is selected", expectedText));
        return true;
    }

    @Step("got the selected option text")
    public String getSelectedOptionText(By dropdownLocator) {
        WebElement dropdownElement = findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        String text = dropdown.getFirstSelectedOption().getText();
        log.info(String.format("got the selected option text %s", text));
        return text;
    }

    @Step("got the selected option text")
    public String getSelectedOptionText(WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        String text = dropdown.getFirstSelectedOption().getText();
        log.info(String.format("got the selected option text %s", text));
        return text;
    }

    @Step("selected the checkbox")
    public void selectCheckbox(By locator) {
        WebElement checkbox = findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        log.info(String.format("%s the checkbox is selected", checkbox.getText()));
    }

    @Step("checked is the checkbox selected")
    public boolean isCheckboxSelected(By locator) {
        WebElement checkbox = findElement(locator);
        if (!checkbox.isSelected()) {
            log.info(String.format("%s the checkbox isn't selected", checkbox.getText()));
            return false;
        }
        log.info(String.format("%s the checkbox is selected", checkbox.getText()));
        return true;
    }
}


