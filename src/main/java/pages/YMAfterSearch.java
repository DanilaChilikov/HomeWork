package pages;

import common.WebDriverWaitInstrument;
import helpers.Assertions;
import org.openqa.selenium.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import static helpers.Properties.testsProperties;

/**
 *
 * @author [Данила]
 * @version 2.0
 */

public class YMAfterSearch {

    private final WebDriverWaitInstrument wait;

    private WebElement minPrice;
    private WebElement maxPrice;
    private WebElement firstCheckbox;
    private WebElement secondCheckbox;
    private WebElement searchField;
    private WebElement searchButton;

    private List<WebElement> searchItems;
    private List<WebElement> itemList;

    private Set<String> brandNames = new HashSet<>();


    private final static String searchFieldLocator = "//div/div/div/div/article/div/div/div/div/div/div/div/a/span[contains(.,'%s')]";

    By locator = null;

    public YMAfterSearch(WebDriver driver) {
        this.wait = new WebDriverWaitInstrument(driver, testsProperties.defaultTimeout());
    }

    public void checkingBySectionItem(){
        Assertions.assertFalse(!wait.findElement(By.xpath("//h1")).isDisplayed(),
                "Переход в раздел не произошел!");
    }

    public void setPriceRange(String min, String max) {
        minPrice = wait.findElement(By.xpath(
                "//div[contains(@data-auto,'filter-range-glprice')]" +
                        "//span[contains(@data-auto,'filter-range-min')]//input"));
        minPrice.sendKeys(min);
        maxPrice = wait.findElement(By.xpath(
                "//div[contains(@data-auto,'filter-range-glprice')]" +
                        "//span[contains(@data-auto,'filter-range-max')]//input"));
        maxPrice.sendKeys(max);
    }


    public void selectBrands(String firstBrand, String secondBrand) {
            firstCheckbox = wait.findElement(By.xpath(String.format("//div[contains(@data-zone-name,'Filter')]" +
                    "//span[contains(text(), '%s')]", firstBrand)));
            firstCheckbox.click();
            secondCheckbox = wait.findElement(By.xpath(String.format("//div[contains(@data-zone-name,'Filter')]" +
                    "//span[contains(text(), '%s')]", secondBrand)));
            secondCheckbox.click();

    }

    public void hasMoreThan12Elements(String item) {
        itemList = wait.findElements(By.xpath(String.format(
                "//div/div/div/div/article/div/div/div/" +
                        "div/div/div/div/a/span[contains(.,'%s')]", item)));
        Assertions.assertTrue(itemList.size() >= 12, "Элементов в списке меньше 12");
        locator = By.xpath("//div[@data-baobab-name = 'next']");
        while(true){
            try {
                wait.findElement(locator).click();
            }catch (TimeoutException | StaleElementReferenceException e) {
                break;
            }
        }
    }

    public List<String> getBrandNames(String categoryName) {
        return wait
                .findElements(By.xpath(String.format(searchFieldLocator, categoryName)))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void saveBrandNameToList(String item, int index) {
        brandNames.add(
                getBrandNames(item).get(index)
        );
    }

    public void enterTheRememberedItem() {
        searchField = wait.findElement(By.xpath("//input[@id=\"header-search\"]"));
        searchField.click();
        searchField.sendKeys(brandNames.stream().collect(Collectors.toList()).get(0));
        brandNames.clear();
    }

    public void clickButtonFind() {
        wait
                .findElement(By.xpath("//form/div/button"))
                .click();
    }

    public String searchItem(int index, String item){
        searchItems = wait.findElements(By.xpath(String.format(
                "//div/div/div/div/article/div/div/div/" +
                        "div/div/div/div/a/span[contains(.,'%s')]", item)));
        List<String> items = searchItems
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return items.get(index);
    }
}