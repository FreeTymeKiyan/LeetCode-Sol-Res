import java.util.*;

/**
 * Given an array S of n integers, are there K elements (e.g., a, b, c, d, e … 
 * totally K elements) in S such that their sum equal to a given target? Find
 * all such unique K elements tuple(a, b, c, d, e, …) in the array which gives 
 * the given target.
 * 
 * Tags: Array, Two Pointers, Backtracking
 */
class KSum {
    public static void main(String[] args) {
        KSum r = new KSum();
        int[] num = { 7, 2, 5, 1, 6, 3, 8, 9, 10, 4 };
        int k = 6;
        int target = 24;
        List<List<Integer>> res = r.kSum(num, k, target);
        for (List<Integer> l : res) System.out.println(l);
    }
    
    /**
     * Sort num array first
     */
    public List<List<Integer>> kSum(int[] num, int k, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0 || num.length < k) return res;
        Arrays.sort(num); // sort first
        return kSum(num, k, target, 0);
    }
    
    /**
     * Backtracking
     * Base case when k == 2, use 2 pointers and add it to result
     * If k > 2, from position to the end of array
     * Get k - 1 sum from recursive call first
     * Then combine current number with results of k-1 sum and add to result
     */
    public List<List<Integer>> kSum(int[] num, int k, int target, int pos) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k == 2) { // base case, k == 2
            int i = pos;
            int j = num.length - 1;
            while (i < j) {
                if (i > pos && num[i] == num[i - 1]) { // skip duplicates
                    i++;
                    continue;
                }
                int sum = num[i] + num[j];
                if (sum == target) {
                    List<Integer> tuple = new ArrayList<Integer>();
                    tuple.add(num[i]);
                    tuple.add(num[j]);
                    i++;
                    j--;
                    res.add(tuple);
                } 
                else if (sum > target) j--;
                else i++;
            }
            return res;
        }
        // k > 2
        for (int i = pos; i < num.length; i++) {
            if (i > pos && num[i] == num[i - 1]) continue; // skip duplicates
            List<List<Integer>> k1Sum = kSum(num, k-1, target - num[i], i+1); // get k-1 sum from recursive calls
            for (List<Integer> s : k1Sum) {
                List<Integer> tuple = new ArrayList<Integer>();
                tuple.add(num[i]); // add current element
                tuple.addAll(s); // add k-1 sum
                res.add(tuple);
            }
        }
        return res;
    }
}
