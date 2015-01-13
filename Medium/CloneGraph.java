import java.util.*;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a
 * list of its neighbors.
 *
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 *
 * We use # as a separator for each node, and , as a separator for node label
 * and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 *
 * The graph has a total of three nodes, and therefore contains three parts as
 * separated by #.
 *
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming
 * a self-cycle.
 * Visually, the graph looks like the following:
 *
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 *
 * Tags: DFS, BFS, Graph
 */
class CloneGraph {
    public static void main(String[] args) {

    }

    /**
     * BFS, O(n) Time, O(n) Space
     * Use map<Integer, UndirectedGraphNode> to represent graph
     * Get node from queue
     * Check whether in graph already
     * Add neighbors to queue
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        q.add(node);
        while (!q.isEmpty()) { // BFS
            UndirectedGraphNode cur = q.poll();
            if (!map.containsKey(cur.label)) map.put(cur.label, new UndirectedGraphNode(cur.label)); // put in map to set visited
            if (cur.neighbors != null) {
                for (UndirectedGraphNode n : cur.neighbors) {
                    if (!map.containsKey(n.label)) {
                        q.add(n);
                        map.put(n.label, new UndirectedGraphNode(n.label));
                    }
                    // add to neighbors
                    map.get(cur.label).neighbors.add(map.get(n.label));
                }
            }
        }
        return map.get(node.label);
    }

    class UndirectedGraphNode {
         int label;
         List<UndirectedGraphNode> neighbors;

         UndirectedGraphNode(int x) {
             label = x;
             neighbors = new ArrayList<UndirectedGraphNode>();
         }
    }
}
