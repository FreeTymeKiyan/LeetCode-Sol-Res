package com.freetymekiyan.algorithms.level.medium;

/**
 * 277. Find the Celebrity
 * <p>
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The
 * definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 * <p>
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do
 * is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the
 * celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * <p>
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int
 * findCelebrity(n), your function should minimize the number of calls to knows.
 * <p>
 * Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a
 * celebrity in the party. If there is no celebrity, return -1.
 * <p>
 * Company Tags: LinkedIn, Facebook
 * Tags: Array
 */
public class FindTheCelebrity {

    /*
     * The knows API is defined in the parent class Relation.
     * boolean knows(int a, int b);
     */
    public class Solution extends Relation {

        /**
         * Pick the candidate first.
         * If a candidate doesn't know someone, that guy cannot be candidate, candidate remains the same.
         * If a candidate knows someone, he cannot be candidate anymore, the one he knows is the next candidate.
         * After that we need to validate the candidate.
         * For the people before candidate, they cannot be candidate anymore, since:
         * | 1. someone does NOT know him.
         * | 2. he knows someone.
         * So check if candidate knows any of them. If yes, return -1.
         * For the people after, candidate doesn't know them.
         * So check if any of them doesn't know candidate. If there is one, return -1.
         * If the candidate passes this two checks, he is the celebrity everyone knows but doesn't know anyone.
         */
        public int findCelebrity(int n) {
            int candidate = 0;
            for (int i = 1; i < n; i++) {
                if (knows(candidate, i)) {
                    candidate = i;
                }
            }
            for (int i = 0; i < candidate; i++) {
                if (knows(candidate, i)) {
                    return -1;
                }
            }
            for (int i = candidate + 1; i < n; i++) {
                if (!knows(i, candidate)) {
                    return -1;
                }
            }
            return candidate;
        }
    }

    /**
     * Dummy.
     */
    private class Relation {

        boolean knows(int a, int b) {
            return false;
        }
    }

}