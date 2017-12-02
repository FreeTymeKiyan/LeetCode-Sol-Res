package com.freetymekiyan.algorithms.other;

/**
 * Write a function addRecursive() that returns sum of two integers. The function should not use any of the arithmetic
 * operators
 * (+, ++, –, -, .. etc).
 * <p>
 * http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
 * <p>
 * Created by kiyan on 6/3/16.
 */
public class AddWithoutOperators {

    /**
     * Sum of two bits can be obtained by performing XOR (^) of the two bits.
     * Carry bit can be obtained by performing AND (&) of two bits.
     */
    public int add(int x, int y) {
        // Iterate till there is no carry
        while (y != 0) {
            // carry now contains common set bits of x and y
            int carry = x & y;
            // Sum of bits of x and y where at least one of the bits is not set
            x = x ^ y;
            // Carry is shifted by one so that adding it to x gives the required sum
            y = carry << 1;
        }
        return x;
    }

    /**
     * Recursive
     */
    public int addRecursive(int x, int y) {
        if (y == 0)
            return x;
        else
            return addRecursive(x ^ y, (x & y) << 1);
    }
}
