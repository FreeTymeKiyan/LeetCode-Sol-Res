package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 210. Course Schedule II
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take
 * to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
 * courses, return an empty array.
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 * order is [0,1]
 * <p>
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses
 * 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct
 * ordering is[0,2,1,3].
 * <p>
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 * <p>
 * Hints:
 * 1. This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no
 * topological ordering exists and therefore it will be impossible to take all courses.
 * 2. Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
 * Topological Sort.
 * 3. Topological sort could also be done via BFS.
 * <p>
 * Company Tags: Facebook, Zenefits
 * Tags: Depth-first Search, Breadth-first Search, Graph, Topological Sort
 * Similar Problems: (M) Course Schedule, (H) Alien Dictionary, (M) Minimum Height Trees
 */
public class CourseSchedule2 {

    /**
     * Topological Sort.
     * Build graph first, then do dfs or bfs.
     */
    private int currentLabel;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        List<List<Integer>> adjacent = new ArrayList<>(numCourses);
        initGraph(inDegrees, adjacent, prerequisites);
//        return dfs(numCourses, adjacent);
        return bfs(inDegrees, adjacent);
    }

    /**
     * Build an adjacency list and an incoming degree array of graph.
     * Don't know if its acyclic yet.
     * Have to check later in topological sort.
     *
     * @param indegrees In-degree of each node.
     */
    private void initGraph(int[] indegrees, List<List<Integer>> adjs, int[][] prerequisites) {
        int n = indegrees.length;
        while (n-- > 0) {
            adjs.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            indegrees[edge[0]]++;
            adjs.get(edge[1]).add(edge[0]);
        }
    }

    /**
     * Topological Sort. BFS.
     * Start from all nodes with 0 in-degree, which means no prerequisites.
     * While queue is not empty:
     * | Dequeue the next node, add it to result.
     * | Then remove it from the graph by reducing the in-degree of its adjacent nodes.
     * | If adjacent node's in-degree becomes 0, add it to queue.
     * Finally, check whether all nodes are visited.
     */
    private int[] bfs(int[] inDegrees, List<List<Integer>> adjs) {
        int[] order = new int[inDegrees.length];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) { // Add all 0 in-degree node to queue first.
                queue.offer(i);
            }
        }
        int i = 0; // An index to result array.
        while (!queue.isEmpty()) {
            int from = queue.poll();
            order[i++] = from; // Add it to result and update index.
            for (int to : adjs.get(from)) { // Neighbors.
                inDegrees[to]--;
                if (inDegrees[to] == 0) { // If becomes 0, add to queue.
                    queue.offer(to);
                }
            }
        }
        // IMPORTANT! Check whether all vertices are added to result.
        // Otherwise, the graph is not DAG.
        return i == inDegrees.length ? order : new int[0];
    }

    /**
     * Topological Sort. DFS.
     * Create a boolean array from visited state of each node.
     * Create a stack to store the result.
     * Then dfs each unvisited node.
     * Finally, convert the result to an integer array.
     */
    private int[] dfs(int n, List<List<Integer>> adjs) {
        BitSet hasCycle = new BitSet(1); // Whether there is cycle in graph. Temporary mark.
        BitSet visited = new BitSet(adjs.size()); // Whether a node is visited. Permanent mark.
        BitSet onStack = new BitSet(adjs.size()); // Whether the node is on stack already during DFS.
        // DFS.
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = adjs.size() - 1; i >= 0; i--) {
            // Visit each unvisited node.
            if (!visited.get(i) && !hasOrder(i, adjs, visited, onStack, stack)) {
                return new int[0];
            }
        }
        // Convert stack result to int[].
        int[] res = new int[adjs.size()];
        for (int i = 0; !stack.isEmpty(); i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    /**
     * DFS.
     * With node visited states and node on stack states.
     */
    private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack, Deque<Integer> order) {
        visited.set(from); // Mark from temporarily.
        onStack.set(from);
        for (int to : adjs.get(from)) {
            if (visited.get(to) == false) { // Adjacent nodes should not be visited.
                if (hasOrder(to, adjs, visited, onStack, order) == false) {
                    return false;
                }
            } else if (onStack.get(to) == true) { // Adjacent nodes should not be on stack.
                return false;
            }
        }
        onStack.clear(from);
        order.push(from); // Push to stack finally.
        return true;
    }

    /**
     * Topological Sort.
     * Graph represented with map.
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] courses : prerequisites) {
            if (!graph.containsKey(courses[1])) graph.put(courses[1], new HashSet<>());
            graph.get(courses[1]).add(courses[0]);
            inDegrees[courses[0]] += 1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0)
                queue.offer(i);
        }

        int[] order = new int[numCourses];
        int i = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[i] = course;
            i++;
            if (graph.containsKey(course)) {
                for (int neighbor : graph.get(course)) {
                    inDegrees[neighbor] -= 1;
                    if (inDegrees[neighbor] == 0) queue.offer(neighbor);
                }
            }
        }
        return i == numCourses ? order : new int[]{};
    }
}