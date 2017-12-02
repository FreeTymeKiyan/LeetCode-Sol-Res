package com.freetymekiyan.algorithms.level.medium;

import java.util.List;

class FourSumTreeMap {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> fourSum(int[] num, int target) {
        // TODO: 12/1/17 implement this method
        return null;
    }

    /**
     * regard two integers as a pair
     */
    class Pair {
        int a;
        int ai;
        int b;
        int bi;

        public Pair(int a, int ai, int b, int bi) {
            this.a = a;
            this.ai = ai;
            this.b = b;
            this.bi = bi;
        }

        boolean same(Pair p) {
            return p != null && p.a == a && p.b == b;
        }
    }
}