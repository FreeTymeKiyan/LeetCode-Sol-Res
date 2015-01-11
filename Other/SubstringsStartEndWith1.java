/**
 * Given a binary string, count number of substrings that start and end with 1.
 * 
 * Tags: String, Math
 */
class SubstringsStartEndWith1 {
    public static void main(String[] args) {
        
    }
    
    /**
     * O(n) Time
     * Count # of 1's in the string and return combinations c*(c-1) / 2
     */
    public int substringStartEnd(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) == '1') count++;
        return count * (count - 1) / 2
    }
}
