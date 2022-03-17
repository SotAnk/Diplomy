package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    static Faker faker = new Faker(new Locale("en"));
    static Random random = new Random();
    static LocalDate date = LocalDate.now().plusYears(2);
    static LocalDate wrongDate = LocalDate.now().minusYears(1);
    static String monthNumber = Integer.toString(date.getMonthValue());
    static String wrongMonthNumber = Integer.toString(wrongDate.getMonthValue());
    static int cvc = 100 + random.nextInt(999 - 100);
    static String correctCVC = Integer.toString(cvc);
    static String year = Integer.toString(date.getYear());
    static String wrongYear = Integer.toString(wrongDate.getYear());

    private DataHelper() {

    }

    public static CardInformation getValidCardInformation() {
        return new CardInformation("4444 4444 4444 4441", year, monthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInformation getInvalidCardInformation() {
        return new CardInformation("4444 4444 4444 4443", year, monthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInformation getCardInformationWithWrongLongCardNumber() {

        return new CardInformation("4444 4444 4444 444", year, monthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInformation getCardInformationWithShortestCardNumber() {

        return new CardInformation(wrongMonthNumber, year, monthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInformation getCardInformationWithWrongMonth() {
        return new CardInformation("4444 4444 4444 4441", year, wrongMonthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInformation getCardInformationWithWrongYear() {
        return new CardInformation("4444 4444 4444 4441", wrongYear, monthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInformation getCardInformationWithWrongYearWithOneNumber() {
        return new CardInformation("4444 4444 4444 4441", wrongMonthNumber, monthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInformation getCardInformationWithWrongCvc() {
        return new CardInformation("4444 4444 4444 4441", year, monthNumber, faker.name().fullName(), wrongYear);
    }

    public static CardInformation getCardInformationWithWrongHolderName() {
        return new CardInformation("4444 4444 4444 4441", year, monthNumber, correctCVC, correctCVC);
    }

    public static CardInformation getCardInformationWithoutName() {
        return new CardInformation("4444 4444 4444 4441", year, monthNumber, " ", correctCVC);
    }

    @Value
    public static class CardInformation {
        private String number, year, month, holder, cvc;
    }
}
