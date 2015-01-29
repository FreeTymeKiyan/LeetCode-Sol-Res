import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such
 * that a + b + c + d = target? Find all unique quadruplets in the array which
 * gives the sum of target.
 *
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤
 * b ≤ c ≤ d)
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * A solution set is:
 * (-1,  0, 0, 1)
 * (-2, -1, 1, 2)
 * (-2,  0, 0, 2)
 *
 * Tags: Array, HashTable, Two pointers
 */
class FourSum {
    public static void main(String[] args) {

    }
    
    /**
     * Four pointers, O(n^3) time
     * First pointer i starts from 1 to num.length - 4, 3 indices remain
     * Second pointer j starts from i + 1 to num.length - 3, 2 indices remain
     * Then get newTarget and search from both ends of the remaining numbers
     * Skip duplicate every time
     */
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length < 4) return res;
        Arrays.sort(num);

        for (int i = 0; i < num.length - 3; i++) { // 3 indices remain
            if (i > 0 && num[i] == num[i - 1]) continue; // skip duplicate
            for (int j = i + 1; j < num.length - 2; j++) { // 2 indices remain
                if (j > i + 1 && num[j] == num[j - 1]) continue; // skip
                int newTar = target - num[i] - num[j]; // 2 sum
                int l = j + 1;
                int r = num.length - 1;
                while (l < r) {
                    if (l > j + 1 && num[l] == num[l - 1]) { // skip
                        l++; 
                        continue;
                    }
                    if (r < num.length - 1 && num[r] == num[r + 1]) { // skip
                        r--;
                        continue;
                    }
                    int sum = num[l] + num[r];
                    if (sum < newTar) l++;
                    else if (sum > newTar) r--;
                    else { // sum == newTar
                        res.add(new ArrayList<Integer>(Arrays.asList(num[i], num[j], num[l], num[r])));
                        l++;
                        r--;
                    }
                }
            }
        }

        return res;
    }
}
