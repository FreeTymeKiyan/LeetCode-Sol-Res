package com.freetymekiyan.datastructures;

/**
 * Binary Indexed Tree is represented as an array. Let the array be BITree[]. Each node of Binary Indexed Tree stores
 * sum of some elements of given array. Size of Binary Indexed Tree is equal to n where n is size of input array. In
 * the below code, we have used size as n+1 for ease of implementation.
 * <p>
 * Operations
 * <p>
 * getSum(index): Returns sum of arr[0..index]
 * <p>
 * update(index, val): Updates BIT for operation arr[index] += val
 * <p>
 * http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 */
public class BinaryIndexedTree {

    private final int size;
    private final int[] BITree;
    private BinaryIndexedTree tree;

    public BinaryIndexedTree(int[] arr, int len) {
        if (arr == null || arr.length == 0) {
            throw new NullPointerException("Given array is null or empty");
        }
        if (len > arr.length) {
            throw new IllegalArgumentException("Given length is larger than array size");
        }
        size = len;
        BITree = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            BITree[i] = 0;
        }
        // Store the actual values in BITree[] using update()
        for (int i = 0; i < size; i++) {
            update(i, arr[i]);
        }
    }

    /**
     * 1) Initialize sum as 0 and index as index+1.
     * 2) Do following while index is greater than 0.
     * ...a) Add BITree[index] to sum
     * ...b) Go to parent of BITree[index].  Parent can be obtained by removing
     * the last set bit from index, i.e., index = index - (index & (-index))
     * 3) Return sum.
     */
    public int getSum(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of range.");
        }
        int sum = 0;
        index += 1;
        while (index > 0) {
            sum += BITree[index];
            index -= index & (-index); // Move index to parent node
        }
        return sum;
    }

    /**
     * Get range sum, inclusive
     */
    public int getRangeSum(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from: " + from + " is larger than to: " + to);
        }
        if (from < 0 || from >= size || to < 0 || to >= size) {
            throw new IndexOutOfBoundsException("from: " + from + " to: " + to + " should be within [0, " + size + "]");
        }
        return getSum(to) - getSum(from - 1);
    }

    /**
     * 1) Initialize index as index+1.
     * 2) Do following while index is smaller than or equal to n.
     * ...a) Add value to BITree[index]
     * ...b) Go to parent of BITree[index].  Parent can be obtained by removing
     * the last set bit from index, i.e., index = index + (index & (-index))
     *
     * @param index index of the position to be updated
     * @param value value to be added
     */
    public void update(int index, int value) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of range.");
        }
        index += 1;
        while (index <= size) {
            BITree[index] += value;
            index += index & (-index); // Move index to parent node in update View
        }
    }

}
