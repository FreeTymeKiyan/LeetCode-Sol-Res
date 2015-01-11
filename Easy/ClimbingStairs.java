/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * 
 * Tags: DP
 */
class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(44));
        System.out.println(climbStairsBottomUp(44));
    }
    
    /**
     * Bottom-up approach
     * Remember the previous two solutions
     */
    public int climbStairs(int n) {
        if (n <= 1) return n;
        int last = 1, lastlast = 1;
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }

    /**
     * Top-down approach with memory function
     */
    public static int climbStairs(int n) {
        int[] cache = new int[n + 1];
        return helper(n, cache);
    }
    
    public static int helper(int n, int[] cache) {
        if (n < 0) return -1;
        if (n == 0 || n == 1) return 1;
        cache[0] = 1; 
        cache[1] = 1; 
        if (cache[n] == 0)
            cache[n] = helper(n - 1, cache) + helper(n - 2, cache);            
        return cache[n];
    }
    
    // bottom-up approach
    public static int climbStairsBottomUp(int n) {
        if (n < 0) return -1;
        if (n == 0 || n == 1) return 1;
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i < cache.length; i++) {
            cache[i] = cache[i - 1] + cache[i - 2]; // only need the last 2
        }
        return cache[n];
    }
}