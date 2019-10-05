package com.freetymekiyan.algorithms.level.hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 407. Trapping Rain Water II
 * <p>
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute
 * the volume of water it is able to trap after raining.
 * <p>
 * Note:
 * <p>
 * Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
 * <p>
 * Example:
 * <p>
 * Given the following 3x6 height map:
 * |[
 * |  [1,4,3,1,3,2],
 * |  [3,2,1,3,2,4],
 * |  [2,3,3,2,3,1]
 * |]
 * <p>
 * Return 4.
 * https://assets.leetcode.com/uploads/2018/10/13/rainwater_empty.png
 * The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
 * <p>
 * https://assets.leetcode.com/uploads/2018/10/13/rainwater_fill.png
 * After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
 * <p>
 * Related Topics: Heap, Breadth-first Search
 * <p>
 * Companies:
 * <p>
 * Similar Questions: (H) Trapping Rain Water
 */
public class TrappingRainWater2 {

/**
 * Heap.
 * <p>
 * Water needs a container to be trapped.
 * A container is formed by surrounding borders.
 * The borders of the matrix can't hold water, they are initialized as first borders.
 * Use min heap to maintain the current minimum border height.
 * If a cell height is < min border height, water can be trapped. Update volume.
 * If a cell height is >= min border height, water can't be trapped.
 * Add the cell to the heap with its position and min border height.
 * The min border height is maximum of its height or the current min border height.
 * </p>
 */
public int trapRainWater(int[][] heightMap) {
  if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
    return 0;
  }
  final int rows = heightMap.length;
  final int cols = heightMap[0].length;
  final boolean[][] visited = new boolean[rows][cols];
  // Add the matrix border as the initial border since they can't trap water.
  final Queue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(c -> c.minBorderHeight));
  for (int i = 0; i < rows; i++) {
    if (i == 0 || i == rows - 1) {
      for (int j = 0; j < cols; j++) {
        visited[i][j] = true;
        queue.offer(new Cell(i, j, heightMap[i][j]));
      }
    } else {
      visited[i][0] = true;
      visited[i][cols - 1] = true;
      queue.offer(new Cell(i, 0, heightMap[i][0]));
      queue.offer(new Cell(i, cols - 1, heightMap[i][cols - 1]));
    }
  }
  // BFS
  final int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};  // Up, left, down, right
  int volume = 0;
  while (!queue.isEmpty()) {
    final Cell cell = queue.poll(); // Get the cell with minimum border height
    for (final int[] dir : directions) { // BFS 4 neighboring cells
      final int row = cell.row + dir[0];
      final int col = cell.col + dir[1];
      if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row][col]) { // Within matrix, unvisited
        visited[row][col] = true;
        if (cell.minBorderHeight > heightMap[row][col]) {
          volume += cell.minBorderHeight - heightMap[row][col];
        }
        // Or just: volume += Math.max(0, cell.height - heightMap[row][col]);
        queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.minBorderHeight)));
      }
    }
  }
  return volume;
}

class Cell {
  int row;
  int col;
  int minBorderHeight;

  public Cell(int row, int col, int minBorderHeight) {
    this.row = row;
    this.col = col;
    this.minBorderHeight = minBorderHeight;
  }
}
}