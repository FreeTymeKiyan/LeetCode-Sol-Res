/**
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 *
 * You are giving candies to these children subjected to the following
 * requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Tags: Greedy
 */
class Candy {

    public static void main(String[] args) {
        
    }

    /**
     * O(n) Time, O(n) Space
     * From left to right, if ratings[i] increase, give one more
     * From right to left, if ratings[i] increase, give one more
     * Answer should be the max of two array
     * Simplify second traversal by calculate and decide max right away
     * And also calculate the sum
     */
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++)
            candies[i] = ratings[i] > ratings[i - 1] ? candies[i - 1] + 1 : 1;

        int res = candies[candies.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])    
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            res += candies[i];
        }
        return res;
    }
    
    /**
     * O(n) Time, O(1) Space
     * Use a var to store decreasing sequence length
     * Use a var to store previous candies
     */
    public int candyB(int[] ratings) {
        if (ratings.length < 2) return ratings.length;
        int res = 1;
        int dec = 0;
        int gap = 0;
        int curCandy = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] < ratings[i - 1]) {
                if (dec == 0) gap = curCandy;
                dec++;
                curCandy = 1;
                res += dec + curCandy;
                if (gap > 1) {
                    gap--;
                    res--;
                }
            } else {
                dec = 0;
                if (ratings[i] > ratings[i - 1]) curCandy++;
                else curCandy = 1;
                res += curCandy;
            }
        }
        return res;
    }
}
