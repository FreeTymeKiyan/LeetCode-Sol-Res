/**
 * The demons had captured the princess (P) and imprisoned her in the
 * bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid
 * out in a 2D grid. Our valiant knight (K) was initially positioned in the
 * top-left room and must fight his way through the dungeon to rescue the
 * princess.
 * 
 * The knight has an initial health point represented by a positive integer. If
 * at any point his health point drops to 0 or below, he dies immediately.
 * 
 * Some of the rooms are guarded by demons, so the knight loses health
 * (negative integers) upon entering these rooms; other rooms are either empty
 * (0's) or contain magic orbs that increase the knight's health (positive
 * integers).
 * 
 * In order to reach the princess as quickly as possible, the knight decides to
 * move only rightward or downward in each step.
 * 
 * Write a function to determine the knight's minimum initial health so that he
 * is able to rescue the princess.
 * 
 * For example, given the dungeon below, the initial health of the knight must
 * be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * 
 * -2(K)    -3      3
 * -5       -10     1
 * 10       30      -5(P)
 * 
 * Notes:
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight
 * enters and the bottom-right room where the princess is imprisoned.
 * 
 * Tags:DP, Binary Search
 */
class DungeonGame {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Build a table for the minimum hp needed to get to the bottom right
     * Build from bottom right to get minimum from i, j to the end
     * Instead of build from to-left, because it's hard to get correct relation
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;
        dungeon[m][n] = Math.max(1 - dungeon[m][n], 1);
        for (int i = m - 1; i >= 0; i--) dungeon[i][n] = Math.max(dungeon[i + 1][n] - dungeon[i][n], 1);
        for (int j = n - 1; j >= 0; j--) dungeon[m][j] = Math.max(dungeon[m][j + 1] - dungeon[m][j], 1);
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dungeon[i][j] = Math.max(Math.min(dungeon[i + 1][j], dungeon[i][j + 1]) - dungeon[i][j], 1);
            }
        }
        return dungeon[0][0];
    }
    /**
     * Why “from the bottom right corner to left top”?
     * It depends on the way you formulate the problem. If you define a value
     * in DP table d[i][j] as 'the minimum hp required to REACH (i, j) from (0,
     * 0)", then the final answer should be d[nrows-1][ncols-1], and you need
     * to start filling from the top left; 
     * However, in the reference answer provided with the question, dp[i][j] is 
     * defined as 'the minimum hp required to REACH (nrows-1, ncols-1) from (i,
     * j)'. Here dp[0][0] is the final answer so we must fill from (nrows-1, 
     * ncols-1). For many other problems such as 'Minimum Path Sum', both 
     * formulation would work. 
     * However, in this problem, the former formulation will lead us to 
     * trouble, because it is very hard, if not impossible, to get d[i][j] 
     * based on d[i-1][j] and d[i][j-1].
     */
}