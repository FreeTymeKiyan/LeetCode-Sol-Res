/**
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * 
 * Keys: Last digit is 0, Reversed integer might overflow
 * 
 * Have you thought about this?
 * Here are some good questions to ask before coding. Bonus points for you if
 * you have already thought through this!
 * 
 * If the integer's last digit is 0, what should the output be? ie, cases such
 * as 10, 100.
 * 
 * Did you notice that the reversed integer might overflow? Assume the input is
 * a 32-bit integer, then the reverse of 1000000003 overflows. How should you
 * handle such cases?
 * 
 * Throw an exception? Good, but what if throwing an exception is not an
 * option? You would then have to re-design the function (ie, add an extra
 * parameter).
 * 
 * Tags: Math
 */
class ReverseInt {
    
    public static void main(String[] args) {
        System.out.println(new ReverseInt().reverse(-123));
    }
    
    /**
     * Last digit is zero, output?
     * Reversed might overflow? 1000000003
     */
    public int reverse(int x) {
        long out = 0; // result might overflow
        while (x != 0) {
            out = out * 10 + x % 10; // append last digit of x
            x = x / 10; // remove last digit
        }
        if (out > Integer.MAX_VALUE || out < Integer.MIN_VALUE) return 0;
        return (int)out;
    }
}
