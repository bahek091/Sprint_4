package org.example.tests;

import org.example.pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseUITest {
    protected WebDriver driver;

    @Before
    public void startUp() {
        driver = initChrome();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public WebDriver initChrome() {
        return new ChromeDriver();
    }

    public WebDriver initFireFox() {
        return new FirefoxDriver();
    }

    protected MainPage openMainPage(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.cookieButtonClick();
        return mainPage;
    }
}
