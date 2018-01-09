package com.freetymekiyan.algorithms.level.medium;

/**
 * 75. Sort Colors
 * <p>
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * <p>
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then
 * 1's and followed by 2's.
 * <p>
 * Could you come up with an one-pass algorithm using only constant space?
 * <p>
 * Company Tags: Pocket Gems, Microsoft, Facebook
 * Tags: Array, Two Pointers, Sort
 * Similar Problems: (M) Sort List, (M) Wiggle Sort, (M) Wiggle Sort II
 */
public class SortColors {

    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int BLUE = 2;

    /**
     * Two pointers. One-pass.
     * Similar to find minimum and second minimum.
     * Remember the count of red, and count of red + white.
     * Loop through the array.
     * For each color, we get its value, and overwrite it with blue.
     * Then check if it's red,
     * Note:
     * Why do we update WHITE first?
     * Since whiteEnd and redEnd can be at the same position.
     * what ought to be RED can be overrode by WHITE.
     */
    public void sortColors(int[] nums) {
        int redEnd = -1; // Ending index of red
        int whiteEnd = -1; // Ending index of white

        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            nums[i] = BLUE;
            if (v == RED) {
                nums[++whiteEnd] = WHITE; // Update white first
                nums[++redEnd] = RED; // If there is no white yet, will overwrite white.
            } else if (v == WHITE) {
                nums[++whiteEnd] = WHITE;
            }
        }
    }

    /**
     * 3-way Partitioning. Two pointers. One-pass.
     * One pointer redEnd for the end of red.
     * The other blueStart for the start of blue from the end.
     * If its blue, swap with the end.
     * If its red, swap with the start.
     * All whites will remain in the middle.
     * <p>
     * Implementation:
     * redEnd = 0, blueStart = n-1
     * For each i from 0 to blueStart:
     * | While nums[i] is blue and i < blueStart:
     * |   Swap i with blueStart.
     * |   Update blueStart to blueStart - 1.
     * | While nums[i] is red and i > redEnd:
     * |   Swap i with redEnd.
     * |   Update redEnd to redEnd + 1.
     * Note:
     * Why swap BLUE first not RED? If swap RED first what would happen?
     * If we swap RED first, when we swap with BLUE, the element resulting at i can be 0, 1, 2, anything.
     * We must take care of 0's further.
     * If we swap with BLUE first, 0's are taken care of when we swap with RED later.
     * The elements before i are already in order, so they are either 0 or 1. There won't be a 2 to further swap.
     */
    public void sortColors2(int nums[]) {
        int redEnd = 0, blueStart = nums.length - 1;
        for (int i = 0; i <= blueStart; i++) {
            while (nums[i] == BLUE && i < blueStart) { // Move all BLUEs to the end.
                swap(nums, i, blueStart--);
            }
            while (nums[i] == RED && i > redEnd) { // Move all REDs to the front.
                swap(nums, i, redEnd++);
            }
        }
    }

    /**
     * 3-way partitioning. Standard.
     * Move red to the front, blue to the end. Keep white in the middle.
     * One pointer to the end of red, redEnd, starting from 0.
     * Another pointer to the start of blue, blueStart, starting from n-1, go backwards.
     * For i = 0 to blueStart:
     * | If nums[i] is RED:
     * |   Swap nums[i] with nums[redEnd].
     * |   Move redEnd.
     * |   Can move i too because the color swapped to i won't be red or blue.
     * | If nums[i] is BLUE:
     * |   Swap nums[i] with nums[blueStart].
     * |   Move blueStart.
     * |   No need to move i since the color swapped to i can be blue or red.
     * | If nums[i] is WHITE:
     * |   i++, just skip.
     */
    public void sortColors3(int[] nums) {
        int redEnd = 0;
        int blueStart = nums.length - 1;
        int i = 0;
        while (i <= blueStart) { // i stops at blueStart, no blueStart - 1.
            if (nums[i] == RED) {
                swap(nums, i++, redEnd++);
            } else if (nums[i] == BLUE) {
                swap(nums, i, blueStart--);
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }

    /**
     * Counting sort. Two-pass.
     * First iterate through the array to find each color's count.
     * Then iterate again and write colors to array.
     */
    public void sortColors4(int[] nums) {
        int red = 0;
        int white = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == RED) {
                red++;
            } else if (nums[i] == WHITE) {
                white++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < red) {
                nums[i] = RED;
            } else if (i < red + white) {
                nums[i] = WHITE;
            } else {
                nums[i] = BLUE;
            }
        }
    }
}