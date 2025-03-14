package pages;

import common.WebDriverWaitInstrument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static helpers.Properties.testsProperties;

/**
 *
 * @author [Данила]
 * @version 1.0
 */

public class YMBeforeSearch {


    private final WebDriver driver;


    private WebElement buttonCatalog;


    private WebElement categoryElement;


    private WebElement itemCategory;


    private final WebDriverWaitInstrument wait;



    public YMBeforeSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWaitInstrument(driver, testsProperties.defaultTimeout());
    }



    public void clickCatalog() {
        buttonCatalog = wait.findElement(By.xpath(
                "//noindex/button"));
        buttonCatalog.click();
    }



    public void hoverOverCategory(String categoryValue) {
        categoryElement = wait.findElement(By.xpath(
                String.format( "//div[@data-zone-name='catalog-content']//span[contains(text(), '%s')]",
                        categoryValue)));
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryElement).build().perform();
    }


    public void clickCategoryItem(String item) {
        itemCategory = wait.findElement(By.xpath(
                String.format( "//ul[contains(@data-autotest-id, 'subItems')]/li/div/a[contains(text(), '%s')]",
                        item)));
        itemCategory.click();
    }
}
