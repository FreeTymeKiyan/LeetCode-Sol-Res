package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 281. Zigzag Iterator
 * <p>
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * <p>
 * Example:
 * <p>
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * <p>
 * Output: [1,3,2,4,5,6]
 * <p>
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,3,2,4,5,6].
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * <p>
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you,
 * replace "Zigzag" with "Cyclic". For example:
 * <p>
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * <p>
 * Output: [1,4,8,2,5,9,3,6,7].
 * <p>
 * Companies: Google, Facebook, Apple, Amazon, Cruise Automation, Groupon
 * <p>
 * Related Topics: Design
 * <p>
 * Similar Questions: (M) Binary Search Tree Iterator, (M) Flatten 2D Vector, (M) Peeking Iterator, (M) Flatten Nested
 * List Iterator
 */
public class ZigzagIterator {

  List<List<Integer>> list;
  Queue<Integer> buffer;
  int i;

  // 2 vectors
  public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    this(new ArrayList<>());
    this.list.add(v1);
    this.list.add(v2);

  }

  // K vectors
  public ZigzagIterator(List<List<Integer>> list) {
    this.list = list;
    this.buffer = new LinkedList<>();
    this.i = 0;
  }

  public int next() {
    if (!hasNext()) return -1;
    if (!buffer.isEmpty()) return buffer.poll();

    // if buffer is empty, add i-th item from each list
    for (List<Integer> l : list) {
      if (i < l.size()) buffer.add(l.get(i));
    }
    i++;

    return next();
  }

  public boolean hasNext() {
    if (!buffer.isEmpty()) return true;

    for (List<Integer> l : list) {
      if (i < l.size()) return true;
    }

    return false;
  }
}