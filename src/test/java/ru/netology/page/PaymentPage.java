package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private final SelenideElement heading = $(byText("Оплата по карте"));
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement holderField = fields.get(3);
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement continueButton = $(withText("Продолжить"));

    private final SelenideElement successNotification = $(withText("Успешно"));
    private final SelenideElement errorNotification = $(withText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement invalidFormat = $(withText("Неверный формат"));
    private final SelenideElement requiredField = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement expiredYearError = $(withText("Истёк срок действия карты"));
    private final SelenideElement invalidDateError = $(withText("Неверно указан срок действия карты"));

    public PaymentPage() {
        heading.shouldBe(visible);
        cardNumberField.shouldBe(visible);
        monthField.shouldBe(visible);
        yearField.shouldBe(visible);
        holderField.shouldBe(visible);
        cvcField.shouldBe(visible);
        continueButton.shouldBe(visible);
        successNotification.shouldBe(hidden);
        errorNotification.shouldBe(hidden);
    }

    public void inputData(DataHelper.CardInformation cardInformation) {
        cardNumberField.setValue(cardInformation.getCardNumber());
        monthField.setValue(cardInformation.getMonth());
        yearField.setValue(cardInformation.getYear());
        holderField.setValue(cardInformation.getHolder());
        cvcField.setValue(cardInformation.getCVV());
        continueButton.click();
    }

    public void getSuccessNotification() {
        successNotification.should(visible, Duration.ofSeconds(15));
    }

    public void getErrorNotification() {
        errorNotification.should(visible, Duration.ofSeconds(15));
    }
    public void getInvalidFormatCard() {
        invalidFormat.shouldBe(visible);
    }

    public void getRequiredFieldCard() {
        requiredField.shouldBe(visible);
    }

    public void expiredCardYear() {
        expiredYearError.shouldBe(visible);
    }

    public void getInvalidDateCard() {
        invalidDateError.shouldBe(visible);
    }
}