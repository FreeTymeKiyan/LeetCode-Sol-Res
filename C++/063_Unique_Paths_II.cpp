// 063. Unique Paths II
/**
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * 
 * Note: m and n will be at most 100.
 * 
 * Tags: Array, Dynamic Programming
 * 
 * Similar Problems: (M) Unique Paths
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>

using namespace std;

class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size();
        if (m == 0)
        {
            return 0;
        }

        int n = obstacleGrid[0].size();
        if (n == 0)
        {
            return 0;
        }

        // DP[m][n] indicates the unique paths to point (m, n)
        vector<vector<int>> DP(m + 1, vector<int> (n + 1, 0));
        DP[0][1] = 1;    // define the DP[0][1] to unify the boundaries
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                // if there is an obstacle, the path getting there is zero
                if (!obstacleGrid[i - 1][j - 1])
                {
                    // DP[m][n] = DP[m - 1][n] + DP[m][n - 1]
                    DP[i][j] = DP[i - 1][j] + DP[i][j - 1];
                }
            }
        }

        return DP[m][n];
    }
};

// keep the most recent layer instead of the whole matrix
class Solution_Layer {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size();
        if (m == 0)
        {
            return 0;
        }

        int n = obstacleGrid[0].size();
        if (n == 0)
        {
            return 0;
        }
        
        // DP information of the recent row or column
        vector<int> DP(m, 0);
        for (int i = 0; i < m; i++)
        {
            if (!obstacleGrid[i][0])
            {
                DP[i] = 1;
            }
            else
            {
                // obstacle blocks the path
                break;
            }
        }

        for (int j = 1; j < n; j++)
        {
            bool isBlocked = true;    // a flag showing if the path is blocked
            if (obstacleGrid[0][j])
            {
                // obstacle blocks the path
                DP[0] = 0;
            }
            else
            {
                // path not blocked
                isBlocked = false;
            }

            for (int i = 1; i < m; i++)
            {
                if (!obstacleGrid[i][j])
                {
                    DP[i] += DP[i - 1];
                    if (DP[i])
                    {
                        isBlocked = false;
                    }
                }
                else
                {
                    // obstacle blocks the path
                    DP[i] = 0;
                }
            }

            if (isBlocked)
            {
                return 0;
            }
        }

        return DP[m - 1];
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

