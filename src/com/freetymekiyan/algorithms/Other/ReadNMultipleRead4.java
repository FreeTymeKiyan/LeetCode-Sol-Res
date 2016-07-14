/**
 * Similar to Read N Characters Given Read4, but the read 
 * function may be called multiple times
 * 
 * Tags: 
 */
class ReadNMultipleRead4 {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Store state of previous call
     * Including a buffer, an offset index of the buffer, 
     */
    private char[] buffer = new char[4];
    int offset = 0, bufsize = 0;
    
    /**
     * Call multiple times, storing states
     * The difference between single time multiple times is:
     * There can be some characters return by read4 in the buffer
     * We need to get them for the next call
     * For example, supppose n is 5, read4 will be call twice, 3 chars remain
     * Next read5 call needs to get those 3 characters
     * 
     * So we make buffer as a field variable, along with offset and bufsize
     * If bufsize > 0, means something in buffer
     * 
     * @param buf Destination buffer
     * @param n Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false; // flag
        while (!eof && readBytes < n) {
            int sz = (bufsize > 0) ? bufsize : read4(buffer);
            if (bufsize == 0 && sz < 4) eof = true; // empty buffer, no more
            int bytes = Math.min(n - readBytes, sz);
            // from offset in buffer, to readBytes in output buffer
            System.arraycopy(buffer, offset, buf, readBytes, bytes);
            offset = (offset + bytes) % 4; // update offset
            bufsize = sz - bytes; // size read - size copied
            readBytes += bytes; // update readBytes
        }
        return readBytes;
    }
}
