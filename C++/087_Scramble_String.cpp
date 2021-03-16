// 087. Scramble String
/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * 
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * 
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * 
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 * 
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * 
 * Tags: Dynamic Programming, String
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>

using namespace std;

// Recursive
// To test if s2 is a scrambled string of s1, we can divide them in to two substrings
//                                        s2[0...len-1]
//                                       /             \
//        s1[0...len-1]            s2[0...k-1]   s2[k...len-1]
//       /             \
// s1[0...k-1]   s1[k...len-1]            s2[0...len-1]
//                                       /             \
//                             s2[0...len-k-1]   s2[len-k...len-1]
//
// Then s1 and s2 scramble strings if:
// a) isScramble(s1[0...k-1], s2[0...k-1]) && isScramble(s1[k...len-1], s2[k...len-1])
// b) isScramble(s1[0...k-1], s2[len-k...len-1]) && isScramble(s1[k...len-1], s2[0...len-k-1])
//
// To speed up recursion, we can terminate our recursion early (pruning) by checking if s1 and s2 have the same character set
class Solution {
public:
    bool isScramble(string s1, string s2) {
        int len1 = s1.size(), len2 = s2.size();
        if (len1 != len2)
        {
            return false;
        }

        if ((len1 == 0) || (s1 == s2))
        {
            return true;
        }

        // use a count array to store how many times a char appears in the string
        int count[256] = {0};
        for (int i = 0; i < len1; i++)
        {
            count[s1[i]]++;
            count[s2[i]]--;
        }

        // if a char appears different times in s1 and s2, return false
        for (int i = 0; i < len1; i++)
        {
            if (count[s1[i]] != 0)
            {
                return false;
            }
        }

        // divide into substring and recursively check if they are scramble strings
        for (int i = 1; i < len1; i++)
        {
            if (isScramble(s1.substr(0, i), s2.substr(0, i)) && isScramble(s1.substr(i), s2.substr(i)))
            {
                return true;
            }

            if (isScramble(s1.substr(0, i), s2.substr(len1 - i)) && isScramble(s1.substr(i), s2.substr(0, len1 - i)))
            {
                return true;
            }

        }

        return false;
    }
};

// Dynamic Programming
// isScr[n-1][i][j] is a 3D DP array which indicates if s1[i...i+n-1] is the scrambled string of s2[j...j+n-1]
// isScr[n-1][i][j] = (isScr[k-1][i][j] && isScr[n-k-1][i+k][j+k]) || (isScr[k-1][i][j+n-k] && isScr[n-k-1][i+k][j])
class Solution_DP {
public:
    bool isScramble(string s1, string s2) {
        int len1 = s1.size(), len2 = s2.size();
        if (len1 != len2)
        {
            return false;
        }

        if ((len1 == 0) || (s1 == s2))
        {
            return true;
        }

        // allocate DP array
        bool*** isScr = new bool**[len1];
        for (int i = 0; i < len1; i++)
        {
            isScr[i] = new bool*[len1];
            for (int j = 0; j < len1; j++)
            {
                isScr[i][j] = new bool[len2]();
            }
        }

        // initialize DP array
        for (int i = 0; i < len1; i++)
        {
            for (int j = 0; j < len2; j++)
            {
                isScr[0][i][j] = (s1[i] == s2[j]);
            }
        }

        for (int n = 2; n <= len1; n++)
        {
            for (int i = 0; i <= len1 - n; i++)
            {
                for (int j = 0; j <= len2 - n; j++)
                {
                    bool bScr = false;
                    // for any k that makes it scramble strings, isScr[n][i][j] = true
                    for (int k = 1; (k < n) && (bScr == false); k++)
                    {
                        bScr = (isScr[k-1][i][j] && isScr[n-k-1][i+k][j+k]) || (isScr[k-1][i][j+n-k] && isScr[n-k-1][i+k][j]);
                    }
                    isScr[n-1][i][j] = bScr;
                }
            }
        }

        // save result, deallocate memory to prevent memory leak
        bool res = isScr[len1-1][0][0];
        for (int i = 0; i < len1; i++)
        {
            for (int j = 0; j < len2; j++)
            {
                delete[] isScr[i][j];
            }
            delete[] isScr[i];
        }
        delete[] isScr;
        
        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string s1("abb");
    string s2("bab");
    Solution_DP mySolution;
    bool res = mySolution.isScramble(s1, s2);
    return 0;
}

