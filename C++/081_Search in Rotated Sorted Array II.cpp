//   Problem 81, Search in Rotated Sorted Array II

//   Follow up for "Search in Rotated Sorted Array":
//   What if duplicates are allowed?
//   Would this affect the run-time complexity? How and why?
//   Write a function to determine if a given target is in the array.
//
//   FYI: "Search in Rotated Sorted Array":
//   Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//   (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//   You are given a target value to search. If found in the array return its index, otherwise return -1.
//   You may assume no duplicate exists in the array.

#include <stddef.h>
#include <vector>
#include <string.h>
#include <stdio.h>
#include <algorithm> 
#include <iostream>
#include <map>

using namespace std;

class Solution {
public:
    bool search(vector<int>& nums, int target) {
        
        int start = 0; 
        int end = nums.size()-1;
        
        while(start <= end){
            // skip the repeating numbers on both sides
            while(start < end && nums[start] == nums[start + 1]) start++;
            while(start < end && nums[end] == nums[end - 1]) end--;
            
            int mid = (start + end)/2;
            if(nums[mid] == target) return true; // found
            if(nums[mid] < target){
                // for cases like {6 7 0 1 2 4 5} mid is 1, start is 6, end is 5, target is 2
                // and cases like {2 4 5 6 7 0 1} mid is 6, start is 2, end is 1, target is 7
                if(nums[start] > target || nums[mid] >= nums[start])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            else{
                // for cases like {4 5 6 7 0 1 2}, mid is 7, start is 4, end is 2, target is 5
                // and cases like {6 7 0 1 2 4 5}, mid is 1, start is 6, end is 5, target is 0
                if(nums[start] <= target || nums[mid] < nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
        }
        return false;
    }
};
int main()
{
	Solution* sol = new Solution();
	vector<int> nums;
	nums.push_back(4);
	nums.push_back(5);
	nums.push_back(6);
	nums.push_back(7);
	nums.push_back(0);
	nums.push_back(1);
	nums.push_back(2);

	cout<<sol->search(nums,3)<<endl;
	cout<<sol->search(nums,0)<<endl;
	cout<<sol->search(nums,6)<<endl;
	cout<<sol->search(nums,5)<<endl;
	
	char c;
	cin>>c;

	return 0;
}