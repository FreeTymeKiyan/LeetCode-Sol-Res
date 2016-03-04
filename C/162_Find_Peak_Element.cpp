//A peak element is an element that is greater than its neighbors.
//
//Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
//
//The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//You may imagine that num[-1] = num[n] = -∞.
//
//For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
//
//Author: Xinyu Liu

#include <iostream>
#include <vector>
using namespace std;



class Solution {

public:
	int findPeakElement(vector<int>& nums) {
		int sz = nums.size();
		if (sz == 1)
			return 0;
		if (nums.at(0)>nums.at(1)) 
			return 0;
		if (nums.at(sz-1)>nums.at(sz-2)) 
			return sz-1;

		for(int i =1; i<nums.size();i++)
		{
			
			if (nums.at(i-1)<nums.at(i)&nums.at(i)>nums.at(i+1))
				return i;
		}
		return -1;

	}
};

void main(){


	// Initialize vector
	int myints[] = {1,2,3,4,3};
	int index;
	std::vector<int> test (myints, myints + sizeof(myints) / sizeof(int) );

	Solution sol;
	index = sol.findPeakElement (test);

	printf("%i \n",index);

}
