package helpers;

import common.WebDriverWaitInstrument;
import org.openqa.selenium.By;


public class Exists {
    public static boolean exists(By locator, WebDriverWaitInstrument wait) {
        return !wait.findElements(locator).isEmpty();
    }
}
