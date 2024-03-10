package com.remindly.tests;

import org.testng.annotations.Test;

public class AddReminderTests extends TestBase{
    @Test
    public void addReminderPositiveTest(){
        app.getMainScreen().taOnAddReminder();
        app.getReminder().enterReminderTitle("Holiday");
        app.getReminder().tapOnDateField();
        app.getReminder().swipeToMonth("future","MAY", 2);
        app.getReminder().selectDate(1);
        app.getReminder().selectYear("future","2025");
       // app.getReminder().tapOnOk();
    }
}
