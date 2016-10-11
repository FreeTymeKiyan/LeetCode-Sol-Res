package com.freetymekiyan.algorithms.level.easy;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * <p>
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
 * left in the file.
 * <p>
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * <p>
 * Note:
 * The read function will only be called once for each test case.
 * <p>
 * Company Tags: Facebook
 * Tags: String
 * Similar Problems: (H) Read N Characters Given Read4 II - Call multiple times
 */
public class ReadNCharactersGivenRead4 {

    /*
     * The read4 API is defined in the parent class Reader4.
     * int read4(char[] buf);
     */
    public class Solution extends Reader4 {

        /**
         * Read with read4 to an intermediate buffer.
         * Every time check if we reach the end of the file.
         * If read4 returns size smaller than 4, it means end of file.
         * Copy characters in read4 buffer to output buffer.
         * Update readBytes.
         *
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return The number of characters read
         */
        public int read(char[] buf, int n) {
            int readBytes = 0;
            char[] buffer = new char[4];
            int bufPointer = 0;
            int bufCounter = 0;

            while (readBytes < n) {
                if (bufPointer == 0) {
                    bufCounter = read4(buffer);
                }
                if (bufCounter == 0) {
                    break;
                }
                while (readBytes < n && bufPointer < bufCounter) {
                    buf[readBytes++] = buffer[bufPointer++];
                }
                if (bufPointer == bufCounter) {
                    bufPointer = 0;
                }
            }
            return readBytes;
        }
    }

    /**
     * Dummy
     */
    private class Reader4 {

        int read4(char[] buf) {
            return 0;
        }
    }
}
