package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverWaitInstrument {

    private final WebDriver driver;

    private final WebDriverWait wait;


    public WebDriverWaitInstrument(WebDriver driver, int timeOut) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeOut);

    }

    public WebElement findElement(By locator) {
        wait.until(ExpectedConditions.and(
                        ExpectedConditions.presenceOfElementLocated(locator),
                        ExpectedConditions.elementToBeClickable(locator),
                        ExpectedConditions.visibilityOfElementLocated(locator)
                ));
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

}
