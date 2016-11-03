package com.freetymekiyan.algorithms.level.hard;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 -
 * 1.
 * <p>
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Hint:
 * <p>
 * Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 * Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and
 * convert just that chunk to words.
 * There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010?
 * (middle chunk is zero and should not be printed out)
 * <p>
 * Company Tags: Microsoft, Facebook
 * Tags: Math, String
 * Similar Problems: (M) Integer to Roman
 */
public class IntegerToEnglishWords {

    public static final String[]
        LESS_THAN_TWENTY =
        {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
         "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
         "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    // Notice the empty string in the front to make index relate to word.
    public static final String[]
        TENS =
        {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    /**
     * Math, String.
     * Try to find the pattern first.
     * The numbers less than 1000, e.g. xyz, can be x Hundred y"ty" z.
     * The numbers larger than 1000, we need to add thousand or million or billion.
     * Given a number num, we pronounce the least significant digits first.
     * Then concat the result to the end to next three least significant digits.
     * So the recurrence relation is:
     * Next result of num = the pronunciation of least three digits + current result of num
     * After that, remove those three digits from number.
     * Stop when number is 0.
     */
    public String numberToWords(int num) {
        if (num == 0) { // The only case that 0 should be pronounced.
            return LESS_THAN_TWENTY[0];
        }

        int i = 0;
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            if (num % 1000 != 0) { // Get last 3 digits and skip intermediate zeros.
                res.insert(0, " ");
                res.insert(0, THOUSANDS[i]); // THOUSAND[0] is empty for first 3 digits.
                res.insert(0, helper(num % 1000));
            }
            num /= 1000; // Remove last 3 digits.
            i++; // Move to next THOUSANDS word.
        }
        return res.toString().trim();
    }

    /**
     * Recursive.
     * Convert number n < 1000 to English words string.
     * Base cases:
     * If n == 0, no need to convert except when the number is only 0.
     * If n < 20, can be directly fetch from less than 20 string array.
     * Recurrence relation:
     * 3 digits is the most significant digit + Hundred + recursive call on the 2 digits.
     * 2 digits is TENS + space + recursive call on the 1 digit.
     * If n < 100, combine tens' digit with the rest.
     * If 100 < n < 1000, combine hundreds' digit with " Hundred ", with the words less than 100.
     * Every word should be followed with a space, very important.
     */
    private String helper(int n) {
        if (n == 0) {
            return ""; // Only one 0 is already handled as a special case.
        } else if (n < 20) {
            return LESS_THAN_TWENTY[20] + " "; // Note the blank is the space at the end.
        } else if (n < 100) {
            return TENS[n / 10] + " " + helper(n % 10); // Note the space in between.
        } else {
            return LESS_THAN_TWENTY[n / 100] + " Hundred " + helper(n % 100);
        }
    }

}
