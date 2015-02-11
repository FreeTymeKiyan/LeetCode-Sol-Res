/**
 * Find two disjoint (non-overlapping) subsequences in an array with maximum 
 * difference.
 * 
 * Tags: Array
 */
class MaxSubseqDifferenceNoOverlap {
    public static void main(String[] args) {
        
    }
    
    /**
     * Cut input array in two halves
     * Find max contiguous subsequences sum in left half, min sum in right half
     * Find max contiguous subsequences sum in right half, min sum in left half
     * Get the two differences, the bigger one can be max difference
     * Update maxDiff and range with that
     */
    public int MaxSubseqDifferenceNoOverlap(int[] A) {
        int[] finalMax = null; // subsequences
        int[] finalMin = null;
        Integer maxDiff = null; // use Integer to determine whether it's set
        for (int i = 0; i < A.length; i++) {
            int[] preMax = maxKadane(A, 0, i); // left max
            int[] postMin = minKadane(A, i, A.length); // right min
            int[] preMin = minKadane(A, 0 ,i); // left min
            int[] postMax = maxKadane(A, i, A.length); // right max
            
            int diff1 = sum(A, preMax) - sum(A, postMin);
            int diff2 = sum(A, postMax) - sum(A, preMin);
            if (diff1 > diff2) {
                // if maxDiff not set or diff1 is bigger
                if (maxDiff == null || diff1 > maxDiff) { // update maxDiff
                    maxDiff = diff1;
                    finalMax = preMax;
                    finalMin = postMin;
                }
            } else {
                if (maxDiff == null || diff2 > maxDiff) {
                    maxDiff = diff2;
                    finalMax = postMax;
                    finalMin = preMin;
                }
            }
        }
        return maxDiff;
    }
    
    /**
     * 
     */
    private int[] maxKadane(int[] A, int s, int e) {
        int beginTemp = s;
        int begin = s;
        int end = s;
        int maxSoFar = A[s];
        int maxEndingHere = A[s];
        
        for (int i = 1; i < e; i++) {
            if (maxEndingHere < 0) {
                maxEndingHere = A[i];
                beginTemp = i;
            } 
            else maxEndingHere += A[i];

            if (maxEndingHere >= maxSoFar) {
                maxSoFar = maxEndingHere;
                begin = beginTemp;
                end = i;
            }
        }
        
        return new int[]{begin, end};
    }
    
    /**
     * Modification of 
     */
    private int[] minKadane(int[] A, int s, int e) {
        int beginTemp = s;
        int begin = s;
        int end = s;
        int minSoFar = A[s];
        int minEndingHere = A[s];
        
        for (int i = 1; i < e; i++) {
            if (minEndingHere > 0) {
                minEndingHere = A[i];
                beginTemp = i;
            }
            else minEndingHere += A[i];
            
            if (minEndingHere <= minSoFar) {
                minSoFar = minEndingHere;
                begin = beginTemp;
                end = i;
            }
        }
        
        return new int[]{begin, end};
    }
    
    private int sum(int[] A, int[] range) {
        int sum = 0;
        for (int i = range[0]; i <= range[1]; i++) {
            sum += A[i];
        }
        return sum;
    }
}
