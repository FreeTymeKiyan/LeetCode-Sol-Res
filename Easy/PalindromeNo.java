/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Keys: Negative integer? 
 * 
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * 
 * If you are thinking of converting the integer to string, note the 
 * restriction of using extra space.
 * 
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might
 * overflow. How would you handle such case?
 * 
 * There is a more generic way of solving this problem.
 * 
 * Tags: Math
 */
class PalindromeNo {
    public static void main(String[] args) {
        System.out.println(isPalindrom(1));
        System.out.println(isPalindrom(12));
        System.out.println(isPalindrom(32123)); 
        System.out.println(isPalindrom(321123)); 
        System.out.println(isPalindrom(-1)); 
        System.out.println(isPalindrom(1234567));
        System.out.println(isPalindrom(1000000021));
    }
    
    /**
     * Clarify whether negative nums are palindrom first
     * Compare each digit
     */
    public static boolean isPalindrom(int num) {
        if (num < 0) return false;
        /*find the most significant digit*/
        int div = 1;
        while (num / div >= 10) div *= 10; // two digits
        
        while (num != 0) { // stop till num is 0, all digits compared
            int l = num / div; // left digit
            int r = num % 10; // right digit
            if (l != r) return false; // compare
            num = (num % div) / 10; // remove first and last digit
            div /= 100; // div should be smaller
        }
        return true;
    }
    
    /**
     * compare the reversed result, can go out of integer's range
     */
    public static int reverse(int num) {
        long rev = 0;
        while (num != 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return num < 0 ? -1 * (int)rev : (int)rev;
    }
}
