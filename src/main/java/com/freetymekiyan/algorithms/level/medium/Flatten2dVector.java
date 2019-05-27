package com.freetymekiyan.algorithms.level.medium;

import java.util.Iterator;
import java.util.List;

/**
 * 251. Flatten 2D Vector
 * <p>
 * Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and
 * hasNext.
 * <p>
 * Example:
 * <p>
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 * <p>
 * iterator.next(); // return 1
 * iterator.next(); // return 2
 * iterator.next(); // return 3
 * iterator.hasNext(); // return true
 * iterator.hasNext(); // return true
 * iterator.next(); // return 4
 * iterator.hasNext(); // return false
 * <p>
 * Notes:
 * <p>
 * Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across
 * multiple test cases. Please see here for more details.
 * You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d
 * vector
 * when next() is called.
 * <p>
 * Follow up:
 * <p>
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 * <p>
 * Companies: Airbnb, Facebook, Uber, Apple, Salesforce, Atlassian, Lyft, Amazon, LinkedIn, Yelp, Google, Twitter,
 * Zenefits
 * <p>
 * Related Topics: Design
 * <p>
 * Similar Questions: (M) Binary Search Tree Iterator, (M) Zigzag Iterator, (M) Peeking Iterator, (M) Flatten Nested
 * List Iterator
 */
public class Flatten2dVector {

  public class Vector2D {
    Iterator<List<Integer>> itrs;
    Iterator<Integer> row;

    public Vector2D(List<List<Integer>> vec2d) {
      if (vec2d == null || vec2d.size() == 0) return;
      itrs = vec2d.iterator();
      row = itrs.next().iterator();
      getNextRow();
    }

    private void getNextRow() {
      while (!row.hasNext() && itrs.hasNext()) row = itrs.next().iterator();
    }

    public int next() {
      int next = row.next();
      getNextRow();
      return next;
    }

    public boolean hasNext() {
      return row != null && row.hasNext();
    }
  }
}