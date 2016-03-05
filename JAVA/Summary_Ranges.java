package gitLeetCode;

/*
 * 228. Summary Ranges
 * Given a sorted integer array without duplicates, return the summary of its ranges.

   For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
   time O(n) using a start point
 */

import java.util.*;

public class Summary_Ranges228 {
   public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        if(nums.length == 0){
            return res;
        }
        if(nums.length == 1){
            res.add(nums[0]+"");
            return res;
        }
        int start = 0;
        int end = 0;
        while (end<nums.length){
          if(end+1<nums.length && nums[end]+1 == nums[end+1]){
              end ++;
          }
          else {
             if(start == end){
               res.add(nums[start]+"");
           }
            else{
               res.add(nums[start]+"->"+nums[end]);
            }
            end++;
            start  = end;
           }
       }
        return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] res = {1,2,4,5,7,8,10};
		System.out.print(summaryRanges(res)); 	
	}

}
