import java.util.*;

/**
 * Given a matrix with each grid a color type. Start from a random point, find 
 * the perimeter of the region of the same color. 
 * 
 * Tags: BFS
 */
class MatrixColor {
    public static void main(String[] args) {
        MatrixColor m = new MatrixColor();
        int[][] mat = {
            {2, 2, 1, 2},
            {2, 2, 1, 2},
            {2, 1, 1, 2},
            {2, 2, 1, 2}, 
        };
        System.out.println(m.findPerimeter(mat, 0, 2));
        System.out.println(m.findPerimeter(mat, 0, 1));
        System.out.println(m.findPerimeter(mat, 0, 3));
    }
    
    /**
     * BFS
     * Add the start point to a queue, and set it as visited
     * Convert 2d coordinate to 1d integer and store it in queue
     * While the queue is not empty, get an integer from the queue
     * Convert it back to 2d and search the 4 cells around it
     * If within board, and not visited, add it to queue and set visited
     * If not same color, add edge count
     * If not within board, add edge count
     */
    public int findPerimeter(int[][] mat, int x, int y) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;
        
        Queue<Integer> q = new LinkedList<Integer>();
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        q.add(x * n + y);
        visited[x][y] = true;
        int perimeter = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int pos = q.poll();
                int curX = pos / n;
                int curY = pos % n;
                // update perimeter
                // System.out.println("curX: " + curX + " curY: " + curY);
                int count = 0;
                for (int[] d : dir) {
                    int nX = curX + d[0];
                    int nY = curY + d[1];
                    if (nX >= 0 && nX < m && nY >= 0 && nY < n) {
                        if (mat[nX][nY] == mat[x][y]) {
                            if (!visited[nX][nY]) {
                                q.add(nX * n + nY);
                                visited[nX][nY] = true;
                            }
                        } else count++;
                    }
                    else count++;
                }
                perimeter += count;
                // System.out.println("count: " + count + " p: " + perimeter);
            }
        }
        return perimeter;
    }
}
