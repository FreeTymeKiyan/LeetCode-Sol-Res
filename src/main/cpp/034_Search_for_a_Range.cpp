// 034. Search for a Range
/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 *
 * Tags: Array, Binary Search
 *
 * Similar Problems: (E)First Bad Version
 */

#include "stdafx.h"
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> range;
        range.push_back(-1);
        range.push_back(-1);
        int n = nums.size();
        int left = 0, right = n - 1;
        if ((n == 0) || (nums[left] > target) || (nums[right] < target))
        {
            return range;
        }

        if (nums[left] == nums[right])
        {
            range[0] = 0;
            range[1] = n - 1;
            return range;
        }

        // binary search left: finding the first value >= target
        int mid = 0;
        while (left <= right)
        {
            mid = (left + right) / 2;
            if (target > nums[mid])
            {
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }
        range[0] = left;

        // binary search right: finding the last value <= target
        left = 0, right = n - 1;
        while (left <= right)
        {
            mid = (left + right) / 2;
            if (target < nums[mid])
            {
                right = mid - 1;
            }
            else
            {
                left = mid + 1;
            }
        }
        range[1] = right;

        // if first > last, not found
        if (range[0] > range[1])
        {
            range[0] = -1;
            range[1] = -1;
        }

        return range;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> nums;
    nums.push_back(5);
    nums.push_back(7);
    nums.push_back(7);
    nums.push_back(8);
    nums.push_back(8);
    nums.push_back(10);
    int target = 10;

    Solution mySolution;
    vector<int> res = mySolution.searchRange(nums, target);
    cout << "[" << res[0] << "," << res[1] << "]" << endl;
    system("pause");

    return 0;
}

