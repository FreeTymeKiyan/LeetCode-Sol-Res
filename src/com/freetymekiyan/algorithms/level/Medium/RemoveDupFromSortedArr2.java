/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * 
 * Tags: Array, Two pointers
 */
class RemoveDupFromSortedArr2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * skip if length <=2
     * compare current element with second last element
     */
    public int removeDuplicates(int[] A) {
        if (A == null) return 0;
        int n = A.length;
        if (n <= 2) return n; // no need to deal with n<=2 case.
        int len = 2, i = 2;
        while (i < n) {
            // compare cuurent with second last element
            if (A[i] != A[len - 2]) A[len++] = A[i];
            i++;
        }
        return len;
    }
}