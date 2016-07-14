import java.util.List;
import java.util.ArrayList;

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

    /**
     * DP
     * Math.min(result.get(i), result.get(i + 1)) + triangle.get(curLv).get(i)
     * Pick the smaller one of next row and add it up to current level
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> res = new ArrayList<Integer>(triangle.get(level));
        int level = triangle.size() - 1;
        for (int i = level - 1; i >= 0; i--) { // start from second last row
            for (int j = 0; j <= i; j++) { // go through each node
                int res = Math.min(res.get(j), res.get(j + 1)) + triangle.get(i).get(j); // add the smaller one 
                res.set(j, res);
            }
        }
        return res.get(0);
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
}