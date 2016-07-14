import java.util.*;

/**
 * Dijkstra's Algorithm
 * Find the shortest weighted path from some starting vertex to all vertices.
 * Edges have non-negative weight
 * 
 * Tags: Graph
 */
class Dijkstra {
    
    public static void main(String[] args) {
        
    }

    /**
     * Create a wrapper class for edge
     */
    class Path implements Comparable<Path> {
        Vertex dest;
        double cost;
        
        public Path(Vertex d, double c) {
            dest = d;
            cost = c;
        }
        
        @Override
        public int compareTo(Path rhs) {
            double otherCost = rhs.cost;
            return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
        }
    }
    
    HashMap<String, Vertex> vertexMap = new HashMap<String, Vertex>();
    
    public void dijkstra(String startName) {
        PriorityQueue<Path> pq = new PriorityQueue<Path>();
        Vertex start = vertexMap.get(startName);
        if (start == null) 
            throw new NoSuchElementException("Start vertex not found");
        
        pq.add(new Path(start, 0));
        start.dist = 0;
        
        int nodesSeen = 0;
        while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {
            Path vrec = pq.remove();
            Vertex v = vrec.dest;
            if (v.visited != 0) // skip visited
                continue;
            
            v.visited = 1; // set visited
            nodesSeen++;
            
            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double cvw = e.cost; // cost between v and w
                if (cvw < 0) {
                    // throw new GraphException("Graph has negative edges");
                }
                
                if (w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v; // set previous Vertex
                    pq.add(new Path(w, w.dist));
                }
            }
        }
    }
    
    class Vertex {
        int visited;
        Vertex prev;
        double dist;
        List<Edge> adj;
    }
    
    class Edge {
        Vertex dest;
        double cost;
    }
}
