/**
 * Fibonnaci Number
 * 
 * Tags: Math, Recursion, DP
 */
class Fib {
    public static void main(String[] args) {
        Fib f = new Fib();
        for (int n = 0; n <= 10; n++) {
            System.out.println(f.fib(n, new int[n+1]));
            System.out.println(f.fib2(n));
            System.out.println(f.fib3(n));
            System.out.println("--------");
        }
    }
    
    /**
     * DP, top-down approach
     */
    public int fib(int n, int[] res) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (res[n] == 0) res[n] = fib(n - 1, res) + fib(n - 2, res);
        return res[n];        
    }
    
    /**
     * DP, bottom-up approach
     */
    public int fib2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int prev = 0;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            int temp = res;
            res += prev;
            prev = temp;
        }
        return res;
    }
    
    /**
     * Recursion
     */
    public int fib3(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib3(n - 1) + fib3(n - 2);
    }
}
