package com.freetymekiyan.algorithms.level.hard;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * <p>
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
 * left in the file.
 * <p>
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * <p>
 * Note:
 * The read function may be called multiple times.
 * <p>
 * Company Tags: Bloomberg, Google, Facebook
 * Tags: String
 * Similar Problems: (E) Read N Characters Given Read4
 */
public class ReadNCharactersGivenRead42 {

    /**
     * The read4 API is defined in the parent class Reader4.
     * int read4(char[] buf);
     */
    public class Solution extends Reader4 {

        private int bufferPointer = 0;
        private int bufferCounter = 0;
        private char[] buffer = new char[4];

        /**
         * Read from the file into an intermediate buffer.
         * Write from that buffer to the outside buffer.
         * If that buffer is used up, read from file again.
         * If that buffer still has characters, keep for the next time.
         * Return when we reach the end of file, or we reach n.
         *
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return The number of characters read
         */
        public int read(char[] buf, int n) {
            int readBytes = 0;
            while (readBytes < n) {
                if (bufferPointer == 0) { // Refill intermediate buffer if needed.
                    bufferCounter = read4(buffer);
                }
                if (bufferCounter == 0) { // End of file
                    break;
                }
                while (readBytes < n && bufferPointer < bufferCounter) { // Copy to outside buffer
                    buf[readBytes++] = buffer[bufferPointer++];
                }
                if (bufferPointer == bufferCounter) { // Outside buffer used up
                    bufferPointer = 0;
                }
            }
            return readBytes;
        }
    }

    /**
     * What is the difference between call once and call multiple times?
     * When you call read4() which reads 4 bytes into your buffer you might read more than you need.
     * So you want to store those bytes.
     * And next time you call read will start from those stored bytes, then read more from the file.
     * <p>
     * Example:
     * You have 4 chars "a, b, c, d" in the file, and you want to call your function twice like this:
     * read(buf, 1); // should return 'a'
     * read(buf, 3); // should return 'b, c, d'
     * All the 4 chars will be consumed in the first call of read4().
     * So the tricky part of this question is:
     * how can you preserve the remaining 'b, c, d' to the second call.
     * https://discuss.leetcode.com/topic/36179/what-is-the-difference-between-call-once-and-call-multiple-times
     */
    private class Reader4 {

        int read4(char[] buf) {
            return 0;
        }
    }
}
