/**
 * Created by kiyan on 5/5/16.
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m > n) return isOneEditDistance(t, s); // n is always bigger
        if (n - m > 1) return false;
        int i = 0;
        int shift = n - m; // 0 or 1
        while (i < m && s.charAt(i) == t.charAt(i)) i++; // arrives at non-matching char
        if (i == m) return shift > 0; // whether T has more characters
        if (shift == 0) i++; // skip both
        while (i < m && s.charAt(i) == t.charAt(i + shift)) i++; // go on
        return i == m; // should reach the end of shorter string
    }
}
