/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 * 
 * Tags: Array, Greedy, DP
 */
class JumpGame {
    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        int[] A = {3, 2, 1, 0, 4};
        int[] B = {2, 3, 1, 1, 4};
        int[] C = {0};
        int[] D = {2, 5, 0, 0};
        System.out.println(j.canJump(A));
        System.out.println(j.canJump(B));
        System.out.println(j.canJump(C));
        System.out.println(j.canJump(D));
    }
    
    /**
     * Dynamic Programming
     * Keep track of the maximum of jumps we left
     * Initialized as A[0]
     * Traverse from second to second last
     * Reduce 1 every time we jump
     * maxJump should be max of maxJump - 1 and A[i]
     * if maxJump reduces to zero, we are not able to reach anymore
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) return false;
        if (A.length == 1) return true; // already reach last index
        if (A[0] == 0) return false; // note its important cause we start from 1
        int maxJump = A[0];
        for (int i = 1; i < A.length - 1; i++) {
            maxJump = Math.max(maxJump - 1, A[i]);
            if (maxJump == 0) return false;
        }
        return true;
    }
}
