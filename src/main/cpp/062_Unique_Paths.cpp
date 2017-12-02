// 62_Unique_Paths.cpp : Defines the entry point for the console application.
/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * Start  -  -  -  -  -  -    -
 *   -    -  -  -  -  -  -    -
 *   -    -  -  -  -  -  -  Finish
 * 
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * 
 * Note: m and n will be at most 100.
 * 
 * Tags: Array, Dynamic Programming
 *
 * Similar Problems: (M) Unique Paths II, (M) Minimum Path Sum, (H) Dungeon Game
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"

// dynamic programming
// DP[m][n] indicates the unique paths from start to (m + 1)th row, (n + 1)th column
// obviously, DP[0][0] = 1, DP[0][m] = 1, DP[n][0] = 1
// DP[m][n] = DP[m - 1][n] + DP[m][n - 1]
// because we have only one way to get to (m, n) from (m - 1, n) or (m, n - 1)
// Time: O(m * n), Space: O(m * n)
class Solution {
public:
    int uniquePaths(int m, int n) {
        int DP[100][100];

        for(int i = 0; i < m; i++)
        {
            DP[i][0] = 1;
        }

        for(int j = 0; j < n; j++)
        {
            DP[0][j] = 1;
        }

        for(int i = 1; i < m; i++)
        {
            for(int j = 1; j < n; j++)
            {
                DP[i][j] = DP[i - 1][j] + DP[i][j - 1];
            }
        }

        return DP[m - 1][n - 1];
    }
};

// keep the most recent layer instead of the whole DP matrix
// Time: O(m * n), Space: O(min(m, n))
class Solution_Layer {
public:
    int uniquePaths(int m, int n) {
        // update DP layer by layer, only keep information of the last row or column
        int *DP = new int[m]();

        DP[0] = 1;    // DP[0][n] is always 1
        for(int j = 0; j < n; j++)
        {
            for(int i = 1; i < m; i++)
            {                
                // DP[m][n] = DP[m - 1][n] + DP[m][n - 1]
                DP[i] += DP[i - 1];
            }
        }

        int res = DP[m - 1];
        delete[] DP;
        return res;
    }
};

// math combination
// we need (m - 1) + (n - 1) moves to point (m, n)
// choose (n - 1) down moves among those moves
// the rest will be right moves
// Time: O(min(m, n)), Space: O(1)
class Solution_Combination {
public:
    int uniquePaths(int m, int n) {
        int total = m + n - 2;    // total moves
        int k = m > n ? n : m;    // choose the smaller one from total moves
        double res = 1;
        for(int i = 1; i < k; i++)
        {
            // C(k - 1, total)
            res *= total--;
            res /= i;
        }

        return (int)res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

