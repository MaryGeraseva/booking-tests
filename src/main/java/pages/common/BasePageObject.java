package pages.common;

import common.drivers.Driver;
import common.logger.LogInstance;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
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
        waitForVisibilityOf(locator, 15);
        String text = driver.findElement(locator).getText();
        log.info(String.format("got web element text %s", text));
        return text;
    }

    @Step("got the web element attribute")
    public String getAttribute(By locator, String attributeName) {
        waitForVisibilityOf(locator, 15);
        String value = findElement(locator).getAttribute(attributeName);
        log.info(String.format("got web element attribute %s", value));
        return value;
    }

    public WebElement findElement(By locator) {
        waitForVisibilityOf(locator, 15);
        return driver.findElement(locator);
    }

    public List<WebElement> findAllElements(By locator) {
        waitForVisibilityOf(locator, 15);
        return driver.findElements(locator);
    }

    public void click(By locator) {
        waitForVisibilityOf(locator, 15);
        findElement(locator).click();
        log.info(String.format("clicked on the web element %s", locator.toString()));
    }

    public void click(WebElement element) {
        waitForVisibilityOf(element, 15);
        element.click();
    }

    @Step("typed in the field")
    public void type(By locator, String text) {
        waitForVisibilityOf(locator, 15);
        findElement(locator).sendKeys(text);
        log.info(String.format("in the field typed: %s", text));
    }

    public void sendKey(By locator, Keys key) {
        findElement(locator).sendKeys(key);
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

    public void waitForVisibilityOf(WebElement element, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOf(element),
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

    public List<WebElement> getColumns(By locator) {
        return driver.findElement(locator).findElements(By.tagName("td"));
    }

    @Step("made multiple clicks on the stepper")
    public void multipleClicks(By locator, int clicksCount) {
        for (int i = 0; i < clicksCount; i++) {
            click(locator);
        }
        log.info(String.format("made multiple clicks on the stepper: %s times", clicksCount));
    }

    @Step("selected the dropdown option by index")
    public void selectOptionByIndex(WebElement dropdownElement, int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
        log.info(String.format("the dropdown option with index %s and text %s is selected",
                index, dropdown.getFirstSelectedOption().getText().trim()));
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

    @Step("switched to the other browser tab")
    public void switchToTab(String firstWindowHeader) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(firstWindowHeader)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @Step("got current window header")
    public String getWindowHeader() {
        return driver.getWindowHandle();
    }
}


