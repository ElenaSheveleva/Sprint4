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

    private final By question1 = By.id("accordion__heading-0");
    private final By question2 = By.id("accordion__heading-1");
    private final By question3 = By.id("accordion__heading-2");
    private final By question4 = By.id("accordion__heading-3");
    private final By question5 = By.id("accordion__heading-4");
    private final By question6 = By.id("accordion__heading-5");
    private final By question7 = By.id("accordion__heading-6");
    private final By question8 = By.id("accordion__heading-7");

    private final By answer1 = By.xpath("//div[@id='accordion__panel-0']/p");
    private final By answer2 = By.xpath("//div[@id='accordion__panel-1']/p");
    private final By answer3 = By.xpath("//div[@id='accordion__panel-2']/p");
    private final By answer4 = By.xpath("//div[@id='accordion__panel-3']/p");
    private final By answer5 = By.xpath("//div[@id='accordion__panel-4']/p");
    private final By answer6 = By.xpath("//div[@id='accordion__panel-5']/p");
    private final By answer7 = By.xpath("//div[@id='accordion__panel-6']/p");
    private final By answer8 = By.xpath("//div[@id='accordion__panel-7']/p");

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

        // Ожидание появления ответа
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
            case 1: return question1;
            case 2: return question2;
            case 3: return question3;
            case 4: return question4;
            case 5: return question5;
            case 6: return question6;
            case 7: return question7;
            case 8: return question8;
            default: return question1;
        }
    }

    private By getAnswerLocator(int index) {
        switch (index) {
            case 1: return answer1;
            case 2: return answer2;
            case 3: return answer3;
            case 4: return answer4;
            case 5: return answer5;
            case 6: return answer6;
            case 7: return answer7;
            case 8: return answer8;
            default: return answer1;
        }
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderHeaderButton).click();
    }

    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderFooterButton);
        scrollToElement(element);
        element.click();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}