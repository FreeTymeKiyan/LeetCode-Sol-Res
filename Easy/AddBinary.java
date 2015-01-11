/**
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 * 
 * Tags: Math, String
 */
class AddBinary {
    
    public static void main(String[] args) {
        String a = "11110011001011110111110001010000111110011110101100011111010010001000001101111001000111";
        String b = "111001011011111010001001111001100000101010000101100010101100010010010011011000";
        // String a = "1010";
        // String b = "1011";
        System.out.println(addBinary(a, b));
        System.out.println(addBinaryOwn(a, b));
    }
    
    /**
     * Traverse the longest binary backwards
     * 
     * Use + to insert to front, turn digit sum to int and restore to binary
     */
    public static String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        int carry = 0;
        String res = "";
        int i = 0;
        while (i < m || i < n) {
            int p = i < m ? a.charAt(m - 1 - i) - '0' : 0;
            int q = i < n ? b.charAt(n - 1 - i) - '0' : 0;
            int temp = p + q + carry;
            carry = temp / 2;
            res = temp % 2 + res;
            i++;
        }
        return carry == 0 ? res : "1" + res;
    }
    
    // add 0 and calculate one by one
    public static String addBinaryOwn(String a, String b) {
        if (a == null) return b;
        if (b == null) return a;
        
        StringBuilder result = new StringBuilder();
        int lenA = a.length();
        int lenB = b.length();
        int i = lenA - 1;
        int j = lenB - 1;
        
        boolean carry = false;
        while (i > -1 || j > -1) {
            char c1 = i > -1 ? a.charAt(i) : '0';
            char c2 = j > -1 ? b.charAt(j) : '0';
            if (c1 == '1' && c2 == '1') {
                if (carry) 
                    result.append(1);
                else result.append(0);
                carry = true; // set carry for next digit
            } else if (c1 == '1' || c2 == '1') {
                if (carry) {
                    result.append(0);
                    carry = true; // set carry for next digit
                } else {
                    result.append(1);
                    carry = false;
                }
            } else {
                if (carry) result.append(1);
                else result.append(0);
                carry = false;
            }
            i--;
            j--;
        }
        if (carry) result.append('1');
        return result.reverse().toString();
    }
}
