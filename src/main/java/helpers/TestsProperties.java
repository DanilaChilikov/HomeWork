package helpers;

import org.aeonbits.owner.Config;


/**
 * Интерфейс TestsProperties загружает параметры тестовой конфигурации
 * из файла `tests.properties`.
 *
 * @author [Данила]
 * @version 1.0
 */

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/tests.properties"
})
public interface TestsProperties extends Config {

    /**
     * Получает URL Яндекс.Маркета.
     * @return строка с URL.
     */

    @Config.Key("yandexMarket.url")
    String yandexMarketUrl();

    /**
     * Получает значение таймаута ожидания по умолчанию.
     * @return время в миллисекундах.
     */

    @Config.Key("default.timeout")
    int defaultTimeout();

    /**
     * Получает название веб-драйвера.
     * @return строка с названием веб-драйвера.
     */

    @Config.Key("webDriver")
    String webDriver();

    /**
     * Получает системные переменные.
     * @return строка с переменными окружения.
     */

    @Config.Key("systemVariables")
    String systemVariables();
}
