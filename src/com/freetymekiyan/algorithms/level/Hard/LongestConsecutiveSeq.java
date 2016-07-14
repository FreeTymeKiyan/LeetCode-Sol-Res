import java.util.*;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
 * length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * Tags: Array, HashTable
 */
class LongestConsecutiveSeq {
    public static void main(String[] args) {
        LongestConsecutiveSeq l = new LongestConsecutiveSeq();
        int[] a = {100, 4, 200, 1, 3, 2};
        System.out.println(l.longestConsecutive(a));
    }

    /**
     * Use a map to store ranges
     * Get lower bound with smaller value
     * Get upper bound with larger value
     * Update max length with new bound
     * Put all possible ranges into map
     * including num[i] ~ num[i], low ~ upp, upp ~ low
     */
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLen = 0;
        for(int i = 0; i < num.length; i++) {
            if (map.containsKey(num[i])) continue; // skip duplicates
            int low = num[i]; // initialize ranges
            int upp = num[i];
            if (map.containsKey(num[i] - 1)) low = map.get(num[i] - 1);
            if (map.containsKey(num[i] + 1)) upp = map.get(num[i] + 1);
            maxLen = Math.max(maxLen, upp - low + 1); // update length
            // put possible ranges into map for next iteration
            map.put(num[i], num[i]);
            map.put(low, upp);
            map.put(upp, low);
        }
        return maxLen;
    }
}
