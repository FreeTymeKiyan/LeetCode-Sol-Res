import java.util.*;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by
 * 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded
 * region.
 * 
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * Tags: BFS
 */
class SurroundedRegions {
    public static void main(String[] args) {
        
    }
    
    /**
     * Use a queue to store index to do BFS
     * A 2d boolean array to remember whether a point is visited
     * A 2d int array to represent 4-connectivity
     * 
     * Traverse through the board and BFS at where it's 'O' and not visited
     * Set surround as true at first
     * Create an integer list for points to change
     * Check points around to see whether there is an 'O' point within the board
     * and not visited
     * If so, add it to queue, set visited true
     * If not, it's not surrounded
     */
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        
        Queue<Integer> q = new LinkedList<Integer>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    boolean surround = true;
                    List<Integer> pointsToChange = new ArrayList<Integer>();
                    q.add(i * n + j); // add root
                    visited[i][j] = true; // set root visited
                    while (q.size() > 0) { // BFS
                        int point = q.poll(); // get from queue
                        pointsToChange.add(point);
                        int x = point / n; // get coordinates
                        int y = point % n;
                        // try 4 direction
                        for (int k = 0; k < dir.length; k++) { 
                            int nextX = x + dir[k][0];
                            int nextY = y + dir[k][1];
                            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) { // within board
                                if (board[nextX][nextY] == 'O' && !visited[nextX][nextY]) // add to queue
                                    q.add(nextX * n + nextY);
                                visited[nextX][nextY] = true; // set visited
                            } else surround = false; // false if on the boundry
                        }
                    }
                    if (surround) for (int p : pointsToChange) board[p / n][p % n] = 'X'; // set to 'X'
                }
            }
        }
    }
}
