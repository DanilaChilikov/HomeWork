package steps;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.YMAfterSearch;
import pages.YMBeforeSearch;
import java.util.List;

import static helpers.Properties.testsProperties;

/**
 * Класс StepsAll реализует последовательность шагов для тестирования поиска ноутбуков
 * на сайте Яндекс Маркет. Используется библиотека Allure для аннотаций @Step.
 *
 * @author [Данила]
 * @version 1.0
 */

public class StepsAll {

    /** Веб-драйвер для управления браузером */

    private static WebDriver driver;

    /**
     *Ожидание появления элементов на странице
     */

    private static WebDriverWait wait;

    /**
     * Открывает указанный сайт и проверяет его заголовок.
     *
     * @param url           URL-адрес сайта
     * @param title         Ожидаемый заголовок страницы
     * @param currentDriver Экземпляр {@link WebDriver}
     */

    @Step("Переходим на сайт: {url}")
    public static void openSite(String url, String title, WebDriver currentDriver) {
        driver = currentDriver;
        driver.get(url);
        wait = new WebDriverWait(driver, testsProperties.defaultTimeout());
        wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * Нажимает на кнопку "Каталог" на сайте Яндекс Маркет.
     */

    @Step("Ищем на сайте Yandex Market кнопку: Каталог")
    public static void searchInYandexMarket(){
        YMBeforeSearch yandexMarketBeforeSearch = new YMBeforeSearch(driver);
        yandexMarketBeforeSearch.clickCatalog();
    }

    /**
     * Наводит курсор на раздел "Электроника" в каталоге.
     */

    @Step("Ищем в каталоге кнопку: {categories}")
    public static void searchInCatalog(String categories){
        YMBeforeSearch yandexMarketBeforeSearch = new YMBeforeSearch(driver);
        yandexMarketBeforeSearch.hoverOverCategory(categories);
    }

    /**
     * Переходит в категорию "Ноутбуки".
     */

    @Step("Ищем в текущей категории кнопку: {item}")
    public static void searchInCategory(String itemTitle){
        YMBeforeSearch yandexMarketBeforeSearch = new YMBeforeSearch(driver);
        yandexMarketBeforeSearch.clickCategoryItem(itemTitle);
    }

    /**
     * Проверяет, что переход в раздел "Ноутбуки" произошел успешно.
     */

    @Step("Проверяем наличие раздела в результатах поиска YandexMarket")
    public static void validateYandexMarket() {
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        yandexMarketAfterSearch.checkingBySectionItem();
    }

    /**
     * Устанавливает диапазон цен для фильтрации ноутбуков.
     *
     * @param min Минимальная цена
     * @param max Максимальная цена
     */

    @Step("Задаем параметры цены")
    public static void priceItem(String min, String max) {
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        yandexMarketAfterSearch.setPriceRange(min, max);
    }

    /**
     * Выбирает производителей ноутбуков (Lenovo и HP).
     */

    @Step("Выбираем производителя: {firstBrand}, {secondBrand}")
    public static void selectBrandsItem(String firstBrand, String secondBrand) {
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        yandexMarketAfterSearch.selectBrands(firstBrand, secondBrand);
    }

    /**
     * Проверяет, что в списке отображается не менее 12 ноутбуков.
     */

    @Step("Проверяем больше ли 12 элементов в списке")
    public static void hasMoreThanItem(String item){
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        yandexMarketAfterSearch.hasMoreThan12Elements(item);
    }

    /**
     * Проверяет, что все ноутбуки соответствуют выбранным брендам (Lenovo и HP).
     */

    @Step("Проверяем все ли брэнды производителя соответствуют фильтру")
    public static void filterItems(String firstBrand, String secondBrand, String item){
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        List<String> itemName = yandexMarketAfterSearch.getItemName(item);
        Assertions.softAssertTrue(itemName.stream()
                        .allMatch(name ->
                                {
                                    String lowerCaseName = name.toLowerCase();
                                    return lowerCaseName .contains(firstBrand) || name.contains(secondBrand);
                                }),
                "В списке есть модели, не относящиеся к данным брендам");
    }

    /**
     * Запоминает название первого ноутбука в списке.
     */

    @Step("Запоминаем первое наименование товара")
    public static void findItem(String item, int index) {
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        yandexMarketAfterSearch.findItemInList(item, index);
    }

    /**
     * Вводит в строку поиска запомненное название ноутбука.
     */

    @Step("В поисковую строку ввести запомненный товар")
    public static void searchItem(String item, int index) {
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        yandexMarketAfterSearch.enterTheRememberedItem(item, index);
    }

    /**
     * Нажимает кнопку "Найти" для поиска ноутбука.
     */

    @Step("Нажимаем кнопку «Найти»")
    public static void clickButtonFindItem() {
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        yandexMarketAfterSearch.clickButtonFind();
    }

    /**
     * Проверяет, что в результатах поиска на первой странице присутствует искомый ноутбук.
     */

    @Step("Проверяем, что в результатах поиска, на первой странице, есть искомый товар")
        public static void itemHasDesiredName(int index, String item) {
        YMAfterSearch yandexMarketAfterSearch = new YMAfterSearch(driver);
        String itemInList = yandexMarketAfterSearch.findItemInList(item, index);
        String findItem = yandexMarketAfterSearch.searchItem(index,item);
        Assertions.assertEquals(itemInList, findItem,
                "Искомый товар не равен запомненному товару");
    }

}
