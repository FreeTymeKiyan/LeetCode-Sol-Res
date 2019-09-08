package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 * <p>
 * Note:
 * The number of people is less than 1,100.
 * <p>
 * Example
 * <p>
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * Tags: Greedy
 * Similar Problems: (H) Count of Smaller Numbers After Self
 */
public class QueueReconstructionByHeight {

  /**
   * Greedy.
   * Assuming there's always a solution.To fit the requirements:
   * People have smaller height should go to the tail as much as possible.
   * People have larger k should go to the front as much as possible.
   * <p>
   * Sort the array by height descending and k ascending.
   * Insert the person to result according to k.
   * Repeat till there is no more people.
   * <p>
   * If k means the # of people in front of him that are shorter or equal.
   * We should sort by height ascending and k ascending instead.
   */
  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, (person1, person2) -> person2[0] - person1[0] == 0 ? person1[1] - person2[1] : person2[0] - person1[0]);
    final List<int[]> res = new ArrayList<>(people.length);
    for (int[] p : people) {
      res.add(p[1], p);
    }
    return res.toArray(new int[0][0]);
  }

  /**
   * Same idea.
   * But comparator is faster than lambda in execution.
   */
  public int[][] reconstructQueue2(int[][] people) {
    Arrays.sort(people, new Comparator<int[]>() {
      @Override
      public int compare(int[] person1, int[] person2) {
        // Sort based on height desc, k asc
        return person2[0] - person1[0] == 0 ? person1[1] - person2[1] : person2[0] - person1[0];
      }
    });
    final List<int[]> res = new ArrayList<>(people.length);
    for (int[] p : people) {
      res.add(p[1], p);
    }
    return res.toArray(new int[0][0]);
  }
}
