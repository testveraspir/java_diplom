package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {

    }

    @Value
    public static class NumberCard {
        private String numberCard;


        public static NumberCard numberCardApproved() {
            return new NumberCard("4444444444444441");
        }

        public static NumberCard numberCardDeflected() {
            return new NumberCard("4444444444444442");
        }

        public static NumberCard number0() {
            return new NumberCard("0000000000000000");
        }

        public static NumberCard numberCardNotExist() {
            while (true) {
                long number = 1111111111111111L + (long) (Math.random() * 8888888888888889L);
                if (number != 4444444444444441L && number != 4444444444444442L) {
                    String numberString = String.valueOf(number);
                    return new NumberCard(numberString);
                }
            }
        }

        public static NumberCard numberCardInvalidFormat() {
            long number = (long) (Math.random() * 999999999999999L);
            String numberString = String.valueOf(number);
            return new NumberCard(numberString);
        }

    }

    @Value
    public static class Month {
        private String month;

        public static Month monthValid() {
            int number = 1 + (int) (Math.random() * 12);
            if (number < 10)
                return new Month("0" + number);
            else return new Month(String.valueOf(number));
        }

        public static Month monthInvalidFormat() {
            int number = (int) (Math.random() * 10);
            return new Month(String.valueOf(number));
        }

        public static Month monthInvalidWith13() {
            int number = 13 + (int) (Math.random() * (99 - 13 + 1));
            return new Month(String.valueOf(number));
        }

        public static Month month0() {
            return new Month("00");
        }
    }

    @Value
    public static class Year {
        private String year;

        public static Year yearValid() {
            LocalDate date = LocalDate.now();
            String yearString = date.format(DateTimeFormatter.ofPattern("yy"));
            int yearFirst = Integer.parseInt(yearString) + 1;
            int yearLast = yearFirst + 4;
            int number = yearFirst + (int) (Math.random() * (yearLast - yearFirst + 1));
            return new Year(String.valueOf(number));
        }

        public static Year yearInvalidFormat() {
            int number = (int) (Math.random() * 10);
            return new Year(String.valueOf(number));
        }

        public static Year yearInvalidDate() {
            LocalDate date = LocalDate.now();
            String yearString = date.format(DateTimeFormatter.ofPattern("yy"));
            int year = Integer.parseInt(yearString) + 6;
            int number = year + (int) (Math.random() * (99 - year + 1));
            return new Year(String.valueOf(number));
        }

        public static Year yearFinishPeriod() {
            int number = 1 + (int) (Math.random() * 9);
            return new Year("0" + number);
        }
    }

    @Value
    public static class Name {
        private String name;

        public static Name nameGenerate(String locale) {
            Faker faker = new Faker(new Locale(locale));
            String name = faker.name().firstName() + " " + faker.name().lastName();
            return new Name(name);
        }

        public static Name nameNumber(){
            Faker faker=new Faker();
            String name=String.valueOf(faker.number().numberBetween(1, 9999));
            return new Name(name);
        }
    }

    @Value
    public static class CvcCvv {
        private String cvcCvv;

        public static CvcCvv cvcCvvValid() {
            int number = 111 + (int) (Math.random() * 889);
            return new CvcCvv(String.valueOf(number));
        }

        public static CvcCvv cvcCvvInvalidFormat() {
            int number = (int) (Math.random() * 99);
            return new CvcCvv(String.valueOf(number));
        }

        public static CvcCvv cvcCvv0() {
            return new CvcCvv("000");
        }
    }

    @Value
    public static class MonthYear {
        private String monthInvalid;
        private String currentYear;

        public static MonthYear invalidMonthCurrentYear() {
            LocalDate date = LocalDate.now();
            date = date.minusMonths(1);
            int dateInt = date.getMonthValue();
            String month;
            if (dateInt < 10) {
                month = "0" + dateInt;
            } else {
                month = String.valueOf(dateInt);
            }
            String year = date.format(DateTimeFormatter.ofPattern("yy"));
            return new MonthYear(month, year);

        }
    }

}
