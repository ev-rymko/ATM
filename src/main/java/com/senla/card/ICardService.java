package com.senla.card;

import java.util.ArrayList;

public interface ICardService {
    Card create(long cardNumber, int password, double balance);

    Card delete(long cardNumber, int pass);

    Card update(long cardNumber, int previousPassword, int newPassword, double newBalance);

    ArrayList<Card> showAll();

    Card getByNumberAndPassword(long cardNumber, int password);

    double getBalance(long cardNumber, int pass);

    double withdrawingMoney(long cardNumber, int pass, double amount);

    double refillBalance(long cardNumber, int pass, double amount);
}
