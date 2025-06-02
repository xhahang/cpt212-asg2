public class BoyerMoore {

    private static final int ALPHABET_SIZE = 256; // Total ASCII characters

    // Bad Character Heuristic
    private static int[] buildBadCharacterTable(String pattern) {
        int[] badCharTable = new int[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            badCharTable[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            badCharTable[pattern.charAt(i)] = i;
        }
        return badCharTable;
    }

    // Preprocess for strong good suffix rule
    private static void preprocessStrongSuffix(int[] shift, int[] bpos, String pat, int m) {
        int i = m, j = m + 1;
        bpos[i] = j;

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
    private static void preprocessCase2(int[] shift, int[] bpos, String pat, int m) {
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

    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] badChar = buildBadCharacterTable(pattern);
        int[] bpos = new int[m + 1];
        int[] shift = new int[m + 1];

        for (int i = 0; i < shift.length; i++) {
            shift[i] = 0;
        }

        preprocessStrongSuffix(shift, bpos, pattern, m);
        preprocessCase2(shift, bpos, pattern, m);

        int s = 0;
        boolean found = false;

        while (s <= n - m) {
            int j = m - 1;

            // Match pattern from right to left
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern found at index " + s);
                found = true;
                s += shift[0];
            } else {
                int badCharShift = j - badChar[text.charAt(s + j)];
                int goodSuffixShift = shift[j + 1];
                s += Math.max(goodSuffixShift, badCharShift);
            }
        }

        if (!found) {
            System.out.println("Pattern not found");
        }
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Key in the Text: ");
        String text = scanner.nextLine();
        System.out.print("Key in the Pattern: ");
        String pattern = scanner.nextLine();
        scanner.close();

        search(text, pattern);
    }
}