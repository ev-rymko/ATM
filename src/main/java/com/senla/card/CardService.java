package com.senla.card;

import com.senla.exceptions.InvalidAmountException;
import com.senla.exceptions.WrongDataException;

import java.util.ArrayList;

public class CardService implements ICardService {
    private static final String INSUFFICIENT_FUNDS = "Sorry, there are not enough funds on your card to complete the transaction. Please try again.";
    private static final String EXCESS_AMOUNT = "Sorry, the amount should not exceed 1000000. Please try again.";
    private final CardDao cardDao = new CardDao();

    @Override
    public Card create(long cardNumber, int password, double balance) {
        return cardDao.create(cardNumber, password, balance);
    }

    @Override
    public Card delete(long cardNumber, int pass) {
        try {
            return cardDao.delete(cardNumber, pass);
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }
        return new Card();
    }

    @Override
    public Card update(long cardNumber, int previousPassword, int newPassword, double newBalance) {
        try {
            return cardDao.update(cardNumber, previousPassword, newPassword, newBalance);
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }
        return new Card();
    }

    @Override
    public ArrayList<Card> showAll() {
        return cardDao.showAll();
    }

    @Override
    public Card getByNumberAndPassword(long cardNumber, int password) {
        try {
            return cardDao.get(cardNumber, password);
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public double getBalance(long cardNumber, int pass) {
        Card card = cardDao.get(cardNumber, pass);
        return card.getBalance();
    }

    @Override
    public double withdrawingMoney(long cardNumber, int pass, double amount) {
        Card card = cardDao.get(cardNumber, pass);
        if (amount > card.getBalance()) {
            throw new InvalidAmountException(INSUFFICIENT_FUNDS);
        }
        double newBalance = card.getBalance() - amount;
        cardDao.update(cardNumber, pass, pass, newBalance);
        return newBalance;
    }

    @Override
    public double refillBalance(long cardNumber, int pass, double amount) {
        Card card = cardDao.get(cardNumber, pass);
        if (amount > 1000000) {
            throw new InvalidAmountException(EXCESS_AMOUNT);
        }
        double newBalance = card.getBalance() + amount;
        cardDao.update(cardNumber, pass, pass, newBalance);
        return newBalance;
    }
}
