import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find
 * all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used <strong>once</strong> in the combination.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 * 
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
 * A solution set is: 
 * [1, 7] 
 * [1, 2, 5] 
 * [2, 6] 
 * [1, 1, 6] 
 * 
 * Tags: Array, Backtracking
 */
class CombinationSum2 {
    public static void main(String[] args) {
        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
        int tar = 8;
        List<List<Integer>> solution = new CombinationSum2().combinationSum2(candidates, tar);
        for (List<Integer> l : solution) System.out.println(l.toString());
    }
    
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return res;
        Arrays.sort(num);
        combinationSum2(num, target, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    /**
     * Skip duplicates after new target is generated
     */
    public void combinationSum2(int[] num, int target, int index, List<Integer> comb, List<List<Integer>> result) {
        if (target == 0) { // && !result.contains(comb)
            result.add(new ArrayList<Integer>(comb));
            return;
        }
        
        for (int i = index; i < num.length; i++) {
            int newTarget = target - num[i];
            if (newTarget >= 0) {
                comb.add(num[i]);
                combinationSum2(num, newTarget, i + 1, comb, result);
                comb.remove(comb.size() - 1);
            } else break;
            // skip dups, note the range
            while (i < num.length - 1 && num[i] == num[i + 1]) i++;
        }
    }
}
