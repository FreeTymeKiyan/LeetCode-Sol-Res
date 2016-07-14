import java.util.*;

/**
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit
 * in the 32-bit signed integer range.
 * 
 * Tags: Sort
 */
class MaximumGap {
    public static void main(String[] args) {
        MaximumGap mg = new MaximumGap();
        System.out.println(mg.maximumGap(new int[]{3, 6, 9, 1}));
    }
    
    /**
     * O(n) Time, O(n) Space
     * Find max and min in one traverse
     * Calculate bucket length and divide numbers into buckets
     * Traverse buckets to find max gap
     */
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2) return 0;
        int n = num.length;
        /*find max and min value*/
        int min = num[0];
        int max = num[0];
        for (int i : num) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        /*put into buckets*/
        double dist = (double)(max - min) / (n - 1);
        int[] uppers = new int[n - 1];
        int[] lowers = new int[n - 1];
        Arrays.fill(uppers, -1);
        Arrays.fill(lowers, -1);
        for (int i : num) {
            int idx = (i == max ? n - 2 : (int)((i - min) / dist));
            if (lowers[idx] == -1 || i < lowers[idx]) lowers[idx] = i;
            if (uppers[idx] == -1 || i > uppers[idx]) uppers[idx] = i;
        }
        /*find max gap*/
        int prevUpper = uppers[0]; // previous bucket can be skipped
        int maxGap = uppers[0] - lowers[0];
        for (int i = 1; i < n - 1; i++) {
            if (lowers[i] == -1) continue; // no min in this bucket
            maxGap = Math.max(maxGap, lowers[i] - prevUpper);
            prevUpper = uppers[i];
        }
        return maxGap;
    }
}
