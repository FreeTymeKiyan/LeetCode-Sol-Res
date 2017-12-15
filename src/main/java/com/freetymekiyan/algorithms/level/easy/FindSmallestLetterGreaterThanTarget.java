package com.freetymekiyan.algorithms.level.easy;

/**
 * 744. Find Smallest Letter Greater Than Target
 * <p>
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find
 * the smallest element in the list that is larger than the given target.
 * <p>
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 * <p>
 * Examples:
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 * Note:
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique letters.
 * target is a lowercase letter.
 * <p>
 * Related Topics: Binary Search
 */
public class FindSmallestLetterGreaterThanTarget {

    /**
     * Binary Search.
     * Note that letters are sorted but can have DUPLICATES!
     */
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) throw new IllegalArgumentException();
        if (target < 'a' || target > 'z') throw new IllegalArgumentException();
        if (target >= letters[letters.length - 1]) return letters[0];
        int left = 0;
        int right = letters.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[left];
    }

    /**
     * Binary Search.
     * Find the next letter of target in the array.
     * Can be faster since we can return as soon as the letter is found.
     */
    public char nextGreatestLetter2(char[] letters, char target) {
        if (letters == null || letters.length == 0) throw new IllegalArgumentException();
        if (target < 'a' || target > 'z') throw new IllegalArgumentException();
        if (target >= letters[letters.length - 1]) return letters[0];
        target++; // Next letter.
        int left = 0;
        int right = letters.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] == target) {
                return letters[mid];
            }
            if (letters[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[left];
    }

    /**
     * Binary Search.
     * Same idea. But use modular operation at the end.
     * Since left is within [0, letters.length].
     * left = letters.length when the target is always larger than letters[hi].
     */
    public char nextGreatestLetter3(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        target++;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (letters[mi] == target) return letters[mi];
            if (letters[mi] < target) lo = mi + 1;
            else hi = mi;
        }
        return letters[lo % letters.length];
    }
}