package com.senla.menu;

import com.senla.card.Card;
import com.senla.card.CardService;
import com.senla.exceptions.InvalidAmountException;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserMenu implements IMenu {
    private final CardService cardService = new CardService();
    private final CardNumberVerification cardNumberVerification = new CardNumberVerification();
    private static final String ENTER_CARD_NUMBER = "Enter card number(must consist of 16 numbers): ";
    private static final String ENTER_PASSWORD = "Enter password: ";

    @Override
    public void start() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);

        long cardNumber;
        int password;
        do {
            System.out.println(ENTER_CARD_NUMBER);
            cardNumber = scanner.nextLong();
            cardNumberVerification.checkCardNumber(cardNumber);
            System.out.println(ENTER_PASSWORD);
            password = scanner.nextInt();

            for (int i = 0; true; i++) {
                Card card = cardService.getByNumberAndPassword(cardNumber, password);
                if (card != null) {
                    break;
                }
                if (i == 2) {
                    System.out.println("Your card has been blocked for 24 ours. The lock will be removed at: "
                            + LocalDateTime.now().plusHours(24));
                    System.exit(0);
                }
                System.out.println(ENTER_CARD_NUMBER);
                cardNumber = scanner.nextInt();
                cardNumberVerification.checkCardNumber(cardNumber);
                System.out.println(ENTER_PASSWORD);
                password = scanner.nextInt();
            }
            System.out.println("1. VIEW BALANCE");
            System.out.println("2. WITHDRAW MONEY FROM A CARD");
            System.out.println("3. REFILL THE CARD");
            System.out.println("0. EXIT");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Your balance: " + cardService.getBalance(cardNumber, password));
                }
                case 2 -> {
                    System.out.println("Amount: ");
                    int amount = scanner.nextInt();
                    try {
                        System.out.println("The money was successfully withdrawn. Your balance is " +
                                cardService.withdrawingMoney(cardNumber, password, amount));
                    } catch (InvalidAmountException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Amount: ");
                    int amountForRefill = scanner.nextInt();
                    try {
                        System.out.println("Your balance was successfully refilled. Your balance is " +
                                cardService.refillBalance(cardNumber, password, amountForRefill));
                    } catch (InvalidAmountException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } while (choice != 0);
    }
}
