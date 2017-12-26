package com.freetymekiyan.algorithms.level.easy;

/**
 * 278. First Bad Version
 * <p>
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of
 * your product fails the quality check. Since each version is developed based on the previous version, all the versions
 * after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following
 * ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * Company Tags: Facebook
 * Tags: Binary Search
 * Similar Problems: (M) Search for a Range, (M) Search Insert Position, (E) Guess Number Higher or Lower
 */
public class FirstBadVersion {

    /**
     * Binary Search.
     * Initial search range:
     * n versions from 1 to n.
     * Get the middle version m.
     * If m is bad, then m+1 to n are all bad. The first bad version can be m or previous versions.
     * If m is good, the first bad version can only be larger than m.
     * Stop when there is only one version left.
     */
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;

        while (l < r) {
            int m = l + (r - l) / 2; // Avoid overflow, since l >= 1, n <= Integer.MAX_VALUE
            if (!isBadVersion(m)) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    /**
     * Binary Search. Pruning.
     * If we know m is a bad version, we can check m - 1 right after that.
     * If m - 1 is 1, which means that's the only available good version, m would be the first bad version.
     * If m - 1 is good, then m is the first bad version.
     */
    public int firstBadVersion2(int n) {
        int l = 1;
        int r = n;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (!isBadVersion(m)) {
                l = m + 1;
            } else if (m == 1 || !isBadVersion(m - 1)) {
                // Early return. If current version is bad and the previous one is good.
                return m;
            } else {
                r = m;
            }
        }
        return l;
    }

    // FAKE method to make compilation pass
    private boolean isBadVersion(int ver) {
        return false;
    }

}