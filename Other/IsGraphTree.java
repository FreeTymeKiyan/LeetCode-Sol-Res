import java.util.*;

/**
 * Check whether a given graph is a tree
 * 
 * Tags: Graph, Tree, DFS
 */
class IsGraphTree<T> {
    public static void main(String[] args) {
        
    }
    
    /**
     * # of edges = # of vertices - 1
     * No cycles, use dfs to do cycle detection
     * Connected, which means all nodes are visited
     */
    public boolean checkTree(Graph G, Vertex<T> V) {
        if (G.edges.size() != G.vertices.size() - 1) return false;
        HashSet<Vertex<T>> set = new HashSet<Vertex<T>>();
        boolean hasCycle = dfs(G, V, set);
        if (hasCycle) return false;
        if (set.size() != G.vertices.size()) return false;
        return true;
    }
    
    private boolean dfs(Graph G, Vertex<T> V, HashSet<Vertex<T>> set) {
        set.add(V);
        for (Vertex<T> v : V.adjacent) {
            if (!set.contains(v)) return dfs(G, v, set);
            else return true;
        }
        return false;
    }
    
    class Vertex<T> {
        T value;
        List<Vertex<T>> adjacent;
    }
    
    class Graph {
        List<Vertex> vertices;
        List<Edge> edges;
    }
    
    class Edge {
        Vertex src;
        Vertex dest;
    }
}
