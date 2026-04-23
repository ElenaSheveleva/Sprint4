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

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    private final boolean useTopButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String[] colors;
    private final String comment;

    public OrderFlowTest(boolean useTopButton, String name, String surname, String address,
                         String metro, String phone, String date, String period,
                         String[] colors, String comment) {
        this.useTopButton = useTopButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.colors = colors;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {true, "Иван", "Петров", "ул. Ленина, 1", "Сокольники", "89991234567",
                        "08.05.2025", "сутки", new String[]{"black"}, "Позвоните за 10 минут"},
                {false, "Анна", "Сидорова", "пр. Мира, 10", "Комсомольская", "89261112233",
                        "09.05.2025", "двое суток", new String[]{"grey"}, "Домофон 123"}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        homePage.open(BASE_URL);
        homePage.acceptCookie();
    }

    @Test
    public void positiveOrderFlow() {
        if (useTopButton) {
            homePage.clickOrderButtonTop();
        } else {
            homePage.clickOrderButtonBottom();
        }

        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, period, colors, comment);
        assertTrue(orderPage.isOrderSuccess());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
