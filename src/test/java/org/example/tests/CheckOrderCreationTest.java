package org.example.tests;

import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(value = Parameterized.class)
public class CheckOrderCreationTest extends BaseUITest {
    private String firstName;
    private String lastName;
    private String adress;
    private String metroStation;
    private String phone;
    private String delivaryDate;
    private String duration;
    private String color;
    private String comment;

    public CheckOrderCreationTest(String firstName, String lastName, String adress,
                                  String metroStation, String phone, String delivaryDate,
                                  String duration, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.metroStation = metroStation;
        this.phone = phone;
        this.delivaryDate = delivaryDate;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва", "Сокольники", "+79999999999", "20.11.2024", "сутки", "black", "Комментарий для теста"},
                {"Иван", "Иванов", "Москва", "Динамо", "+79999999999", "20.11.2024", "сутки", "black", "Комментарий для теста"},
                {"Иван", "Иванов", "Москва", "Курская", "+79999999999", "20.11.2024", "сутки", "black", "Комментарий для теста"}
        };
    }

    @Test
    public void checkCreateOrderWithTopCreateOrderButtonTest() {
        MainPage mainPage = openMainPage(driver);
        mainPage.headerOrderButtonClick();
        OrderPage orderPage = fillOrderFormText();
        orderPage.modalYesButtonClick();
        Assert.assertTrue("Couldn't create order with header button.", orderPage.isOrderConfirmationWindowShown());
    }

    @Test
    public void shouldCreateOrderWithBottomCreateOrderButton() {
        MainPage mainPage = openMainPage(driver);
        mainPage.bottomOrderButtonClick();
        OrderPage orderPage = fillOrderFormText();
        orderPage.modalYesButtonClick();
        Assert.assertTrue("Couldn't create order with bottom button.", orderPage.isOrderConfirmationWindowShown());
    }

    private OrderPage fillOrderFormText(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(firstName, lastName, adress, metroStation,
                phone, delivaryDate, duration, color, comment);
        return orderPage;
    }

}
