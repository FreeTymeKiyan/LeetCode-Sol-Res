/**
 * Given a string, determine if it is a palindrome, considering only 
 * alphanumeric characters and ignoring cases.
 * 
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * 
 * Note:
 * Have you consider that the string might be empty? This is a good question to 
 * ask during an interview.
 * 
 * For the purpose of this problem, we define empty string as valid palindrome.
 * 
 * Tags: Two pointers, String
 */
class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
    
    /**
     * Two pointers
     */
    public static boolean isPalindromeB(Stinrg s) {
        if (s == null || s.length() == 0) return true;

        int start = 0;
        int end = s.length() - 1;
        s = s.toLowerCase();
        while (start < end) {
            // move start
            while (start <= end && !isValid(s.charAt(start))) start++;
            // for string without letters or digit
            if (start > end) return true;
            // move end
            while (start <= end && ! isValid(s.charAt(end))) end--;
            if (s.charAt(start)) != s.charAt(end))) break;
            start++;
            end--;
        }
        return end <= start;
    }
    
    private boolean isValid(char c) {
        return Character.isLetterOrDigit(c);
    }
    
    /**
     * Put letters and numbers in a new string and convert to lowercase
     * O(2n) Time, O(n) Space
     */
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) if (Character.isLetterOrDigit(c)) sb.append(c);
        String copy = sb.toString().toLowerCase();
        int length = copy.length();
        for (int i = 0; i < length / 2; i++) {
            if (copy.charAt(i) != copy.charAt(length - (i + 1))) {
                return false;
            }
        }
        return true;
    }
}
