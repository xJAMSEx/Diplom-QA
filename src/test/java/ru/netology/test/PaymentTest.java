package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DataBaseHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");
    }

     @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Epic(value = "UI-тесты")
    @DisplayName("Покупка по карте со статусом APPROVED")
    @Test
    public void shouldConfirmPaymentApprovedCard() throws InterruptedException {
        MainPage mainPage = new MainPage();

        var CardInfo = DataHelper.getValidCard();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(CardInfo);
        paymentPage.getSuccessNotification();

        assertEquals("APPROVED", DataBaseHelper.getStatusPayment());
        Thread.sleep(3000);
    }

    @Epic(value = "UI-тесты")
    @DisplayName("Покупка по карте с текущим сроком действия")
    @Test
    public void shouldConfirmPaymentCurrentMonthAndYear()  {
        MainPage mainPage = new MainPage();

        var validCardInformation = DataHelper.getCurrentMonthAndYear();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(validCardInformation);
        paymentPage.getSuccessNotification();


        assertEquals("APPROVED", DataBaseHelper.getStatusPayment());

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Покупка по карте со статусом DECLINED")
    @Test
    public void shouldNotPayDeclinedCard()  {
        MainPage mainPage = new MainPage();

        var declinedCard = DataHelper.getDeclinedCard();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(declinedCard);
        paymentPage.getErrorNotification();

        assertEquals("APPROVED", DataBaseHelper.getStatusPayment());

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Все поля пустые")
    @Test
    public void shouldNotPayEmptyForm()  {
        MainPage mainPage = new MainPage();

        var emptyCardInformation = DataHelper.getAllFieldsEmpty();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(emptyCardInformation);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Номер карты")
    @Test
    public void shouldNotPayEmptyCard()  {
        MainPage mainPage = new MainPage();

        var fieldCardEmpty = DataHelper.getCardNumberEmpty();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(fieldCardEmpty);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Месяц")
    @Test
    public void shouldNotPayEmptyMonth()  {
        MainPage mainPage = new MainPage();

        var fieldMonthEmpty = DataHelper.getMonthEmpty();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(fieldMonthEmpty);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Год")
    @Test
    public void shouldNotPayEmptyYear()  {
        MainPage mainPage = new MainPage();

        var fieldYearEmpty = DataHelper.getYearEmpty();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(fieldYearEmpty);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Владелец")
    @Test
    public void shouldNotPayEmptyHolder()  {
        MainPage mainPage = new MainPage();

        var fieldHolderEmpty = DataHelper.getHolderEmpty();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(fieldHolderEmpty);
        paymentPage.getRequiredFieldCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле CVV")
    @Test
    public void shouldNotPayEmptyCvv()  {
        MainPage mainPage = new MainPage();

        var fieldCvvEmpty = DataHelper.getCVVEmpty();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(fieldCvvEmpty);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Невалидный номер карты")
    @Test
    public void shouldNotPayInvalidNumber()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getInvalidNumber();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Невалидное значение в поле Месяц")
    @Test
    public void shouldNotPayWrongMonth()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getInvalidMonth();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidDateCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Невалидное значение в поле Год")
    @Test
    public void shouldNotPayWrongYear()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getWrongYear();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidDateCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Цифры в поле Владелец")
    @Test
    public void shouldNotPayNumericHolder()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getNumericName();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Неверный формат CVV-код")
    @Test
    public void shouldNotPayInvalidCVV()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getInvalidCVV();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Истёкший месяц карты")
    @Test
    public void shouldNotPayExpiredMonth()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getExpiredMonth();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidDateCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Истёкший год карты")
    @Test
    public void shouldNotPayExpiredYear()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getExpiredYear();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.expiredCardYear();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Поле Номер карты - нули")
    @Test
    public void shouldNotPayZeroNumber()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getZeroCard();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidFormatCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Поле Месяц значение - 0")
    @Test
    public void shouldNotPayZeroMonth()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getZeroMonth();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidDateCard();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Поле CVV значение - 000")
    @Test
    public void shouldNotPayZeroCVV()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getZeroCVV();

        PaymentPage paymentPage = mainPage.paymentButtonClick();
        paymentPage.inputData(invalidCard);
        paymentPage.getInvalidFormatCard();

    }
}
