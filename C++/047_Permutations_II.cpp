//047 Permutations_II
/*
 *Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 *For example,
 *[1,1,2] have the following unique permutations:
 *[1,1,2], [1,2,1], and [2,1,1].
 *
 *Tag: Backtracking
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include "stdlib.h"
#include <iostream> 
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        vector<int> singleResult;
        vector<vector<int>> result;
        vector<bool> visited(nums.size(), false); 
        sort(nums.begin(),nums.end());
        permuteHelper(nums, singleResult, result, visited);

        return result;
    }

    void permuteHelper(vector<int> &nums, vector<int> &singleResult, vector<vector<int>> &result, vector<bool> &visited) {
        if (singleResult.size() == nums.size())
        {
            result.push_back(singleResult);
            return;
        }

        for (int i = 0; i < nums.size(); i++)
        {
            if(visited[i]) continue;
            if(i>0 && nums[i]==nums[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            singleResult.push_back(nums[i]);
            permuteHelper(nums, singleResult, result, visited);
            singleResult.pop_back();
            visited[i] = false;

        }
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> input;
    vector<vector<int>> ouput;

    input.push_back(1);
    input.push_back(1);
    input.push_back(2);
    input.push_back(2);

    Solution sol;
    ouput = sol.permuteUnique(input);
    return 0;
}

