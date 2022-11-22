package com.senla.card;

import java.io.Serializable;

public class Card implements Serializable {
    private static long finalVersionUID = 1L;
    private long cardNumber;
    private int password;
    private double balance;

    public Card(long cardNumber, int password, double balance) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = balance;
    }

    public Card() {
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNumber +
                ", password=" + password +
                ", balance=" + balance +
                '}';
    }
}
