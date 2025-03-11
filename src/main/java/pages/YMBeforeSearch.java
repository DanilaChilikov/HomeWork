package pages;

import common.WebDriverWaitInstrument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static helpers.Properties.testsProperties;

/**
 * Класс YMBeforeSearch реализует шаги для взаимодействия с главным меню
 * Яндекс Маркета перед выполнением поиска.
 * Используется паттерн Page Object.
 *
 * @author [Данила]
 * @version 1.0
 */

public class YMBeforeSearch {

    /** Веб-драйвер для управления браузером */

    private final WebDriver driver;

    /** Кнопка "Каталог" */

    private WebElement buttonCatalog;

    /** Раздел "Электроника" */

    private WebElement categoryElement;

    /** Раздел "Ноутбуки" */

    private WebElement itemCategory;

    /** Ожидание появления элементов на странице */

    private final WebDriverWaitInstrument wait;

    /**
     * Конструктор класса YMBeforeSearch.
     *
     * @param driver экземпляр {@link WebDriver}, используемый для взаимодействия с браузером
     */

    public YMBeforeSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWaitInstrument(driver, testsProperties.defaultTimeout());
    }

    /**
     * Метод для нажатия на кнопку "Каталог".
     * Ожидает появления элемента перед взаимодействием.
     */

    public void clickCatalog() {
        buttonCatalog = wait.findElement(By.xpath(
                "//noindex/button"));
        buttonCatalog.click();
    }

    /**
     * Метод для наведения курсора на категорию "Электроника" в каталоге.
     * Использует класс {@link Actions} для эмуляции наведения курсора.
     */

    public void hoverOverCategory(String categoryValue) {
        categoryElement = wait.findElement(By.xpath(
                String.format( "//div[@data-zone-name='catalog-content']//span[contains(text(), '%s')]",
                        categoryValue)));
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryElement).build().perform();
    }

    /**
     * Метод для клика по категории "Ноутбуки" в разделе "Электроника".
     * Ожидает появления элемента перед взаимодействием.
     */

    public void clickItem(String item) {
        itemCategory = wait.findElement(By.xpath(
                String.format( "//ul[contains(@data-autotest-id, 'subItems')]/li/div/a[contains(text(), '%s')]",
                        item)));
        itemCategory.click();
    }
}
