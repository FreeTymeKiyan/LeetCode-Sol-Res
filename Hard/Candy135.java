/*
   There are N children standing in a line. Each child is assigned a rating value.

	You are giving candies to these children subjected to the following requirements:
	
	Each child must have at least one candy.
	Children with a higher rating get more candies than their neighbors.
	What is the minimum candies you must give?
	
	Analysis:
	  2 loop
	  time O(n)
 */
public class Candy {
	  public static int candy(int[] ratings) {
		    if(ratings == null || ratings.length==0)
		    {
		        return 0;
		    }
		    int[] nums = new int[ratings.length];
		    nums[0] = 1;
		    
		    for(int i = 1; i < ratings.length; i++)
		    {
		        if(ratings[i] > ratings[i-1])
		        {
		            nums[i] = nums[i-1]+1;
		        }
		        else
		        {
		            nums[i] = 1;
		        }
		    }
		    int res = nums[ratings.length-1];  
		    
		    for(int i = ratings.length-2; i >= 0; i--)
		    {
		        int cur = 1;
		        if(ratings[i] > ratings[i+1])
		        {
		            cur = nums[i+1]+1;
		        }
		        res += Math.max(cur,nums[i]);
		        nums[i] = cur;
		    }
		    return res;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] rateArray = {1,2,4,1,3};
		int res = candy(rateArray);
		System.out.print(res);
	}

}
