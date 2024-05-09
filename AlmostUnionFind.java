/**
 * Project       : Almost Union-Find

 * Program Description:
    This Java program manages sets and performs various operations on them based on user input.
    Sets are represented as disjoint sets with elements, and operations include merging sets,
    transferring elements between sets, and querying information about sets.

 * Input:
 - The program expects input from the console, where each line represents a test case.
 - Each test case consists of two integers: setSize (the initial size of the set) and
   numOperations (the number of operations to perform).
 - The subsequent lines contain operation codes and parameters:
   1. Operation code 0: Terminate the current test case.
   2. Operation code 1: Merge two sets, specified by the IDs of their elements.
   3. Operation code 2: Transfer an element from one set to another.
   4. Operation code 3: Query the number of elements and the sum of elements in a set.

 * Output:
 - For operation code 3, the program outputs the number of elements and the sum of elements in the set.
 - The program continues reading and processing test cases until it encounters a test case with setSize 0.

 * Implementation Details:
 - The program uses a nested class SetManager to manage sets efficiently.
 - It initializes arrays to keep track of set information, including parentArray,
   nextArray, setSizeArray, and sumElementsArray.
 - The SetManager class provides methods for finding the parent set of an element,
   merging two sets, transferring an element between sets, and querying set information.
 - It uses path compression for efficient set operations.
 - The main loop reads input, creates SetManager instances, and performs set operations
   based on the user's input.
 - The program terminates when a test case with setSize 0 is encountered.
 */

import java.util.Scanner;

public class AlmostUnionFind {
    public static void main(String[] args) {
        // Create a scanner to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Define a nested class SetManager for managing sets
        class SetManager {
            // Arrays to keep track of set information
            private final int[] parentArray;
            private final int[] nextArray;
            private final int[] setSizeArray;
            private final long[] sumElementsArray;

            // Constructor for SetManager
            SetManager(int setSize) {
                // Initialize arrays based on the specified set size
                parentArray = new int[setSize + 1];
                nextArray = new int[setSize + 1];
                setSizeArray = new int[setSize + 1];
                sumElementsArray = new long[setSize + 1];

                // Initialize each set element
                for (int i = 1; i <= setSize; i++) {
                    parentArray[i] = i;
                    nextArray[i] = i;
                    setSizeArray[i] = 1;
                    sumElementsArray[i] = i;
                }
            }

            // Find the parent set of an element using path compression
            int findParentSet(int element) {
                int parentSet = nextArray[element];

                for (; parentSet != parentArray[parentSet]; parentSet = parentArray[parentSet]) {
                    // This loop will continue as long as the condition is true
                    // and update parentSet in each iteration.
                }

                nextArray[element] = parentSet;
                return parentSet;
            }

            // Merge two sets into one
            void mergeSets(int element1, int element2) {
                int setA = findParentSet(element1);
                int setB = findParentSet(element2);

                if (setA != setB) {
                    if (setSizeArray[setA] < setSizeArray[setB]) {
                        // Swap setA and setB for efficiency
                        setA = setA ^ setB;
                        setB = setA ^ setB;
                        setA = setA ^ setB;
                    }

                    // Update set information after merging
                    parentArray[setB] = setA;
                    nextArray[element1] = setA;
                    setSizeArray[setA] += setSizeArray[setB];
                    sumElementsArray[setA] += sumElementsArray[setB];
                }
            }

            // Transfer an element from one set to another
            void transferSet(int fromSet, int toSet) {
                int setFrom = findParentSet(fromSet);
                int setTo = findParentSet(toSet);

                if (setFrom != setTo) {
                    // Update set information after transfer
                    nextArray[fromSet] = setTo;
                    setSizeArray[setFrom]--;
                    setSizeArray[setTo]++;
                    sumElementsArray[setFrom] -= fromSet;
                    sumElementsArray[setTo] += fromSet;
                }
            }

            // Get the number of elements in a set
            int countElementsInSet(int element) {
                return setSizeArray[findParentSet(element)];
            }

            // Get the sum of elements in a set
            long sumOfElementsInSet(int element) {
                return sumElementsArray[findParentSet(element)];
            }
        }

        // Read input and perform set operations
        while (scanner.hasNext()) {
            int setSize = scanner.nextInt();
            int numOperations = scanner.nextInt();

            SetManager setManager = new SetManager(setSize);

            for (int i = 0; i < numOperations; i++) {
                if (!scanner.hasNextInt()) {
                    break; // Terminate when no more integers are available
                }
                int operationCode = scanner.nextInt();

                if (operationCode == 0) {
                    break; // Terminate when the operation code is 0
                }

                switch (operationCode) {
                    case 1:
                        int firstElement = scanner.nextInt();
                        int secondElement = scanner.nextInt();
                        setManager.mergeSets(firstElement, secondElement);
                        break;
                    case 2:
                        int sourceSet = scanner.nextInt();
                        int destinationSet = scanner.nextInt();
                        setManager.transferSet(sourceSet, destinationSet);
                        break;
                    default:
                        int targetSet = scanner.nextInt();
                        int numElements = setManager.countElementsInSet(targetSet);
                        long sumOfElements = setManager.sumOfElementsInSet(targetSet);
                        System.out.println(numElements + " " + sumOfElements);
                        break;
                }
            }
        }

        // Close the scanner to release resources
        scanner.close();
    }
}
