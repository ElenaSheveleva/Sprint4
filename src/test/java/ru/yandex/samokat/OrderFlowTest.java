package ru.yandex.samokat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.samokat.pages.HomePage;
import ru.yandex.samokat.pages.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderFlowTest {
    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;

    private final boolean useTopButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String color;
    private final String comment;

    public OrderFlowTest(boolean useTopButton, String name, String surname, String address,
                         String metro, String phone, String date, String color, String comment) {
        this.useTopButton = useTopButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {true, "Иван", "Петров", "ул. Ленина, 1", "Сокольники", "89991234567", "08.05.2025", "black", "Позвоните за 10 минут"},
                {false, "Анна", "Сидорова", "пр. Мира, 10", "Комсомольская", "89261112233", "09.05.2025", "grey", "Домофон 123"}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        homePage.open("https://qa-scooter.praktikum-services.ru/");
        homePage.acceptCookie();
    }

    @Test
    public void positiveOrderFlow() {
        homePage.clickOrderButton(useTopButton);
        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, color, comment);
        assertTrue("Заказ не оформился!", orderPage.isOrderSuccess());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}