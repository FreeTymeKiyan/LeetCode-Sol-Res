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
        RemoveElement r = new RemoveElement();
        // int[] A = { 1 };
        // int[] A = { 1, 2, 3, 4 };
        int[] A = { 1, 2, 1 };
        int elem = 1;
        System.out.println(r.removeElement(A, elem));
    }
    
    /**
     * Order is not important
     * Just move the last elem to removed position
     */
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) return 0;
        int i = 0;
        int j = A.length;
        while (i < j) {
            if (A[i] == elem) {
                A[i] = A[j - 1]; // move last element
                j--; // decrease length
            } else i++; // move on
        }
        return j;
    }
}
