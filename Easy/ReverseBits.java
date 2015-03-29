import java.util.*;

/**
 * Reverse bits of a given 32 bits <strong>unsigned</strong> integer.
 * 
 * Example: 
 * input 43261596, represented in binary as 00000010100101000001111010011100
 * return 964176192, represented in binary as 00111001011110000010100101000000
 * 
 * Follow up:
 * If this function is <strong>called many times</strong>, how would you 
 * optimize it?
 * 
 * Related problem: Reverse Integer
 * 
 * Tags: Bit Manipulation
 */
class ReverseBits {
    public static void main(String[] args) {
        ReverseBits r = new ReverseBits();
        int a = 43261596;
        System.out.println(r.reverseBits(a));
    }
    
    /**
     * O(1) Time, O(1) Space
     * Move res 1 bit left, a
     * Get first bit of n, b
     * res = a ^ b
     * Move n right 1 bit for next loop
     * Unsigned shift means fill new bit at the left with 0 instead of 1
     */
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) ^ (n & 1); // add first bit of n to last bit of res
            n >>>= 1; // unsigned shift to right
        }
        return res;
    }
}
