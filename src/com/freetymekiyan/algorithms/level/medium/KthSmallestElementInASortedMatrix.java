package com.freetymekiyan.algorithms.level.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element
 * in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 * <p>
 * Tags: Binary Search, Heap
 * Similar Problems: (M) Find K Pairs with Smallest Sums
 */
public class KthSmallestElementInASortedMatrix {

    /**
     * Heap (Priority Queue).
     * Put first column or row into the min heap.
     * Poll the root and put the next one in the same column or row into heap.
     * Need to keep track of the position of elements in the queue.
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        Queue<Element> minHeap = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            minHeap.offer(new Element(matrix[0][i], 0, i));
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            Element ele = minHeap.poll();
            res = ele.number;
            if (ele.row + 1 < n) {
                minHeap.offer(new Element(matrix[ele.row + 1][ele.col], ele.row + 1, ele.col));
            }
        }
        return res;
    }

    /**
     * Binary Search.
     * Binary search the rank of a certain value and find the one that's kth smallest.
     * The initial range is matrix[0][0] to matrix[n - 1][n - 1].
     * Get the middle value and find its position in the matrix by checking how many elements are smaller than it.
     * You may go through each row to find how many elements are larger since the matrix is column ascending. O(n).
     * Then if the ranking is > k, it means the value should be smaller, right range shrinks.
     * If the ranking is < k, the value should be bigger, left range shrinks.
     * If the ranking is k, return the value.
     */
    public int kthSmallestBinarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        int mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            int j = n - 1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                while (j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += j + 1;
            }
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    class Element implements Comparable<Element> {

        int number;
        int row;
        int col;

        public Element(int number, int row, int col) {
            this.number = number;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.number, o.number);
        }
    }
}
