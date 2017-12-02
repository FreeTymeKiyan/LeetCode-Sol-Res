package com.freetymekiyan.algorithms.other;

/**
 * Given:
 * function:  isFriend(a, b)
 * Returns true iff b is treated as a friend by a group of persons, say,
 * represented as an array
 */
class Celebrity {
    public static void main(String[] args) {

    }

    /**
     * Traverse the list
     * Update leader index if leader is friend of current id
     * Then check if leader is real with one more traversal
     */
    int hasLeader(Person[] persons) {
        int n = persons.length;
        int leader = 0;
        int cur = 1;
        while (cur < n) {
            if (isFriend(persons[leader], persons[cur])) leader = cur;
            cur++;
        }

        for (int i = 0; i < n; i++) {
            if (i != leader && isFriend(persons[leader], persons[i])) {
                return -1;
            }
        }
        return leader;
    }

    private boolean isFriend(Person p1, Person p2) {
        return false;
    }

    class Person {
    }
}