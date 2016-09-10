package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 * <p>
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant
 * 2 bits being 10.
 * This is how the UTF-8 encoding would work:
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 * <p>
 * Note:
 * The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This
 * means each integer represents only 1 byte of data.
 * <p>
 * Example 1:
 * <p>
 * data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
 * <p>
 * Return true.
 * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 * Example 2:
 * <p>
 * data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
 * <p>
 * Return false.
 * The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
 * The next byte is a continuation byte which starts with 10 and that's correct.
 * But the second continuation byte does not start with 10, so it is invalid.
 * <p>
 * Tags: Bit Manipulation
 */
public class Utf8Validation {

    private Utf8Validation u;

    /**
     * Bit Manipulation.
     * How to use bit masks to get how many bytes?
     */
    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int c : data) {
            if (count == 0) {
                if ((c >> 5) == 0b110) { // 2 bytes
                    count = 1;
                } else if ((c >> 4) == 0b1110) { // 3 bytes
                    count = 2;
                } else if ((c >> 3) == 0b11110) { // 4 bytes
                    count = 3;
                } else if ((c >> 7) > 0) { // Most significant digit cannot be 1 if it's not multiple bytes
                    return false;
                }
            } else {
                if ((c >> 6) != 0b10) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }

    /**
     * Math.
     * Check how many bytes with number range.
     * 1) 1 byte, [0, 127]
     * 2) 2 bytes, [192, 223]
     * 3) 3, [224, 239]
     * 4) 4, [240, 247]
     * 5) If out of these ranges, return false.
     * Check following numbers, with range [128, 191].
     */
    public boolean validUtf8B(int[] data) {
        int i = 0;
        while (i < data.length) {
            int b = getBytes(data[i]);
            if (b == 0) {
                return false;
            }
            for (int j = i + 1; j < i + b; j++) {
                if (j >= data.length || 128 > data[j] || 192 < data[j]) {
                    return false;
                }
            }
            i += b;
        }
        return true;
    }

    private int getBytes(int d) {
        if (0 <= d && d <= 127) {
            return 1;
        } else if (192 <= d && d <= 223) {
            return 2;
        } else if (224 <= d && d <= 239) {
            return 3;
        } else if (240 <= d && d <= 247) {
            return 4;
        }
        return 0;
    }

    @Before
    public void setUp() {
        u = new Utf8Validation();
    }

    @Test
    public void testExamples() {
        int[] data = {197, 130, 1};
        Assert.assertTrue(u.validUtf8(data));
        data = new int[]{235, 140, 4};
        Assert.assertFalse(u.validUtf8(data));
        data = new int[]{255};
        Assert.assertFalse(u.validUtf8(data));
        data = new int[]{240, 162, 138, 147, 145};
        Assert.assertFalse(u.validUtf8(data));
    }

    @After
    public void tearDown() {
        u = null;
    }

}
