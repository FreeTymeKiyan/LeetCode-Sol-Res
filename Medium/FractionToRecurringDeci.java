import java.util.*;

/**
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in
 * parentheses.
 * 
 * For example,
 * 
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 * 
 * Tags: Hashtable, Math
 */
class FractionToRecurringDeci {

    public static void main(String[] args) {
        FractionToRecurringDeci f = new FractionToRecurringDeci();
        // System.out.println(f.fractionToDecimal(1, 2));
        // System.out.println(f.fractionToDecimal(2, 1));
        // System.out.println(f.fractionToDecimal(2, 3));
        System.out.println(f.fractionToDecimal(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }
    
    /**
     * Valid input, denominator can't be zero
     * Convert to long to avoid overflow
     * Divide into three parts, sign, before dot and after dot
     * Before dot = numerator / denominator
     * After dot = remainder * 10 / denominator
     * if already showed up, insert parentheses
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "";
        if (numerator == 0) return "0";
        
        StringBuilder res = new StringBuilder();
        Long n = new Long(numerator); // convert to long
        Long d = new Long(denominator);
        if ((n < 0 && d > 0) || (n > 0 && d < 0)) res.append("-"); // negative
        
        n = Math.abs(n); // to abstract value
        d = Math.abs(d);
        res.append(n / d); // before dot 
        if (n % d == 0) return res.toString(); // no fraction
        
        res.append("."); // add dot
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        Long r = n % d; // get first remainder
        while (r > 0) {
            if (map.containsKey(r)) { // remainder appeared before
                res.insert(map.get(r), "("); // insert an open paren
                res.append(")"); // append a close paren
                break;
            }
            map.put(r, res.length()); // save remainder and the length
            r *= 10; // simulate long division
            res.append(r / d);
            r %= d; // get next remainder
        }
        return res.toString();
    }
}
