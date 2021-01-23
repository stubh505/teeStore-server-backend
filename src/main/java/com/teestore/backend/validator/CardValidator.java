package com.teestore.backend.validator;


import com.teestore.backend.model.Card;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class CardValidator {

    public static void validateCard(Card c) throws Exception {
        if (!validateCardNumber(c.getCardNumber()))
            throw new Exception("CardValidator.INVALID_CARD_NUMBER_FORMAT");
        if (!validateCardHolderName(c.getCardHolderName()))
            throw new Exception("CardValidator.INVALID_CARD_NAME_FORMAT");
        if (!validateCardExpiryMonthYear(c.getExpiryMonthYear()))
            throw new Exception("CardValidator.INVALID_EXPIRY_DATE");
        if (!validateCardCvv(c.getCvv()))
            throw new Exception("CardValidator.INVALID_CARD_CVV");
    }

    private static Boolean validateCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16)
            return false;
        String reg = "[0-9]{16}";
        return cardNumber.matches(reg);
    }

    private static Boolean validateCardHolderName(String name) {
        if (name == null || name.length() > 50)
            return false;
        if (!name.equals("")) {
            String reg = "([A-Za-z]{2,})+( [A-Za-z]{2,}){0,2}";
            return name.matches(reg);
        }
        return false;
    }

    private static Boolean validateCardExpiryMonthYear(String expiryMonthYear) {

        if (expiryMonthYear == null || expiryMonthYear.equals(""))
            return false;

        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                // month-year
                .appendPattern("MM-yy")
                // default value for day
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                // create formatter
                .toFormatter();
        LocalDate dt = LocalDate.parse(expiryMonthYear, fmt);

        return !dt.isBefore(LocalDate.now()) && !dt.isAfter(LocalDate.now().plusYears(6));
    }

    private static Boolean validateCardCvv(String cvv) {
        if (cvv == null)
            return false;

        if (!cvv.equals("")) {
            String reg = "[0-9]{3}";
            return cvv.matches(reg);
        }
        return false;
    }
}
