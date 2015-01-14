import java.util.*;
    
/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead
 * of an integer.
 * 
 * Tags: Sort
 */
class LargestNum {
    public static void main(String[] args) {
        LargestNum ln = new LargestNum();
        int[] num = {3, 30, 34, 5, 9};
        System.out.println(ln.largestNumber(num));
    }
    
    /**
     * Create a comparator for sorting
     * Convert num to String and compare the concatenated result of them
     * Note {0, 0} is a special case
     */
    public String largestNumber(int[] num) {
        StringBuilder res = new StringBuilder();
        if (num == null || num.length == 0) return res.toString();
        String[] str = new String[num.length];
        for (int i = 0; i < num.length; i++) str[i] = num[i] + "";
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Long.compare(Long.valueOf(s2 + s1), Long.valueOf(s1 + s2));
            }
        };
        Arrays.sort(str, comp);
        if (str[0].equals("0")) return "0"; // deal with 0
        for (String s : str) res.append(s);
        return res.toString();
    }
}
