/*
 * Project       : Islands
 *
 * Program Description:
     This Java program analyzes a grid containing land ('L') and water ('W') cells to determine
     the number of islands present in the grid. An island is a group of adjacent land cells
     (horizontally or vertically). The program employs a breadth-first search (BFS) algorithm
     to explore and count islands.

     Input:
     The program reads input from the console, which includes the following:
      - The dimensions of the grid (number of rows and columns).
      - The content of the grid represented by 'L' (land) and 'W' (water) characters.

     Output:
      - The program outputs the count of islands found in the grid to the console.
      - The result is a single integer representing the number of islands.

 * Pseudocode:
    function main():
        Initialize a reader to read input from the console
        Initialize a writer to write output to the console

        Read the dimensions of the grid (number of rows and columns)
        Parse the number of rows and columns

        Create a 2D array to represent the grid

        Initialize row counter
        while row < numRows:
            Initialize column counter
            while column < numColumns:
                Read a character from the input and store it in the grid
                Increment the column counter
            Increment the row counter

        Call the countIslands function to find and count the number of islands in the grid

        Write the result to the console

    function countIslands(grid, numRows, numColumns):
        Define directions for moving (up, down, left, right)
        Create a 2D boolean array to track visited cells in the grid
        Initialize an island counter

        Initialize row counter
        while row < numRows:
            Initialize column counter
            while column < numColumns:
                switch grid[row][column]:
                    case 'L':
                        if the cell hasn't been visited:
                            Mark it as visited
                            Create a queue for breadth-first search
                            Add the current cell to the queue
                            Perform breadth-first search to explore the island
                            Increment the island count when the island is fully explored
                    case 'W':
                        Mark the water cell as visited
                Increment the column counter
            Increment the row counter

        Return the total number of islands found
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Islands {
    public static void main(String[] args)
            throws IOException {
        // Create a reader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Create a writer to write output to the console
        PrintWriter writer = new PrintWriter(System.out);

        // Read the dimensions of the grid (number of rows and columns) as a string and split it into an array
        String[] dimensions = reader.readLine().split(" ");

        // Parse the number of rows and columns from the input
        int numRows = Integer.parseInt(dimensions[0]);
        int numColumns = Integer.parseInt(dimensions[1]);

        // Create a 2D array to represent the grid
        char[][] grid = new char[numRows][numColumns];

        // Initialize row counter
        int row = 0;

        // Read the grid's content row by row
        while (row < numRows) {
            // Read a line of characters from the input
            String line = reader.readLine();

            // Initialize column counter
            int column = 0;

            // Read each character in the line and store it in the grid array
            while (column < numColumns) {
                char cell = line.charAt(column);
                grid[row][column] = cell;
                column++;
            }
            row++;
        }

        // Call the countIslands function to find and count the number of islands in the grid
        int result = countIslands(grid, numRows, numColumns);

        // Write the result to the console as a string
        writer.write(Integer.toString(result));

        // Flush the writer to ensure the output is written to the console
        writer.flush();
    }

    public static int countIslands(char[][] grid, int numRows, int numColumns) {
        // Define directions for moving (up, down, left, right)
        int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        // Create a 2D boolean array to track visited cells in the grid
        boolean[][] visited = new boolean[numRows][numColumns];

        // Initialize a counter for the number of islands found
        int islandCount = 0;

        // Initialize the row counter
        int row = 0;

        // Loop through each cell in the grid
        while (row < numRows) {
            // Initialize the column counter
            int column = 0;

            // Check the type of cell and process accordingly
            while (column < numColumns) {
                switch (grid[row][column]) {
                    case 'L':
                        // If it's a land ('L') cell and it hasn't been visited, start a search for an island
                        if (!visited[row][column]) {
                            visited[row][column] = true;

                            // Create a queue to perform a breadth-first search
                            Queue<GridCell> queue = new ArrayDeque<>();

                            // Add the current cell to the queue
                            queue.add(new GridCell(row, column));

                            // Perform a breadth-first search to explore the island
                            while (!queue.isEmpty()) {
                                GridCell current = queue.poll();

                                int i = 0;

                                // Check all four directions (up, down, left, right)
                                while (i < 4) {
                                    int nextRow = current.row + directions[i][0];
                                    int nextColumn = current.column + directions[i][1];

                                    // Check if the next cell is within bounds and it's not water ('W') or visited
                                    if (nextRow >= 0 && nextColumn >= 0 && nextRow < numRows && nextColumn < numColumns
                                            && grid[nextRow][nextColumn] != 'W' && !visited[nextRow][nextColumn]) {
                                        visited[nextRow][nextColumn] = true;
                                        queue.add(new GridCell(nextRow, nextColumn));
                                    }
                                    i++;
                                }
                            }

                            // Increment the island count when the entire island is explored
                            islandCount++;
                        }
                        break;

                    case 'W':
                        // If it's a water ('W') cell, mark it as visited
                        visited[row][column] = true;
                        break;
                }
                column++;
            }
            row++;
        }

        // Return the total number of islands found
        return islandCount;
    }
}

// Define a custom class to represent a cell in the grid
class GridCell {
    int row;
    int column;

    GridCell(int row, int column) {
        this.row = row;
        this.column = column;
    }
}