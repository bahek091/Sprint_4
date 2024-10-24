package org.example.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrackPage {
    private WebDriver driver;
    private By notFoundImg = By.xpath(".//img[@alt = 'Not found']");
    private By trackContentDiv = By.xpath(".//div[contains(@class, 'Track_Content')]");

    public TrackPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayedNotFoundOrderImg() {
        new WebDriverWait(driver, Duration.ofSeconds(MainPage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(trackContentDiv));
        return driver.findElement(notFoundImg).isDisplayed();
    }


}
