package com.freetymekiyan.algorithms.level.medium;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 353. Design Snake Game
 * <p>
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not
 * familiar with the game.
 * <p>
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * <p>
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's
 * score both increase by 1.
 * <p>
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was
 * eaten by the snake.
 * <p>
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 * <p>
 * Example:
 * <p>
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * <p>
 * Snake snake = new Snake(width, height, food);
 * <p>
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * <p>
 * |S| | |
 * | | |F|
 * <p>
 * snake.move("R"); -> Returns 0
 * <p>
 * | |S| |
 * | | |F|
 * <p>
 * snake.move("D"); -> Returns 0
 * <p>
 * | | | |
 * | |S|F|
 * <p>
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 * <p>
 * | |F| |
 * | |S|S|
 * <p>
 * snake.move("U"); -> Returns 1
 * <p>
 * | |F|S|
 * | | |S|
 * <p>
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * <p>
 * | |S|S|
 * | | |S|
 * <p>
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 * <p>
 * Companies: Google, Uber, Amazon, Apple, Salesforce, Zillow, Microsoft
 * <p>
 * Related Topics: Design, Queue
 */
public class DesignSnakeGame {

  public class SnakeGame {

    //2D position info is encoded to 1D and stored as two copies
    Set<Integer> set; // this copy is good for fast loop-up for eating body case
    Deque<Integer> body; // this copy is good for updating tail
    int score;
    int[][] food;
    int foodIndex;
    int width;
    int height;

    public SnakeGame(int width, int height, int[][] food) {
      this.width = width;
      this.height = height;
      this.food = food;
      set = new HashSet<>();
      set.add(0); //intially at [0][0]
      body = new LinkedList<>();
      body.offerLast(0);
    }


    public int move(String direction) {
      //case 0: game already over: do nothing
      if (score == -1) {
        return -1;
      }

      // compute new head
      int rowHead = body.peekFirst() / width;
      int colHead = body.peekFirst() % width;
      switch (direction) {
        case "U":
          rowHead--;
          break;
        case "D":
          rowHead++;
          break;
        case "L":
          colHead--;
          break;
        default:
          colHead++;
      }
      int head = rowHead * width + colHead;

      //case 1: out of boundary or eating body
      set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily
      if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
        return score = -1;
      }

      // add head for case2 and case3
      set.add(head);
      body.offerFirst(head);

      //case2: eating food, keep tail, add head
      if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
        set.add(body.peekLast()); // old tail does not change, so add it back to set
        foodIndex++;
        return ++score;
      }

      //case3: normal move, remove tail, add head
      body.pollLast();
      return score;

    }
  }
}