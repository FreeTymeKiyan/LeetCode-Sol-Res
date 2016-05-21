/**
 * Remove Duplicates from Sorted Array II
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 * Tag: Linkedlist
 *
 * Note: create a countEach variable to count duplicated times for each integer in array.
 * Time complexity = O(n) 
 * @author chenshuna
 */

public class RemoveDuplicatesfromSortedArray2 {
    public static int removeDuplicates(int[] nums) {
        int res = 1;
        if(nums.length <= 1){
            return nums.length;
        }
        int countEach = 1;
        for(int i = 1; i<nums.length; i++){
            if(nums[i] == nums[i-1] && countEach < 2){
                nums[res] = nums[i];
                countEach++;
                res++;
            }
            else if(nums[i] != nums[i-1]){
                nums[res] = nums[i];
                res++;
                countEach = 1;
            }
            else{
                countEach++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] res = {1,1,1,2,3}; 
        System.out.print(removeDuplicates(res));
    }
}
