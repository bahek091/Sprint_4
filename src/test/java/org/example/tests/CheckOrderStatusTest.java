package org.example.tests;

import org.example.pageobject.MainPage;
import org.example.pageobject.TrackPage;
import org.junit.Assert;
import org.junit.Test;


public class CheckOrderStatusTest extends BaseUITest {
    private final String incorrectOrder = "1";


    @Test
    public void OrderStatusNotExistTest() {
        MainPage mainPage = openMainPage(driver);
        mainPage.statusOrderButtonClick();
        mainPage.setOrderNumber(incorrectOrder);
        mainPage.goButtonClick();

        TrackPage trackPage = new TrackPage(driver);
        Assert.assertTrue("Expected NotFoung img to be shown.", trackPage.isDisplayedNotFoundOrderImg());
    }

}
