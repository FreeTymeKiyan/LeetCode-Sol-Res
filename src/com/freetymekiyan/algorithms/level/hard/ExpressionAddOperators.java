package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.List;

/**
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
        if (num == null || num.length() == 0) {
            return res;
        }
        backtrack(res, num, target, 0, "", 0, 0);
        return res;
    }

    /**
     * Backtracking.
     * What kind of data do we need to preserve for between levels?
     * Of course the original data, the number string and the target.
     * And the result we are going to generate.
     * Then the state of each level, like:
     * 1. current position in the number string.
     * 2. the formula generated so far.
     * 3. the evaluation result of that formula.
     * 4. For multiplication, it is higher priority than + or -, so need to remember the previous multiplied.
     * <p>
     * Implementation:
     * Stop when we reach the end of number.
     * If we found target, add the path to result.
     * For each value start from the starting index, parse it to long.
     * If start is 0, it means the first number. Just add it to path.
     * Else we search "+", "-", "*" one by one.
     * For path just concatenate the number to path with relative operator.
     * res, num and target are the same.
     * Next starting index will increment by 1.
     * Evaluate result will change.
     * Note that for multiplication, the evaluation result should first minus previous multiplied value.
     * Then add the product of that value and current value.
     * Then multiplied value should also multiply current value.
     *
     * @param res    Result paths.
     * @param path   Current path.
     * @param num    Original number string.
     * @param target The target value to find.
     * @param start  Starting index in number string.
     * @param eval   Actual value of the path.
     * @param multed The value to be multiplied in the next recursion.
     */
    private void backtrack(List<String> res, String num, int target, int start, String path, long eval, long multed) {
        if (start == num.length()) { // Reach the end of num.
            if (target == eval) { // Found target.
                res.add(path);
            }
            return;
        }

        for (int i = start; i < num.length(); i++) {
            if (num.charAt(start) == '0' && i != start) { // Avoid multiple digits start with 0.
                break;
            }
            long cur = Long.parseLong(num.substring(start, i + 1)); // Avoid overflow, along with eval and multed.
            if (start == 0) { // First number
                backtrack(res, num, target, i + 1, path + cur, cur, cur);
            } else {
                backtrack(res, num, target, i + 1, path + "+" + cur, eval + cur, cur);
                backtrack(res, num, target, i + 1, path + "-" + cur, eval - cur, -cur);
                backtrack(res, num, target, i + 1, path + "*" + cur, eval - multed + multed * cur, multed * cur);
            }
        }
    }

}
