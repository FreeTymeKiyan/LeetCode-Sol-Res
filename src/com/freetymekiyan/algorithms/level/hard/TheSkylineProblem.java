package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a
 * distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * <p>
 * Figure A: https://leetcode.com/static/images/problemset/skyline1.jpg
 * Figure B: https://leetcode.com/static/images/problemset/skyline2.jpg
 * <p>
 * Buildings Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are
 * the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect
 * rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20
 * 10], [19 24 8] ] .
 * <p>
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has
 * zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline
 * contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8],
 * [24, 0] ].
 * <p>
 * Notes:
 * <p>
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5],
 * [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final
 * output as such: [...[2 3], [4 5], [12 7], ...]
 * <p>
 * Company Tags: Microsoft, Google, Facebook, Twitter, Yelp
 * Tags: Binary Indexed Tree, Segment Tree, Heap, Divide and Conquer
 */
public class TheSkylineProblem {

    /**
     * Heap. Line Sweep Algorithm.
     * All possible key points are at the edges of rectangle.
     * Add all top-left and top-right corners to a list.
     * Set top-left's height to negative to indicate it's left and put them before right points.
     * Sort the points by x asc, y asc.
     * Create a tree map with 0,1 in it by default.
     * Track the height of previous added key point.
     * Then for each point p in the list:
     * | If p[1] < 0, left point:
     * |   Add the height count to tree map.
     * | If p[1] > 0, right point:
     * |   Reduce the height count to tree map. If count is 1, remove the height.
     * | Get the highest by map.firstKey().
     * | If the highest != previous height, a key point found:
     * |   Add it to result.
     * |   Update previous height to current height.
     * <p>
     * Tricks:
     * 1. Store left points height as negative, then they can be distinguished from right points.
     * 2. Put height 0 count 1 into tree map as a dummy rectangle. 0 is the max height when there is no rectangle.
     * <p>
     * https://briangordon.github.io/2014/08/the-skyline-problem.html
     */
    public List<int[]> getSkyline(int[][] buildings) {
        // Build and sort critical points.
        List<int[]> cps = new ArrayList<>();
        for (int[] b : buildings) {
            cps.add(new int[]{b[0], -b[2]}); // Left point. Set to negative to benefit sorting.
            cps.add(new int[]{b[1], b[2]}); // Right point.
        }
        Collections.sort(cps, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        // A hash heap. Store height and number of rectangles available. Get max height by calling firstKey().
        TreeMap<Integer, Integer> maxHeight = new TreeMap<>(Collections.reverseOrder()); // Note reverse order.
        maxHeight.put(0, 1); // Add a dummy rectangle with height 0.
        int prevHeight = 0; // Store previous height for comparison later.
        List<int[]> skyLine = new ArrayList<>();
        for (int[] cp : cps) {
            // Update heap according to left/right point.
            if (cp[1] < 0) { // Height < 0, left point, add rectangle.
                Integer cnt = maxHeight.getOrDefault(-cp[1], 0); // Convert negative height to positive.
                maxHeight.put(-cp[1], cnt + 1);
            } else { // Height > 0, right point, remove rectangle.
                Integer cnt = maxHeight.get(cp[1]);
                if (cnt == 1) { // If only 1 rectangle, remove the height from heap.
                    maxHeight.remove(cp[1]);
                } else {
                    maxHeight.put(cp[1], cnt - 1);
                }
            }
            // Update result.
            int currHeight = maxHeight.firstKey(); // Get the highest rectangle.
            if (prevHeight != currHeight) { // If current max height is the same as prevHeight, not a contour.
                skyLine.add(new int[]{cp[0], currHeight});
                prevHeight = currHeight;
            }
        }
        return skyLine;
    }
}
