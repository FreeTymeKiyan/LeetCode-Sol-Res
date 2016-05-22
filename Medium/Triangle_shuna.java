import java.util.List;

/**
 * 120. Triangle
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 *    [2],
 *   [3,4],
 *  [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * Tags: Array Dynamic Programming
 * Analysis: DP
 * @author chenshuna
 */ 

public class Triangle {
    public static int minimumTotalBottonUp(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--)
            for (int j = 0; j < triangle.get(i).size(); j++)
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
        return dp[0];
    }
    
    public static int minimumTotal(List<List<Integer>> triangle) {
        int minSum = Integer.MAX_VALUE;
        int[] sum = new int[triangle.size()];
        sum[0] = triangle.get(0).get(0);
        for(int i = 1; i < triangle.size(); i++){  // from top to bottom
            List<Integer> line = triangle.get(i);
            for(int j = i; j >= 0; j--){
                if(j == 0){
                    sum[j] += line.get(j);
                }
                else if(j == i){
                    sum[j] = sum[j - 1] + line.get(j);
                }
                else{
                    sum[j] = Math.min(sum[j], sum[j - 1]) + line.get(j);
                }
            }
        }
        for(int cnt : sum){
            minSum = Math.min(minSum, cnt);
        }
        return minSum;
    }

    public static void main(String[] args) {
        List<List<Integer>> test1 = new ArrayList<>();
        List<Integer> test3 = new ArrayList<Integer>();
        test3.add(4);
        test1.add(test3);
        List<Integer> test4 = new ArrayList<Integer>();
        test4.add(5);
        test4.add(7);
        test1.add(test4);
        List<Integer> test5 = new ArrayList<Integer>();
        test5.add(1);
        test5.add(8);
        test5.add(3);
        test1.add(test5);
        System.out.print(minimumTotal(test1));   
    }
}