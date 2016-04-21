// 097. Interleaving String
/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * 
 * Tags: Dynamic Programming, String
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>

using namespace std;

// DP[i][j] indicates if s3[0...i + j - 1] is the interleaving of s1[0...i - 1] and s2[0..j - 1]
// DP[i][j] = (DP[i - 1][j] && s1[i - 1] == s3[i + j - 1]) || (DP[i][j - 1] && s2[j - 1] == s3[i + j - 1])
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int len1 = s1.size(), len2 = s2.size(), len3 = s3.size();
        if (len3 != len1 + len2)    // length not match
        {
            return false;
        }

        if ((len1 == 0 || len2 == 0) && (s3 != s1 + s2))    // at least one of s1 and s2 is empty
        {
            return false;
        }

        // 2D DP array
        bool **DP = new bool*[len1 + 1];
        for (int i = 0; i <= len1; i++)
        {
            DP[i] = new bool[len2 + 1];
        }
        DP[0][0] = true;    // s3 is the interleaving of empty string s1 and s2

        for (int i = 1; i <= len1; i++)
        {
            // corner cases if s2 is empty
            DP[i][0] = DP[i - 1][0] && s1[i - 1] == s3[i - 1];
        }

        for (int j = 1; j <= len2; j++)
        {
            // corner cases if s1 is empty
            DP[0][j] = DP[0][j - 1] && s2[j - 1] == s3[j - 1];
            for (int i = 1; i <= len1; i++)
            {
                // update DP array
                DP[i][j] = (DP[i - 1][j] && s1[i - 1] == s3[i + j - 1]) 
                    || (DP[i][j - 1] && s2[j - 1] == s3[i + j - 1]);
            }
        }

        bool res = DP[len1][len2];
        for (int i = 0; i <= len1; i++)
        {
            delete[] DP[i];
        }
        delete[] DP;

        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string s1(""), s2(""), s3("");
    Solution mySolution;
    bool res = mySolution.isInterleave(s1, s2, s3);
	return 0;
}

