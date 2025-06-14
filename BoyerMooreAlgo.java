import java.util.ArrayList;
import java.util.List;

public class BoyerMooreAlgo {
    private static int NO_OF_CHARS = 256; // Total ASCII characters

    // Bad Character Heuristic
    public static int[] badCharacterHeuristic(String pattern) {
        // Prepare the bad character table
        int[] badCharTable = new int[NO_OF_CHARS];
        for (int i = 0; i < NO_OF_CHARS; i++) {
            // Initialize all occurrences as -1
            badCharTable[i] = -1;
        }

        // Fill the bad character table
        for (int i = 0; i < pattern.length(); i++) {
            badCharTable[pattern.charAt(i)] = i;
        }
        return badCharTable;
    }

    // Preprocess for Strong Good Suffix Rule (Case 1)
    public static void preprocessStrongSuffix(int[] shift, int[] bpos, String pat, int m) {
        int i = m, j = m + 1;
        bpos[i] = j;
        
        //Start comparing from right to left
        while (i > 0) {
            while (j <= m && pat.charAt(i - 1) != pat.charAt(j - 1)) {
                if (shift[j] == 0) {
                    shift[j] = j - i;
                }
                j = bpos[j];
            }
            i--;
            j--;
            bpos[i] = j;
        }
    }

    // Preprocess case 2 for good suffix rule
    // Applies fallback shifts using border positions
    // when no strong suffix match is available
    public static void preprocessCase2(int[] shift, int[] bpos, String pat, int m) {
        int j = bpos[0];
        for (int i = 0; i <= m; i++) {
            if (shift[i] == 0) {
                shift[i] = j;
            }
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
            StringBuilder indicator = new StringBuilder();
            for (int i = 0; i < s + mismatchIndex; i++) {
                indicator.append(" ");
            }
            indicator.append("Mismatch at '" + text.charAt(s + mismatchIndex) + "'");
            System.out.println("         " + indicator);
        } else {
            System.out.println("         " + padding + "^ Match found");
        }
    }

    //Search method to find the pattern in the text
    public static void search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        // Create bad character table
        int[] badChar = badCharacterHeuristic(pattern);

        // Create good suffix shift and border position arrays
        int[] shift = new int[m + 1];
        int[] bpos = new int[m + 1];
        
        //List to store found pattern indices
        List<Integer> foundIndex = new ArrayList<>();

        for (int i = 0; i < shift.length; i++) {
            shift[i] = 0;
        }

        // Preprocess for good suffix rule
        preprocessStrongSuffix(shift, bpos, pattern, m);
        preprocessCase2(shift, bpos, pattern, m);

        int s = 0; // Shift the pattern over text
        boolean found = false;

        // Loop through the text to find the pattern
        while (s <= n - m) {
            int j = m - 1;

            // Compare pattern starting from right to left
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }

            // Visualize the current step
            visualizeStep(text, pattern, s, j);

            // If the pattern is found
            if (j < 0) {
                foundIndex.add(s); // Store the found index
                System.out.println("Pattern found at index " + s);
                s += shift[0]; // Shift pattern for full match using good suffix rule
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
            System.out.print("Pattern found at index: ");
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
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        System.out.print("Enter the pattern: ");
        String pattern = scanner.nextLine();
        scanner.close();

        search(text, pattern);
    }
}
