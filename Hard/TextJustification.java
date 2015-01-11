import java.util.*;

/**
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * 
 * Return the formatted lines as:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 
 * Note: Each word is guaranteed not to exceed L in length.
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you
 * do in this case?
 * In this case, that line should be left-justified.
 * 
 * Tags: String
 */
class TextJustification {
    public static void main(String[] args) {
        System.out.println(fullJustify(new String[]{""}, 2));
    }
    
    /**
     * Track length of words and space in current line
     * Go through the words
     * Add with next word's length, if within range, append word and update len
     * If not, check how many words in this line. If only 1, append spaces
     * If more than 1, get total # of spaces and divide it with # of words - 1
     * The quotient is # of spaces basiaclly between each word
     * The remainder is # of sections that should add 1 more space
     * Then add string to result, clear line and add word of next line
     * Deal with last line after loop is over.
     */
    public static List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0 || L < 0) return res;
        List<String> line = new ArrayList<String>();
        String str = "";
        int len = 0, div, mod;
        for (int i = 0; i < words.length; i++) {
            if (len + line.size() + words[i].length() <= L) {
                line.add(words[i]);
                len += words[i].length();
            } else {
                if (line.size() == 1) { // only 1 word in this line
                    str = line.get(0);
                    for (int j = L - str.length(); j > 0; j--) str += " ";
                } else if (line.size() > 1) {
                    div = (L - len) / (line.size() - 1); // divisor
                    mod = (L - len) % (line.size() - 1); // remainder
                    str = line.get(0); // append first word
                    for (int j = 1; j < line.size(); j++) { // append rest of the words
                        for (int k = 0; k < div; k++) str += " ";
                        if (j <= mod) str += " "; // append 1 more space
                        str += line.get(j);
                    }
                }
                res.add(str);
                line.clear();
                line.add(words[i]); // next line
                len = words[i].length();
            }
        }
        // last line
        str = line.get(0);
        for (int i = 1; i < line.size(); i++) str += " " + line.get(i); // words
        for (int i = L - str.length(); i > 0; i--) str += " "; // append spaces
        res.add(str);
        return res;
    }
}