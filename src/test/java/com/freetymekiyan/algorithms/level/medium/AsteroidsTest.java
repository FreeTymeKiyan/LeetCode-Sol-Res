package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AsteroidsTest {

    // One collision
    private static int[] INPUT1 = {5, 10, -5}, OUTPUT1 = {5, 10};
    // Same size collision
    private static int[] INPUT2 = {8, -8}, OUTPUT2 = {};
    // Two collision
    private static int[] INPUT3 = {10, 2, -5}, OUTPUT3 = {10};
    // No collision, different direction
    private static int[] INPUT4 = {-2, -1, 1, 2}, OUTPUT4 = {-2, -1, 1, 2};
    // Two same size collisions
    private static int[] INPUT5 = {2, 1, -1, -2}, OUTPUT5 = {};
    // Collide all the way
    private static int[] INPUT6 = {1, 2, 3, 4, -10}, OUTPUT6 = {-10};
    // One collision with left moving asteroids in the front
    private static int[] INPUT7 = {-2, -2, 1, -2}, OUTPUT7 = {-2, -2, -2};

    private static int[][] INPUTS = new int[][]{INPUT1, INPUT2, INPUT3, INPUT4, INPUT5, INPUT6, INPUT7};
    private static int[][] OUTPUTS = new int[][]{OUTPUT1, OUTPUT2, OUTPUT3, OUTPUT4, OUTPUT5, OUTPUT6, OUTPUT7};

    @Test
    public void testAsteroidCollisionExamples() {
        Asteroids solution = new Asteroids();
        for (int i = 0; i < INPUTS.length; i++) {
            int[] output = solution.asteroidCollision(INPUTS[i]);
            Assert.assertEquals(output, OUTPUTS[i]);
        }
    }
}
