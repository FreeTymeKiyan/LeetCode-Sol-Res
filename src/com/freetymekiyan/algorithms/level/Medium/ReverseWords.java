/**
 * Given an input string, reverse the string word by word.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 *
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing
 * spaces.
 *
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 *
 * Tags: String
 */
class ReverseWords {
    public static void main(String[] args) {
        String given = "the sky is blue";
        String given2 = "    a    b";
        System.out.println(new ReverseWords().reverseWords(given));
    }

    /**
     * If space, continue
     * If not, get the word and insert to the front of result
     * note that result may not contain spaces before or after
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            else {
                StringBuilder word = new StringBuilder();
                while (i < s.length()) {
                    c = s.charAt(i);
                    if (c == ' ') break;
                    word.append(c);
                    i++;
                }
                res = res.length() == 0 ? word.toString() : word.toString() + " " + res; // insert to front of res
                i--; // reset i
            }
        }
        return res;
    }

    /**
     * Trim input string
     * Split it with a space
     * Traversal backwards
     * Trim result to remove last space
     */
    public String reverseWordsB(String s) {
        if (s == null || s.length() == 0) return "";
        s = s.trim();
        StringBuilder res = new StringBuilder();
        String[] words = s.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].equals("")) {
                res.append(words[i]);
                if (i != 0) res.append(" ");
            }
        }
        return res.toString(); // remove last space
    }
}
