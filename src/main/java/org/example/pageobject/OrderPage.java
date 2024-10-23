package org.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage extends MainPage {

    public static String ORDER_PAGE_URL = MAIN_PAGE_URL + "order";
    public static final String YANDEX_URL = "https://dzen.ru/?yredirect=true";


    private By headerYandexLogoLink = By.xpath(".//a[contains(@class, 'Header_LogoYandex')]");
    private By zenHTML = By.xpath("html[contains(@class, 'zen-page')]");
    private By headersamokatLogoLink = By.xpath(".//a[contains(@class, 'Header_LogoScooter')]");
    private By deliveryDatePicker = By.xpath(".//div[contains(@class,'react-datepicker__day--selected')]");
    private By formFirstNameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    private By formLastNameInput = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    private By formAdressInput = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    private By formMetroStationInput = By.xpath(".//input[contains(@placeholder,'Станция метро')]");
    private By formPhoneInput = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    private By formDateInput = By.xpath(".//input[contains(@placeholder,'Когда привезти')]");
    private By formDurationDiv = By.xpath(".//div[@class = 'Dropdown-placeholder']");
    private By formCommentInput = By.xpath(".//input[contains(@placeholder,'Комментарий')]");
    private By formNextButton = By.xpath(".//button[text()='Далее']");
    private By formOrderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    private By formModalYesButton = By.xpath(".//div[contains(@class,'Order_Modal')]//button[text()='Да']");
    private By formFirstNameError = By.xpath(".//div[text()='Введите корректное имя']");
    private By formLastNameError = By.xpath(".//div[text()='Введите корректную фамилию']");
    private By formAdressError = By.xpath(".//div[text()='Введите корректный адрес']");
    private By formMetroStationError = By.xpath(".//div[text()='Выберите станцию']");
    private By formPhoneError = By.xpath(".//div[text()='Введите корректный номер']");
    private By formModalSuccessMessage = By.xpath(".//div[text() = 'Заказ оформлен']");


    private final String METRO_PATTERN = ".//*[contains(text(), '%s')]";
    private final String DURATION_PATTERN = ".//div[contains(@class, 'Dropdown-option') and text()='%s']";
    private final String COLOR_PATTERN = ".//input[@type = 'checkbox' and @id='%s']";


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void openOrderPage() {
        driver.get(ORDER_PAGE_URL);
    }

    public void clickYandexLogo() {
        driver.findElement(headerYandexLogoLink).click();
    }

    public void clickSamokatLogo() {
        driver.findElement(headersamokatLogoLink).click();
    }

    public void fillOrderForm(String firstName, String LastName, String adress, String metroStation,
                              String phone, String deliveryDate, String duration, String color, String comment) {
        fillInputText(formFirstNameInput, firstName);
        fillInputText(formLastNameInput, LastName);
        fillInputText(formAdressInput, adress);
        selectMetroStation(metroStation, formMetroStationInput);
        fillInputText(formMetroStationInput, metroStation);
        fillInputText(formPhoneInput, phone);
        orderFormNextButtonClick();
        selectDeliveryDate(deliveryDate);
        driver.findElement(formDurationDiv).click();
        driver.findElement(By.xpath(String.format(DURATION_PATTERN, duration))).click();
        driver.findElement(By.xpath(String.format(COLOR_PATTERN, color))).click();
        driver.findElement(formCommentInput).sendKeys(comment);
        driver.findElement(formOrderButton).click();

    }

    public void selectDeliveryDate(String date) {
        driver.findElement(formDateInput).sendKeys(date);
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(deliveryDatePicker)).click();
    }

    public void modalYesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.elementToBeClickable(formModalYesButton)).click();
    }

    private void fillInputText(By element, String text) {
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    private void selectMetroStation(String station, By locator) {
        WebElement input = driver.findElement(locator);
        input.sendKeys(station);
        driver.findElement(By.xpath(String.format(METRO_PATTERN, station))).click();
    }

    public boolean isOrderConfirmationWindowShown() {
        return new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(formModalSuccessMessage)).isDisplayed();
    }

    public void orderFormNextButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.elementToBeClickable(formNextButton)).click();
    }

    public boolean isFirstNameErrorMessageShown() {
        return driver.findElement(formFirstNameError).isDisplayed();
    }

    public boolean isLastNameErrorMessageShown() {
        return driver.findElement(formLastNameError).isDisplayed();
    }

    public boolean isAdressErrorMessageShown() {
        return driver.findElement(formAdressError).isDisplayed();
    }

    public boolean isMetroStationErrorMessageShown() {
        return driver.findElement(formMetroStationError).isDisplayed();
    }

    public boolean isPhoneErrorMessageShown() {
        return driver.findElement(formPhoneError).isDisplayed();
    }

    public void clickYaLogoAndPageLoad(){
        clickYandexLogo();
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.presenceOfElementLocated(zenHTML));
    }


}
