/*
 * Project       : Coconut Splat
 *
 * Program Description:
 *
 * This Java program simulates a coconut passing game among a group of players.
 * The game starts with a specified number of coconuts and players. In each round,
 * a coconut is passed among the players, and certain players change their states
 * based on predefined rules. The game continues until only one player remains.
 * The winning player's number is then displayed.
 *
 * The program uses a list of player objects and enums to represent player states.
 * It also includes methods to change player states and calculate the game's outcome.
 *
 * Pseudocode:
    1. Read input values for totalCoconuts and totalPlayers from the console.

    2. Create an empty list to store player objects.

    3. Initialize currentPlayerIndex and playerNumber variables to 0.

    4. Initialize players list with Player objects, each assigned a unique player number and state HOLDING_COCONUT.
       a. Repeat until playerNumber is less than totalPlayers:
          i. Create a new Player object with playerNumber and state HOLDING_COCONUT.
          ii. Add the Player object to the players list.
          iii. Increment playerNumber by 1.

    5. While the size of the players list is greater than 1 (i.e., more than one player is left):
       a. Calculate the index of the next player to take an action using currentPlayerIndex, totalCoconuts, and the size of the players list.
       b. Get the current player from the players list using the calculated index.
       c. Check the state of the current player:
          i. If the current player is HOLDING_COCONUT:
             - Change the state of the current player to PASSING_COCONUT.
             - Clone the current player as a new player with state PASSING_COCONUT.
             - Insert the cloned player after the current player in the players list.
          ii. If the current player is PASSING_COCONUT:
             - Change the state of the current player to HOLDING_COCONUT.
             - Move to the next player in the players list by incrementing currentPlayerIndex.
          iii. If the current player is ELIMINATED:
             - Change the state of the current player to ELIMINATED.
             - Remove the current player from the players list.

    6. Print the number of the winning player by accessing the first player in the players list and adding 1 to match player numbers (which start from 1).

    7. End the program.
 */

import java.io.*;
import java.util.List;
import java.util.LinkedList;


public class CoconutSplat {
    public static void main(String[] args)
            throws IOException {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Read input line and split it into two parts: totalCoconuts and totalPlayers
        String[] input = reader.readLine().split(" ");
        int totalCoconuts = Integer.parseInt(input[0]); // Total number of coconuts
        int totalPlayers = Integer.parseInt(input[1]);  // Total number of players

        // Create a list to store Player objects
        List<Player> players = new LinkedList<>();

        // Initialize variables for tracking the current player and player number
        int currentPlayerIndex = 0;
        int playerNumber = 0;

        // Initialize the list of players with their numbers and initial state (HOLDING_COCONUT)
        while (playerNumber < totalPlayers) {
            players.add(new Player(playerNumber, PlayerState.HOLDING_COCONUT));
            playerNumber++;
        }

        // Play the coconut game until only one player is left
        while (players.size() > 1) {
            // Calculate the index of the next player to take an action
            currentPlayerIndex = (currentPlayerIndex + totalCoconuts - 1) % players.size();
            Player currentPlayer = players.get(currentPlayerIndex); // Get the current player

            // Check the state of the current player and perform the corresponding action
            switch (currentPlayer.getState()) {
                case HOLDING_COCONUT:
                    // Change the state of the current player to PASSING_COCONUT
                    currentPlayer.changeState();
                    // Clone the current player as a new player with state PASSING_COCONUT
                    Player clonedPlayer = new Player(currentPlayer.getNumber(), PlayerState.PASSING_COCONUT);
                    // Insert the cloned player after the current player in the list
                    players.add(players.indexOf(currentPlayer) + 1, clonedPlayer);
                    break;
                case PASSING_COCONUT:
                    // Change the state of the current player to HOLDING_COCONUT
                    currentPlayer.changeState();
                    // Move to the next player in the list
                    currentPlayerIndex++;
                    break;
                default: // PlayerState.ELIMINATED
                    // Change the state of the current player to ELIMINATED
                    currentPlayer.changeState();
                    // Remove the current player from the list as they are eliminated
                    players.remove(currentPlayer);
                    break;
            }
        }

        // Print the winning player's number (adding 1 to match the player numbers, which start from 1)
        System.out.println((players.get(0).getNumber() + 1));
    }
}

// Enum to represent the possible states of a player
enum PlayerState {
    HOLDING_COCONUT,
    PASSING_COCONUT,
    ELIMINATED
}

// Class representing a player with a number and state
class Player {
    private final int number; // Player number
    private PlayerState state; // Player's current state

    // Constructor to initialize a player with a number and initial state
    Player(int number, PlayerState state) {
        this.number = number;
        this.state = state;
    }

    // Getter method to get the player's number
    int getNumber() {
        return this.number;
    }

    // Method to change the player's state based on the game rules
    void changeState() {
        // Switch the state based on the current state
        switch (this.state) {
            case HOLDING_COCONUT:
                this.state = PlayerState.PASSING_COCONUT;
                break;
            case PASSING_COCONUT:
                this.state = PlayerState.ELIMINATED;
                break;
            default: // PlayerState.ELIMINATED
                // No action needed if already eliminated
                break;
        }
    }

    // Getter method to get the player's current state
    PlayerState getState() {
        return this.state;
    }

    // Override the toString method to provide a formatted string representation of the player
    @Override
    public String toString() {
        return String.format("Player #%d : State %s", this.number + 1, this.state);
    }
}