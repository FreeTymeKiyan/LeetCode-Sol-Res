import java.util.*;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 * 
 * Tags: Array, Hashtable, Stack, DP
 */
class MaxRectangle {
    public static void main(String[] args) {
        
    }
    
    /**
     * row by row
     * create a height integer array to bigger than column size
     * set last height to zero(out of bounds)
     * build new height on each row
     * use stack to store indices
     * update area according to largest rectangle in histogram
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n + 1];
        height[n] = 0;
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            Stack<Integer> s = new Stack<Integer>();
            for (int j = 0; j < n + 1; j++) {
                if (j < n) { // build height
                    if (matrix[i][j] == '1') height[j] += 1;
                    else height[j] = 0;
                }
                while (!s.isEmpty() && height[j] < height[s.peek()]) {
                    int h = height[s.pop()];
                    int w = (s.isEmpty() ? j : j - s.peek() - 1);
                    max = Math.max(max, h * w);
                }
                s.push(j);
            }
        }
        return max;
    }
}
