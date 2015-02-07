import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and 
 * T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to 
 * identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 * 
 * For example,
 * 
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * 
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * Tags: Bit Manipulation
 */
class RepeatedDNASeq {
    public static void main(String[] args) {
        
    }
    
    /**
     * To optimize space usage, map string to other key that won't collide
     * Design a hash function according to observation
     * A: 0x41, C: 0x43, G: 0x47, T: 0x54, last 3 bits are different
     * 10 chars, each 3 bits, 10 x 3 = 30 bits < 32
     * 
     * Key: an int to record the bit mask of current substring, 
     * Value: a boolean, true means showed up before, false means already added
     * Update the map
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() < 10) return res;
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for (int t = 0, i = 0; i < s.length(); i++) {
            t = (t << 3 & 0x3FFFFFFF) | (s.charAt(i) & 7);
            if (map.containsKey(t)) {
                if (map.get(t)) {
                    res.add(s.substring(i - 9, i + 1));
                    map.put(t, false);
                }
            } else {
                map.put(t, true);
            }
        }
        return res;
    }
    
    /**
     * HashSet with previous appeared results
     * O(n) Space
     */
    public List<String> findRepeatedDnaSequencesB(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() < 10) return res;
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (set.contains(sub)) {
                res.add(s);
            }
            set.add(s);
        }
        return res;
    }
}
