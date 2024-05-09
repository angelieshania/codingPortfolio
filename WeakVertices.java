/*
 * Project       : Weak Vertices
 *
 * Program Description:
     This Java program reads an adjacency matrix representing a graph from the console
     and identifies the valid vertices in the graph. A vertex is considered valid if it
     is connected to two other vertices forming a triangle. The program then writes the
     non-valid vertices to the console.

     Input:
     - The input consists of multiple test cases. Each test case begins with an integer
       N (1 <= N <= 20) representing the number of vertices in the graph.
     - Following N lines contain N space-separated integers (0 or 1), forming the adjacency
       matrix of the graph. A value of 1 at position (i, j) indicates an edge between
       vertex i and vertex j.
     - The end of input is marked by a test case where N is equal to -1.

     Output:
     - For each test case, the program identifies the valid vertices and writes the
       non-valid vertices to the console.
     - Each line of output contains the indices (0-based) of the non-valid vertices
       separated by spaces.
     - If there are no non-valid vertices in a test case, no output is generated for that case.
     - A newline character is used to separate the output for different test cases.

 * Pseudocode:
    1. Read the number of test cases until -1 is entered.
    2. For each test case:
        a. Read the number of vertices (numTestCases).
        b. Create an empty adjacency matrix (adjacencyMatrix) of size numTestCases x numTestCases.
        c. Create a boolean array (isValidVertex) to mark valid vertices, initialized to all false.
        d. Read the adjacency matrix values from input and populate adjacencyMatrix.
        e. Call a function findValidVertices(adjacencyMatrix, isValidVertex) to mark valid vertices.
        f. Call a function writeNonValidVertices(writer, isValidVertex, numTestCases) to write non-valid vertices to the output.

    3. Close the input and output streams.

    Function findValidVertices(adjacencyMatrix, isValidVertex):
    Input: adjacencyMatrix (2D array), isValidVertex (boolean array)
    Output: None

    4. Initialize numVertices as the size of adjacencyMatrix.

    5. For each vertex i from 0 to numVertices:
        a. For each vertex l from 0 to numVertices:
            i. Skip if i is equal to l.
            ii. For each vertex m from 0 to numVertices:
                A. Skip if l is equal to m or i is equal to m.
                B. Check adjacencyMatrix[i][l]:
                    a. If it is equal to 1:
                        i. Check adjacencyMatrix[i][m], adjacencyMatrix[l][m]:
                            A. If all are equal to 1, mark isValidVertex[i], isValidVertex[l], isValidVertex[m] as true.

    Function writeNonValidVertices(writer, isValidVertex, numVertices):
    Input: writer (output stream), isValidVertex (boolean array), numVertices (integer)
    Output: None

    6. Initialize anyNonValid as false.

    7. For each vertex i from 0 to numVertices:
        a. If isValidVertex[i] is false:
            i. Write i to the writer (output stream).
            ii. Set anyNonValid as true.

    8. If anyNonValid is true:
        a. Write a newline character to the writer.
 */

import java.io.BufferedReader;  // Import the BufferedReader class to read input
import java.io.BufferedWriter;  // Import the BufferedWriter class to write output
import java.io.IOException;  // Import the IOException class for handling I/O exceptions
import java.io.InputStreamReader;  // Import the InputStreamReader class for reading input
import java.io.OutputStreamWriter;  // Import the OutputStreamWriter class for writing output
import java.util.StringTokenizer;  // Import the StringTokenizer class for parsing strings

public class WeakVertices {
    public static void main(String[] args)
            throws IOException {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Create a BufferedWriter to write output to the console
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCases;  // Declare a variable to store the number of test cases

        // Read the number of test cases from the input until -1 is entered
        while ((numTestCases = Integer.parseInt(reader.readLine())) != -1) {
            // Create a 2D array to store an adjacency matrix for the graph
            int[][] adjacencyMatrix = new int[numTestCases][numTestCases];
            // Create a boolean array to mark valid vertices
            boolean[] isValidVertex = new boolean[numTestCases];

            // Read the adjacency matrix from input and populate the 2D array
            for (int i = 0; i < numTestCases; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < numTestCases; j++) {
                    adjacencyMatrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // Call the findValidVertices function to mark valid vertices
            findValidVertices(adjacencyMatrix, isValidVertex);

            // Write non-valid vertices to the output
            writeNonValidVertices(writer, isValidVertex, numTestCases);
        }

        // Close the BufferedReader and BufferedWriter
        reader.close();
        writer.close();
    }

    public static void findValidVertices(int[][] adjacencyMatrix, boolean[] isValidVertex) {
        int numVertices = adjacencyMatrix.length;

        // Nested loops to iterate through all pairs of vertices
        for (int i = 0; i < numVertices; i++) {
            for (int l = 0; l < numVertices; l++) {
                if (i == l) {
                    continue;
                }
                for (int m = 0; m < numVertices; m++) {
                    if (l == m || i == m) {
                        continue;
                    }
                    // Check the adjacencyMatrix for specific conditions and mark valid vertices
                    switch (adjacencyMatrix[i][l]) {
                        case 1:
                            if (adjacencyMatrix[i][m] == 1 && adjacencyMatrix[l][m] == 1) {
                                isValidVertex[i] = true;
                                isValidVertex[l] = true;
                                isValidVertex[m] = true;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public static void writeNonValidVertices(BufferedWriter writer, boolean[] isValidVertex, int numVertices)
            throws IOException {
        boolean anyNonValid = false;  // To keep track if any non-valid vertices are found
        for (int i = 0; i < numVertices; i++) {
            if (!isValidVertex[i]) {
                writer.write(i + " ");  // Write non-valid vertices to the output
                anyNonValid = true;  // Mark that at least one non-valid vertex has been found
            }
        }
        if (anyNonValid) {
            writer.write("\n");  // Write a newline character if non-valid vertices are found
        }
    }
}
