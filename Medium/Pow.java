/**
 * Implement pow(x, n).
 * 
 * Tags: Math, Binary Search
 */
class Pow {
    public static void main(String[] args) {
        System.out.println(pow(2.0, 5));
    }
    
    /**
     * Divide n by 2
     * 
     * Questions: 
     * 1. can x be zero?
     * 2. can n be negative?
     */
    public static double pow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) { // neg case
            n = -n;
            x = 1 / x; // x can be zero?
        }
        double result = 1;
        for (double f = x; n > 0; n = n >> 1) { // n/=2
            if (n % 2 == 1) result *= f; // lose one f
            f = f * f; // x -> x*x -> x*x*x*x, expand
        }
        return result;
    }
}