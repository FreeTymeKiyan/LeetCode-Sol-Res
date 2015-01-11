import java.util.*;
/**
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * For example:
 * 
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * Tags: Backtracking, String
 */
class RestoreIPAddr {
    public static void main(String[] args) {
        restoreIPAddresses("25525511135");
        restoreIPAddresses("010010");
    }
    
    /**
     * Figure out what is a valid IP address
     * Use backtracking to insert dots into string
     */
    public static List<String> restoreIPAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if (s.length() < 4 && s.length() > 12) return res;
        backtrack(s, 3, res, "");
        return res;
    }
    
    /**
     * @param s current string for this backtrack
     * @param dot how many dots left, when equals 0, add last value to result
     * @param res result list of strings
     * @param ip current ip for this backtrack
     */
    public static void backtrack(String s, int dot, List<String> res, String ip) {
        if (dot == 0) {
            if (isValid(s)) {
                ip += s;
                res.add(ip);
            }
            return;
        }
        for (int i = 1; i < 4 && i < s.length(); i++) {
            String pre = s.substring(0, i);
            if (isValid(pre)) backtrack(s.substring(i), dot - 1, res, ip + pre + ".");
        }
    }
    
    /**
     * A valid IP address, each block should be from 0 to 255
     * Should not be 0X or 0XX
     */
    static boolean isValid(String s) {
        if (s.startsWith("0") && s.length() > 1 || Integer.valueOf(s) > 255) return false;
        return true;
    }
}
