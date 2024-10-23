package org.example.tests;


import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.junit.Assert;
import org.junit.Test;

public class CheckLogoLinksTest extends BaseUITest{

    @Test
    public void shouldOpenYandexPageOnYandexLogoClick(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.openOrderPage();
        orderPage.clickYaLogoAndPageLoad();
        Assert.assertTrue("Expected dzen.ru is openned in new tab.", OrderPage.YANDEX_URL.equals(driver.getCurrentUrl()));
    }

    @Test
    public void shouldOpenMainPageOnSamokatLogoClick(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.openOrderPage();
        orderPage.clickSamokatLogo();

        Assert.assertTrue(MainPage.MAIN_PAGE_URL.equals(driver.getCurrentUrl()));
    }
}
