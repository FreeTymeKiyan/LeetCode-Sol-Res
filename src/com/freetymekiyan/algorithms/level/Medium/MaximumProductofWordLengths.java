import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not
 * share common letters. You may assume that each word will contain only lower case letters. If no such two words
 * exist, return 0.
 * <p>
 * Example 1:
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".
 * <p>
 * Example 2:
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".
 * <p>
 * Example 3:
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words.
 * <p>
 * Tags: Bit Manipulation
 */
public class MaximumProductofWordLengths {

    private MaximumProductofWordLengths m;

    public int maxProduct(String[] words) {
        if (words == null || words.length < 2) return 0;
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());

        int[] masks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            masks[i] = getBitMask(words[i]);
        }

        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].length() * words[i].length() <= max) break; // Pruning, all words[j] are shorter than words[i]
            for (int j = i + 1; j < words.length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                    break; // Pruning, find one is enough, the following words are shorter
                }
            }
        }
        return max;
    }

    private int getBitMask(String s) {
        int mask = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            mask |= 1 << index;
        }
        return mask;
    }

    @Before
    public void setUp() {
        m = new MaximumProductofWordLengths();
    }

    @Test
    public void testInvalidInput() {
        Assert.assertEquals(0, m.maxProduct(null));
        Assert.assertEquals(0, m.maxProduct(new String[]{}));
        Assert.assertEquals(0, m.maxProduct(new String[]{""}));
        Assert.assertEquals(0, m.maxProduct(new String[]{"whatever"}));
    }

    @Test
    public void testExamples() {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        Assert.assertEquals(16, m.maxProduct(words));
        words = new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        Assert.assertEquals(4, m.maxProduct(words));
        words = new String[]{"a", "aa", "aaa", "aaaa"};
        Assert.assertEquals(0, m.maxProduct(words));
    }

    @After
    public void tearDown() {
        m = null;
    }
}
