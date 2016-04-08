// 040. Combination Sum II
/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
 * The solution set must not contain duplicate combinations.
 *
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
 * A solution set is: 
 * [1, 7] 
 * [1, 2, 5] 
 * [2, 6] 
 * [1, 1, 6] 
 * 
 * Tags: Array, Backtracking
 *
 * Similar Problems: (M) Combination Sum
 *
 * Author: Kuang Qin
 */


#include "stdafx.h"
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

class Solution {
    void backtrack(vector<vector<int>>& res, vector<int>& combination, vector<int>& candidates, int index, int target) {
        if (target == 0)
        {
            res.push_back(combination);    // record results if sum equals to target
            return;
        }

        int n = candidates.size();
        for (int i = index; i < n; i++)
        {
            if (target - candidates[i] < 0)
            {
                break;    // skip if the sum is larger than target
            }

            combination.push_back(candidates[i]);
            backtrack(res, combination, candidates, i + 1, target - candidates[i]);
            combination.pop_back();

            // skip dupliactes
            while (i + 1 < n && candidates[i + 1] == candidates[i])
            {
                i++;
            }
        }
    }
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<vector<int>> res;
        int n = candidates.size();
        if (n == 0)
        {
            return res;
        }

        sort(candidates.begin(), candidates.end());
        vector<int> combination;

        // start backtracking
        backtrack(res, combination, candidates, 0, target);
        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> candidates;
    // 10,1,2,7,6,1,5
    candidates.push_back(10);
    candidates.push_back(1);
    candidates.push_back(2);
    candidates.push_back(7);
    candidates.push_back(6);
    candidates.push_back(1);
    candidates.push_back(5);
    int target = 7;

    Solution mySolution;
    vector<vector<int>> result = mySolution.combinationSum2(candidates, target);

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

