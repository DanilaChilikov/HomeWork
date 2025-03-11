package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;


/**
 * Класс DataProvider предоставляет тестовые данные для параметризованных тестов.
 *
 * @author [Данила]
 * @version 1.0
 */

public class DataProvider {

    /**
     * Провайдер данных для тестирования фильтрации ноутбуков по цене.
     *
     * @return Поток аргументов с диапазоном цен
     */

    public static Stream<Arguments> providerCheckingLaptops() {
        return Stream.of(
                Arguments.of("Электроника","Ноутбуки","10000",
                        "30000",  "Lenovo", "HP", "Lenovo", "HP", 1 , 1)
        );
    }

}
