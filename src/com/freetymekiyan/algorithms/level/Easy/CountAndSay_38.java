package com.freetymekiyan.algorithms.level.easy;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
   1, 11, 21, 1211, 111221, ...

 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.

 * Note: The sequence of integers will be represented as a string.
 * Note: brute way: loop string
 * @author chenshuna
 *
 */

class CountAndSay_38 {
    public static String countAndSay(int n) {
        String restult = "1";
        for(int i=1;i<n;i++){
            restult = countAndSayArray(restult);
        }
        return restult;
    }
     public static String countAndSayArray(String cas) {
         int n = cas.length();
         int count = 1;
         int point = 0;
         StringBuilder res = new StringBuilder();
         while(point < n){
             while((point+count) < n && cas.charAt(point) == cas.charAt(point+count)){
                 count++;
             }
             res.append(count + "");
             res.append(cas.charAt(point) + "");
             point = point + count;
             count = 1;
         }
         return res.toString();
     }
     public static void main(String[] args) {
	// TODO Auto-generated method stub
	System.out.print(countAndSay(4));
     }

}
