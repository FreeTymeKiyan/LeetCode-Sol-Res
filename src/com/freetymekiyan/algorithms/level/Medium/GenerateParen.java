import java.util.*;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * 
 * Tags: Backtracking. String
 */
class GenerateParen {
    public static void main(String[] args) {
        
    }

    /**
     * Backtracking
     * Helper function use left and right to represent available parentheses
     * Initialize left as n, right as 0
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        if (n <= 0) return ans;
        dfs(n, 0, "", ans);
        return ans;
    }

    /**
     * @param left available left parentheses
     * @param right available right parentheses
     * @param res current result
     * @param ans the answer list of the problem
     */
    public void dfs(int left, int right, String res, List<String> ans) {
        if (left == 0 && right == 0) {
            ans.add(res);
            return;
        }
        if (left > 0) dfs(left - 1, right + 1, res + "(", ans); // add (, right + 1
        if (right > 0) dfs(left, right - 1, res + ")", ans); // add ), right - 1
    }
}