package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentByCard {
    private SelenideElement heading = $$("h3").find(Condition.text("Оплата по карте"));
    private SelenideElement numberCardField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement nameField = $$(".input__inner").find(Condition.text("Владелец")).$(".input__control");
    private SelenideElement cvcCvvField = $("[placeholder='999']");
    private SelenideElement buttonContinue = $$(".button__text").find(Condition.text("Продолжить"));
    private SelenideElement buttonClose = $(".notification_status_error").$("button");

    public PaymentByCard() {
        heading.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void paymentByCard(DataHelper.NumberCard numberCard, DataHelper.Month month, DataHelper.Year year, DataHelper.Name name, DataHelper.CvcCvv cvcCvv) {
        numberCardField.setValue(numberCard.getNumberCard());
        monthField.setValue(month.getMonth());
        yearField.setValue(year.getYear());
        nameField.setValue(name.getName());
        cvcCvvField.setValue(cvcCvv.getCvcCvv());
        buttonContinue.click();

    }


    public void emptyField() {
        buttonContinue.click();
    }

    public void paymentByCardInvalidPeriod(DataHelper.NumberCard numberCard, DataHelper.MonthYear monthYear, DataHelper.Name name, DataHelper.CvcCvv cvcCvv) {
        numberCardField.setValue(numberCard.getNumberCard());
        monthField.setValue(monthYear.getMonthInvalid());
        yearField.setValue(monthYear.getCurrentYear());
        nameField.setValue(name.getName());
        cvcCvvField.setValue(cvcCvv.getCvcCvv());
        buttonContinue.click();
    }

    public void clickCloseMessageLuck() {
        buttonClose.click();
    }


    public void shouldMessageAboutLuck() {
        $(".notification_status_ok").shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void shouldMessageAboutError() {
        $(".notification_status_error").shouldBe(Condition.visible, Duration.ofSeconds(20));

    }

    public void shouldInscriptionFillInField() {
        $$(".input__inner").find(Condition.text("Номер карты")).$(".input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        $$(".input__inner").find(Condition.text("Месяц")).$(".input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        $$(".input__inner").find(Condition.text("Год")).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        $$(".input__inner").find(Condition.text("CVC/CVV")).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        $$(".input__inner").find(Condition.text("Владелец")).$(".input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));


    }

    public void shouldInscriptionInvalidFormat() {
        $$(".input__inner").find(Condition.text("Номер карты")).$(".input__sub").shouldHave(Condition.exactText("Неверный формат"));
        $$(".input__inner").find(Condition.text("Месяц")).$(".input__sub").shouldHave(Condition.exactText("Неверный формат"));
        $$(".input__inner").find(Condition.text("Год")).$(".input__sub").shouldHave(Condition.exactText("Неверный формат"));
        $$(".input__inner").find(Condition.text("CVC/CVV")).$(".input__sub").shouldHave(Condition.exactText("Неверный формат"));

    }

    public void shouldMessageUnderFieldMonth() {
        $$(".input__inner").find(Condition.text("Месяц")).$(".input__sub").shouldHave(Condition.exactText("Неверно указан срок действия карты"));


    }

    public void shouldMessageUnderFieldYearInvalid() {
        $$(".input__inner").find(Condition.text("Год")).$(".input__sub").shouldHave(Condition.exactText("Неверно указан срок действия карты"));

    }

    public void shouldMessageUnderFieldYearFinishDate() {
        $$(".input__inner").find(Condition.text("Год")).$(".input__sub").shouldHave(Condition.exactText("Истёк срок действия карты"));

    }

    public void shouldMessageUnderFieldNameInvalid() {
        $$(".input__inner").find(Condition.text("Владелец")).$(".input__sub").shouldHave(Condition.exactText("Введены некорректные данные"));

    }

    public void notShouldMessageAboutLuck() {
        $(".notification_status_ok").shouldBe(Condition.hidden);

    }

    public void shouldInscriptionAbout0() {
        $$(".input__inner").find(Condition.text("Номер карты")).$(".input__sub").shouldHave(Condition.exactText("Поле не должно состоять целиком из нулей"));
        $$(".input__inner").find(Condition.text("Месяц")).$(".input__sub").shouldHave(Condition.exactText("Поле не должно состоять целиком из нулей"));
        $$(".input__inner").find(Condition.text("CVC/CVV")).$(".input__sub").shouldHave(Condition.exactText("Поле не должно состоять целиком из нулей"));

    }
}

