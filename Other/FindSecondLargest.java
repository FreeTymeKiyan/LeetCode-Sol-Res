/**
 * Find second largest element from a given array
 * 
 * Example: 
 * Input: {5, 2, 3, 4}
 * Output: 4
 * 
 * Tags: Array
 */
class FindSecondLargest {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Second largest element is smaller than max
     * When update max, second is also updated
     * But if current element is bigger than second max, also update
     */
    public int secondLargest(int[] array) {
        if (array == null || array.length < 2) return 0;
        int max = Math.max(array[0], array[1]);
        int sec = Math.min(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            if (array[i] > max) { // max updated
                sec = max;
                max = array[i];
            } 
            else if (array[i] > sec && array[i] != max) sec = array[i]; // smaller than max
        }
        return sec;
    }
}
