import java.util.*;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C <strong>unlimited</strong>
 * number of times.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7, 
 * A solution set is: 
 * [7] 
 * [2, 2, 3] 
 * 
 * Tags: Backtracking
 */
class CombinationSum {
    // [2, 3, 6, 7], 7
    public static void main(String[] args) {
        
    }
    
    /**
     * Sort the array
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper(candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }

    /**
     * Bakctracking
     */
    private void helper(int[] candidates, int target, int pos, List<Integer> comb, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(comb)); // dereference
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            int newTarget = target - candidates[i];
            if (newTarget >= 0) {
                comb.add(candidates[i]);
                helper(candidates, newTarget, i, comb, res); // note i
                comb.remove(comb.size() - 1);
            } else break; // too big
        }
    }
}
