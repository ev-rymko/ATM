package com.senla.card;

import java.util.ArrayList;

public interface ICardDao {
    Card create(long cardNumber, int password, double balance);

    Card get(long cardNumber, int pass);

    Card delete(long cardNumber, int pass);

    Card update(long cardNumber, int previousPassword, int newPassword, double newBalance);

    ArrayList<Card> showAll();
}
