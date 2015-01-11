/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * Tags: Divide and Conquer, Array, Bit Manipulation
 */
class MajorityEle {
    public static void main(String[] args) {
        
    }
    
    /**
     * Go through the array, assign maj ele if count is 0
     * Add 1 to count if same element, otherwise minus 1
     */
    public static int majorityElement(int[] num) {
        int maj = num[0];
        for (int count = 0, i = 0; i < num.length && count <= num.length / 2; i++){
            if (count == 0){
                maj = num[i];
                count++;
            }
            else count = num[i] == maj ? count + 1 : count - 1;
        }
        return maj;
    }
    
    /**
     * Runtime: O(n) — Bit manipulation: We would need 32 iterations, each
     * calculating the number of 1's for the ith bit of all n numbers. Since a
     * majority must exist, therefore, either count of 1's > count of 0's or
     * vice versa (but can never be equal). The majority number’s ith bit must
     * be the one bit that has the greater count.
     */
    public static int majorityElement2(int[] num) {
        
    }
}