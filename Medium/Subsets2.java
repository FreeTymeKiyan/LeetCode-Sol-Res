import java.util.*;

/**
 * Given a collection of integers that might contain duplicates, S, return all
 * possible subsets.
 * 
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,2], a solution is:
 * 
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * 
 * Tags: Array, Backtracking
 */
class Subsets2 {
    
    public static void main(String[] args) {
        int[] num = {1, 2, 2};
        subsetsWithDup(num);
    }
    
    /**
     * Backtrack to generate all subsets
     */
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (null == num || num.length == 0) return res;
        Arrays.sort(num); // sort first
        subsetsHelper(res, new ArrayList<Integer>(), num, 0);
        return res;
    }

    /**
     * Add list to result
     * Backtrack from current position to the end of array
     * Skip duplicates first
     * Add number to list and pass list and i+1 to next backtrack
     * Reset list after that
     */
    private void subsetsHelper(List<List<Integer>> res, List<Integer> list, int[] num, int pos) {
        res.add(new ArrayList<Integer>(list));
        for (int i = pos; i < num.length; i++) {
            if (i != pos && num[i] == num[i - 1]) continue; // skip dups
            list.add(num[i]);
            subsetsHelper(res, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
    /**
     * if a number from S is the first one of the numbers with the same value,
     * it can be used to extend all previous subsets and create new 
     * non-duplicate subsets.
     * if a number from S is a duplicated number of some value, it cannot be
     * used to extend all previous subsets. Only part of them. The idea is that
     * this number should help make some different subsets than its 
     * predecessor. So it only needs to extend subsets which contains its
     * predecessor.
     * 
     * [1 2 2]
     * [ ], [1], [2], [1 2]
     * [1 2 2], [2 2] (add 2 to subsets which have 2)
     */
    public static List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>()); // empty set
        if (null == num || num.length == 0) return res;
        Arrays.sort(num); // sort first
        
        int j, prevSize = 0;
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) // dup number
                j = prevSize; // # of previous sets before last number
            else
                j = 0;
            prevSize = res.size(); // # of previous sets
            /*add to previous sets with same num*/
            for (; j < prevSize; j++) { 
                List<Integer> temp = new ArrayList<Integer>(res.get(j));
                temp.add(num[i]);
                res.add(temp);
            }
        }
        return res;
    }
}