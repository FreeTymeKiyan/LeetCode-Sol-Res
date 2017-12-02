// 035. Search Insert Position
/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples.
 * [1,3,5,6], 5 -> 2
 * [1,3,5,6], 2 -> 1
 * [1,3,5,6], 7 -> 4
 * [1,3,5,6], 0 -> 0
 * 
 * Tags: Array, Binary Search
 * 
 * Similar Problems: (E) First Bad Version
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int n = nums.size();
        if (n == 0)
        {
            return 0;
        }

        int left = 0, right = n - 1;
        while (left <= right)
        {
            int mid = (left + right) / 2;
            if (target == nums[mid])
            {
                return mid;
            }
            else if (target > nums[mid])
            {
                left = mid + 1;
            }
            else // target < nums[mid]
            {
                right = mid - 1;
            }
        }

        return left;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> nums;
    nums.push_back(1);
    nums.push_back(3);
    nums.push_back(5);
    nums.push_back(6);
    int target = 0;

    Solution mySolution;
    int res = mySolution.searchInsert(nums, target);
    cout << res << endl;
    system("pause");
    return 0;
}

