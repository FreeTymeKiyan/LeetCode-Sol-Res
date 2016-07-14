/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * Tags: Math, String
 */
class RomanToInt {
    
    public static void main(String[] args) {
        
    }
    
    char[] roman = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
    int[] val = { 1, 5, 10, 50, 100, 500, 1000 };
    
    /**
     * negative? all uppercase?
     * Traverse backwords, switch case for all chars
     * C = 100 X = 10 I = 1 are special
     * Compare with 500, 50 and 5
     * If bigger, it means current value is negative
     */
    public static int romanToInt(String s) {
        int res = 0;
        /*validate input*/
        if (s == null || s.length() == 0) return res;
        int len = s.length() - 1;
        /*traverse backwards*/
        for (int i = len; i >= 0 ; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'M':
                res += 1000;
                break;
                case 'D':
                res += 500;
                break;
                case 'C':
                res += 100 * (res >= 500 ? -1 : 1); // >= not just >
                break;
                case 'L':
                res += 50;
                break;
                case 'X':
                res += 10 * (res >= 50 ? -1 : 1); // >= not just >
                break;
                case 'V':
                res += 5;
                break;
                case 'I':
                res += (res >= 5 ? -1 : 1); // >= not just >
                break;
                default:
                break;
            }
        }
        return res;
    }
    
    public int romanToInt(String s) {
        if (s == null || s.length()==0) return 0;
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int length = s.length();
        int result = m.get(s.charAt(length - 1));
        for (int i = length - 2; i >= 0; i--) { // backwards
            if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) 
                result += m.get(s.charAt(i));
            else 
                result -= m.get(s.charAt(i));
        }
        return result;
    }
}
