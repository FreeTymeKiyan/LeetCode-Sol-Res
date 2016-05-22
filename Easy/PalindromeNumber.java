/**
 * Palindrome Number
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Tags: Math
 * Similar Problems:  (E) Palindrome Linked List
 * 
 * Notes: For palindrome int, we can compare half to half.
 * Time:O(n)
 * @author:chenshuna
 */

public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if(x < 0 || (x!=0 && (x % 10) == 0)){
            return false;
        }
        int res = 0;
        while(res < x){
            res = x % 10 + res * 10;
            x = x / 10;
        }
        return (x == res || res/10 == x);
    }
    public static void main(String[] args) {
        System.out.print(isPalindrome(14541));
    }
}