/**
 Leetcode: 45. Jump Game II
 Date: 03/0402015
 Author: Sasa150
*/

/**
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
*/

/**
 This can be solved using greedy algo.
 Initially we are at index 0, and step count is 0;
 then after 1 jump, we can expland our jump range from [0, 0] to [0, x] which is [0, current end] + [current end +1, new end]
 then we need to search the range after 2nd jump, which starts from [current end +1] to [new end]
 while doing this, record how many steps we have passed.
 finally if the new end is larger than or equal to nums.size()-1, then it means the end has been reached..
*/

class Solution {
public:
    int jump(vector<int>& nums) {
        int start=0, cur=0, cnt=0, newcur=0;  //start index, current max index, current steps, and new end
        while(cur<(nums.size()-1)){
            while(start<=cur){// while we can reach this range
                newcur = max(newcur, nums[start]+start);    //update the new end if possible
                ++start;
            }
            if(newcur<=cur) return -1;      //not possible..
            cur=newcur;                     //now the new end will be the current
            ++cnt;                          //add step count
        }
        
        return cnt;
    }
};
