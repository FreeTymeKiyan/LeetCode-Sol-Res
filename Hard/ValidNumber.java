/**
 * Validate if a given string is numeric.
 * 
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should
 * gather all requirements up front before implementing one.
 * 
 * Tags: Math, String
 */
class ValidNumber {
    public static void main(String[] args) {
        
    }
    
    /**
     * Whitespace, +, -, digit, ., e
     */
    public boolean isNumber(String s) {
        int len = s.length();
        int i = 0, e = len - 1;
        // whitespace
        while (i <= e && Character.isWhitespace(s.charAt(i))) i++;
        if (i > len - 1) return false;
        while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
        // skip leading +/-
        if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
        boolean num = false; // is a digit
        boolean dot = false; // is a '.'
        boolean exp = false; // is a 'e'
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) num = true;
            else if (c == '.') { // '.' appear
                if(exp || dot) return false; // exp can't have '.' or dots
                dot = true;
            } else if (c == 'e') { // e appear
                if(exp || num == false) return false; // already e but not num 
                exp = true; // is exp, see whether is num from now
                num = false;
            } else if (c == '+' || c == '-') { // +, - must appear after e
                if (s.charAt(i - 1) != 'e') return false;
            }
            else return false;
            i++;
        }
        return num; // whether is num or not
    }
    
    /**
     * Trim and convert trimmed string to char array
     * Deal with sign
     * Deal with point
     * Deal with digit after point
     * Deal with no point or e
     * Deal with digit after e
     * Deal with sign, no digit and other character cases
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        char[] c = s.trim().toCharArray();
        if (c.length == 0) return false; // all whitespaces
        int i = 0;
        int num = 0;
        if (c[0] == '+' || c[0] == '-') i++; // skip sign
        for (; i < c.length && (c[i] >= '0' && c[i] <= '9'); i++) num++;
        if (i < c.length && c[i] == '.') i++; // skip point
        
        for (; i < c.length && (c[i] >= '0' && c[i] <= '9'); i++) num++; // !
        if (num == 0) return false; // no digit before or after point
        
        if (i == c.length) return true; // no point or e
        else if (i < c.length && c[i] != 'e') return false; // last letter not e
        else i++; // skip e
        
        num = 0; // reset num and check numbers after e
        if (i < c.length && (c[i] == '+' || c[i] == '-')) i++;
        for (; i < c.length && (c[i] >= '0' && c[i] <= '9'); i++) num++;
        if (num == 0) return false; // no more numbers after e
        if (i == c.length) return true; // no other letter except e
        else return false; // other letter shows up
    }
}