import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
 * non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * Tags: Array, Two Pointers
 *
 * Similar Problems: (E) Remove Element
 */
public class MoveZeroes {

    /**
     * move all positive to the front
     * add trailing zeroes
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int count = 0;
        for (int n : nums) {
            if (n != 0) {
                nums[count] = n;
                count++;
            }
        }
        for (; count < nums.length; count++) {
            nums[count] = 0;
        }
    }

    @Test
    public void tests() {
        MoveZeroes mz = new MoveZeroes();
        int[] nums = null;
        // null -> null
        mz.moveZeroes(nums);
        assertNull(nums);
        // empty -> empty
        nums = new int[]{};
        mz.moveZeroes(nums);
        assertEquals(nums.length, 0);
        assertNotNull(nums);
        // [1] -> [1]
        nums = new int[]{1};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{1});
        // no zero, [1, 2, 3] -> [1, 2, 3]
        nums = new int[]{1, 2, 3};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{1, 2, 3});
        // all zeroes, [0, 0, 0] -> [0, 0, 0]
        nums = new int[]{0, 0, 0};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{0, 0, 0});
        // normal case, [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
        nums = new int[]{0, 1, 0, 3, 12};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{1, 3, 12, 0, 0});
    }
}
