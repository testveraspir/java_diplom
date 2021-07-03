package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.TravelDay;

import static com.codeborne.selenide.Selenide.open;

public class PaymentInCreditTest {
    private final static String URL = "http://localhost:8080";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void validFiledPaymentCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardApproved();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageAboutLuck();
    }

    @Test
    void invalidFiledCardDeflectedCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardDeflected();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageAboutError();
    }

    @Test
    void invalidFiledCardNotExistCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardNotExist();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageAboutError();
    }

    @Test
    void emptyFiledCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        paymentByCard.emptyField();
        paymentByCard.shouldInscriptionFillInField();
    }

    @Test
    void invalidFormatDataCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardInvalidFormat();
        val month = DataHelper.Month.monthInvalidFormat();
        val year = DataHelper.Year.yearInvalidFormat();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvInvalidFormat();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldInscriptionInvalidFormat();
    }

    @Test
    void invalidFieldMonthCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardApproved();
        val month = DataHelper.Month.monthInvalidWith13();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageUnderFieldMonth();
    }

    @Test
    void invalidFieldYearCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardApproved();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearInvalidDate();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageUnderFieldYearInvalid();
    }

    @Test
    void invalidFieldMonthCurrentYearCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardApproved();
        val monthYear = DataHelper.MonthYear.invalidMonthCurrentYear();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCardInvalidPeriod(numberCard, monthYear, name, cvcCvv);
        paymentByCard.shouldMessageUnderFieldMonth();
    }

    @Test
    void invalidFieldYearFinishPeriodCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardApproved();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearFinishPeriod();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageUnderFieldYearFinishDate();
    }

    @Test
    void invalidFieldNameCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardApproved();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameGenerate("ru");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageUnderFieldNameInvalid();
    }

    @Test
    void notShouldMessageLuckIfCardNotExistCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardNotExist();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageAboutError();
        paymentByCard.clickCloseMessageLuck();
        paymentByCard.notShouldMessageAboutLuck();
    }
}
