package com.freetymekiyan.algorithms.other;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input:
 * [
 * ("ian", 2.8, 5.8),
 * ("bob", 3.6, 7.6),
 * ("yang", 9.1, 12.3)
 * ]
 *
 * Output:
 * [(2.8, 1), (3.6, 2), (5.8, 1), (7.6, 0), (9.1, 1), (12.3, 0)]
 */
public class UserSessions {

    public String generateUserCount(UserSession[] userSessions) {
        if (userSessions == null || userSessions.length == 0) return "[]";
        List<Event> events = new ArrayList<>();
        for (UserSession us : userSessions) {
            events.add(new Event(us.loginTs, true));
            events.add(new Event(us.logoutTs, false));
        }
        Collections.sort(events, (o1, o2) -> o1.timestamp > o2.timestamp ? 1 : -1);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = events.size();
        for (int i = 0; i < size; i++) {
            Event cur = events.get(i);
            if (cur.isLogin) {
                count++;
            } else count--;
            while (i + 1 < size) { // timestamp can duplicate
                Event next = events.get(i + 1);
                if (next.timestamp == cur.timestamp) {
                    if (next.isLogin) {
                        count++;
                    } else count--;
                    i++;
                } else break;
            }
            sb.append("(").append(cur.timestamp).append(", ").append(count).append(i != size - 1 ? "), " : ")");
        }
        sb.append("]");
        return sb.toString();
    }

    @Test
    public void testInvalidInputs() {
        UserSessions us = new UserSessions();
        String res = us.generateUserCount(null);
        Assert.assertEquals("[]", res);
        UserSession[] userSessions = new UserSession[0];
        res = us.generateUserCount(userSessions);
        Assert.assertEquals("[]", res);
    }

    @Test
    public void testWithoutDup() {
        UserSession us1 = new UserSession("ian", 2.8, 5.8);
        UserSession us2 = new UserSession("bob", 3.6, 7.6);
        UserSession us3 = new UserSession("yang", 9.1, 12.3);
        UserSession[] userSessions = {us1, us2, us3};
        UserSessions us = new UserSessions();
        String res = us.generateUserCount(userSessions);
        Assert.assertEquals("[(2.8, 1), (3.6, 2), (5.8, 1), (7.6, 0), (9.1, 1), (12.3, 0)]", res);
    }

    @Test
    public void testWithDup() {
        UserSession us1 = new UserSession("ian", 2.8, 5.8);
        UserSession us2 = new UserSession("bob", 2.8, 5.8);
        UserSession us3 = new UserSession("yang", 5.8, 7.6);
        UserSession[] userSessions = {us1, us2, us3};
        UserSessions us = new UserSessions();
        String res = us.generateUserCount(userSessions);
        Assert.assertEquals("[(2.8, 2), (5.8, 1), (7.6, 0)]", res);
    }

    private static class Event {
        double timestamp;
        boolean isLogin;

        public Event(double timestamp, boolean isLogin) {
            this.timestamp = timestamp;
            this.isLogin = isLogin;
        }
    }

    private static class UserSession {
        String name;
        double loginTs;
        double logoutTs;

        public UserSession(String name, double loginTs, double logoutTs) {
            this.name = name;
            this.loginTs = loginTs;
            this.logoutTs = logoutTs;
        }
    }
}
