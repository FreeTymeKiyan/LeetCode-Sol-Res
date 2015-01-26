import java.util.*;

/**
 * 01*0*, * can be 0 or 1
 * Generate all possible outputs
 * 
 * Tags: Backtracking
 */
class Astroid {
    public static void main(String[] args) {
        Astroid a = new Astroid();
        System.out.println(a.astroid("01*0*"));
    }
    
    public List<String> astroid(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0) return res;
        astroid(s, 0, new StringBuilder(s), res);
        return res;
    }
    
    /**
     * Backtracking on the string
     * Replace if current char is *
     * Otherwise, go to next position
     */
    public void astroid(String s, int pos, StringBuilder sb, List<String> res) {
        if (pos == sb.length()) {
            res.add(sb.toString());
            return;
        }
        if (s.charAt(pos) == '*') {
            sb.setCharAt(pos, '0');
            astroid(s, pos + 1, sb, res);
            sb.setCharAt(pos, '1');
            astroid(s, pos + 1, sb, res);
        } else {
            astroid(s, pos + 1, sb, res);            
        }
    }
}
