package pages;

import common.WebDriverWaitInstrument;
import helpers.Assertions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.Exists.exists;
import static helpers.Properties.testsProperties;

/**
 * Класс YMAfterSearch реализует шаги для взаимодействия с результатами поиска
 * ноутбуков на сайте Яндекс Маркет.
 * Используется паттерн Page Object.
 *
 * @author [Данила]
 * @version 1.0
 */

public class YMAfterSearch {

    /** Веб-драйвер для управления браузером */

    private final WebDriver driver;

    /** Ожидание появления элементов на странице */

    private final WebDriverWaitInstrument wait;

    /** Поле ввода минимальной цены */

    private WebElement minPrice;

    /** Поле ввода максимальной цены */

    private WebElement maxPrice;

    /** Чекбокс производителя HP */

    private WebElement firstCheckbox;

    /** Чекбокс производителя Lenovo */

    private WebElement secondCheckbox;

    /** Список найденных товаров */

    private List<WebElement> itemList;

    /** Поле поиска */

    private WebElement searchField;

    /** Кнопка поиска */

    private WebElement searchButton;


    /** Список элементов после поиска */

    private List<WebElement> searchItems;

    By locator = null;

    private WebElement nextPage;

    /**
     * Конструктор класса YMAfterSearch.
     *
     * @param driver экземпляр {@link WebDriver}, используемый для взаимодействия с браузером
     */

    public YMAfterSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWaitInstrument(driver, testsProperties.defaultTimeout());
    }

    /**
     * Проверяет, что произошел переход в раздел "Ноутбуки".
     * Если заголовок не найден, тест завершится с ошибкой.
     */

    public void checkingBySectionItem(){
        Assertions.assertFalse(wait.findElements(By.xpath("//h1")).isEmpty(),
                "Переход в раздел Ноутбуки не произошел!");
    }

    /**
     * Устанавливает диапазон цен для фильтрации ноутбуков.
     *
     * @param min минимальная цена
     * @param max максимальная цена
     */

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

    /**
     * Выбирает производителей ноутбуков (Lenovo и HP) с помощью чекбоксов.
     */

    public void selectBrands(String firstBrand, String secondBrand) {
        firstCheckbox = wait.findElement(By.xpath(String.format("//div[contains(@data-zone-name,'Filter')]" +
                "//span[contains(text(), '%s')]", firstBrand)));
        firstCheckbox.click();
        secondCheckbox = wait.findElement(By.xpath(String.format("//div[contains(@data-zone-name,'Filter')]" +
                "//span[contains(text(), '%s')]", secondBrand)));
        secondCheckbox.click();
    }

    /**
     * Проверяет, что в списке отображается не менее 12 товаров.
     * Если товаров меньше, тест завершится с ошибкой.
     */

    public void hasMoreThan12Elements() {
        itemList = wait.findElements(By.xpath(
                "//div/div/div/div/article/div/div/div/div/div/div/div/a/span[contains(.,'Ноутбук')]"));
        Assertions.assertTrue(itemList.size() >= 12, "В списке Ноутбуков меньше 12");
        locator = By.xpath("//div[@data-baobab-name = 'next']");
        while (exists(locator,wait)) {
            nextPage = wait.findElement(locator);
            nextPage.click();
        }
    }

    /**
     * Получает список названий ноутбуков из результатов поиска.
     *
     * @return список названий ноутбуков
     */

    public List<String> getItemName() {
        itemList = wait.findElements(By.xpath(
                "//div/div/div/div/article/div/div/div/div/div/div/div/a/span[contains(.,'Ноутбук')]"));
        return itemList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает название первого ноутбука из списка.
     *
     * @return название первого ноутбука или "Ноутбук не найден", если список пуст
     */

    public String findItemInList(int index) {
        return getItemName().get(index);
    }

    /**
     * Вводит в строку поиска запомненное название первого ноутбука.
     */

    public void enterTheRememberedItem(int index) {
        searchField = wait.findElement(By.xpath("//input[@id=\"header-search\"]"));
        searchField.click();
        String nameItem = findItemInList(index);
        searchField.sendKeys(nameItem);
    }

    /**
     * Нажимает кнопку "Найти" на странице поиска.
     */

    public void clickButtonFind() {
        searchButton = wait.findElement(By.xpath("//form/div/button"));
        searchButton.click();
    }

    /**
     * Получает название первого ноутбука из результатов поиска.
     *
     * @return название первого ноутбука или "Ноутбук не найден", если список пуст
     */

    public String searchItems(int index){
        searchItems = wait.findElements(By.xpath("//div/div/div/div/article/div/div" +
                "/div/div/div/div/div/a/span[contains(.,'Ноутбук')]"));
        List<String> items = searchItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        String elementItem = items.get(index);
        return elementItem;
    }
}