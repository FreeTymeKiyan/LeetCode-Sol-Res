package com.freetymekiyan.algorithms.level.hard;

/**
 * 158. Read N Characters Given Read4 2
 * <p>
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
    public static class Solution extends Reader4 {

        private int pointer = 0;
        private int size = 0;
        private char[] buffer = new char[4];

        /**
         * Read from the file into an intermediate buffer.
         * Write from that buffer to the outside buffer.
         * If that buffer is used up, read from file again.
         * If that buffer still has characters, keep for the next time.
         * Return when we reach the end of file, or we reach n.
         * | readBytes -> 0
         * | while readBytes < n:
         * |   if pointer == 0:
         * |     Refill buffer and update count.
         * |   if count == 0, end of file and break.
         * |   while readBytes < n and pointer < count, means n not reached, buffer not used up.
         * |     Write from cache to outside buffer.
         * | If pointer == count, reach the end:
         * |   Reset pointer for the next refill.
         */
        public int read(char[] buf, int n) {
            int bytes = 0;
            while (bytes < n) {
                if (pointer == size) { // When buffer is used up.
                    size = read4(buffer); // Reload by calling read4 again.
                    pointer = 0; // Reset pointer.
                }
                if (size == 0) break; // EOF.
                while (bytes < n && pointer < size) { // Copy to outside buffer.
                    buf[bytes++] = buffer[pointer++];
                }
            }
            return bytes;
        }
    }

    /**
     * Easier version.
     * Keep the state of each call by tracking:
     * 1) The number of characters in the buffer.
     * 2) The index of next character to read in the buffer.
     * If there are leftover characters, read them first.
     * If there are no leftovers, read regularly and copy from buffer.
     * Combine these two cases, we will find out that:
     * 1) Only when all characters in the buffer are read, when we call read4 again.
     */
    private static class Solution2 extends Reader4 {

        private int pointer = 0;
        private int size = 0;
        private char[] buffer = new char[4];

        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return The number of characters read
         */
        public int read(char[] buf, int n) {
            int bytes = 0;
            if (pointer < size) {
                while (bytes < n && pointer < size) {
                    buf[bytes++] = buffer[pointer++];
                }
                if (size < 4) return bytes;
            }

            while (bytes < n) {
                size = read4(buffer);
                while (bytes < n && pointer < size) {
                    buf[bytes++] = buffer[pointer++];
                }
                if (size < 4) break;
            }
            return bytes;
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
    private static class Reader4 {

        private String input = "ab";
        private int i = 0;

        int read4(char[] buf) {
            int bytes = 0;
            while (bytes < 4 && i < input.length()) {
                buf[bytes++] = input.charAt(i++);
            }
            return bytes;
        }
    }
}