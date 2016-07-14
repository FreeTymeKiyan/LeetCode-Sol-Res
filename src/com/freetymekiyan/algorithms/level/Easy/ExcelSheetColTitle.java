/**
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 *
 * For example:
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *
 * Tags: Math
 */
class ExcelSheetColTitle {
    public static void main(String[] args) {
        // for (int i = 1; i <= 26; i++) {
        //     System.out.println(convertToTitle(i));
        // }
        // for (int i = 27; i <= 52; i++) {
        //     System.out.println(convertToTitle(i));
        // }
        // for (int i = 53; i <= 78; i++) {
        //     System.out.println(convertToTitle(i));
        // }
        // for (int i = 703; i <= 728; i++) {
        //     System.out.println(convertToTitle(i));
        // }
        System.out.println(convertToTitleRec(26));
    }

    /**
     * Get the remainder in each loop
     * It should be the last digit
     * Note that the map shall have 1 offset
     */
    public static String convertToTitle(int n) {
        if (n <= 0) return "";
        StringBuilder title = new StringBuilder();
        while (n > 0) {
            n--; // note the 1 offset
            int r = n % 26;
            title.insert(0, (char)('A' + r));
            n = n / 26;
        }
        return title.toString();
    }
    
    /**
     * Recursive version, one line
     */
    public static String convertToTitleRec(int n) {
        return n <= 0 ? "" : convertToTitleRec(--n / 26) + (char)('A' + (n % 26));
    }
}
