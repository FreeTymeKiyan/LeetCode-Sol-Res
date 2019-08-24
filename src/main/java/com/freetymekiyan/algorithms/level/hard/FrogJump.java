package com.freetymekiyan.algorithms.level.hard;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 * <p>
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the
 * river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1
 * unit.
 * <p>
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog
 * can only jump in the forward direction.
 * <p>
 * Note:
 * <p>
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * Example 1:
 * <p>
 * [0,1,3,5,6,8,12,17]
 * <p>
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 * <p>
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 * Example 2:
 * <p>
 * [0,1,2,3,4,8,9,11]
 * <p>
 * Return false. There is no way to jump to the last stone as
 * the gap between the 5th and 6th stone is too large.
 * <p>
 * Related Topics: Dynamic Programming
 * Companies: Google, Apple, Facebook, Oracle
 */
public class FrogJump {

  private boolean[][] canCross;

/**
 * DP, bottom-up.
 * Recurrence relation:
 * canCross[i][j] denotes the state whether the frog can cross
 * at stone i+1 with j steps
 * For any j < i, distance = stones[i] - stones[j]
 * If canCross[j][distance] = true
 * Then canCross[i][distance] = true
 * canCross[i][distance+1] = true if distance + 1 <  N+1
 * canCross[i][distance-1] = true if distance - 1 >= 0
 * Base cases:
 * canCross[0][1] = true since the frog starts at stone 1 with 0 step
 */
public boolean canCross(int[] stones) {
  if (stones == null || stones.length == 0) {
    return false;
  }
  if (stones.length == 1) {
    return true; // Frog is initially on the first stone
  }
  final int numOfStones = stones.length;
  // Row is stone - 1, column is the steps can take
  final boolean[][] canCross = new boolean[numOfStones][numOfStones];
  canCross[0][1] = true; // Frog can jump 1 step from first stone
  for (int i = 1; i < canCross.length; i++) {
    for (int j = 0; j < i; j++) { // Iterate all stones before i
      final int distance = stones[i] - stones[j];
      if (distance < 0 || distance > i || !canCross[j][distance]) {
        continue;
      }
      canCross[i][distance] = true;
      if (distance > 0) { // distance - 1 >= 0
        canCross[i][distance - 1] = true;
      }
      if (distance < numOfStones - 1) { // distance + 1 < numOfStones
        canCross[i][distance + 1] = true;
      }
      if (i == numOfStones - 1) { // Reach the last stone
        return true;
      }
    }
  }
  return false;
}

  /**
   * DP, top-down.
   * Recurrence relation:
   * canCross(n, k) denotes whether (n + 1)th stone can be reached with k steps
   * for all k from [0, n-1]:
   * | canCross(n, k) = canCross(n-k, k-1) || canCross(n-k, k) || canCross(n-k, k+1)
   * Base case:
   * canCross(0, 1) = true since the first stone can be reached with 0 step
   */
  public boolean canCross2(int[] stones) throws NoSuchMethodException {
    canCross = new boolean[stones.length][stones.length];
    return canCross(stones, stones.length, stones.length);
  }

  private boolean canCross(int[] stones, final int n, final int k) throws NoSuchMethodException {
    throw new NoSuchMethodException("TODO: 2019-08-24 add top-down approach");
  }
}