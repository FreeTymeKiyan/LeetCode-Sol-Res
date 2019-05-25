package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * <p>
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * <p>
 * Example:
 * <p>
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * Related Topics: String, Backtracking
 * Similar Questions: IP to CIDR
 */
public class RestoreIpAddresses {

  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<>();
    if (s.length() > 12) return res;
    backtrack(s, 3, res, "");
    return res;
  }

  public void backtrack(String s, int dot, List<String> res, String ip) {
    if (dot == 0) {
      if (isValid(s)) {
        ip += s;
        System.out.println(ip);
        res.add(ip);
      }
      return;
    }

    for (int i = 1; i < 4 && i < s.length(); i++) {
      String pre = s.substring(0, i);
      if (!isValid(pre)) continue;
      backtrack(s.substring(i), dot - 1, res, ip + pre + ".");
    }
  }

  public boolean isValid(String s) {
    if (s.startsWith("0") && s.length() > 1 || Integer.valueOf(s) > 255) return false;
    return true;
  }
}