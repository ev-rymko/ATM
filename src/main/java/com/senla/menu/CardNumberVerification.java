package com.senla.menu;

public class CardNumberVerification {
    public void checkCardNumber(long cardNumber) {
        String cardNumberToString = String.valueOf(cardNumber);
        if (cardNumberToString.length() == 16) {
            StringBuilder sb = new StringBuilder(cardNumberToString);
            sb.insert(4, "-");
            sb.insert(9, "-");
            sb.insert(13, "-");
            System.out.println(sb);
        } else {
            System.out.println("Invalid card number!");
            System.exit(0);
        }
    }
}
