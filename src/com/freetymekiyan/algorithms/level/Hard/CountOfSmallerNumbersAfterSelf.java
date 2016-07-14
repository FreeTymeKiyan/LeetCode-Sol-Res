import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property
 * where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example:
 * <p>
 * Given nums = [5, 2, 6, 1]
 * <p>
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 * <p>
 * Tags: Divide and Conquer, Binary Indexed Tree, Segment Tree, Binary Search Tree
 * Similar Problems: (H) Count of Range Sum
 * <p>
 * Created by kiyan on 6/2/16.
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
//        int[] input = {5, 2, 6, 1};
        int[] input = {1, 2, 3, 4};
        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();
        List<Integer> result = c.countSmaller(input);
        System.out.println(result);
    }

    /**
     * The smaller numbers on the right of a number are exactly those that jump from its right to its left during a
     * stable sort. So I do mergesort with added tracking of those right-to-left jumps.
     */
    public List<Integer> countSmaller(int[] nums) {
        NumWithIndex[] array = new NumWithIndex[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = new NumWithIndex(nums[i], i);
        }
        return MergeSort.sort(array);
    }

    static class NumWithIndex {
        int num;
        int index;

        public NumWithIndex(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    static class MergeSort {

        public static List<Integer> sort(NumWithIndex[] nums) {
            List<Integer> smaller = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                smaller.add(0);
            }
            System.out.println(smaller.size());
            sort(nums, 0, nums.length - 1, smaller);
            return smaller;
        }

        private static void sort(NumWithIndex[] nums, int start, int end, List<Integer> smaller) {
            if (start < end) {
                int mid = start + (end - start) / 2;
                sort(nums, start, mid, smaller);
                sort(nums, mid + 1, end, smaller);
                merge(nums, start, mid, end, smaller);
            }
        }

        private static void merge(NumWithIndex[] nums, int start, int mid, int end, List<Integer> smaller) {
            NumWithIndex[] copy = new NumWithIndex[nums.length];
            for (int i = start; i <= end; i++) {
                copy[i] = nums[i];
            }

            int low = start;
            int high = mid + 1;
            int currentIdx = low;
            while (low <= mid && high <= end) {
                if (copy[low].num <= copy[high].num) {
                    // how many elements moved from right to left
                    smaller.set(copy[low].index, smaller.get(copy[low].index) + (high - mid - 1));
                    nums[currentIdx++] = copy[low++];
                } else {
                    nums[currentIdx++] = copy[high++];
                }
            }

            for (int i = low; i <= mid; i++) {
                // how many elements moved from right to left
                smaller.set(copy[i].index, smaller.get(copy[i].index) + (high - mid - 1));
                nums[currentIdx++] = copy[i];
            }
        }

    }
}
