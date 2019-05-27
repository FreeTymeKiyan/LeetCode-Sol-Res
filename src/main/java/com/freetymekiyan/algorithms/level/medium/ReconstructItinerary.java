package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 332. Reconstruct Itinerary
 * <p>
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the
 * itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK.
 * <p>
 * Note:
 * <p>
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when
 * read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 * <p>
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 * <p>
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.
 * <p>
 * Companies: Uber, Google, Facebook, Yelp, Snapchat, Amazon, TripAdvisor, Bloomberg, Microsoft, Intuit, Expedia
 * <p>
 * Related Topics: Depth-first Search, Graph
 */
public class ReconstructItinerary {

  public List<String> findItinerary(String[][] tickets) {
    Map<String, PriorityQueue<String>> g = new HashMap<>();
    for (String[] t : tickets) {
      g.computeIfAbsent(t[0], k -> new PriorityQueue<>()).add(t[1]);
    }
    Deque<String> stack = new ArrayDeque<>();
    LinkedList<String> route = new LinkedList<>();
    stack.push("JFK");
    while (!stack.isEmpty()) {
      while (g.containsKey(stack.peek()) && !g.get(stack.peek()).isEmpty()) {
        stack.push(g.get(stack.peek()).poll());
      }
      route.addFirst(stack.pop());
    }
    return route;
  }
}