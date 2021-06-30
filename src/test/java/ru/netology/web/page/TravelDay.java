package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class TravelDay {
    private SelenideElement buttonPay = $$(".button__text").find(Condition.text("Купить"));
    private SelenideElement buttonPayCredit = $$(".button__text").find(Condition.text("Купить в кредит"));

    public PaymentByCard clickButtonPay() {
        buttonPay.click();
        return new PaymentByCard();
    }

    public PaymentInCredit clickButtonPayCredit() {
        buttonPayCredit.click();
        return new PaymentInCredit();
    }
}
