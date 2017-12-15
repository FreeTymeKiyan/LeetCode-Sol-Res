package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 743. Network Delay Time
 * There are N network nodes, labelled 1 to N.
 * <p>
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the
 * target node, and w is the time it takes for a signal to travel from source to target.
 * <p>
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is
 * impossible, return -1.
 * <p>
 * Note:
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
 * <p>
 * Related Topics: Heap, Depth-first Search, Breadth-first Search, Graph
 */
public class NetworkDelayTime {

    /**
     * Dijkstra's Algorithm.
     * Generate minimum distances from node K to each node possible.
     * Then the maximum is the time needed.
     * Similar to BFS, except that if uses a priority queue (min heap).
     * While the min heap is not empty:
     * | Dequeue a node. If it's visited, skip.
     * | If not, update its distance if shorter.
     * | Get all of its edges. For each edge:
     * |   If the neighboring node is not visited, add it and added distance to the heap.
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Queue<int[]> pq = new PriorityQueue<>(N, (i1, i2) -> i1[0] - i2[0]);
        pq.offer(new int[]{0, K});
        while (!pq.isEmpty()) { // BFS with min heap.
            int[] next = pq.poll();
            int d = next[0], node = next[1];
            if (distances[node] != Integer.MAX_VALUE) continue; // Visited.
            distances[node] = d;
            for (int[] time : times) { // Get all edges. Can optimize to O(1) with space.
                if (time[0] == node) {
                    int neighbor = time[1];
                    int d2 = time[2];
                    if (distances[neighbor] == Integer.MAX_VALUE) {
                        pq.offer(new int[]{d + d2, neighbor});
                    }
                }
            }
        }
        // Get maximum time from all distances.
        int maxTime = 0;
        for (int i = 1; i <= N; i++) { // Note that i starts from 1, not 0, since the node range is [1, N].
            maxTime = Math.max(maxTime, distances[i]);
        }
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }

    /**
     * Bellman Ford.
     */
    public int networkDelayTime2(int[][] times, int N, int K) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[K] = 0; // distance to node K is 0.
        for (int i = 0; i < N; i++) {
            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                if (distances[u] != Integer.MAX_VALUE && distances[v] > distances[u] + w) {
                    distances[v] = distances[u] + w;
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) { // Note that i starts from 1, not 0, since the node range is [1, N].
            maxTime = Math.max(maxTime, distances[i]);
        }
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }

    /**
     * Dijkstra. Optimized getEdges to O(1).
     */
    public int networkDelayTime3(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            List<int[]> edges = graph.getOrDefault(time[0], new ArrayList<>());
            edges.add(new int[]{time[1], time[2]});
            graph.put(time[0], edges);
        }

        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Queue<int[]> pq = new PriorityQueue<>(N, Comparator.comparingInt(i1 -> i1[1])); // int[0] is node id, int[1] is distance.
        pq.offer(new int[]{K, 0});
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            int node = next[0], d = next[1];
            if (distances[node] != Integer.MAX_VALUE) continue;
            distances[node] = d;
            List<int[]> edges = graph.get(node);
            if (edges == null) continue;
            for (int[] e : edges) {
                int neighbor = e[0], d2 = e[1];
                if (distances[neighbor] == Integer.MAX_VALUE) {
                    pq.offer(new int[]{neighbor, d + d2});
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = distances[i] > maxTime ? distances[i] : maxTime;
        }
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }
}
