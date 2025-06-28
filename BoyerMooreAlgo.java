import java.util.ArrayList;
import java.util.List;

/**
 * Boyer-Moore String Matching Algorithm Implementation
 *
 * This algorithm efficiently searches for a pattern within a text by using two main heuristics:
 * 1. Bad Character Heuristic - skips characters that don't match
 * 2. Good Suffix Heuristic - uses information about matched suffixes to skip efficiently
 *
 * Time Complexity: O(nm) worst case, O(n/m) best case where n=text length, m=pattern length
 */

public class BoyerMooreAlgo {
    // ASCII character set size - covers all standard ASCII characters (0-255)
    private static int NO_OF_CHARS = 256;

    // Bad Character Heuristic
    public static int[] badCharacterHeuristic(String pattern) {
        // Prepare the bad character table
        int[] badCharTable = new int[NO_OF_CHARS];      // Create array to store the last occurrence of each ASCII character
        for (int i = 0; i < NO_OF_CHARS; i++) {
            // Initialize all positions to -1 (meaning character not found in pattern)
            badCharTable[i] = -1;
        }

        // Record the rightmost position of each character in the pattern
        // If a character appears multiple times, only the rightmost position is kept
        for (int i = 0; i < pattern.length(); i++) {
            badCharTable[pattern.charAt(i)] = i;
        }
        return badCharTable;
    }

    // Preprocess for Strong Good Suffix Rule (Case 1)
    public static void preprocessStrongSuffix(int[] shift, int[] bpos, String pat, int m) {
        int i = m;                      // Start from the end of pattern
        int j = m + 1;                  // Position just beyond the pattern
        bpos[i] = j;                    // Initialize border position

        // Process the pattern from right to left to find borders
        // A border is a substring that appears at both beginning and end of a string
        while (i > 0) {
            // Find the border for current suffix
            // Continue while characters don't match and we haven't reached the end
            while (j <= m && pat.charAt(i - 1) != pat.charAt(j - 1)) {
                // If shift for position j is not set, calculate it
                if (shift[j] == 0) {
                    shift[j] = j - i;   // Distance to shift pattern
                }
                j = bpos[j];            // Move to next border position
            }

            // Move to previous character in pattern
            i--;
            j--;
            bpos[i] = j;                // Store border position
        }
    }

    // Preprocess case 2 for good suffix rule
    // Applies fallback shifts using border positions
    // when no strong suffix match is available
    public static void preprocessCase2(int[] shift, int[] bpos, String pat, int m) {
        int j = bpos[0];

        // Fill in shift values for positions that weren't set in Case 1
        for (int i = 0; i <= m; i++) {
            // If no shift value was set for this position
            if (shift[i] == 0) {
                shift[i] = j;           // Use the fallback shift distance
            }

            // If we've reached the current border position, move to next border
            if (i == j) {
                j = bpos[j];
            }
        }
    }

    // Helper function to visualize the current step of the algorithm
    public static void visualizeStep(String text, String pattern, int s, int mismatchIndex) {
        System.out.println("\nText   : " + text);

        // Create a padding string of spaces equal to the current shift
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < s; i++) {
            padding.append(" ");
        }

        // Build pattern alignment
        String alignedPattern = padding + pattern;
        System.out.println("Pattern: " + alignedPattern);

        // Mark the mismatch if any
        if (mismatchIndex >= 0 && mismatchIndex < pattern.length()) {
            // Create indicator pointing to mismatch position
            StringBuilder indicator = new StringBuilder();
            for (int i = 0; i < s + mismatchIndex; i++) {
                indicator.append(" ");
            }
            indicator.append("Mismatch at '" + text.charAt(s + mismatchIndex) + "'");
            System.out.println("         " + indicator);
        } else {
            // Full match found - show success indicator
            System.out.println("         " + padding + "^ Match found");
        }
    }

    //Search method to find the pattern in the text
    public static void search(String text, String pattern) {
        int m = pattern.length();               // Pattern length
        int n = text.length();                  // Text length

        // Preprocessing Phase 1: Create bad character lookup table
        int[] badChar = badCharacterHeuristic(pattern);

        // Preprocessing Phase 2: Create good suffix shift tables
        int[] shift = new int[m + 1];           // Shift distances for good suffix rule
        int[] bpos = new int[m + 1];            // Border positions for each suffix
        
        //List to store found pattern indices
        List<Integer> foundIndex = new ArrayList<>();

        for (int i = 0; i < shift.length; i++) {
            shift[i] = 0;
        }

        // Preprocess for good suffix rule
        preprocessStrongSuffix(shift, bpos, pattern, m);
        preprocessCase2(shift, bpos, pattern, m);

        int s = 0;                              // Shift the pattern over text
        boolean found = false;                  // Flag to track if any match was found

        // Loop through the text to find the pattern
        while (s <= n - m) {
            int j = m - 1;                      // Start comparing from rightmost character of pattern

            // Compare pattern starting from right to left
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;                            // Move to next character to the left
            }

            // Visualize the current step
            visualizeStep(text, pattern, s, j);

            // If the pattern is found
            if (j < 0) {
                foundIndex.add(s);              // Store the found index
                System.out.println("Pattern found at index " + s);
                s += shift[0];                  // Shift pattern for full match using good suffix rule
                found = true;
            } 

            // If mismatch occurs
            else {
                // Calculate shifts based on both heuristics
                int badCharShift = j - badChar[text.charAt(s + j)];
                int goodSuffixShift = shift[j + 1];
                s += Math.max(goodSuffixShift, badCharShift);
            }
            
        }
        // If no pattern is found
        if (!found) {
            System.out.println("Pattern not found");
        }
        // Print all found pattern's indices
        if (!foundIndex.isEmpty()) {
            System.out.print("\nPattern found at index: ");
            for (int i=0; i<foundIndex.size(); i++) {
                int idx = foundIndex.get(i);
                if (i == foundIndex.size() - 1) {
                    System.out.print(idx);
                } else
                System.out.print(idx + ", ");
            }
        }
    }
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Get input from user
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        System.out.print("Enter the pattern: ");
        String pattern = scanner.nextLine();
        scanner.close();

        // Perform the Boyer-Moore search
        search(text, pattern);
    }
}
