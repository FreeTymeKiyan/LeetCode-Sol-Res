package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 399. Evaluate Division
 * <p>
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * <p>
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
 * where equations.size() == values.size(), and the values are positive. This represents the equations. Return
 * vector<double>.
 * <p>
 * According to the example above:
 * <p>
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * <p>
 * <p>
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is
 * no contradiction.
 * <p>
 * Companies: Google, Bloomberg, Uber, Amazon, Facebook, Adobe
 * <p>
 * Related Topics: Union Find, Graph
 */
public class EvaluateDivision {

  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    Map<String, List<String>> pairs = new HashMap<>();
    Map<String, List<Double>> valuesPair = new HashMap<>();
    for (int i = 0; i < equations.length; i++) {
      String[] equation = equations[i];
      if (!pairs.containsKey(equation[0])) {
        pairs.put(equation[0], new ArrayList<>());
        valuesPair.put(equation[0], new ArrayList<>());
      }
      if (!pairs.containsKey(equation[1])) {
        pairs.put(equation[1], new ArrayList<>());
        valuesPair.put(equation[1], new ArrayList<>());
      }
      pairs.get(equation[0]).add(equation[1]);
      pairs.get(equation[1]).add(equation[0]);
      valuesPair.get(equation[0]).add(values[i]);
      valuesPair.get(equation[1]).add(1 / values[i]);
    }

    double[] result = new double[queries.length];
    for (int i = 0; i < queries.length; i++) {
      String[] query = queries[i];
      result[i] = dfs(query[0], query[1], pairs, valuesPair, new HashSet<String>(), 1.0);
      if (result[i] == 0.0) result[i] = -1.0;
    }
    return result;
  }

  private double dfs(String start, String end, Map<String, List<String>> pairs, Map<String, List<Double>> values, Set<String> set, double value) {
    if (set.contains(start)) return 0.0;
    if (!pairs.containsKey(start)) return 0.0;
    if (start.equals(end)) return value;
    set.add(start);

    List<String> strList = pairs.get(start);
    List<Double> valueList = values.get(start);
    double tmp = 0.0;
    for (int i = 0; i < strList.size(); i++) {
      tmp = dfs(strList.get(i), end, pairs, values, set, value * valueList.get(i));
      if (tmp != 0.0) {
        break;
      }
    }
    set.remove(start);
    return tmp;
  }
}