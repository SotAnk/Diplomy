package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardData {

    private SelenideElement cardNumberField = $("form div:nth-child(1) .input__control");
    private SelenideElement cardNumberFieldError = $("form div:nth-child(1) .input__sub");
    private SelenideElement cardMonthField = $("form div:nth-child(2) > span > span:nth-child(1) .input__control");
    private SelenideElement cardMonthFieldError = $("form div:nth-child(2) > span > span:nth-child(1) .input__sub");
    private SelenideElement cardYearField = $("form div:nth-child(2) > span > span:nth-child(2) .input__control");
    private SelenideElement cardYearFieldError = $("form div:nth-child(2) > span > span:nth-child(2) .input__sub");
    private SelenideElement cardOwnerField = $("form div:nth-child(3) > span > span:nth-child(1) .input__control");
    private SelenideElement cardOwnerFieldError = $("form div:nth-child(3) > span > span:nth-child(1) .input__sub");
    private SelenideElement cardCodeField = $("form div:nth-child(3) > span > span:nth-child(2) .input__control");
    private SelenideElement cardCodeFieldError = $("form div:nth-child(3) > span > span:nth-child(2) .input__sub");
    private SelenideElement continueButton = $("form div:nth-child(4) .button__content");
    private SelenideElement successNotification = $(".notification_status_ok .notification__content");
    private SelenideElement errorMessage = $(".notification_status_error .notification__content");

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