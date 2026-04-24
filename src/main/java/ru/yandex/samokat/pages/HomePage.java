package ru.yandex.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private final By acceptCookieButton = By.id("rcc-confirm-button");
    private final By orderHeaderButton = By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']");
    private final By orderFooterButton = By.xpath("//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");

    private final By questionHowMuch = By.id("accordion__heading-0");
    private final By questionSeveralScooters = By.id("accordion__heading-1");
    private final By questionRentTime = By.id("accordion__heading-2");
    private final By questionTodayDelivery = By.id("accordion__heading-3");
    private final By questionExtendOrReturn = By.id("accordion__heading-4");
    private final By questionCharging = By.id("accordion__heading-5");
    private final By questionCancelOrder = By.id("accordion__heading-6");
    private final By questionDeliveryArea = By.id("accordion__heading-7");

    private final By answerHowMuch = By.xpath("//div[@id='accordion__panel-0']/p");
    private final By answerSeveralScooters = By.xpath("//div[@id='accordion__panel-1']/p");
    private final By answerRentTime = By.xpath("//div[@id='accordion__panel-2']/p");
    private final By answerTodayDelivery = By.xpath("//div[@id='accordion__panel-3']/p");
    private final By answerExtendOrReturn = By.xpath("//div[@id='accordion__panel-4']/p");
    private final By answerCharging = By.xpath("//div[@id='accordion__panel-5']/p");
    private final By answerCancelOrder = By.xpath("//div[@id='accordion__panel-6']/p");
    private final By answerDeliveryArea = By.xpath("//div[@id='accordion__panel-7']/p");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void acceptCookie() {
        driver.findElement(acceptCookieButton).click();
    }

    public void clickQuestion(int index) {
        By questionLocator = getQuestionLocator(index);
        WebElement element = driver.findElement(questionLocator);
        scrollToElement(element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(questionLocator)).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(getAnswerLocator(index)));
    }

    public String getAnswerText(int index) {
        By answerLocator = getAnswerLocator(index);
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).getText();
    }

    private By getQuestionLocator(int index) {
        switch (index) {
            case 1: return questionHowMuch;
            case 2: return questionSeveralScooters;
            case 3: return questionRentTime;
            case 4: return questionTodayDelivery;
            case 5: return questionExtendOrReturn;
            case 6: return questionCharging;
            case 7: return questionCancelOrder;
            case 8: return questionDeliveryArea;
            default: return questionHowMuch;
        }
    }

    private By getAnswerLocator(int index) {
        switch (index) {
            case 1: return answerHowMuch;
            case 2: return answerSeveralScooters;
            case 3: return answerRentTime;
            case 4: return answerTodayDelivery;
            case 5: return answerExtendOrReturn;
            case 6: return answerCharging;
            case 7: return answerCancelOrder;
            case 8: return answerDeliveryArea;
            default: return answerHowMuch;
        }
    }

    public void clickOrderButtonTop() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderHeaderButton))
                .click();
    }

    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderFooterButton);
        scrollToElement(element);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderFooterButton))
                .click();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
