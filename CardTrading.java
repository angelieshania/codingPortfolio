/*
 * Project       : Card Trading
 *
 * This Java program calculates the maximum profit achievable by trading cards based on their
 * buy and sell prices. It reads input data containing card counts, buy and sell prices for each
 * card, and the number of cards to buy or sell. The program computes the optimal trading strategy,
 * sorts the cards by cost, and calculates the profit that can be obtained.
 *
 * Input: Total cards, total prices, cards to buy/sell, card numbers, buy and sell prices.
 * Output: Maximum profit achievable by buying and selling cards.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class CardTrading {
    public static void main(String[] args)
            throws IOException {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read the first line containing totalCards, totalPrices, and cardsToBuySell
        String[] firstLine = reader.readLine().split(" ");
        int totalCards = Integer.parseInt(firstLine[0]);
        int totalPrices = Integer.parseInt(firstLine[1]);
        int cardsToBuySell = Integer.parseInt(firstLine[2]);

        // Initialize an array to store the count of each card
        int[] cardCounts = new int[1000001];

        // Read the second line containing card numbers
        String[] cardNumbers = reader.readLine().split(" ");
        for (String cardNumber : cardNumbers) {
            int card = Integer.parseInt(cardNumber);
            cardCounts[card]++;
        }

        // Create a list to store card prices
        ArrayList<CardPrice> cardPriceList = new ArrayList<>();

        // Read totalPrices lines of card prices
        for (int i = 0; i < totalPrices; i++) {
            String[] priceLine = reader.readLine().split(" ");
            int cardNumber = i + 1;
            int buyPrice = Integer.parseInt(priceLine[0]);
            int sellPrice = Integer.parseInt(priceLine[1]);

            // Calculate buy and sell costs for each card
            int buyCost = (2 - cardCounts[cardNumber]) * buyPrice;
            int sellCost = cardCounts[cardNumber] * sellPrice;

            // Create a CardPrice object and add it to the list
            cardPriceList.add(new CardPrice(cardNumber, buyCost, sellCost));
        }

        // Sort the cardPriceList using the CardPriceComparator
        Collections.sort(cardPriceList, new CardPriceComparator());

        // Calculate and print the profit
        BigInteger profit = calculateProfit(cardPriceList, cardsToBuySell);
        System.out.println(profit);
    }

    // Calculate profit based on the sorted list of card prices and cardsToBuySell
    private static BigInteger calculateProfit(ArrayList<CardPrice> cardPriceList, int cardsToBuySell) {
        BigInteger profit = BigInteger.ZERO;

        for (int i = 0; i < cardPriceList.size(); i++) {
            CardPrice cardPrice = cardPriceList.get(i);

            if (i < cardsToBuySell) {
                // Subtract buy cost for the first cardsToBuySell cards
                profit = profit.subtract(BigInteger.valueOf(cardPrice.getBuyCost()));
            } else {
                // Add sell cost for the rest of the cards
                profit = profit.add(BigInteger.valueOf(cardPrice.getSellCost()));
            }
        }

        return profit;
    }
}

// Represents a card price
class CardPrice {
    private final int cardNumber;
    private final int buyCost;
    private final int sellCost;

    // Constructor to initialize card price details
    CardPrice(int cardNumber, int buyCost, int sellCost) {
        this.cardNumber = cardNumber;
        this.buyCost = buyCost;
        this.sellCost = sellCost;
    }

    // Getter method for card number
    int getCardNumber() {
        return cardNumber;
    }

    // Getter method for buy cost
    int getBuyCost() {
        return buyCost;
    }

    // Getter method for sell cost
    int getSellCost() {
        return sellCost;
    }
}

// Comparator for sorting card prices
class CardPriceComparator implements Comparator<CardPrice> {
    @Override
    public int compare(CardPrice cardPrice1, CardPrice cardPrice2) {
        int cost1 = cardPrice1.getBuyCost() + cardPrice1.getSellCost();
        int cost2 = cardPrice2.getBuyCost() + cardPrice2.getSellCost();

        if (cost1 != cost2) {
            return cost1 - cost2;
        } else {
            return cardPrice1.getBuyCost() - cardPrice2.getBuyCost();
        }
    }
}