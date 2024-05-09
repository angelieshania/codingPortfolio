/*
 * Project       : Kattis's Quest
 *
 * Program Description:
     This Java program manages financial transactions by processing a series of 'add' and 'query' commands.
     It maintains a record of expenses and the corresponding amounts spent. 'add' commands add amounts to
     specific expenses, while 'query' commands calculate the maximum total amount that can be spent to cover
     expenses within a specified budget.

     * Input:
       - The program expects input from the standard input (keyboard) in the following format:
       - The first line contains an integer 'numberOfQueries,' representing the total number of queries to process.
       - Each of the following 'numberOfQueries' lines contains one of two types of commands:
            1. "add expense amount" to record expenses and corresponding amounts.
            2. "query targetExpense" to calculate the maximum total amount within the given budget.

     * Output:
       - For each "query" command, the program outputs an integer representing the maximum total amount that can be
         spent to cover expenses within the specified budget.

 * Pseudocode:
    Create a TreeMap called 'expenseMap' to store expenses and corresponding amounts

    Read the number of queries 'numberOfQueries' from input

    For each query from 1 to 'numberOfQueries':
        Read the query line from input
        Split the query line into 'queryTokens'
        Extract the command from 'queryTokens'

        If the command is 'add':
            Parse the 'amount' and 'expense' from 'queryTokens'
            Add 'amount' to the PriorityQueue in 'expenseMap' associated with 'expense'

        If the command is 'query':
            Parse the 'targetExpense' from 'queryTokens'
            Initialize 'totalAmount' to 0

            While 'targetExpense' is greater than 0 and 'expenseMap' is not empty:
                Find the largest expense key (floorKey) in 'expenseMap' not exceeding 'targetExpense'

                If no key is found, break out of the loop

                Get the PriorityQueue of amounts associated with the largest expense
                Pop the largest amount from the PriorityQueue and add it to 'totalAmount'

                If the PriorityQueue is empty after popping, remove the corresponding expense from 'expenseMap'

                Update 'targetExpense' by subtracting the largest expense from it

            Write 'totalAmount' to the output

    Close the output writer
 */

import java.io.*;
import java.util.*;

public class KattisQuest {
    public static void main(String[] args)
            throws IOException {
        // Create input and output streams for reading and writing data
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Create a data structure to store expenses and corresponding amounts
        TreeMap<Long, PriorityQueue<Long>> expenseMap = new TreeMap<>();

        // Read the number of queries from input
        int numberOfQueries = Integer.parseInt(reader.readLine());

        // Loop through each query
        for (int queryNumber = 1; queryNumber <= numberOfQueries; queryNumber++) {
            // Read the query line
            String queryLine = reader.readLine();
            // Split the query line into tokens
            String[] queryTokens = queryLine.split(" ");
            // Extract the command from the tokens
            String command = queryTokens[0];

            // Check if the command is 'add'
            if (command.equals("add")) {
                // Parse the amount and expense from the tokens
                Long amount = Long.parseLong(queryTokens[2]);
                Long expense = Long.parseLong(queryTokens[1]);
                // Add the amount to the PriorityQueue associated with the expense
                expenseMap.computeIfAbsent(expense, key -> new PriorityQueue<>(Collections.reverseOrder())).add(amount);
            } else { // The command is 'query'
                // Parse the target expense from the tokens
                Long targetExpense = Long.parseLong(queryTokens[1]);
                // Initialize the total amount to 0
                Long totalAmount = 0L;

                // Process the query by deducting expenses from the targetExpense
                while (targetExpense > 0 && !expenseMap.isEmpty()) {
                    // Find the largest expense not exceeding the targetExpense
                    Long largestExpense = expenseMap.floorKey(targetExpense);

                    // If no suitable expense is found, exit the loop
                    if (largestExpense == null) {
                        break;
                    }

                    // Get the PriorityQueue of amounts associated with the largest expense
                    PriorityQueue<Long> amountQueue = expenseMap.get(largestExpense);
                    // Add the largest amount to the totalAmount
                    totalAmount += amountQueue.poll();

                    // If the PriorityQueue is empty after the deduction, remove the expense
                    if (amountQueue.isEmpty()) {
                        expenseMap.remove(largestExpense);
                    }

                    // Update the targetExpense by subtracting the deducted expense
                    targetExpense -= largestExpense.longValue();
                }

                // Write the totalAmount to the output
                writer.write(totalAmount + "\n");
            }
        }

        // Close the output writer
        writer.close();
    }
}






