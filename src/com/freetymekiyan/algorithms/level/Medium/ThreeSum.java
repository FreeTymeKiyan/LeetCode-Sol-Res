package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in
 * the array which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 * <p>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * Company Tags: Amazon, Microsoft, Bloomberg, Facebook, Adobe
 * Tags: Array, Two Pointers
 * Similar Problems: (E) Two Sum, (M) 3Sum Closest, (M) 4Sum, (M) 3Sum Smaller
 */
class ThreeSum {

    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        int[] s = {-1, 0, 1, 2, -1, -4};
        t.printResult(t.threeSum(s));
    }

    /**
     * Two Pointers.
     * Sort given array first.
     * Traverse the array with 1 pointer.
     * Use another 2 pointers from both start(i + 1) and end to find the target.
     * How to avoid duplicate? Compare current number with the previous one, if same, skip.
     * How to early pruning? When current number is positive, stop.
     */
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue; // Skip duplicate
            }
            if (num[i] > 0) {
                break; // Stop at positive integers
            }

            int j = i + 1; // Starts from after i
            int k = num.length - 1; // Ends at the end of the array
            while (j < k) {
                if (j > i + 1 && num[j] == num[j - 1]) { // Skip duplicate
                    j++;
                    continue;
                }
                if (num[i] + num[j] > 0) { // Early pruning
                    break;// already bigger than 0
                }

                if (num[i] + num[j] + num[k] < 0) {
                    j++;
                } else if (num[i] + num[j] + num[k] > 0) {
                    k--;
                } else { // num[i] + num[j] + num[k] == 0
                    List<Integer> triplets = new ArrayList<>(); // Add to result
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

    /**
     * More concise.
     * Compare first, then move pointer to skip all duplicates.
     * Note that it compares current position with the next position.
     * So one more move is needed after that.
     */
    public List<List<Integer>> threeSumB(int[] num) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            if (num[i] > 0) {
                break;
            }
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) { // Skip duplicate
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) {
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        lo++;
                    } else {
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        hi--;
                    }
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
