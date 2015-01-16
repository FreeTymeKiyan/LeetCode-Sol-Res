import java.util.*;

/**
 * http://www.geeksforgeeks.org/find-paths-given-source-destination/
 * 
 * Given a directed graph, a source vertex ‘s’ and a destination vertex ‘d’,
 * print all paths from given ‘s’ to ‘d’.
 * 
 * 0 1 1 1
 * 0 0 0 1
 * 1 1 0 0
 * 0 0 0 0
 * 
 * 2->1->3
 * 2->0->3
 * 2->0->1->3
 * 
 * Tags: Graph, DFS, Backtracking
 */
class Graph {
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);
        g.printAll(2, 3);
    }
    
    void printAll(int s, int d) {
        boolean[] visited = new boolean[4];
        dfs(s, d, visited, new ArrayList<Integer>(), 0);
    }
    
    /**
     * Backtracking to generate all paths
     */
    void dfs(int s, int d, boolean[] visited, List<Integer> path, int pos) {
        visited[s] = true;
        path.add(s);
        if (s == d) {
            for (int i : path) System.out.print(i + "->");
            System.out.println();
        }
        
        for (int next : adjacent.get(s)) {
            if (!visited[next]) {
                dfs(next, d, visited, path, pos+1);
            }
        }
        path.remove(path.size() - 1); // reset
        visited[s] = false;
    }
    
    int V;
    List<List<Integer>> adjacent;
        
    public Graph(int V) {
        this.V = V;
        adjacent = new ArrayList<List<Integer>>(V);
        for (int i = 0; i < V; i++) adjacent.add(new ArrayList<Integer>());
    }
        
    public void addEdge(int u, int v) {
        adjacent.get(u).add(new Integer(v));
    }
}
