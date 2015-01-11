/**
 * Implement strStr().
 * 
 * Returns a pointer to the first occurrence of needle in haystack, 
 * or null if needle is not part of haystack.
 * 
 * Tags: Two Pointers, String
 */
class ImplementStrStr {
    public static void main(String[] args) {
        String str1 = "14531234";
        String str2 = "123";
        String str3 = "1123";
        String str4 = "1245";
        String str5 = "12121212123";
        System.out.println(strStr(str1, str2));
        System.out.println(strStr(str3, str2));
        System.out.println(strStr(str4, str2));
        System.out.println(strStr(str5, str2));
    }
    
    /**
     * return null if anyone is null or haystack is shorter 
     * return haystack if needle's length is 0
     * traverse fewer times by substract the length
     * compare each char with two pointers till needle's length runs out
     * if not returned during loop, return null
     */
    public static String strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) return null;
        if (needle.length() == 0) return haystack;
        int len = haystack.length();
        int tarLen = needle.length();
        for (int i = 0; i < len - tarLen + 1; i++) { // iteration times reduced
            int j = 0;
            int k = i;
            while (k < len && haystack.charAt(k) == needle.charAt(j)) {
                k++;
                j++;
                if (j == needle.length()) return haystack.substring(k - j);
            }
        }
        return null;
    }
    
    public static String strStr(String haystack, String needle) { 
        if((haystack == null)||(needle == null)){ 
            return null;} 
     
        int lengthOfStack = haystack.length(); 
        int lengthOfNeedle = needle.length(); 

        if(lengthOfStack < lengthOfNeedle){ 
            return null; 
        } 

        for(int i = 0; i <= lengthOfStack -lengthOfNeedle; i++){ 
            if(haystack.substring(i,i+lengthOfNeedle).equals(needle)){ 
                return haystack.substring(i); 
            }
        } 
        return null; 
   }
}
