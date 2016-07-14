import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a
 * + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 *
 * Note:
 * Elements in a triplet (a,b,c) must be in <strong>non-descending</strong>
 * order.
 * (ie, a ≤ b ≤ c)
 * The solution set must not contain <strong>duplicate</strong> triplets.
 *
 * For example, given array S = {-1 0 1 2 -1 -4},
 *
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 *
 * Tags: Array, Two Pointers
 */
class ThreeSum {
    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        int[] s = { -1, 0, 1, 2, -1, -4 };
        t.printResult(t.threeSum(s));
    }

    /**
     * Two Pointers.
     * Sort given array first.
     * Traverse the array with 1 pointer
     * Use 2 more pointers from both start(i + 1) and end to find target
     */
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]) continue; // skip duplicate 
            if (num[i] > 0) break; // stop at positive integers
            
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                if (j > i + 1 && num[j] == num[j - 1]) { // skip duplicate 
                    j++;
                    continue;
                }
                if (num[i] + num[j] > 0) break;// already bigger than 0
                
                if (num[i] + num[j] + num[k] < 0) j++;
                else if (num[i] + num[j] + num[k] > 0) k--;
                else { // num[i] + num[j] + num[k] == 0
                    List<Integer> triplets = new ArrayList<Integer>();
                    triplets.add(num[i]);
                    triplets.add(num[j]);
                    triplets.add(num[k]);
                    res.add(triplets);
                    j++; // move j ahead
                    k--;
                }
            }
        }

        return res;
    }

    private void printResult(List<List<Integer>> result) {
        for (List<Integer> l : result) {
            System.out.print("{");
            for (Integer i : l) {
                System.out.print(" " + i);
            }
            System.out.println(" }");
        }
    }
}
