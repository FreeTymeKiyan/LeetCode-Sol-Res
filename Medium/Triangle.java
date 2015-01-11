/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step
 * you may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n
 * is the total number of rows in the triangle.
 * 
 * Tags: Array, DP
 */
import java.util.List;
import java.util.ArrayList;

/**
 * Each step you may move to adjacent numbers on the row below.
 * Work from bottom up and get the minimum sum
 */
class Triangle {

    enum TestType {
        TEST1, TEST2;
    }

    public static void main(String[] args) {
        List<List<Integer>> input = generateInput(TestType.TEST1);
        // List<List<Integer>> input = generateInput(TestType.TEST2);
        testInput(input);
        System.out.println(minimumTotal(input));
    }

    static List<List<Integer>> generateInput(TestType t) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (t == TestType.TEST1) {
            // [[-1],[2,3],[1,-1,-3]]
            List<Integer> row1 = new ArrayList<Integer>();
            row1.add(-1);
            result.add(row1);

            List<Integer> row2 = new ArrayList<Integer>();
            row2.add(3);
            row2.add(2);
            result.add(row2);

            List<Integer> row3 = new ArrayList<Integer>();
            row3.add(-3);
            row3.add(1);
            row3.add(-1);
            result.add(row3);
        } else if (t == TestType.TEST2) {
            // [[2],[3,4],[6,5,7],[4,1,8,3]]
            List<Integer> row1 = new ArrayList<Integer>();
            row1.add(2);
            result.add(row1);

            List<Integer> row2 = new ArrayList<Integer>();
            row2.add(3);
            row2.add(4);
            result.add(row2);

            List<Integer> row3 = new ArrayList<Integer>();
            row3.add(6);
            row3.add(5);
            row3.add(7);
            result.add(row3);

            List<Integer> row4 = new ArrayList<Integer>();
            row4.add(4);
            row4.add(1);
            row4.add(8);
            row4.add(3);
            result.add(row4);
        }
        return result;
    }

    static void testInput(List<List<Integer>> input) {
        for (List<Integer> list : input) {
            System.out.println(list.toString());
        }
        return;
    }

    /**
     * Math.min(result.get(i), result.get(i + 1)) + triangle.get(curLv).get(i)
     * Try to understand the code above
     * Pick the smaller one of next row and add it up to current level
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int level = triangle.size();
        // use bottom level to create a result space
        List<Integer> result = new ArrayList<Integer>(triangle.get(level - 1));
        for (int curLv = level - 2; curLv >= 0; curLv--) { // start from second last row
            for (int i = 0; i <= curLv; i++) { // go through each node
                int res = Math.min(result.get(i), result.get(i + 1)) + triangle.get(curLv).get(i); // add the smaller one 
                result.set(i, res);
            }
        }
        return result.get(0);
    }

    static int quickSelect(List<Integer> row) {
        int left = 0;
        int right = row.size() - 1;
        int target = 1;

        int index;
        while (left <= right) {
            index = partition(row, left, right);
            if (index == target) {
                return row.get(index - 1);
            } else if (index > target) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return -1;
    }

    static int partition(List<Integer> row, int left, int right) {
        int pivot = row.get(left + (right - left) / 2);
        while (left <= right) {
            while (row.get(left) < pivot) left++;
            while (row.get(right) > pivot) right--;
            if (left <= right) {
                int temp = row.get(left);
                row.set(left, row.get(right));
                row.set(right, temp);
                left++;
                right--;
            }
        }
        return left;
    }
}