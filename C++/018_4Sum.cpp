// 018. 4Sum
/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ¡Ü b ¡Ü c ¡Ü d)
 * The solution set must not contain duplicate quadruplets.
 *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 *     A solution set is:
 *     (-1,  0, 0, 1)
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 * 
 * Tags: Array, Hash Table, Two Pointers
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        vector<vector<int>> result;
        int n = nums.size();
        if (n < 4)
        {
            return result;
        }

        // sort the array
        sort(nums.begin(), nums.end());

        for (int i = 0; i < n - 3; i++)
        {
            // stop if the possible minimum is larger than target
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
            {
                break;
            }

            // skip duplicates
            if ((i > 0) && (nums[i] == nums[i - 1]))
            {
                continue;
            }

            // go to next number if the possible maximum is smaller than target
            if (nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target)
            {
                continue;
            }

            int threesum = target - nums[i];
            for (int j = i + 1; j < n - 2; j ++)
            {
                // stop if the possible minimum is larger than target
                if (nums[j] + nums[j + 1] + nums[j + 2] > threesum)
                {
                    break;
                }

                // skip duplicates
                if ((j > i + 1) && (nums[j] == nums[j - 1]))
                {
                    continue;
                }

                // go to next number if the possible maximum is smaller than target
                if (nums[j] + nums[n - 2] + nums[n - 1] < threesum)
                {
                    continue;
                }

                int twosum = threesum - nums[j];

                // Two Sum Problem: use two pointers to search for target
                int left = j + 1, right = n - 1;
                while (left < right)
                {
                    int sum = nums[left] + nums[right];
                    if (sum < twosum)
                    {
                        left++;
                        while ((left < right) && (nums[left] == nums[left - 1]))    // skip duplicates from left
                        {
                            left++;
                        }
                    }
                    else if (sum > twosum)
                    {
                        right--;
                        while ((left < right) && (nums[right] == nums[right + 1]))    // skip duplicates from right
                        {
                            right--;
                        }
                    }
                    else // sum == twosum
                    {
                        vector<int> quadruplets;
                        quadruplets.push_back(nums[i]);
                        quadruplets.push_back(nums[j]);
                        quadruplets.push_back(nums[left]);
                        quadruplets.push_back(nums[right]);
                        result.push_back(quadruplets);

                        left++;
                        while ((left < right) && (nums[left] == nums[left - 1]))    // skip duplicates from left
                        {
                            left++;
                        }

                        right--;
                        while ((left < right) && (nums[right] == nums[right + 1]))    // skip duplicates from right
                        {
                            right--;
                        }
                    }
                }
            }
        }

        return result;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> nums;
    int target = 0;

    // nums = {1, 0, -1, 0, -2, 2}
    nums.push_back(1);
    nums.push_back(0);
    nums.push_back(-1);
    nums.push_back(0);
    nums.push_back(-2);
    nums.push_back(2);

    Solution mySolution;
    vector<vector<int>> result = mySolution.fourSum(nums, target);

    for (int i = 0; i < result.size(); i++)
    {
        for (int j = 0; j < result[i].size(); j++)
        {
            cout << result[i][j] << " ";
        }
        cout << endl;
    }
    system("pause");

    return 0;
}

