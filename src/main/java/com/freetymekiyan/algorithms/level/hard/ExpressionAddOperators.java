package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 * <p>
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 * <p>
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 * <p>
 * Company Tags: Google, Facebook
 * Tags: Divide and Conquer
 * Similar Problems: (M) Evaluate Reverse Polish Notation, (H) Basic Calculator, (M) Basic Calculator II, (M) Different
 * Ways to Add Parentheses
 */
public class ExpressionAddOperators {

    /**
     * Backtracking.
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(res, num.toCharArray(), target, 0, new StringBuilder(), 0, 0);
        return res;
    }

    /**
     * Backtracking.
     * What kind of data do we need to preserve for between recursive calls?
     * Of course the result list we are going to generate.
     * And the original data: the number string and the target.
     * Then the states of each call, like:
     * 1. current position in the number string.
     * 2. the formula generated so far.
     * 3. the evaluation result of that formula.
     * 4. For multiplication, it is higher priority than + or -, so need to remember the previous multiplied number.
     * <p>
     * Implementation:
     * Stop when we reach the end of number.
     * If we found target, add the formula to result.
     * For each value start from the starting index, parse it to long.
     * If start is 0, it means the first number. Just add it to path.
     * Else we search "+", "-", "*" one by one.
     * For formula just concatenate the number to path with relative operator.
     * res, num and target are the same.
     * Next starting position will increment by 1.
     * Evaluate result will change according to the operator added.
     * Note that for multiplication, the evaluation result should first minus previous multiplied value.
     * Then add the product of that value and current value.
     * The multiplied value should also multiply current value.
     *
     * @param exprs  Result paths.
     * @param num    Original number string. Converted to char array for faster speed.
     * @param target The target value to find.
     * @param start  Starting index in number string.
     * @param expr   Current expression.
     * @param eval   Actual value of the expression.
     * @param multed The value to be multiplied in the next recursion.
     */
    private void dfs(List<String> exprs, char[] num, int target, int start, StringBuilder expr, long eval,
                     long multed) {
        if (start == num.length) { // Reach the end of num.
            if (target == eval) { // Found target.
                exprs.add(expr.toString());
            }
            return;
        }
        long cur = 0;
        for (int end = start; end < num.length; end++) {
            if (num[start] == '0' && end != start) { // Avoid multiple digits start with 0.
                break;
            }
            cur = 10 * cur + (num[end] - '0'); // Avoid overflow, along with eval and multed.
            int len = expr.length();
            if (start == 0) { // First number.
                dfs(exprs, num, target, end + 1, expr.append(cur), cur, cur);
                expr.setLength(len); // Reset string builder.
            } else {
                dfs(exprs, num, target, end + 1, expr.append("+").append(cur), eval + cur, cur);
                expr.setLength(len);
                dfs(exprs, num, target, end + 1, expr.append("-").append(cur), eval - cur, -cur);
                expr.setLength(len);
                // For multiplication, eval needs to subtract previous multed first, then add multed * cur
                // multed just multiply cur
                dfs(exprs, num, target, end + 1, expr.append("*").append(cur), eval - multed + multed * cur,
                        multed * cur);
                expr.setLength(len);
            }
        }
    }

    /**
     * Backtracking.
     * More concise.
     * But creating new strings thus more cost.
     */
    public List<String> addOperators2(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, target, 0, "", 0, 0, res);
        return res;
    }

    private void dfs(String num, int target, long eval, String expr, long m, int index, List<String> exprs) {
        if (index == num.length()) {
            if (eval == target) {
                exprs.add(expr);
            }
            return;
        }
        for (int end = index + 1; end <= num.length(); end++) {
            if (num.charAt(index) == '0' && end != index + 1) { // Cannot have multiple digits starting with 0.
                break;
            }
            long n = Long.valueOf(num.substring(index, end));
            if (index == 0) {
                dfs(num, target, eval + n, "" + n, n, end, exprs);
            } else {
                dfs(num, target, eval + n, expr + "+" + n, n, end, exprs);
                dfs(num, target, eval - n, expr + "-" + n, -n, end, exprs);
                dfs(num, target, eval - m + m * n, expr + "*" + n, m * n, end, exprs);
            }
        }
    }
}