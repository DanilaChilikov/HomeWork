package ru.yandex.market;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static helpers.Properties.testsProperties;



public class BaseTest {


    protected WebDriver chromeDriver;



    @BeforeEach
    public void setUp(){
        System.setProperty(testsProperties.webDriver(),System.getenv(testsProperties.systemVariables()));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }



    @AfterEach
    public void quit(){
        chromeDriver.quit();
    }

}
