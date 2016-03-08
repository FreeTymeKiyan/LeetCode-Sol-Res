// 132. Palindrome Partitioning II
/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * Tags: Dynamic programming
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    int minCut(string s) {        
        int n = s.size();
        if (n <= 1)
        {
            return 0;
        }

        // bPalin[i][j] corresponds to whether substring s[i..j] is palindrome or not
        vector<vector<bool>> bPalin(n, vector<bool>(n, false));

        // minCuts[i] indicates the minimum cuts needed for substring s[0..i-1]
        vector<int> minCuts(n + 1, 0);

        // initialize minCuts[i] to the maximum cuts needed for substring s[0..i-1]
        // minCuts[0] now has -1 in case that s[0..i-1] is a palindrome
        for (int i = 0; i <= n; i++)
        {
            minCuts[i] = i - 1;
        }

        // cut[i] is the minimum of current cut[i] and cut[j - 1] + 1 (j <= i), if s[j, i] is palindrome
        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                // if s[j + 1, i - 1] is palindrome, and s[j] == s[i], then s[j, i] is a palindrome
                if ((s[j] == s[i]) && ((i - j < 2) || bPalin[j + 1][i - 1]))
                {
                    bPalin[j][i] = true;
                    minCuts[i + 1] = min(minCuts[i + 1], minCuts[j] + 1);
                }
            }
        }

        return minCuts[n];
    }
};

// this solution is even faster because it updates the minCuts array only when finding palindrome
class Solution_2 {
public:
    int minCut(string s) {        
        int n = s.size();
        if (n <= 1)
        {
            return 0;
        }

        // minCuts[i] indicates the minimum cuts needed for substring s[0..i-1]
        vector<int> minCuts(n + 1, 0);

        // initialize minCuts[i] to the maximum cuts needed for substring s[0..i-1]
        // minCuts[0] now has -1 in case that s[0..i-1] is a palindrome
        for (int i = 0; i <= n; i++)
        {
            minCuts[i] = i - 1;
        }

        // cut[i] is the minimum of current cut[i] and cut[j - 1] + 1 (j <= i), if s[j, i] is palindrome
        for (int i = 1; i < n; i++)
        {
            for(int j = 0; (i - j >= 0) && (i + j < n) && (s[i - j] == s[i + j]); j++) // odd-length palindrome substrings
            {
                minCuts[i + j + 1] = min(minCuts[i + j + 1], 1 + minCuts[i - j]);
            }

            for(int j = 0;(i - j - 1 >= 0) && (i + j < n) && (s[i - j - 1] == s[i + j]); j++) // even-length palindrome substrings
            {
                minCuts[i + j + 1] = min(minCuts[i + j + 1], 1 + minCuts[i - j - 1]);
            }
        }

        return minCuts[n];
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string s("ababaaba");

    Solution_2 mySolution;
    int result = mySolution.minCut(s);
    cout << result << endl;
    system("pause");

    return 0;
}

