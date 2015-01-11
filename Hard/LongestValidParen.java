import java.util.Stack;
import java.util.Arrays;

/**
 * Given a string containing just the characters '(' and ')'
 * find the length of the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length  * = 2.
 * Another example is ")()())", where the longest valid parentheses substring
 * is "()()", which has length = 4.
 * 
 * Follow up:
 * 
 * What if there are curly bracs and brakets as well? {} []?
 * 
 * Tags: DP, String
 */
class LongestValidParen {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()")); // 2
        System.out.println(longestValidParentheses("()(()")); // 2
        System.out.println(longestValidParentheses("()(()(")); // 2
        System.out.println(longestValidParentheses("()(()((")); // 2
        System.out.println(longestValidParentheses("()(()(((")); // 2
        System.out.println(longestValidParentheses("(((((()")); // 2
        System.out.println(longestValidParentheses(")()())")); // 4
        System.out.println(longestValidParentheses("((((((")); // 0
        System.out.println(longestValidParentheses("))))))")); // 0
        System.out.println(longestValidParentheses(")()(())")); // 6
        System.out.println(longestValidParentheses("(())()")); // 6
        System.out.println(longestValidParentheses(")()()")); // 4
    }
    
    /**
     * Optimized DP
     * Build a stack for indices of open parentheses
     * Traverse the string, if current is open paren, push to stack
     * Otherwise, its close paren. 
     * If stack is empty, no open paren left, reset len to 0.
     * If not, pop the index from stack, matchedLen = current index - index of 
     * pop open paren + 1
     * If stack is empty, means this matchedLen can be added to the whole len
     * If not, 
     */
    public static int longestValidParentheses(String s) {
        if (s == null) return 0;
        Stack<Integer> s = new Stack<Integer>();
        int maxLen = 0;
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') s.push(i);
            else if (s.isEmpty()) len = 0;
            else {
                int matchedPos = s.pop();
                int matchedLen = i - matchedPos + 1;
                if (s.isEmpty()) { // ()()
                    len += matchedLen;
                    matchedLen = len;
                } else matchedLen = i - s.peek(); // ()(()()
                maxLen = Math.max(maxLen, matchedLen);
            }
        }
        return maxLen;
    }

    /**
     * DP
     */
    public int longestValidParenthesesB(String s) {
        if (s == null || s.length() == 0) return 0;

        Stack<Integer> stack = new Stack<Integer>(); // Save indices of '('
        int[] dp = new int[s.length()]; // Store the length of the current longest valid sequence.

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);  
            else if (stack.isEmpty()) continue;
            else if (stack.peek() > 0) 
                dp[i] = 2 + dp[stack.pop() - 1] + dp[i - 1]; // connect two valid sequences, or increase the length of current valid sequence. 
            else {
                dp[i] = 2 + dp[i - 1]; // leftmost char is a '('
                stack.pop();
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
    
    /**
     * Two pass
     * Use a stack to store index of unmatched parentheses
     * Go through the stack and find maximum of difference between indices
     * Include len - first and last - 0
     */
    public int longestValidParenthesesC (String s) {
        Stack<Integer> st = new Stack<Integer>(); // store the index of unmatched parens
        int len = s.length();
        int longest = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') st.push(i);
            else if (s.charAt(i) == ')' && !st.isEmpty() && s.charAt(st.peek()) == '(') st.pop(); // pop if there is a pair
            else st.push(i); // right paren, empty or top is also right
        }

        if (st.isEmpty()) return len; // all valid
        /*calculate longest length between each unpaired*/
        int a = len, b = 0;
        while (!st.isEmpty()) {
            b = st.pop();
            longest = Math.max(longest, a - b - 1); // update longest
            a = b;
        }
        longest = Math.max(longest, a); // compare with last - 0
        return longest;
    }
}
