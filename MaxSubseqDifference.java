/**
 * Find two contiguous subsequences in an array with maximum difference. The 
 * subsequences may be overlapping.
 * 
 * Tags: Array
 */
class MaxSubseqDifference {
    public static void main(String[] args) {
        int[] A = { -6, -1, -4, -8, -4, -6, -1, -9, -1, -9 };
        MaxSubseqDifference m = new MaxSubseqDifference();
        System.out.println(m.findMaxDiff(A));
    }
    
    /**
     * Find max and min sum contiguous subsequences seperately 
     * Then get the difference 
     */
    public int findMaxDiff(int[] A) {
        int[] maxSubArr = maxKadane(A);
        int[] minSubArr = minKadane(A);
        int sum1 = printArr(A, maxSubArr[0], maxSubArr[1]);
        int sum2 = printArr(A, minSubArr[0], minSubArr[1]);
        return sum1 - sum2;
    }
    
    /**
     * Find max sum contiguous subsequences
     */
    private int[] maxKadane(int[] A) {
        int beginTemp = 0;
        int begin = 0;
        int end = 0;
        int maxSoFar = A[0];
        int maxEndingHere = A[0];
        
        for (int i = 1; i < A.length; i++) {
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
     * Modification of maxKadane to find min sum contiguous subsequence
     */
    private int[] minKadane(int[] A) {
        int beginTemp = 0;
        int begin = 0;
        int end = 0;
        int minSoFar = A[0];
        int minEndingHere = A[0];
        
        for (int i = 1; i < A.length; i++) {
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
    
    int printArr(int[] A, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            System.out.print(i == end ? A[i] : A[i] + ", ");
            sum += A[i];
        }
        System.out.println();
        return sum;
    }
}
