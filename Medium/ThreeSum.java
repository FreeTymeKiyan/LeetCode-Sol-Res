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
        int[] s = { -1, 0, 1, 2, -1, -4};
        printResult(threeSum(s));
    }

    /**
     * Sort the given array. 
     */
    public static List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue; // for duplicate situation
            }
            if (num[i] > 0) {
                break; // can stop at positive integers
            }
            
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                if (j > i + 1 && num[j] == num[j - 1]) {
                    j++;
                    continue;
                }
                if (num[i] + num[j] > 0) { // already bigger than 0
                    break;
                }
                if (num[i] + num[j] + num[k] < 0) {
                    j++;
                } else if (num[i] + num[j] + num[k] > 0) {
                    k--;
                } else {
                    List<Integer> triplets = new ArrayList<Integer>();
                    triplets.add(num[i]);
                    triplets.add(num[j]);
                    triplets.add(num[k]);
                    res.add(triplets);
                    j++; // move j ahead
                }
            }
        }

        return res;
    }

    private static void printResult(List<List<Integer>> result) {
        for (List<Integer> l : result) {
            System.out.print("{");
            for (Integer i : l) {
                System.out.print(" " + i);
            }
            System.out.println(" }");
        }
    }
}
