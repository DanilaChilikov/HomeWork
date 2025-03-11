package ru.yandex.market;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import static helpers.Properties.testsProperties;

/**
 * Базовый класс для тестов с использованием Selenium WebDriver.
 * <p>
 * Этот класс выполняет инициализацию WebDriver перед каждым тестом
 * и его закрытие после выполнения теста.
 * </p>
 *
 * @author [Данила]
 * @version 1.0
 */

public class BaseTest {

    /** Экземпляр WebDriver для управления браузером */

    protected WebDriver chromeDriver;

    /**
     * Метод выполняется перед каждым тестом.
     * <p>
     * Устанавливает системное свойство для Chrome WebDriver,
     * создает новый экземпляр ChromeDriver, разворачивает окно на весь экран
     * и устанавливает неявное ожидание.
     * </p>
     */

    @BeforeEach
    public void setUp(){
        System.setProperty(testsProperties.webDriver(),System.getenv(testsProperties.systemVariables()));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }

    /**
     * Метод выполняется после каждого теста.
     * <p>
     * Закрывает браузер и завершает работу WebDriver.
     * </p>
     */

    @AfterEach
    public void quit(){
        chromeDriver.quit();
    }

}
