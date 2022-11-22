package com.senla.card;

import com.senla.exceptions.WrongDataException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardDao implements ICardDao {
    private static final String RECORD_NOT_FOUND_EXCEPTION = "Wrong card number or password. Please try again. ";
    private static final String LIST_EMPTY = "The list of cards id empty.";
    private static final String ERROR_INITIALIZATION = "Error initialization stream.";
    private static final String FILE_NOT_FOUND = "File not found";
    private static final String FILE_NOT_EXIST = "File not exist";
    File file = new File("cardsDate.txt");
    private ArrayList<Card> cards = new ArrayList<>();

    {
        if (file.isFile()) {
            cards = readCards();
        } else {
            System.out.println(FILE_NOT_EXIST);
        }
    }

    @Override
    public Card create(long cardNumber, int password, double balance) {
        Card card = new Card(cardNumber, password, balance);
        cards.add(card);
        writeCards();
        return card;
    }

    @Override
    public Card get(long cardNumber, int pass) {
        List<Card> resultCards = cards.stream()
                .filter(c -> c.getCardNumber() == cardNumber && c.getPassword() == pass)
                .collect(Collectors.toList());
        if (resultCards.size() != 0) {
            return resultCards.get(0);
        } else {
            throw new WrongDataException(RECORD_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Card delete(long cardNumber, int pass) {
        Card card = get(cardNumber, pass);
        cards.remove(card);
        writeCards();
        return card;
    }

    @Override
    public Card update(long cardNumber, int previousPassword, int newPassword, double newBalance) {
        Card card = get(cardNumber, previousPassword);
        Card resultCard = cards.set(cards.indexOf(card), new Card(cardNumber, newPassword, newBalance));
        writeCards();
        return resultCard;
    }

    @Override
    public ArrayList<Card> showAll() {
        if (cards != null) {
            return cards;
        } else {
            throw new RuntimeException(LIST_EMPTY);
        }
    }

    private void writeCards() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file))) {
            oos.writeObject(cards);
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(ERROR_INITIALIZATION);
        }
    }

    private ArrayList<Card> readCards() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            cards = (ArrayList<Card>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(ERROR_INITIALIZATION);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cards;
    }

}
