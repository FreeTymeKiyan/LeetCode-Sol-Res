/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 *
 * For example,
 * Given s = "Hello World",
 * return 5.
 *
 * Tags: String
 */
class LengthOfLastWord {
    public static void main(String[] args) {
        String a = " ";
        String b = "Hello World";
        String c = "           ";
        String d = "a";
        System.out.println(lengthOfLastWord(a));
        System.out.println(lengthOfLastWord(b));
        System.out.println(lengthOfLastWord(c));
        System.out.println(lengthOfLastWord(d));
    }

    /**
     * Traverse backwards
     * Use count to remember length of word
     * Start counting from non-space char
     * Return when next space is met and length is not zero
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)  return 0;

        int len = s.length();
        int count = 0;

        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') count++;
            if (s.charAt(i) == ' ' && count != 0)  return count;
        }
        return count;
    }

    // mine, trim and check from back
    public static int lengthOfLastWord(String s) {
        s = s.trim(); // remove front and trailing spaces
        char space = ' ';
        if (s.indexOf(space) == -1) return s.length(); // dont have a space
        int len = s.length();
        for (int i = len - 1; i >= 0; i--) { // traverse backwards
            if (s.charAt(i) == ' ' && i != len - 1) { // is space and not last one
                return len - 1 - i;
            }
        }
        return 0;
    }
}
