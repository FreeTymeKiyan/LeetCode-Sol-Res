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
        // System.out.println(fractionToDecimal(1, 2));
        // System.out.println(fractionToDecimal(2, 1));
        // System.out.println(fractionToDecimal(2, 3));
        System.out.println(fractionToDecimal(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }
    
    /**
     * Valid input, denominator can't be zero
     * Convert to long to avoid overflow
     * Divide into three parts, sign, before dot and after dot
     * Before dot = numerator / denominator
     * After dot = remainder * 10 / denominator
     * if already showed up, insert parentheses
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "";
        if (numerator == 0) return "0";
        
        StringBuilder res = new StringBuilder();
        Long n = new Long(numerator);
        Long d = new Long(denominator);
        if (n * d < 0) res.append("-");
        
        n = Math.abs(n);
        d = Math.abs(d);
        res.append(n / d);
        if (n % d == 0) return res.toString();
        
        res.append(".");
        // use map to remember the index of same remainder
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        Long r = n % d;
        while (r > 0) {
            if (map.containsKey(r)) {
                res.insert(map.get(r), "(");
                res.append(")");
                break;
            }
            map.put(r, res.length());
            r *= 10; // remainder * 10 / denominator 
            res.append(r / d);
            r %= d; // get remainder 
        }
        return res.toString();
    }
}
