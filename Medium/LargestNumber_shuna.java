import java.util.*;

/**
 * Largest Number 
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * Tags: Sort
 * Notes: PriorityQueue and Comparator
 * @author chenshuna
 */ 
 
public class LargestNumber {
    public String largestNumber(int[] nums) {  
        Queue<String> queue = new PriorityQueue<>(new Comparator<String>(){
            public int compare(String num1, String num2){
                return (num2 + num1).compareTo(num1 + num2);
            }
        });
        for(int num : nums){
            queue.offer(num + "");
        }
        StringBuilder sb = new StringBuilder();
        while(queue.size() > 0){
            sb.append(queue.poll());
        }
        /**
         * Need care the special case like [0,0], return 0;
         */ 
        int sLen = sb.length();
        int i = 0 ;
        while(i < sLen && sb.charAt(i) == '0')
        {
            i++;
        }
        if(i == sLen) return "0";
        return sb.substring(i);
    }
}