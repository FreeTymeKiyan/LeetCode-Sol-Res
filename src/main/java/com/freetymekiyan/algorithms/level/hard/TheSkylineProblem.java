package com.freetymekiyan.algorithms.level.hard;

import java.util.*;

/**
 * 218. The Skyline Problem
 * <p>
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
     * My own implementation with max heap.
     * The candidates are sorted by x asc, y desc.
     * For each candidate point:
     * | If it's a left point, it's a start of a segment.
     * |   Add the height to heap.
     * |   If the height is higher than previous height, add it as key point and update previous height.
     * | If it's a right point, it's an end of a segment.
     * |   Remove the height from heap. Now the height may change.
     * |   If there is no segment, add (x, 0) to key points, update previous height as 0.
     * |   If there are more segments, add (x, highest) to key points, update previous height as highest.
     * The logic above can further simplify to: 1. Update heap. 2. If height changes, add key points and update height.
     * If height doesn't change, of course there is no new key points.
     * Return a list of key points.
     */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> corners = new ArrayList<>(buildings.length * 2);
        for (int[] b : buildings) {
            corners.add(new int[]{b[0], b[2]});
            corners.add(new int[]{b[1], -b[2]});
        }
        corners.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // Max heap.
        pq.offer(0); // Add 0 as minimum height so that we won't need to check whether heap is empty when we remove a height.
        List<int[]> keyPoints = new ArrayList<>();
        int height = 0;
        for (int[] c : corners) {
            if (c[1] > 0) { // Top-left. Add a segment height to heap.
                pq.offer(c[1]);
            } else { // Top-right. Remove a segment height from heap.
                pq.remove(-c[1]); // This is O(n). Can be faster with TreeMap.
            }
            if (pq.peek() != height) { // If the height changes, that means a key point.
                keyPoints.add(new int[]{c[0], pq.peek()});
                height = pq.peek();
            }
        }
        return keyPoints;
    }

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
     * <p>
     * My comments:
     * The use of tree map makes me feel a bit unnecessary, since priority queue or heap can contain duplicates.
     * A priority queue or max heap is enough.
     * The only upside is that TreeMap get/put in O(logn). Priority queue removes a specific value in O(n).
     * Because it scans through all values to find the one to remove.
     */
    public List<int[]> getSkyline2(int[][] buildings) {
        // Build and sort critical points.
        List<int[]> corners = new ArrayList<>();
        for (int[] b : buildings) {
            corners.add(new int[]{b[0], -b[2]}); // Left point. Set to negative to benefit sorting.
            corners.add(new int[]{b[1], b[2]}); // Right point.
        }
        Collections.sort(corners, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        // A hash heap. Store height and number of rectangles available. Get max height by calling firstKey().
        TreeMap<Integer, Integer> heights = new TreeMap<>(Collections.reverseOrder()); // Note reverse order, max heap.
        heights.put(0, 1); // Add a dummy rectangle with height 0.
        int prevHeight = 0; // Store previous height for comparison later.
        List<int[]> skyLine = new ArrayList<>();
        for (int[] c : corners) {
            // Update heap according to left/right point.
            if (c[1] < 0) { // Height < 0, left point, add rectangle.
                heights.merge(-c[1], 1, (oldValue, newValue) -> oldValue + newValue);
            } else { // Height > 0, right point, remove rectangle.
                heights.merge(c[1], 1, (oldValue, newValue) -> oldValue.equals(newValue) ? null : oldValue - newValue);
            }
            if (prevHeight != heights.firstKey()) { // If current max height is the same as prevHeight, not a contour.
                skyLine.add(new int[]{c[0], heights.firstKey()});
                prevHeight = heights.firstKey();
            }
        }
        return skyLine;
    }
}