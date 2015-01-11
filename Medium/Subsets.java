import java.util.*;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 * 
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * 
 * Tags: Array, Backtracking, Bit Manipulation
 */
class Subsets {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> res = subsetsB(nums);
        for (List<Integer> l : res) {
            System.out.println(l.toString());
        }
    }
    
    /**
     * Remember the start position and do backtracking
     */
    public static List<List<Integer>> subsetsB(int[] s) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // Arrays.sort(s); // unneccessary
        subsetsB(s, 0, new ArrayList<Integer>(), res);
        return res;
    }

    public static void subsetsB(int[] s, int start, List<Integer> set, List<List<Integer>> result) {
        result.add(new ArrayList<Integer>(set));
        for (int i = start; i < s.length; i++) {
            set.add(s[i]); // with i
            subsetsB(s, i + 1, set, result); // DFS
            set.remove(set.size() - 1); // remove last element
        }
    }
    
    public static List<List<Integer>> subsetsA(int[] s) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(s); // unneccessary
        subsetsA(s, 0, new ArrayList<Integer>(), res);
        res.add(new ArrayList<Integer>()); // add blank set
        return res;
    }
    
    /**
     * Recursive down to two branches.
     */
    public static void subsetsA(int[] s, int start, List<Integer> set, List<List<Integer>> result) {
        if (start == s.length) {
            result.add(set);
            return;
        }
        List<Integer> copy = new ArrayList<Integer>(set);
        subsetsA(s, start + 1, set, result); // without
        copy.add(s[start]);
        subsetsA(s, start + 1, copy, result); // with
    }
}
