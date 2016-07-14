/**
 * Given a string, find the first non-repeating character in it. For example, 
 * if the input string is “GeeksforGeeks”, then output should be ‘f’ and if 
 * input string is “GeeksQuiz”, then output should be ‘G’.
 * 
 * Tags: String
 */
class FirstNonRepeatingChar {
    public static void main(String[] args) {
        String s = "geeksforgeeks";
        FirstNonRepeatingChar r = new FirstNonRepeatingChar();
        System.out.println(s.charAt(r.firstNonRepeating(s)));
    }
    
    /**
     * Use string characters as index and build a count array.
     * Augment the count array by storing not just counts but also the index of 
     * the first time you encountered the character 
     * e.g. (3, 26) for ‘a’ meaning that ‘a’ got counted 3 times and the first 
     * time it was seen is at position 26. 
     * Scan the count array, instead of the string.
     */
    int firstNonRepeating(String s) {
        CountIndex[] count = getCharCountArray(s);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < count.length; i++) {
            if (count[i].count == 1 && res > count[i].index) res = count[i].index;
        }
        return res;
    }
    
    /**
     * Build an array of character count and the index of its first appearance
     */
    CountIndex[] getCharCountArray(String s) {
        CountIndex[] count = new CountIndex[256]; // # of chars
        for (int i = 0; i < count.length; i++) count[i] = new CountIndex();
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)].count++;
            if (count[s.charAt(i)].count == 1) count[s.charAt(i)].index = i;
        }
        return count;
    }
    
    class CountIndex {
        int count;
        int index;
        
        CountIndex() {
            count = 0;
            index = 0;
        }
    }
}
