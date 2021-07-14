package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.TravelDay;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @AfterEach
    void clear() {
        SQLHelper.clearTables();
    }

    // проверка одобренной карты и подключения базы данных
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
        assertEquals(SQLHelper.APPROVED, SQLHelper.getStatusPayment(SQLHelper.REQUEST_STATUS_CREDIT, SQLHelper.COLUMN_STATUS_CREDIT));
        assertEquals(SQLHelper.getStatusPayment(SQLHelper.REQUEST_BANK_ID_CREDIT, SQLHelper.COLUMN_BANK_ID_CREDIT), SQLHelper.getStatusPayment(SQLHelper.REQUEST_ORDER_ENTITY, SQLHelper.COLUMN_PAYMENT_ID));
    }

    // проверка отклоненной карты и подключения базы данных
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
        assertEquals(SQLHelper.DECLINED, SQLHelper.getStatusPayment(SQLHelper.REQUEST_STATUS_CREDIT, SQLHelper.COLUMN_STATUS_CREDIT));
        assertEquals(SQLHelper.getStatusPayment(SQLHelper.REQUEST_BANK_ID_CREDIT, SQLHelper.COLUMN_BANK_ID_CREDIT), SQLHelper.getStatusPayment(SQLHelper.REQUEST_ORDER_ENTITY, SQLHelper.COLUMN_PAYMENT_ID));
    }

    // проверка несуществующей карты
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

    // проверка отсутствия данных в полях
    @Test
    void emptyFiledCredit() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        paymentByCard.emptyField();
        paymentByCard.shouldInscriptionFillInField();
    }

    // проверка ввода данных в неверном формате
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

    // проверка ввода месяца больше 12
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

    // проверка ввода года карты, сроком действия больше пяти лет
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

    //проверка истекшего месяца в текущем году
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

    //проверка ввода карты с истекшим годом
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

    //проверка ввода владельца карты на русском языке
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

    //проверка отсутствия сообщения об успехе
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

    //проверка ввода нулевых значений в поля: номер карты, месяц, cvc/cvv
    @Test
    void shouldMessageFieldFrom0() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.number0();
        val month = DataHelper.Month.month0();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameGenerate("en");
        val cvcCvv = DataHelper.CvcCvv.cvcCvv0();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldInscriptionAbout0();

    }

    //проверка ввода в поле “Владелец” цифр
    @Test
    void shouldMessageErrorInvalidName() {
        val travelDay = open(URL, TravelDay.class);
        val paymentByCard = travelDay.clickButtonPayCredit();
        val numberCard = DataHelper.NumberCard.numberCardApproved();
        val month = DataHelper.Month.monthValid();
        val year = DataHelper.Year.yearValid();
        val name = DataHelper.Name.nameNumber();
        val cvcCvv = DataHelper.CvcCvv.cvcCvvValid();
        paymentByCard.paymentByCard(numberCard, month, year, name, cvcCvv);
        paymentByCard.shouldMessageUnderFieldNameInvalid();
    }

}
