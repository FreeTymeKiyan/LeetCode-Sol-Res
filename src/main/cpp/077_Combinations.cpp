// 77. Combinations
/** 
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * 
 * For example,
 * If n = 4 and k = 2, a solution is:
 * 
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 
 * Tags: Backtracking
 * 
 * Similar Problems: (M) Combination Sum, (M) Permutations
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>

using namespace std;

class Solution {
    void backtrack(vector<vector<int>> &res, vector<int> &comb, int index, int n, int k) {
        if (k == comb.size())
        {
            // size == k, record results
            res.push_back(comb);
            return;
        }

        for (int i = index; i <= n; i++)
        {
            comb.push_back(i);
            backtrack(res, comb, i + 1, n, k);
            comb.pop_back();
        }

        return;
    }
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        if (n < 1 || k < 1)
        {
            return res;
        }

        vector<int> comb;
        backtrack(res, comb, 1, n, k);
        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    Solution mySolution;
    vector<vector<int>> res = mySolution.combine(4, 2);
    return 0;
}

