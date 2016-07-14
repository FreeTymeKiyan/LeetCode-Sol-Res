/**
 * x = [0, 1, 2, 3, -1, 4, 5, 6, 7, 8, -2, 9, 10, 11, 12, 13]
 * f(x) = [4, 5, ...... 13]
 * An+1 = An + 1
 * <p>
 * Follow iup, allow only one mistake: [0,1,2,3,-5,-4,-3,-2,-1]
 */
public class LongestIncresingSubsequence {

    public int[] findLongestIncreasingSequence(int[] array) {
        if (array == null || array.length <= 1) return new int[]{0, array.length};
        int start = 0;
        int end = 0;
        int maxLength = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int temp = i;
            while (i < array.length - 1 && array[i + 1] - array[i] == 1) {
                i++;
            }
            int length = i - temp + 1;
            if (length > maxLength) {
                maxLength = length;
                start = temp;
                end = i;
            }
        }
        return new int[]{start, maxLength};
    }

//    Test 1: x = null or empty, f(x) = [0, 0]
//    Test 2: x = [0], f(x) = [0, 1]
//    Test 3: x = [4, 3, 2, 1], f(x) = [0, 1]
//    Test 4: x = [0, 1, 2, 3, -1, 4, 5, 6, 7, 8], f(x) = [5, 5]
//    Pressure test
}