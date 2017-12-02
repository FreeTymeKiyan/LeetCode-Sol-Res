package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AsteroidsTest {

    private static int[] input1 = {5, 10, -5}, output1 = {5, 10};
    private static int[] input2 = {8, -8}, output2 = {};
    private static int[] input3 = {10, 2, -5}, output3 = {10};
    private static int[] input4 = {-2, -1, 1, 2}, output4 = {-2, -1, 1, 2};
    private static int[] input5 = {2, 1, -1, -2}, output5 = {};
    private static int[] input6 = {1, 2, 3, 4, -10}, output6 = {-10};
    private static int[] input7 = {-2, -2, 1, -2}, output7 = {-2, -2, -2};

    private static int[][] inputs = new int[][]{input1, input2, input3, input4, input5, input6, input7};
    private static int[][] outputs = new int[][]{output1, output2, output3, output4, output5, output6, output7};

    @Test
    public void testAsteroidCollisionExamples() {
        Asteroids solution = new Asteroids();
        for (int i = 0; i < inputs.length; i++) {
            int[] output = solution.asteroidCollision(inputs[i]);
            Assert.assertEquals(output, outputs[i]);
        }
    }
}
