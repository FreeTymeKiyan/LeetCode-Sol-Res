/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * 
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 * Tags: String
 */
class ZigZagConversion {
    public static void main(String[] args) {
        
    }
    
    /**
     * Build an array of StringBuffers
     * Traverse the given string and append characters in correct StringBuffer
     * Append all other rows to first row to get output
     */
    public String convert(String s, int nRows) {
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
        int len = s.length();
        int i = 0;
        while (i < len) {
            for (int j = 0; j < nRows && i < len; j++) { // from 0 to nRows - 1
                sb[j].append(s.charAt(i++));
            }
            for (int j = nRows - 2; j > 0 && i < len; j--) { // nRows - 2 to 1
                sb[j].append(s.charAt(i++));
            }
        }
        for (int k = 1; k < nRows; k++) sb[0].append(sb[k]);
        return sb[0].toString();
    }
}
