/*
 * Project       : Sort of Sorting
 *
 * Program Description:
 *
 * This Java program reads a list of names, sorts them based on their first and second characters,
 * and prints the sorted names to the console. It supports multiple test cases, where each test case
 * begins with the number of names in that case, followed by the names themselves.
 *
 * Pseudocode:
    Initialize a variable testCaseCount to 0

    Loop indefinitely:
        Read an integer nameCount from the user

        If nameCount is equal to 0, exit the loop

        If testCaseCount is greater than 0:
            Print a newline

        Initialize an empty list named names

        Repeat nameCount times:
            Read a string name from the user
            Add name to the names list

        Sort the names list based on the first and second characters of each name

        For each name in the names list:
            Print the name

        Increment testCaseCount

    Close the output stream
 */

import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class SortofSorting {
    public static void main(String[] args)
            throws IOException {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Create a PrintWriter to write output to the console
        PrintWriter writer = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));

        // Initialize a counter for test cases
        int testCaseCount = 0;
        while (true) {
            // Read the number of names in the current test case
            int nameCount = Integer.parseInt(reader.readLine());
            // If nameCount is 0, exit the loop
            if (nameCount == 0) {
                break;
            }

            // If this is not the first test case, print a newline
            if (testCaseCount > 0) {
                writer.println(); // Use println() for a newline
            }

            // Create a list to store names for the current test case
            List<String> names = new ArrayList<>();
            while (nameCount > 0) {
                // Read each name and add it to the list
                String name = reader.readLine();
                names.add(name);
                nameCount--;
            }

            // Sort the names based on the first and second characters
            Collections.sort(names, (name1, name2) -> {
                if (name1.charAt(0) != name2.charAt(0)) {
                    return name1.charAt(0) - name2.charAt(0);
                } else {
                    return name1.charAt(1) - name2.charAt(1);
                }
            });

            // Print the sorted names
            for (String name : names) {
                writer.println(name);
            }

            // Increment the test case counter
            testCaseCount++;
        }

        // Close the PrintWriter
        writer.close();
    }
}