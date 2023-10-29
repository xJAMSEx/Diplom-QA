package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DataBaseHelper;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        DataHelper.cleanData();
    }

    @Test
    void shouldPaymentApprovedCard() {
        val cardInfo = new DataHelper().getValidCardInfo("approved");
        val paymentPage = new OrderPage().goToPayment();

        paymentPage.payment(cardInfo);
        paymentPage.approved();

        assertEquals("APPROVED",new DataBaseHelper().getPaymentStatus());
        assertEquals(4500000, new DataBaseHelper().getPaymentAmount());
        assertNull(new DataBaseHelper().getCreditId());
    }

    @Test
    void shouldPaymentDeclinedCard() {
        val cardInfo = new DataHelper().getValidCardInfo("declined");
        val paymentPage = new OrderPage().goToPayment();

        paymentPage.payment(cardInfo);
        paymentPage.declined();

        assertEquals("DECLINED", new DataBaseHelper().getPaymentStatus());
        assertNull(new DataBaseHelper().getCreditId());
    }

    @Test
    void shouldGetNotificationInvalidCard() {
        val cardInfo = new DataHelper().getInvalidCardInfo("approved");
        val paymentPage = new OrderPage().goToPayment();

        paymentPage.payment(cardInfo);
        paymentPage.invalidCardNotification();
    }

    @Test
    void shouldGetNotificationWrongFormatCard() {
        val cardInfo = new DataHelper().getInvalidFormatCard("4444");
        val paymentPage = new OrderPage().goToPayment();

        paymentPage.payment(cardInfo);
        paymentPage.wrongFormatNotification();
    }

    @Test
    void shouldGetNotificationEmptyFields() {
        val paymentPage = new OrderPage().goToPayment();

        paymentPage.emptyFieldNotification();
    }
}
