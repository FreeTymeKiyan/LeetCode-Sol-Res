package com.freetymekiyan.algorithms.level.easy;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 359. Logger Rate Limiter
 * <p>
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if
 * and only if it is not printed in the last 10 seconds.
 * <p>
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given
 * timestamp, otherwise returns false.
 * <p>
 * It is possible that several messages arrive roughly at the same time.
 * <p>
 * Example:
 * <p>
 * Logger logger = new Logger();
 * <p>
 * // logging string "foo" at timestamp 1
 * logger.shouldPrintMessage(1, "foo"); returns true;
 * <p>
 * // logging string "bar" at timestamp 2
 * logger.shouldPrintMessage(2,"bar"); returns true;
 * <p>
 * // logging string "foo" at timestamp 3
 * logger.shouldPrintMessage(3,"foo"); returns false;
 * <p>
 * // logging string "bar" at timestamp 8
 * logger.shouldPrintMessage(8,"bar"); returns false;
 * <p>
 * // logging string "foo" at timestamp 10
 * logger.shouldPrintMessage(10,"foo"); returns false;
 * <p>
 * // logging string "foo" at timestamp 11
 * logger.shouldPrintMessage(11,"foo"); returns true;
 * <p>
 * Companies: Google, Uber, Facebook, Yelp
 * <p>
 * Related Topics: Hash Table, Design
 * <p>
 * Similar Questions: (M) Design Hit Counter
 */
public class LoggerRateLimiter {

  class Logger {
    Queue<Tuple> q = new ArrayDeque<>();
    Set<String> dict = new HashSet<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
      while (!q.isEmpty() && q.peek().t <= timestamp - 10) {
        Tuple next = q.poll();
        dict.remove(next.msg);
      }
      if (!dict.contains(message)) {
        q.offer(new Tuple(timestamp, message));
        dict.add(message);
        return true;
      }
      return false;
    }

    private class Tuple {
      int t;
      String msg;

      public Tuple(int t, String msg) {
        this.t = t;
        this.msg = msg;
      }
    }
  }
}