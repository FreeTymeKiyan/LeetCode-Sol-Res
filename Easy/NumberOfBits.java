/**
 * Write a function that takes an unsigned integer and returns the number of
 * ’1' bits it has (also known as the Hamming weight).
 * 
 * For example, the 32-bit integer '11' has binary representation
 * 00000000000000000000000000001011, so the function should return 3.
 * 
 * Tags: Bit Manipulation
 */
class NumberOfBits {
    public static void main(String[] args) {
        NumberOfBits nob = new NumberOfBits();
        int n = 111;
        System.out.println(nob.hammingWeight(n));
        System.out.println(nob.hammingWeightB(n));
        System.out.println(nob.hammingWeightC(n));
    }
    
    /**
     * Pure bit manipulation
     * "n &= n - 1" is used to delete the right "1" of n
     * Stop when all 1s are deleted and n is zero
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }
    
    /**
     * Most straight forward solution
     * Iterate 32 times to check each digit in n
     */
    public int hammingWeightB(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) // no need to iterate 32 times
            if ((n >>> i & 0x1) == 1) res++;
        return res;
    }
    
    /**
     * Recursive
     * If n is 0 or 1, return n
     * If not, return n & 1 + hammingWeightC(n >>> 1)
     */
    public int hammingWeightC(int n) {
        return n == 0 || n == 1 ? n : (n & 1) + hammingWeightC(n>>>1);
    }
}
