/**
 * A type of encoding has only 1 byte encode or 2-byte encode. If the first bit 
 * is 0, this byte represents 1 character. If the first bit is 1, it represents 
 * 2 characters. Given a string of bits, find out whether the last char is 1 
 * byte encoded or 2-byte encoded. 
 * 
 * Tags: Recursive
 */
class LastByte {
    public static void main(String[] args) {
        LastByte l = new LastByte();
        String s1 = "0xxxxxxx0xxxxxxx0xxxxxxx0xxxxxxx";
        String s2 = "0xxxxxxx0xxxxxxx1xxxxxxx0xxxxxxx";
        String s3 = "0xxxxxxx1xxxxxxx1xxxxxxx0xxxxxxx";
        System.out.println(l.lastByte(s1));
        System.out.println(l.lastByte(s2));
        System.out.println(l.lastByte(s3));
    }
    
    /**
     * Recursive
     * Check backwards
     * If s.charAt(-16) is 0, it must be 1
     * If it is 1, it can be 1 or 0, according to recurse result
     * Recurse with string without last 8 bit
     * If returns 1, it means 1xxxxxxx is not connected with string before
     * If returns 2, it means 1xxxxxxx is part of a 2-byte, return 1
     */
    public int lastByte(String s) {
        if (s == null || s.length() < 16) return 1;
        if (s.charAt(s.length() - 16) == '0') return 1;
        return lastByte(s.substring(0, s.length() - 8)) == 1 ? 2 : 1;
    }
}
