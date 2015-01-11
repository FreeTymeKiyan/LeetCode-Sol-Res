/**
 * Given two numbers represented as strings, return multiplication of the
 * numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 * 
 * Tags: Math, String
 */
class MultiplyStrings {

    public static void main(String[] args) {
        
    }
    
    /**
     * Use an integer array to store the result of each digit and build
     * Convert digit to integer to calculate res of each digit
     * Add carry to next res, and mod current res with 10
     * Note that the result digit is reversed in the array
     * Skip trailing zeros and build from behind
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "";
        if (num1.equals("0") || num2.equals("0")) return "0"; // if one is 0
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        int m = c1.length;
        int n = c2.length;
        int[] res = new int[m + n]; // max length possible
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // note += below
                res[m + n - i - j - 2] += (c1[i] - '0') * (c2[j] - '0'); // reverse to keep zeros of result but skip unnecessary zeros
                res[m + n - i - j - 1] += res[m + n - i - j - 2] / 10;
                res[m + n - i - j - 2] %= 10;
            }
        }
        // build ans string
        StringBuilder ans = new StringBuilder();
        for (int i = m + n - 1; i >= 0; i--) {
            if (res[i] != 0) {
                for (int j = i; j >= 0; j--) ans.append(res[j]); // note res[j]
                return ans.toString();
            }
        }
        return "0";
    }
}
