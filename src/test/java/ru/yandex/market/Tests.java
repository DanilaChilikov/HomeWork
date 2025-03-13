package ru.yandex.market;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static helpers.Assertions.assertAll;
import static helpers.Properties.testsProperties;
import static steps.StepsAll.*;

/**
 * Класс с тестами для проверки функционала фильтрации ноутбуков на Яндекс Маркете.
 * Используется фреймворк JUnit Jupiter, Selenium и паттерн Page Object.
 *
 * @author [Данила]
 * @version 1.0
 */

public class Tests extends BaseTest {

    /**
     * Тест для проверки корректности работы фильтрации ноутбуков.
     * Тест выполняется с разными значениями параметров (минимальная и максимальная цена),
     * используя {@link helpers.DataProvider#providerCheckingLaptops()} для параметризации.
     *
     * @param min минимальная цена для фильтрации ноутбуков
     * @param max максимальная цена для фильтрации ноутбуков
     */

    @Feature("Проверка ноутбуков")
    @DisplayName("Проверка ноутбуков - всё в степах")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingLaptops")
    public void yandexMarketTestWithStepsAll(String categories, String item, String min, String max,
                                             String firstBrand, String secondBrand, int index){

        /**
         * 1. Открываем сайт Яндекс Маркет и проверяем, что заголовок соответствует ожиданиям.
         */

        openSite(testsProperties.yandexMarketUrl(),
                "Интернет-магазин Яндекс Маркет — покупки с быстрой доставкой", chromeDriver);

        /**
         * 2. Переходим в каталог товаров.
         */

        searchInYandexMarket();

        /**
         * 3. Переход в раздел "Имя раздела".
         */

        searchInCatalog(categories);

        /**
         * 4. Переход в категорию "Имя раздела".
         */

        searchInCategory(item);

        /**
         * 5. Проверяем, что мы находимся в разделе "Ноутбуки".
         */

        validateYandexMarket();

        /**
         * 6. Устанавливаем фильтр по цене.
         */

        priceItem(min, max);

        /**
         * 7. Выбираем производителей ноутбуков (например, HP и Lenovo).
         */

        selectBrandsItem(firstBrand, secondBrand);

        /**
         * 8. Проверяем, что на первой странице отображается более 12 товаров.
         */

        hasMoreThanItem();

        /**
         * 9. Проверяем, что все отображенные ноутбуки соответствуют фильтру по брендам.
         */

        filterItems(firstBrand, secondBrand);

        /**
         * 10. Запоминаем название первого ноутбука в списке.
         */

        findItem(index);

        /**
         * 11. Выполняем поиск по запомненному названию ноутбука.
         */

        searchItem(index);

        /**
         * 12. Нажимаем кнопку "Найти".
         */

        clickButtonFindItem();

        /**
         * 13. Проверяем, что в результатах поиска присутствует искомый ноутбук.
         */

        itemHasDesiredName(index);

        /**
         * 14. Выполняем финальную проверку всех soft-assert'ов.
         */

        assertAll();
    }


}
