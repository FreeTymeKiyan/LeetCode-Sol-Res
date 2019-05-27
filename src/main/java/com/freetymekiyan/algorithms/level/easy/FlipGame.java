package com.freetymekiyan.algorithms.level.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 293. Flip Game
 * <p>
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: +
 * and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no
 * longer make a move and therefore the other person will be the winner.
 * <p>
 * Write a function to compute all possible states of the string after one valid move.
 * <p>
 * Example:
 * <p>
 * Input: s = "++++"
 * Output:
 * | [
 * |   "--++",
 * |   "+--+",
 * |   "++--"
 * | ]
 * Note: If there is no valid move, return an empty list [].
 * <p>
 * Companies: Google
 * <p>
 * Related Topics: String
 * <p>
 * Similar Questions: (M) Flip Game II
 */
public class FlipGame {

  public List<String> generatePossibleNextMoves(String s) {
    List<String> list = new ArrayList<>();
    for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; )
      list.add(s.substring(0, i) + "--" + s.substring(i + 2));
    return list;
  }

  public List<String> generatePossibleNextMoves2(String s) {
    List<String> answer = new ArrayList<>();
    char[] arr = s.toCharArray();
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] == '+' && arr[i + 1] == '+') {
        arr[i] = '-';
        arr[i + 1] = '-';
        answer.add(new String(arr));
        arr[i] = '+';
        arr[i + 1] = '+';
      }
    }
    return answer;
  }
}