package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you
 * should also have finished course 1. So it is impossible.
 * <p>
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 * <p>
 * Hints:
 * This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological
 * ordering exists and therefore it will be impossible to take all courses.
 * <p>
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
 * Topological Sort.
 * <p>
 * Topological sort could also be done via BFS.
 * <p>
 * Tags: Depth-first Search, Breadth-first Search, Graph, Topological Sort
 * Similar Problems: (M) Course Schedule II, (M) Graph Valid Tree, (M) Minimum Height Trees
 */
public class CourseSchedule {

    /**
     * Topological Sort. BFS.
     * Detect whether a cycle exists in a directed graph.
     * Also make sure it's connected.
     * Implementation:
     * Build an array of in-degrees of each node.
     * Add all 0 in-degree node to the queue.
     * Count the number of nodes visited.
     * Remove the node from graph by updating in-degrees of all nodes it connects.
     * If in-degree becomes 0, add the node to queue.
     * Return whether count is numCourses.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        // Build in degrees array for each node.
        int[] inDegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++;
        }
        // Enqueue all 0 in-degree nodes.
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // Toposort.
        int count = 0;
        while (!queue.isEmpty()) {
            int c = queue.poll(); // Dequeue node and add to result.
            count++;
            for (int[] p : prerequisites) {
                if (c == p[1]) { // Remove c from graph.
                    inDegrees[p[0]]--; // Reduce in-degree.
                    if (inDegrees[p[0]] == 0) {
                        queue.offer(p[0]);
                    }
                }
            }
        }
        return count == numCourses;
    }

    /**
     * Topological Sort. DFS.
     * Detect whether there is a cycle.
     * Build an adjacency list with edge list.
     * The DFS toposort on the graph.
     */
    public boolean canFinishB(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Toposort. DFS.
     * Check temporary mark. If temporary mark is true, there is a cycle. Return false.
     * Set temporary mark to true.
     * Visit all neighbors first.
     * Set temporary mark to false.
     * Return true.
     */
    private boolean dfs(List<List<Integer>> graph, boolean[] visited, int course) {
        if (visited[course]) { // Cycle detected.
            return false;
        }
        visited[course] = true; // Set temp mark.
        for (int n : graph.get(course)) {
            if (!dfs(graph, visited, n)) {
                return false;
            }
        }
        visited[course] = false; // Reset temp mark.
        return true;
    }

}
