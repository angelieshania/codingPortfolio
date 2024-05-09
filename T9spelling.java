/*
 * Project      : T9 spelling
 *
 * Program Description:
 * This Java program converts text input into T9 spelling, a numeric representation of text messages
 * often found on mobile phones. It reads the number of test cases, input strings, and converts
 * characters to their corresponding T9 representations. The program ensures that spaces are added
 * appropriately to match T9 input behavior.
 *
 * Input: Number of test cases, input strings for each test case.
 * Output: T9 spelling for each input string.
 *
 * Pseudocode:
    Initialize a BufferedReader to read input from the console.
    Initialize a PrintWriter to write output to the console.
    Read the number of test cases from the console.
    Initialize a variable `testCaseNumber` to 1.
    While `numTestCases` is greater than 0:
        a. Read the input string for this test case.
        b. Create an empty StringBuilder called `t9Spelling` to build the T9 spelling.
        c. Convert the input string to an array of characters.
        d. For each character in the input string:
            i. Convert the current character to its T9 representation using the `convertToT9` function.
            ii. Append the T9 representation to the `t9Spelling` StringBuilder.
            iii. If there is a next character and it is the same as the current character or has the same digit in T9:
                - Append a space to the `t9Spelling` StringBuilder.
    Write the result for this test case to the console in the format "Case #X: Y" where X is `testCaseNumber` and Y is `t9Spelling`.
    Decrement `numTestCases` by 1.
    Increment `testCaseNumber` by 1.
    Close the output writer for the console.
    Define a helper function `convertToT9` that takes a character as input and returns its T9 representation as a StringBuilder.
    End of the program.
 */

import java.io.*;

public class T9spelling {
    public static void main(String[] args)
            throws IOException {

        // Create a BufferedReader to read input from the console
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        // Create a PrintWriter to write output to the console
        PrintWriter consoleWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Read the number of test cases from the console
        int numTestCases = Integer.parseInt(consoleReader.readLine());

        int testCaseNumber = 1;
        while (numTestCases > 0) {
            // Read the input string for this test case
            String inputString = consoleReader.readLine();
            // Create a StringBuilder to build the T9 spelling
            StringBuilder t9Spelling = new StringBuilder();

            // Convert the input string to an array of characters
            char[] inputCharacters = inputString.toCharArray();
            for (int i = 0; i < inputCharacters.length; i++) {
                // Convert the current character to its T9 representation
                StringBuilder digitSequence = convertToT9(inputCharacters[i]);
                // Append the T9 representation to the result
                t9Spelling.append(digitSequence);
                if (i < inputCharacters.length - 1) {
                    // If the next character is the same or has the same digit in T9, add a space
                    StringBuilder nextDigitSequence = convertToT9(inputCharacters[i + 1]);
                    if (inputCharacters[i] == inputCharacters[i + 1] || digitSequence.charAt(0) == nextDigitSequence.charAt(0)) {
                        t9Spelling.append(" ");
                    }
                }
            }
            // Write the result for this test case to the console
            consoleWriter.write(String.format("Case #%d: %s", testCaseNumber, t9Spelling) + "\n");
            numTestCases -= 1;
            testCaseNumber += 1;

        }
        // Close the output writer for the console
        consoleWriter.close();
    }

    // Helper function to convert a character to its T9 representation
    static StringBuilder convertToT9(char letter) {
        StringBuilder result = new StringBuilder("0");
        switch (letter) {
            case 'a':
                result = new StringBuilder("2");
                break;
            case 'b':
                result = new StringBuilder("22");
                break;
            case 'c':
                result = new StringBuilder("222");
                break;
            case 'd':
                result = new StringBuilder("3");
                break;
            case 'e':
                result = new StringBuilder("33");
                break;
            case 'f':
                result = new StringBuilder("333");
                break;
            case 'g':
                result = new StringBuilder("4");
                break;
            case 'h':
                result = new StringBuilder("44");
                break;
            case 'i':
                result = new StringBuilder("444");
                break;
            case 'j':
                result = new StringBuilder("5");
                break;
            case 'k':
                result = new StringBuilder("55");
                break;
            case 'l':
                result = new StringBuilder("555");
                break;
            case 'm':
                result = new StringBuilder("6");
                break;
            case 'n':
                result = new StringBuilder("66");
                break;
            case 'o':
                result = new StringBuilder("666");
                break;
            case 'p':
                result = new StringBuilder("7");
                break;
            case 'q':
                result = new StringBuilder("77");
                break;
            case 'r':
                result = new StringBuilder("777");
                break;
            case 's':
                result = new StringBuilder("7777");
                break;
            case 't':
                result = new StringBuilder("8");
                break;
            case 'u':
                result = new StringBuilder("88");
                break;
            case 'v':
                result = new StringBuilder("888");
                break;
            case 'w':
                result = new StringBuilder("9");
                break;
            case 'x':
                result = new StringBuilder("99");
                break;
            case 'y':
                result = new StringBuilder("999");
                break;
            case 'z':
                result = new StringBuilder("9999");
                break;
        }
        return result;
    }
}