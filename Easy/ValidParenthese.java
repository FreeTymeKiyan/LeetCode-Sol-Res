/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all
 * valid but "(]" and "([)]" are not.
 * 
 * Tags: Stack, String
 */
import java.util.Stack;
class ValidParenthese {
    
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("[({(())}[()])]"));
    }
    
    /**
     * Use a stack to store the parens
     * If   left paren, push to stack
     * Elif stk is empty, return false
     * Elif mathches, pop and go on
     * Else don't match, return false
     */
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stk.push(c);
            } else {
                if (stk.isEmpty()) return false;
                if (c == ')' && stk.peek() == '(') {
                    stk.pop();
                } else if (c == ']' && stk.peek() == '[') {
                    stk.pop();
                } else if (c == '}' && stk.peek() == '{') {
                    stk.pop();
                } else {
                    return false;
                }
            }
        }
        return stk.isEmpty() ? true : false;
    }   
    
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        Stack<Character> stk = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stk.isEmpty()) {
                stk.push(c);
            } else if (stk.peek() == '(' && c == ')') {
                stk.pop();
            } else if (stk.peek() == '[' && c == ']') {
                stk.pop();
            } else if (stk.peek() == '{' && c == '}') {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.isEmpty() ? true : false;
    }
}