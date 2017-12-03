package com.freetymekiyan.algorithms.level.medium;

import org.junit.Test;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * <p>
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * <p>
 * Example:
 * Given nums = [1, 3, 5]
 * <p>
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 * <p>
 * Tags: Segment Tree, Binary Indexed Tree
 * Similar Problems: (E) Range Sum Query - Immutable, (H) Range Sum Query 2D - Mutable
 */
public class RangeSumQueryMutable {

    public class NumArray {

        private final int[] nums;
        private final int[] BiTree;
        private final int n;

        public NumArray(int[] nums) {
            this.nums = nums;
            n = nums.length;
            BiTree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                init(i, nums[i]);
            }
        }

        private void init(int i, int val) {
            i++;
            while (i <= n) {
                BiTree[i] += val;
                i += (i & -i);
            }
        }

        void update(int i, int val) {
            int diff = val - nums[i];
            nums[i] = val;
            init(i, diff);
        }

        private int getSum(int i) {
            i++;
            int sum = 0;
            while (i > 0) {
                sum += BiTree[i];
                i -= (i & -i);
            }
            return sum;
        }

        public int sumRange(int i, int j) {
            return getSum(j) - getSum(i - 1);
        }
    }

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);


    @Test
    public void testExamples() {
        int[] input = {1, 3, 5};
        NumArray n = new NumArray(input);
        System.out.println(n.sumRange(0, 2));
        n.update(1, 2);
        System.out.println(n.sumRange(0, 2));
    }
}
