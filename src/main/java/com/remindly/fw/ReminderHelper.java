package com.remindly.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReminderHelper extends BaseHelper{
    public ReminderHelper(AppiumDriver driver) {
        super(driver);
    }

    public void enterReminderTitle(String text) {
        type(By.id("reminder_title"), text);
    }

    public void tapOnDateField() {
        tap(By.id("date"));
    }

    public void swipeToMonth(String period, String month,int number) {
        pause(500);
        if(!getSelectedMonth().equals(month)){
            for (int i = 0; i < number; i++) {
                if (period.equals("future")) {
                    swipe(0.8,0.4);
            }else if (period.equals("past")){
                    swipe(0.5,0.8);
        
                }
            }
        }
    }

    public String getSelectedMonth() {
        return driver.findElement(By.id("date_picker_month")).getText();
    }

    public void swipe(double start, double stop) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * start);
        int stopY = (int) (size.height * stop);
        action.longPress(PointOption.point(x,startY))
                .moveTo(PointOption.point(x,stopY))
                .release().perform();


    }

    public void selectDate(int index) {
        List <WebElement>days  = driver.findElements(By.className("android.view.View"));
        days.get(index).click();
    }

    public void selectYear(String period2, String year) {
        pause(500);
        tap(By.id("date_picker_year"));
        if(!getSelectedYear().equals(year)){
            if(period2.equals("future")){
                swipeUntilNeededYear(year,0.6,0.5);
            } else if (period2.equals("past")) {
                swipeUntilNeededYear(year,0.5,0.6);
            }
        }
    }

    public void swipeUntilNeededYear(String year,double startPoint,double stopPoint) {
while (!getSelectedYear().equals(year))
    moveInElement(By.className("android.widget.ListView"),startPoint,stopPoint);
driver.findElement(By.id("month_text_view")).getText();
    }

    public void moveInElement(By element, double startPoint, double stopPoint) {
        TouchAction action =new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        //get activity point
        int startY = (int) (size.height * startPoint);
        int stopY = (int) (size.height * stopPoint);
        //get locator's point
        WebElement locator = driver.findElement(element);
        int lefX = locator.getLocation().getX();
        int rightX = lefX + locator.getSize().getWidth();
        int middleX = (lefX+rightX)/2;
        action.longPress(PointOption.point(middleX,startY))
                .moveTo(PointOption.point(middleX,stopY))
                .release().perform();

    }

    public String getSelectedYear(){
        return driver.findElement(By.id("date_picker_year")).getText();
    }
}

