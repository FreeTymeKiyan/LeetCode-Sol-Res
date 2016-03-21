import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a
 * rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 *
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 *
 * Format The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
 * undirected edges (each edge is a pair of labels).
 *
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 *
 * Example 1:
 *
 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 * 0 | 1 / \ 2   3 return [1]
 *
 * Example 2:
 *
 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 * 0  1  2 \ | / 3 | 4 | 5 return [3, 4]
 *
 * Note:
 *
 * (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are
 * connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 *
 * (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Tags: BFS, Graph Similar Problems: (M) Course Schedule, (M) Course Schedule II
 */
class MinimumHeightTrees {

    public static void main(String[] args) {
        MinimumHeightTrees mht = new MinimumHeightTrees();
        mht.findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}});
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        if (n == 2) return Arrays.asList(0, 1);
        // build graph
        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adj.add(new HashSet<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // find leaves
        LinkedList<Integer> leaves = new LinkedList<>(); // better memory usage
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) leaves.offer(i);
        }

        while (n > 2) {
            int numLeaf = leaves.size();
            n -= numLeaf;
            for (int i = 0; i < numLeaf; i++) {
                // update graph
                int curNode = leaves.poll();
                int j = adj.get(curNode).iterator().next();
                adj.get(j).remove(curNode);
                if (adj.get(j).size() == 1) leaves.offer(j); // new leaves
            }
        }
        return leaves;
    }

}
