/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 * 
 * Tags: Math, Binary Search
 */
class DivideTwoIntegers {

    public static void main(String[] args) {
        
    }
    
    /**
     * Take care of special cases, 0, +1, -1
     * dividend = a0 * 1 * divisor + a1 * 2 * divisor + a2 * 2^2 * divisor...
     * 
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        final boolean neg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long ldividend = Math.abs((long)dividend);
        final long ldivisor = Math.abs((long)divisor);
        int res = 0;
        for (int bit = Integer.SIZE - 1; bit >= 0 && ldividend >= ldivisor; bit--) { // note how to initialize bit
            if (ldividend >= (ldivisor << bit)) {
                res |= 1 << bit; // set 1 in relative bit in result
                ldividend -= ldivisor << bit;
            }
        }
        return neg ? -res : res;
    }
}