import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle values.
 *
 * Examples:
 *
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 *
 * double findMedian() - Return the median of all elements so far.
 *
 * For example:
 *
 * add(1)
 *
 * add(2)
 *
 * findMedian() -> 1.5
 *
 * add(3)
 *
 * findMedian() -> 2
 *
 * Tags: Heap, Design
 */
public class FindMedianFromDataStream {

    static class MedianFinder {

        // smaller half, max heap
        private Queue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        // larger half, min heap
        private Queue<Integer> large = new PriorityQueue<>();

        // Adds a number into the data structure.
        public void addNum(int num) {
            small.offer(num);
            large.offer(small.poll());
            if (small.size() < large.size()) {
                small.offer(large.poll());
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            return small.size() == large.size() ? small.peek() + (large.peek() - small.peek()) / 2.0 : small.peek();
        }

        // Your MedianFinder object will be instantiated and called as such:
        // MedianFinder mf = new MedianFinder();
        // mf.addNum(1);
        // mf.findMedian();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        // [1, 2] -> [1.5]
        System.out.println(mf.findMedian());
        mf.addNum(3);
        // [1, 2, 3] -> [2]
        System.out.println(mf.findMedian());
    }
}
