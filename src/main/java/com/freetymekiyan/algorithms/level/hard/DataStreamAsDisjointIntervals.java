package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 352. Data Stream as Disjoint Intervals
 * <p>
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list
 * of disjoint intervals.
 * <p>
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * <p>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * <p>
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 * A: Optimize addNum complexity to O(logn). getIntervals can be O(n) since intervals are small.
 * <p>
 * Related Topics: Binary Search Tree
 * Similar Questions: (M) Summary Ranges, (M) Find Right Interval, (H) Range Module
 */
public class DataStreamAsDisjointIntervals {

    /**
     * Your SummaryRanges object will be instantiated and called as such:
     * SummaryRanges obj = new SummaryRanges();
     * obj.addNum(val);
     * List<Interval> param_2 = obj.getIntervals();
     */
    class SummaryRanges {

        private final TreeMap<Integer, Interval> startToIntervals;

        /**
         * Initialize your data structure here.
         */
        public SummaryRanges() {
            startToIntervals = new TreeMap<>();
        }

        /**
         * TreeMap. O(logn) Time.
         * Tree map already has the ability to search lower/higher keys.
         * Save a start to interval mapping in a tree map.
         * When adding number, check if the tree map contains the value already.
         * If yes, return.
         * If no, get lower key, low, and higher key, high.
         * If low and high both exist and low's interval's end + 1 = val and high - 1 = val:
         * | The val merges two intervals. Setting low interval's end to high interval's end and remove high interval.
         * If low exists and low interval's end + 1 = val:
         * | val can expand the ending of the low interval. Increment low interval's end by 1.
         * If low exists and low interval's end + 1 > val:
         * | val is within the low interval. No change needed.
         * If high exists and high - 1 = val:
         * | val can expand the starting of the high interval. Decrement high interval's start by 1.
         * | Note that the map's key, which is start, should also update. So put a new entry and remove the old one.
         * If both don't exist:
         * | Put a new entry to map.
         */
        public void addNum(int val) {
            if (startToIntervals.containsKey(val)) return;
            Integer low = startToIntervals.lowerKey(val);
            Integer high = startToIntervals.higherKey(val);
            if (low != null && high != null && startToIntervals.get(low).end + 1 == val && high - 1 == val) {
                // Merge
                startToIntervals.get(low).end = startToIntervals.get(high).end;
                startToIntervals.remove(high);
            } else if (low != null && startToIntervals.get(low).end + 1 >= val) {
                startToIntervals.get(low).end = Math.max(startToIntervals.get(low).end, val);
            } else if (high != null && high - 1 == val) {
                startToIntervals.put(val, new Interval(val, startToIntervals.get(high).end));
                startToIntervals.remove(high);
            } else {
                startToIntervals.put(val, new Interval(val, val));
            }
        }

        /**
         * Get intervals list directly from the map's value collection. O(1) Time.
         */
        public List<Interval> getIntervals() {
            return new ArrayList<>(startToIntervals.values());
        }
    }

    /**
     * Binary Search Tree.
     * Search for nearest start or end, which are val-1 and val+1.
     * Binary search tree can keep the values sorted and provide O(logn) search/find.
     */
    class SummaryRanges2 {

        private BstNode root;
        private List<Interval> intervals;

        /**
         * Initialize your data structure here.
         */
        public SummaryRanges2() {
            root = null;
            intervals = new ArrayList<>();
        }

        /**
         * Insert one value into the BST.
         * If root is null, create a root.
         * If root is not null:
         * | Find the value in the BST. If not found, return.
         * | Find val-1 in the BST as low, val+1 as high.
         * | If both exist:
         * |   Save low interval and high interval's end, newEnd.
         * |   Update BST by deleting the high interval.
         * |   Update low interval's end with newEnd.
         * | If only low interval exists:
         * |   Increment low interval's end by 1.
         * | If only high interval exists:
         * |   Decrement high interval's start by 1.
         * | If both don't exist:
         * |   Add val as a new node.
         */
        public void addNum(int val) {
            if (root == null) {
                root = addKey(val, root);
            } else {
                if (findKey(val, root) != null) return; // val already exists.
                BstNode low = findKey(val - 1, root);
                BstNode high = findKey(val + 1, root);
                if (low != null && high != null) {
                    Interval l = low.interval;
                    int newEnd = high.interval.end;
                    root = remove(high.interval, root);
                    l.end = newEnd;
                } else if (low != null) {
                    low.interval.end++;
                } else if (high != null) {
                    high.interval.start--;
                } else {
                    root = addKey(val, root);
                }
            }
        }

        /**
         * In-order traversal on the BST. O(n) Time.
         */
        public List<Interval> getIntervals() {
            intervals.clear();
            inOrder(root);
            return intervals;
        }

        /**
         * Find the minimum of BST. O(logn) Time.
         * If root is null, return null.
         * If left subtree is null, return root itself.
         * Otherwise return the minimum of left subtree.
         */
        BstNode findMin(BstNode root) {
            if (root == null) return null;
            if (root.left == null) return root;
            return findMin(root.left);
        }

        /**
         * Remove an interval from BST and return a updated root node.
         * If root is null, return null.
         * If interval is null, nothing to remove, return root.
         * If interval is on root interval's right, remove in the right subtree.
         * If interval is on root interval's left, remove in the left subtree.
         * Now only root is possible.
         * If both subtrees exist:
         * | Find the minimum of right subtree.
         * | Swap with root.
         * | Delete the minimum.
         * If any subtree is null:
         * | Set root to the other existing subtree.
         * Return the root node.
         */
        BstNode remove(Interval in, BstNode root) {
            if (root == null) {
                return null;
            }
            if (in == null) {
                return root;
            }
            if (in.start > root.interval.end) {
                root.right = remove(in, root.right);
            } else if (in.end < root.interval.start) {
                root.left = remove(in, root.left);
            } else if (root.left != null && root.right != null) {
                root.interval = findMin(root.right).interval;
                root.right = remove(root.interval, root.right);
            } else {
                root = (root.left != null) ? root.left : root.right;
            }
            return root;
        }

        /**
         * Find value in BST.
         * If root is null, return null.
         * If val is on interval's left, find in left subtree.
         * If val is on interval's right, find in right subtree.
         * Otherwise return root.
         */
        BstNode findKey(int val, BstNode root) {
            if (root == null) {
                return null;
            }
            if (val < root.interval.start) {
                return findKey(val, root.left);
            } else if (val > root.interval.end) {
                return findKey(val, root.right);
            } else {
                return root;
            }
        }

        /**
         * Insert a new key into the BST.
         * If root is null, create a new root with the given value.
         * If val < root.interval.start, should insert to the left subtree.
         * If val > root.interval.end, should insert to the right subtree.
         * Return root after tree is updated.
         */
        BstNode addKey(int val, BstNode root) {
            if (root == null) {
                root = new BstNode(new Interval(val, val));
            } else if (val < root.interval.start) {
                root.left = addKey(val, root.left);
            } else if (val > root.interval.end) {
                root.right = addKey(val, root.right);
            }
            return root;
        }

        void inOrder(BstNode root) {
            if (root != null) {
                inOrder(root.left);
                intervals.add(root.interval);
                inOrder(root.right);
            }
        }

        /**
         * Binary Search Tree node that contains an interval as value.
         * Also has two children, left and right.
         */
        class BstNode {
            Interval interval;
            BstNode left;
            BstNode right;

            BstNode(Interval in) {
                interval = in;
            }

            BstNode(int start, int end) {
                interval = new Interval(start, end);
            }
        }
    }
}