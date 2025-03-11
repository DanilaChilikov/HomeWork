package helpers;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс Properties загружает и предоставляет доступ к тестовым настройкам.
 *
 * @author [Данила]
 * @version 1.0
 */

public class Properties {

    /**
     * Экземпляр интерфейса TestsProperties, содержащий параметры конфигурации тестов.
     */

    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}
