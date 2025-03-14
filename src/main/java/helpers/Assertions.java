package helpers;

import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;



public class Assertions {



    private static final List<String> errors = new ArrayList<>();



    @Step("Проверяем что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }



    @Step("Проверяем что нет ошибки: {message}")
    public static void softAssertTrue(boolean condition, String message) {
        if (!condition) {
            errors.add(message);
        }
    }



    @Step("Проверяем что {expected} равен {actual}")
    public static void assertEquals(Object expected, Object actual, String message) {
        org.junit.jupiter.api.Assertions.assertEquals(expected, actual, message);
    }



    @Step("Проверяем что нет ошибки: {message}")
    public static void assertFalse(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertFalse(condition, message);
    }



    public static void assertAll() {
        if (!errors.isEmpty()) {
            throw new AssertionError("Ошибки:\n" + String.join("\n", errors));
        }
    }

}
