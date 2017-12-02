//46 Permutations
/*
 *Given a collection of distinct numbers, return all possible permutations.
 *
 *For example,
 *[1,2,3] have the following permutations:
 *[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
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

using namespace std;

class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<int> singleResult;
        vector<vector<int>> result;
        vector<bool> visited(nums.size(), false); 

        permuteHelper(nums, 0, singleResult, result, visited);

        return result;
    }

    void permuteHelper(vector<int> &nums, int level, vector<int> &singleResult, vector<vector<int>> &result, vector<bool> &visited) {
        if (level == nums.size())
        {
            result.push_back(singleResult);
            return;
        }

        for (int i = 0; i < nums.size(); i++)
        {
            if (visited[i] ==  false)
            {
                visited[i] = true;
                singleResult.push_back(nums[i]);
                permuteHelper(nums, level+1, singleResult, result, visited);
                singleResult.pop_back();
                visited[i] = false;
            }
        }
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> input;
    vector<vector<int>> ouput;
    for(int i= 0;i<2; i++) {
        input.push_back(i);
    }

    Solution sol;
    ouput = sol.permute(input);
    return 0;
}

