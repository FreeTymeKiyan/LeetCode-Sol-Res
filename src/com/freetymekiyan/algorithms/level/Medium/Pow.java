/**
 * Implement pow(x, n).
 * 
 * Tags: Math, Binary Search
 */
class Pow {
    public static void main(String[] args) {
        Pow p = new Pow();
        System.out.println(p.pow(2.0, 5));
    }
    
    /**
     * Binary Search, divide n by 2
     * Questions: 
     * 1. can x be zero?
     * 2. can n be negative?
     *
     * When n is odd, multiply result by f
     * f multiply by itself each time
     * Repeat until n == 0
     * x^10 = x^2 * x^8
     * x^9 = x * x^8
     * x^8 = x^8
     * x^7 = x * x^2 * x^4
     * x^6 = x^2 * x^4
     */
    public double pow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) { // neg case
            n = -n;
            x = 1 / x; // x can be zero?
        }
        double res = 1; // mind overflow
        for (double f = x; n > 0; n = n >> 1) { // n >>= 1, n/=2
            if (n % 2 == 1) res *= f;
            f = f * f; // f *= f
        }
        return res;
    }
}
