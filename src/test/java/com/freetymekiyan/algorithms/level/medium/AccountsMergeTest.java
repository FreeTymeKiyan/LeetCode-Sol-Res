package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsMergeTest {

    List<List<String>> input1 = Collections.unmodifiableList(
            List.of(
                    List.of("Alex", "Alex5@m.co", "Alex4@m.co", "Alex0@m.co"),
                    List.of("Ethan", "Ethan3@m.co", "Ethan3@m.co", "Ethan0@m.co"),
                    List.of("Kevin", "Kevin4@m.co", "Kevin2@m.co", "Kevin2@m.co"),
                    List.of("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe2@m.co"),
                    List.of("Gabe", "Gabe3@m.co", "Gabe4@m.co", "Gabe2@m.co")
            ));
    List<List<String>> output1 = Collections.unmodifiableList(
            List.of(
                    List.of("Alex", "Alex0@m.co", "Alex4@m.co", "Alex5@m.co"),
                    List.of("Ethan", "Ethan0@m.co", "Ethan3@m.co"),
                    List.of("Gabe", "Gabe0@m.co", "Gabe2@m.co", "Gabe3@m.co", "Gabe4@m.co"),
                    List.of("Kevin", "Kevin2@m.co", "Kevin4@m.co")
            )
    );
    List<List<String>> input2 = Collections.unmodifiableList(
            List.of(
                    List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                    List.of("John", "johnnybravo@mail.com"),
                    List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                    List.of("Mary", "mary@mail.com")
            ));
    List<List<String>> output2 = Collections.unmodifiableList(
            List.of(
                    List.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                    List.of("John", "johnnybravo@mail.com"),
                    List.of("Mary", "mary@mail.com")
            )
    );

    @Test
    public void testAccountsMerge() throws Exception {
        AccountsMerge am = new AccountsMerge();
        Assert.assertTrue(compareLists(am.accountsMerge(input1), output1));
        Assert.assertTrue(compareLists(am.accountsMerge(input2), output2));
    }

    @Test
    public void testAccountsMerge2() throws Exception {
        AccountsMerge am = new AccountsMerge();
        Assert.assertTrue(compareLists(am.accountsMerge2(input1), output1));
        Assert.assertTrue(compareLists(am.accountsMerge2(input2), output2));
    }

    /**
     * Compare 2 lists ignoring order.
     */
    private boolean compareLists(List<List<String>> list1, List<List<String>> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        // Strings are ordered. Using extra space. O(m + n) time.
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> list :
                list1) {
            map.put(list.get(0), list);
        }
        for (List<String> list :
                list2) {
            map.remove(list.get(0));
        }
        return map.isEmpty();
    }
}