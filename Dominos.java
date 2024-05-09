/**
 * Project       : Dominos
 * Program Description:

 * This Java program takes input to solve a problem involving a set of dominos and their connections.
 * It calculates the minimum number of dominos that need to be knocked down to cause a chain reaction of falling dominos.

 * Input:
   The program reads input from the standard input (System.in). The input consists of multiple test cases.
   Each test case begins with a line containing an integer 'numTestCases' denoting the number of test cases.
   For each test case, the following input is provided:
    - The first line contains two integers, 'numDominos' and 'numConnections', where 'numDominos' is the number of dominos,
      and 'numConnections' is the number of connections between dominos.
    - Subsequent 'numConnections' lines provide the connections between dominos, with each line containing two integers,
      'fromDomino' and 'toDomino', representing a connection from 'fromDomino' to 'toDomino'.

 * Output:
  For each test case, the program calculates and prints the minimum number of dominos required to be knocked down to initiate
  a chain reaction, which will cause all connected dominos to fall.

 * Program Implementation:
   - The program uses an adjacency list to represent the connections between dominos.
   - It employs depth-first search (DFS) to explore the dominos and identify the minimum number of dominos to be knocked down.
   - The program iterates through all the test cases, processes each test case individually, and prints the results.
   - It uses a stack to perform DFS and ensure that dominos are knocked down in the correct order to trigger a chain reaction.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dominos {
    public static void main(String[] args)
            throws IOException {
        // Initialize input and output streams for reading and writing
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Read the number of test cases from the input
        int numTestCases = Integer.parseInt(inputReader.readLine());
        while (numTestCases-- > 0) {
            // For each test case, call the solveTestCase method
            solveTestCase(inputReader, outputWriter);
        }

        // Close the output stream
        outputWriter.close();
    }

    public static void solveTestCase(BufferedReader inputReader, PrintWriter outputWriter)
            throws IOException {
        // Read the number of dominos and connections for this test case
        String[] dominosAndConnections = inputReader.readLine().split(" ");
        int numDominos = Integer.parseInt(dominosAndConnections[0]);
        int numConnections = Integer.parseInt(dominosAndConnections[1]);

        // Create an adjacency list to represent connections between dominos
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= numDominos; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Read and store the connections between dominos
        while (numConnections-- > 0) {
            String[] connection = inputReader.readLine().split(" ");
            int fromDomino = Integer.parseInt(connection[0]);
            int toDomino = Integer.parseInt(connection[1]);
            adjacencyList.get(fromDomino).add(toDomino);
        }

        // Find the minimum dominos to knock down and print the result
        int result = findMinDominosToKnockDown(adjacencyList, numDominos);
        outputWriter.println(result);
    }

    public static int findMinDominosToKnockDown(List<List<Integer>> adjacencyList, int numDominos) {
        // Create an array to keep track of visited dominos
        boolean[] visited = new boolean[numDominos + 1];
        // Use a stack to perform depth-first search
        Stack<Integer> stack = new Stack<>();

        // Iterate through all dominos
        for (int i = 1; i <= numDominos; i++) {
            if (!visited[i]) {
                // Start a depth-first search from unvisited dominos
                depthFirstSearch(i, adjacencyList, visited, stack);
            }
        }

        int knockedDown = 0;
        visited = new boolean[numDominos + 1];

        // Process the stack to count dominos knocked down
        for (; !stack.isEmpty(); ) {
            int domino = stack.pop();
            if (!visited[domino]) {
                knockedDown++;
                depthFirstSearch(domino, adjacencyList, visited, null);
            }
        }

        return knockedDown;
    }

    public static void depthFirstSearch(int domino, List<List<Integer>> adjacencyList, boolean[] visited, Stack<Integer> stack) {
        visited[domino] = true;

        int neighborIndex = 0;
        while (neighborIndex < adjacencyList.get(domino).size()) {
            int neighbor = adjacencyList.get(domino).get(neighborIndex);
            if (!visited[neighbor]) {
                // Recursively perform depth-first search on unvisited neighbors
                depthFirstSearch(neighbor, adjacencyList, visited, stack);
            }
            neighborIndex++;
        }

        if (stack != null) {
            // If stack is provided, push the domino onto the stack
            stack.push(domino);
        }
    }
}