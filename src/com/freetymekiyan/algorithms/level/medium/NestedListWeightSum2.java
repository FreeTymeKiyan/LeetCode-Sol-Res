package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from
 * bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 * <p>
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * <p>
 * Example 2:
 * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 =
 * 17)
 * <p>
 * Company Tags: LinkedIn
 * Tags: Depth-first Search
 * Similar Problems: (E) Nested List Weight Sum
 */
public class NestedListWeightSum2 {

    /**
     * DFS.
     * Instead of level, pass the integer sum of this level to the next level.
     * Recurrence Relation:
     * sum = this level's integers' sum + all previous levels integers' sum + all following levels integers' sum.
     * Base case:
     * If list is empty, return 0.
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        return dfs(nestedList, 0);
    }

    /**
     * DFS.
     * Create next level's list.
     * For each NestedInteger ni in nestedList:
     * | If ni.isInteger():
     * |   Add it to prev.
     * | Else:
     * |   Add ni.getList() to next level.
     * Return previous sum + recursive call sum.
     */
    private int dfs(List<NestedInteger> nestedList, int prev) {
        if (nestedList.isEmpty()) {
            return 0;
        }
        List<NestedInteger> nextLevel = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                prev += ni.getInteger();
            } else {
                nextLevel.addAll(ni.getList());
            }
        }
        return prev + dfs(nextLevel, prev);
    }

    /**
     * DFS. Iterative.
     * Add integer in list multiple times.
     * Sum of current level is: sum of all integers in this level + sum of all integers in previous levels.
     * Iterate current list:
     * | For each NestedInteger in nestedList:
     * |   If ni.isInteger(), add it to previous sum
     * |   Else if ni is a list, add its list to next level
     * | Add prev to sum.
     * | nestedList = nextLevel
     * Return sum.
     */
    public int depthSumInverseB(List<NestedInteger> nestedList) {
        int cur = 0, sum = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    cur += ni.getInteger();
                } else {
                    nextLevel.addAll(ni.getList());
                }
            }
            sum += cur;
            nestedList = nextLevel;
        }
        return sum;
    }
}

