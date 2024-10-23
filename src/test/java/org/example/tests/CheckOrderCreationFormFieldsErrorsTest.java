package org.example.tests;

import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckOrderCreationFormFieldsErrorsTest extends BaseUITest{
    private OrderPage orderPage;

    @Before
    public void openOrderForm(){
        System.out.println("openOrderForm before steps");
        orderPage = new OrderPage(driver);
        orderPage.openOrderPage();
        new MainPage(driver).cookieButtonClick();
        orderPage.orderFormNextButtonClick();
    }

    @Test
    public void shouldShowFirstNameErrorMessageTest(){
        Assert.assertTrue("Error message for empty FirstName field is not shown", orderPage.isFirstNameErrorMessageShown());

    }

    @Test
    public void shouldShowLastNameErrorMessageTest(){
        Assert.assertTrue("Error message for empty LastName field is not shown", orderPage.isLastNameErrorMessageShown());

    }

    @Test
    public void shouldShowAdressErrorMessageTest(){
        Assert.assertTrue("Error message for empty Adress field is not shown", orderPage.isAdressErrorMessageShown());

    }

    @Test
    public void shouldShowMetroStationErrorMessageTest(){
        Assert.assertTrue("Error message for empty MetroStation field is not shown", orderPage.isMetroStationErrorMessageShown());

    }

    @Test
    public void shouldShowPhoneErrorMessageTest(){
        Assert.assertTrue("Error message for empty Phone field is not shown", orderPage.isPhoneErrorMessageShown());
    }

}
