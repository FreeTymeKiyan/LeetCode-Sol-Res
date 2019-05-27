package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 294. Flip Game II
 * <p>
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: +
 * and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no
 * longer make a move and therefore the other person will be the winner.
 * <p>
 * Write a function to determine if the starting player can guarantee a win.
 * <p>
 * Example:
 * <p>
 * Input: s = "++++"
 * Output: true
 * Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity.
 * <p>
 * Companies: Google
 * <p>
 * Related Topics: Backtracking, Minimax
 * <p>
 * Similar Questions: (E) Nim Game, (E) Flip Game, (M) Guess Number Higher or Lower II, (M) Can I Win
 */
public class FlipGame2 {

  public boolean canWin(String s) {
    if (s == null || s.length() < 2) return false;
    Map<String, Boolean> map = new HashMap<>();
    return canWin(s, map);
  }

  public boolean canWin(String s, Map<String, Boolean> map) {
    if (map.containsKey(s)) return map.get(s);
    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
        String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
        if (!canWin(opponent, map)) {
          map.put(s, true);
          return true;
        }
      }
    }
    map.put(s, false);
    return false;
  }
}