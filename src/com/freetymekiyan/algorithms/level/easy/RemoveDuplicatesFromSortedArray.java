/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new
 * length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't
 * matter what you leave beyond the new length.
 * <p>
 * Hide Company Tags Microsoft Bloomberg Facebook
 * Hide Tags Array Two Pointers
 * Hide Similar Problems (E) Remove Element
 */
class RemoveDuplicates {

    public static void main(String[] args) {
        RemoveDuplicates r = new RemoveDuplicates();
        int[] A = {1, 1, 2, 2, 3};
        System.out.println(r.removeDup(A));
    }

    /**
     * Two Pointers, one in the front, one for the dups
     */
    public static int removeDupB(int[] A) {
        int i = 0;
        int j = 0;
        int pos = i + 1; // record current position
        while (i < A.length) {
            j = i + 1;
            while (j < A.length && A[i] == A[j]) {
                j++;
            }
            if (j >= A.length) {
                break; // out of range
            }
            A[pos] = A[j];
            pos++;
            i = j;
        }
        return pos;
    }

    /**
     * Two Pointers.
     * Ignore first number.
     * From the second number, compare it with the last number in new array.
     * Only add if they are not the same.
     */
    public int removeDup(int[] nums) {
        int len = 0;
        for (int n : nums) {
            if (len < 1 || n > nums[len - 1]) {
                nums[len++] = n;
            }
        }
        return len;
    }
}
