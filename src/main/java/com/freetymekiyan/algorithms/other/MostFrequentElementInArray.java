package com.freetymekiyan.algorithms.other;

/**
 * // [1,1,1,2,2,3,3,3,3,3,3,4,4,5,5,6]
 * // [1,1,1,1,1,1,1,2,2,2,2,2,2,3,3]
 */
public class MostFrequentElementInArray {

    public static void main(String[] args) {
        int[] a = {1, 2};
        System.out.println(mostFrequent(a));
    }

    public static int mostFrequent(int[] array) {
        if (array == null || array.length == 0) throw new IllegalArgumentException();
        int max = 1;
        int res = array[0];
        for (int i = 0; i < array.length - max; i++) {
            int start = i;
            if (array[start] != array[start + max]) {
                int end = start + max;
                while (array[end] == array[end - 1]) {
                    end--;
                }
                i = end - 1;
                continue;
            }
            while (i < array.length - max && array[i] == array[i + 1]) {
                i++;
            }
            int length = i - start + 1;
            if (length > max) {
                max = length;
                res = array[start];
            }
        }
        return res;
    }
}
