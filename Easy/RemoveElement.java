/**
 * Given an array and a value, remove all instances of that value in place and 
 * return the new length.
 * 
 * The order of elements can be changed. It doesn't matter what you leave 
 * beyond the new length.
 * 
 * Tags: Array, Two pointers
 */
class RemoveElement {
    public static void main(String[] args) {
        // int[] A = { 1 };
        // int[] A = { 1, 2, 3, 4 };
        int[] A = { 1, 2, 1 };
        int elem = 1;
        System.out.println(removeElement(A, elem));
    }
    
    /**
     * order is not important, so just move the last elem to removed position
     */
    public static int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) return 0;
        int i = 0;
        int j = A.length;
        while (i < j) {
            if (A[i] == elem) {
                A[i] = A[j - 1];
                j--;
            } else i++;
        }
        return j;
    }
}