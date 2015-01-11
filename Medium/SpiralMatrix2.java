/**
 * Given an integer n, generate a square matrix filled with elements from 1 to
 * n^2 in spiral order.
 * 
 * For example,
 * Given n = 3,
 * 
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * 
 * Tags: Array
 */
class SpiralMatrix2 {
    public static void main(String[] args) {
        int[][] mat = generateMatrix(5);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        
    }
    
    /**
     * Track current level
     * Work level by level toward center
     */
    public static int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];
        int[][] ans = new int[n][n];
        int num = 1;
        int lv = 0;
        while (2 * lv < n) {
            for (int i = lv; i < n - lv; i++) ans[lv][i] = num++;
            for (int i = lv + 1; i < n - lv; i++) ans[i][n - lv - 1] = num++;
            for (int i = n - lv - 2; i >= lv; i--) ans[n - lv - 1][i] = num++;
            for (int i = n - lv - 2; i >= lv + 1; i--) ans[i][lv] = num++;
            lv++;
        }
        return ans;
    }
    
    /**
     * use startR, endR, startC, endC to mark the range
     * update relative range whenever finish filling up a row or column
     */
    public static int[][] generateMatrixB(int n) {
        if (n <= 0) return new int[0][0];
        int[][] ans = new int[n][n];
        int i = 1;
        int startR = 0;
        int startC = 0;
        int endR = n - 1;
        int endC = n - 1;
        while (startR <= endR && startC <= endC) {
            for (int j = startC; j <= endC; j++) ans[startR][j] = i++;
            startR++;
            for (int j = startR; j <= endR; j++) ans[j][endC] = i++;
            endC--;
            for (int j = endC; j >= startC; j--) ans[endR][j] = i++;
            endR--;
            for (int j = endR; j >= startR; j--) ans[j][startC] = i++;
            startC++;
        }
        return ans;
    }
}
