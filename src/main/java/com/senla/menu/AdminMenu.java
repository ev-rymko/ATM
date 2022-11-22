package com.senla.menu;

import com.senla.card.Card;
import com.senla.card.CardService;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu implements IMenu {

    private static final String ENTER_CARD_NUMBER = "Enter card number(must consist of 16 numbers): ";
    private static final String ENTER_PASSWORD = "Enter password: ";
    private final CardService cardService = new CardService();
    private final CardNumberVerification cardNumberVerification = new CardNumberVerification();

    @Override
    public void start() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. ADD A NEW CARD");
            System.out.println("2. DISPLAY ALL CARDS");
            System.out.println("3. DELETE A CARD");
            System.out.println("4. UPDATE A CARD");
            System.out.println("0. EXIT");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println(ENTER_CARD_NUMBER);
                    long cardNumber = scanner.nextLong();
                    cardNumberVerification.checkCardNumber(cardNumber);
                    System.out.println(ENTER_PASSWORD);
                    int password = scanner.nextInt();
                    System.out.println("Enter balance: ");
                    double balance = scanner.nextInt();
                    cardService.create(cardNumber, password, balance);
                    System.out.println("Card has been successfully added.");
                }
                case 2 -> {
                    ArrayList<Card> cards = cardService.showAll();
                    for (Card card : cards) {
                        System.out.println(card);
                    }
                }
                case 3 -> {
                    System.out.println(ENTER_CARD_NUMBER);
                    long cardNumberForDelete = scanner.nextLong();
                    cardNumberVerification.checkCardNumber(cardNumberForDelete);
                    System.out.println(ENTER_PASSWORD);
                    int passwordForDelete = scanner.nextInt();
                    System.out.println(cardService.delete(cardNumberForDelete, passwordForDelete));
                    System.out.println("Card has been successfully deleted.");
                }
                case 4 -> {
                    System.out.println(ENTER_CARD_NUMBER);
                    long cardNumberForUpdate = scanner.nextLong();
                    cardNumberVerification.checkCardNumber(cardNumberForUpdate);
                    System.out.println("Enter previous password: ");
                    int previousPassword = scanner.nextInt();
                    System.out.println("Enter new password: ");
                    int newPassword = scanner.nextInt();
                    System.out.println("Enter new balance: ");
                    int newBalance = scanner.nextInt();
                    System.out.println(cardService.update(cardNumberForUpdate, previousPassword, newPassword, newBalance));
                    System.out.println("Card has been successfully updated.");
                }
            }
        } while (choice != 0);
    }
}
