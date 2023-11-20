package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DataBaseHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {

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
    public void shouldConfirmCreditApprovedCard()  {
        MainPage mainPage = new MainPage();

        var CardInfo = DataHelper.getValidCard();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(CardInfo);
        creditPage.getSuccessNotificationCredit();

        assertEquals("APPROVED", DataBaseHelper.getStatusCredit());

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Покупка по карте с текущим сроком действия")
    @Test
    public void shouldConfirmCreditWithCurrentMonthAndYear()  {
        MainPage mainPage = new MainPage();

        var CardInfo = DataHelper.getCurrentMonthAndYear();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(CardInfo);
        creditPage.getSuccessNotificationCredit();

        assertEquals("APPROVED", DataBaseHelper.getStatusCredit());

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Покупка по карте со статусом DECLINED")
    @Test
    public void shouldNotCreditDeclinedCard()  {
        MainPage mainPage = new MainPage();

        var CardInfo = DataHelper.getDeclinedCard();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(CardInfo);
        creditPage.getErrorNotificationCredit();

        assertEquals("APPROVED", DataBaseHelper.getStatusCredit());

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Все поля пустые")
    @Test
    public void shouldNotCreditEmptyForm()  {
        MainPage mainPage = new MainPage();

        var emptyCardInformation = DataHelper.getAllFieldsEmpty();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(emptyCardInformation);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Номер карты")
    @Test
    public void shouldNotCreditEmptyCard()  {
        MainPage mainPage = new MainPage();

        var fieldCardEmpty = DataHelper.getCardNumberEmpty();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(fieldCardEmpty);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Месяц")
    @Test
    public void shouldNotCreditEmptyMonth()  {
        MainPage mainPage = new MainPage();

        var fieldMonthEmpty = DataHelper.getMonthEmpty();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(fieldMonthEmpty);
        creditPage.getInvalidFormatCredit();

    }
    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Год")
    @Test
    public void shouldNotCreditEmptyYear()  {
        MainPage mainPage = new MainPage();

        var fieldYearEmpty = DataHelper.getYearEmpty();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(fieldYearEmpty);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле Владелец")
    @Test
    public void shouldNotCreditEmptyHolder()  {
        MainPage mainPage = new MainPage();

        var fieldHolderEmpty = DataHelper.getHolderEmpty();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(fieldHolderEmpty);
        creditPage.getRequiredFieldCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Пустое поле CVV")
    @Test
    public void shouldNotCreditEmptyCvv()  {
        MainPage mainPage = new MainPage();

        var fieldCvvEmpty = DataHelper.getCVVEmpty();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(fieldCvvEmpty);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Невалидный номер карты")
    @Test
    public void shouldNotCreditInvalidNumber()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getInvalidNumber();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Невалидное значение в поле Месяц")
    @Test
    public void shouldNotCreditWrongMonth()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getInvalidMonth();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidDateCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Невалидное значение в поле Год")
    @Test
    public void shouldNotCreditWrongYear()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getWrongYear();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidDateCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Цифры в поле Владелец")
    @Test
    public void shouldNotCreditNumericHolder()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getNumericName();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Неверный формат CVV-код")
    @Test
    public void shouldNotCreditInvalidCVV()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getInvalidCVV();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Истёкший месяц карты")
    @Test
    public void shouldNotCreditExpiredMonth()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getExpiredMonth();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidDateCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Истёкший год карты")
    @Test
    public void shouldNotCreditExpiredYear()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getExpiredYear();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.expiredCreditYear();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Поле Номер карты - нули")
    @Test
    public void shouldNotCreditZeroNumber()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getZeroCard();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidFormatCredit();

    }

    @Epic(value = "UI-тесты")
    @DisplayName("Поле Месяц значение - 0")
    @Test
    public void shouldNotCreditZeroMonth()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getZeroMonth();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidDateCredit();

    }
    @Epic(value = "UI-тесты")
    @DisplayName("Поле CVV значение - 000")
    @Test
    public void shouldNotCreditZeroCVV()  {
        MainPage mainPage = new MainPage();

        var invalidCard = DataHelper.getZeroCVV();

        CreditPage creditPage = mainPage.creditButtonClick();
        creditPage.inputCreditData(invalidCard);
        creditPage.getInvalidFormatCredit();

    }
}
