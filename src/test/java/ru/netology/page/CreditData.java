package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$;

public class CreditData {

    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardNumberFieldError = cardNumberField.parent().parent().$(".input__sub");
    private SelenideElement cardMonthField = $("input[placeholder='08']");
    private SelenideElement cardMonthFieldError = $x("//*[text()='Месяц']/..//*[@class='input__sub']");
    private SelenideElement cardYearField = $("input[placeholder='22']");
    private SelenideElement cardYearFieldError = $x("//*[text()='Год']/..//*[@class='input__sub']");
    private SelenideElement cardOwnerField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cardOwnerFieldError = $x("//*[text()='Владелец']/..//*[@class='input__sub']");
    private SelenideElement cardCodeField = $("input[placeholder='999']");
    private SelenideElement cardCodeFieldError = $x("//*[text()='CVC/CVV']/..//*[@class='input__sub']");
    private SelenideElement continueButton = $("form button");
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorMessage = $(".notification_status_error");

    public void fillCardInformationForSelectedWay(DataHelper.CardInformation cardInformation) {
        cardNumberField.setValue(cardInformation.getNumber());
        cardMonthField.setValue(cardInformation.getMonth());
        cardYearField.setValue(cardInformation.getYear());
        cardOwnerField.setValue(cardInformation.getHolder());
        cardCodeField.setValue(cardInformation.getCvc());
        continueButton.click();
    }

    public void checkIfPaymentSuccessful() {
        successNotification.waitUntil(Condition.visible, 15000);
    }

    public void checkIfPaymentNotSuccessful() {
        errorMessage.waitUntil(Condition.visible, 15000);
    }

    public void checkIfWrongCardNumber() {
        cardNumberFieldError.shouldBe(Condition.visible);
    }

    public void checkIfWrongCardMonth() {
        cardMonthFieldError.shouldBe(Condition.visible);
    }

    public void checkIfWrongCardYear() {
        cardYearFieldError.shouldBe(Condition.visible);
    }

    public void checkIfWrongCardOwner() {
        cardOwnerFieldError.shouldBe(Condition.visible);
    }

    public void checkIfWrongCardCode() {
        cardCodeFieldError.shouldBe(Condition.visible);
    }

}
