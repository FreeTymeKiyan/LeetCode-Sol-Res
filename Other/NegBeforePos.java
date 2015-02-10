import java.util.*;

/**
 * Given an array which has n integers. It has both positive and negative 
 * integers. Now you need to sort this array in such a way that, the negative 
 * integers should be in the front, and the positive integers should at the 
 * back. Also the relative position should not be changed.
 * 
 * Example:
 * -1 1 3 -2 2 
 * 
 * Output:
 * -1 -2 1 3 2.
 * 
 * Required running time complexity is O(N) and the space complexity is O(1)
 * 
 * Tags: Array, Sort
 */
class NegBeforePos {
    public static void main(String[] args) {
        
    }
    
    /**
     * Two-pass O(n)
     * For the first pass just get the count of negative numbers
     * For the second pass, initialize negIndex as 0, posIndex as negCount
     * If A[negIndex] < 0, move on
     * If A[negIndex] > 0, swap it with posIndex, move posIndex forward
     * Stop when posIndex reach array length or negIndex reach negCount
     */
    public void negBeforePos(int[] A) {
        int negCount = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) negCount++; // find out neg count first
        }

        int negIndex = 0;
        int posIndex = negCount; // positive value will be moved after neg count
        while (posIndex < n && negIndex < negCount) {
            if (A[negIndex] < 0) negIndex++;
            else { // current value > 0, swap
                int temp = A[negIndex];
                A[negIndex] = A[posIndex];
                A[posIndex] = temp; 
                posIndex++; // update posIndex
            }
        }
    }
}
