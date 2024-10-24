package org.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage{
    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public static final int WAIT_TIMEOUT_SEC = 3;

    private By statusOrderButton = By.xpath(".//button[contains(@class, 'Header_Link')]");
    private By orderNumberInput = By.xpath(".//div[starts-with(@class,'Input_InputContainer')]/input[@type='text']");
    private By goButton = By.xpath(".//div[starts-with(@class,'Header_SearchInput')]/button[text()='Go!']");
    private By cookieButton = By.xpath(".//button[contains(@class, 'App_CookieButton')]");
    private By faqAccordionQuestionDiv = By.xpath(".//div[contains(@class, 'accordion__heading')]");
    private By faqOppenedAnswerP = By.xpath(".//div[contains(@class, 'accordion__panel') and not(@hidden)]/p");
    private final String faqQuestionPattern = ".//div[contains(@id, 'accordion__heading') and contains(text(), '%s')]";
    private By headerOrderButton = By.xpath(".//div[contains(@class, 'Header')]/button[text() = 'Заказать']");
    private By bottomOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[text() = 'Заказать']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(MAIN_PAGE_URL);
    }

    public void statusOrderButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.elementToBeClickable(statusOrderButton));
        driver.findElement(statusOrderButton).click();
    }

    public String getFAQAnswerByQuestionText(String question) {
        scrollToElement(driver.findElement(faqAccordionQuestionDiv));
        faqQuestionClick(question);
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(faqOppenedAnswerP));
        return driver.findElement(faqOppenedAnswerP).getText();
    }

    public void setOrderNumber(String newValue) {
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        WebElement orderNumberWebElement = driver.findElement(orderNumberInput);
        orderNumberWebElement.clear();
        orderNumberWebElement.sendKeys(newValue);
    }

    public void goButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.elementToBeClickable(goButton)).click();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void cookieButtonClick() {
        WebElement cookieBtn = driver.findElement(cookieButton);
        waitBeforeClick(MainPage.WAIT_TIMEOUT_SEC, cookieBtn);
        cookieBtn.click();
    }

    public void faqQuestionClick(String question) {
        WebElement questionElement = driver.findElement(By.xpath(String.format(faqQuestionPattern, question)));
        waitBeforeClick(MainPage.WAIT_TIMEOUT_SEC, questionElement);
        questionElement.click();
    }

    public void headerOrderButtonClick() {
        waitBeforeClick(MainPage.WAIT_TIMEOUT_SEC, headerOrderButton);
        driver.findElement(headerOrderButton).click();
    }

    public void bottomOrderButtonClick() {
        waitBeforeClick(MainPage.WAIT_TIMEOUT_SEC, bottomOrderButton);
        driver.findElement(bottomOrderButton).click();
    }





}
