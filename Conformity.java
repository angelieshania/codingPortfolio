/*
 * Project       : Conformity

 *  Program Description:
 *  This Java program reads a list of courses and counts the number of times
    the same combination of course numbers appears in the input. It calculates
    the total maximum count of any course combination and prints it to the console.
 *  This program effectively identifies the most frequently occurring combination of
    course numbers and reports how many times that combination appears in the input.
    It uses a map to keep track of counts and leverages sorting to group equivalent
    course combinations together for counting.

 * Pseudocode       :
 *  1. Read the number of courses from the console and store it in numberOfCourses.
    2. Create an empty dictionary (courseCounts) to store course counts, where keys are sorted course strings, and values are counts.
    3. Initialize maxCount and totalMaxCount to 0.

    4. For each course in range(numberOfCourses):
        a. Read the course input from the console as a string and split it into an array of strings (courseParts).
        b. Convert the array of strings to an array of integers (courseNumbers).
        c. Sort the courseNumbers array.
        d. Convert the sorted courseNumbers array back to a string (sortedCourse).

        e. If sortedCourse is not present in courseCounts:
            i. Add sortedCourse to courseCounts with a count of 1.
            ii. Set currentCount to 1.
        f. Else (if sortedCourse is present in courseCounts):
            i. Increment the count of sortedCourse in courseCounts by 1.
            ii. Set currentCount to the updated count.

        g. If currentCount is greater than maxCount:
            i. Update maxCount to currentCount.
            ii. Set totalMaxCount to currentCount.
        h. Else if currentCount is equal to maxCount:
            i. Increment totalMaxCount by currentCount.

    5. Print the value of totalMaxCount.
 */

import java.io.*;
import java.util.*;

public class Conformity {
    public static void main(String[] args)
            throws IOException {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of courses from input
        int numberOfCourses = Integer.parseInt(reader.readLine());

        // Create a map to store course counts, where the key is a sorted course string and the value is its count
        Map<String, Integer> courseCounts = new HashMap<>();

        // Initialize variables to keep track of the maximum course count and the total maximum count
        int maxCount = 0;
        int totalMaxCount = 0;

        // Iterate through each course input
        for (int i = 0; i < numberOfCourses; i++) {
            // Read and split the course input into an array of strings
            String[] courseParts = reader.readLine().split(" ");

            // Convert the array of strings to an array of integers using Java 8's Stream API
            int[] courseNumbers = Arrays.stream(courseParts)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // Sort the array of integers
            Arrays.sort(courseNumbers);

            // Convert the sorted array of integers back to a string
            String sortedCourse = Arrays.toString(courseNumbers);

            // Update the count of the current course in the map, or initialize it to 1 if not present
            courseCounts.put(sortedCourse, courseCounts.getOrDefault(sortedCourse, 0) + 1);
            int currentCount = courseCounts.get(sortedCourse);

            // Update the maximum course count and total maximum count
            if (currentCount > maxCount) {
                maxCount = currentCount;
                totalMaxCount = currentCount;
            } else if (currentCount == maxCount) {
                totalMaxCount += currentCount;
            }
        }

        // Print the total maximum count
        System.out.println(totalMaxCount);
    }
}