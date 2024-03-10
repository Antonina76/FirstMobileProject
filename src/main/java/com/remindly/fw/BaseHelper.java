package com.remindly.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

public class BaseHelper {
    AppiumDriver driver;

    public BaseHelper(AppiumDriver driver) {
        this.driver = driver;
    }
    public boolean isElementPresent(By locator){

        return driver.findElements(locator).size()>0;
    }

    public void type(By locator, String text) {
        if (text != null) {
            tap(locator);
    driver.findElement(locator).clear();
           driver.findElement(locator).sendKeys(text);
        }
        driver.hideKeyboard();
    }

    public void tap(By locator) {

        driver.findElement(locator).click();
    }
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void tapWithCoordinates(int x, int y) {
        new TouchAction(driver).tap(PointOption.point(x,y))
                .release().perform();
    }
}
