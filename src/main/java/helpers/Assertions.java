package helpers;

import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Assertions предоставляет набор методов для проверки условий в тестах.
 * Поддерживает как жесткие, так и мягкие проверки (soft assertions).
 *
 * @author [Данила]
 * @version 1.0
 */

public class Assertions {

    /** Список ошибок для мягких проверок */

    private static final List<String> errors = new ArrayList<>();

    /**
     * Проверяет, что условие истинно. Если оно ложно, тест завершается с ошибкой.
     *
     * @param condition Условие, которое должно быть истинным
     * @param message Сообщение об ошибке, если условие ложно
     */

    @Step("Проверяем что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }

    /**
     * Выполняет мягкую проверку, не завершая тест немедленно при ошибке.
     * Ошибки накапливаются и будут обработаны в {@link #assertAll()}.
     *
     * @param condition Условие, которое должно быть истинным
     * @param message Сообщение об ошибке, если условие ложно
     */

    @Step("Проверяем что нет ошибки: {message}")
    public static void softAssertTrue(boolean condition, String message) {
        if (!condition) {
            errors.add(message);
        }
    }

    /**
     * Проверяет, что два значения равны. Если они не равны, тест завершается с ошибкой.
     *
     * @param expected Ожидаемое значение
     * @param actual Фактическое значение
     * @param message Сообщение об ошибке, если значения не равны
     */

    @Step("Проверяем что {expected} равен {actual}")
    public static void assertEquals(Object expected, Object actual, String message) {
        org.junit.jupiter.api.Assertions.assertEquals(expected, actual, message);
    }

    /**
     * Проверяет, что условие ложно. Если оно истинно, тест завершается с ошибкой.
     *
     * @param condition Условие, которое должно быть ложным
     * @param message Сообщение об ошибке, если условие истинно
     */

    @Step("Проверяем что нет ошибки: {message}")
    public static void assertFalse(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertFalse(condition, message);
    }

    /**
     * Выполняет финальную проверку для мягких ассертов. Если накопились ошибки, тест завершается с исключением.
     *
     * @throws AssertionError если есть накопленные ошибки
     */

    public static void assertAll() {
        if (!errors.isEmpty()) {
            throw new AssertionError("Ошибки:\n" + String.join("\n", errors));
        }
    }

}
