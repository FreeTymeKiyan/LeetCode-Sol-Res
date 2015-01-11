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
 */
class MaximumGap {
    public static void main(String[] args) {
        System.out.println(maximumGap(new int[]{3, 6, 9, 1}));
    }
    
    /**
     * O(n) Time, O(n) Space
     * Find max and min in one round
     * Calculate bucket length and divide numbers into buckets
     * Go through buckets to find max gap
     */
    public static int maximumGap(int[] num) {
        int n;
        if (num == null || (n = num.length) < 2) return 0;
        /*find max and min value*/
        int min = num[0];
        int max = num[0];
        for (int i : num) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        /*put into buckets*/
        double dist = (double)(max - min) / (n - 1);
        int[] bucketMaxs = new int[n - 1];
        int[] bucketMins = new int[n - 1];
        Arrays.fill(bucketMaxs, -1);
        Arrays.fill(bucketMins, -1);
        for (int i : num) {
            int idx = (i == max ? n - 2 : (int)((i - min) / dist));
            if (bucketMins[idx] == -1 || i < bucketMins[idx]) bucketMins[idx] = i;
            if (bucketMaxs[idx] == -1 || i > bucketMaxs[idx]) bucketMaxs[idx] = i;
        }
        /*find max gap*/
        int prevMax = bucketMaxs[0];
        int maxGap = bucketMaxs[0] - bucketMins[0];
        int mid = 0;
        for (int i = 1; i < n - 1; i++) {
            if (bucketMins[i] == -1) continue; // no min in this bucket
            if (bucketMins[i] - prevMax > maxGap) {
                mid = (bucketMins[i] + prevMax) / 2;
            }
            prevMax = bucketMaxs[i];
        }
        return mid;
    }
}