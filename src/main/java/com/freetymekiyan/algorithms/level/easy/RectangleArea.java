package com.freetymekiyan.algorithms.level.easy;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * https://leetcode.com/static/images/problemset/rectangle_area.png
 * First rectangle: A, B is the bottom left corner, C, D is the top right corner.
 * Second rectangle: E, F is the bottom left corner, G, H is the top right corner.
 * <p>
 * Rectangle Area
 * Assume that the total area is never beyond the maximum possible value of int.
 * <p>
 * Tags: Math
 */
public class RectangleArea {

    /**
     * The total area covered by two rectilinear rectangles is the sum of the two rectangles subtracted by their
     * overlapping area.
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA = (C - A) * (D - B);
        int areaB = (G - E) * (H - F);

        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int top = Math.min(D, H);
        int overlap = 0;
        if (right > left && top > bottom) {
            overlap = (right - left) * (top - bottom);
        }
        return areaA + areaB - overlap;
    }

    /**
     * More concise version.
     * Max right with left, so that when right is smaller that left, right = left, thus right - left = 0.
     * So with top and bottom.
     */
    public int computeAreaB(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E), right = Math.max(Math.min(C, G), left);
        int bottom = Math.max(B, F), top = Math.max(Math.min(D, H), bottom);
        return (C - A) * (D - B) - (right - left) * (top - bottom) + (G - E) * (H - F);
    }
}
