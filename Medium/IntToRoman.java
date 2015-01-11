/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * Tags: Math, String
 */
class IntToRoman {

    public static final int[] intDict = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public static final String[] romanDict = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * Recursion
     * Go through the dict, if num >= dict, insert it to head
     * Pass rest of the integer to next recursion
     */
    public String intToRoman(int num) {
        for (int i = 0; i < intDict.length; i++) {
            if (intDict[i] <= num) {
                return romanDict[i] + intToRoman(num - intDict[i]);
            }
        }
        return ""; // Note the return statement
    }
    
    /**
     * While loop
     * Go through the dict, if num >= intDict[i], append roman integer
     * num -= intDict[i]
     * if num < intDict[i], i++
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0 && i < intDict.length) {
            if (num >= intDict[i]) {
                sb.append(romanDict[i]);
                num -= intDict[i]; // update num
            } else {
                i++; // move to next roman
            }
        }
        return sb.toString();
    }
}