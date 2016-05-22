// 131. Palindrome Partitioning
/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 * 
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 *
 * Tags: Backtracking
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
    bool isPalindrome(string s) {
        int begin = 0;
        int end = s.size() - 1;

        // the string reads the same forward or backward
        while (begin < end)
        {
            if (s[begin++] != s[end--])
            {
                return false;
            }
        }

        return true;
    }

    // use backtracking to find partition
    // maintain a stack of palindrome and keep checking if the sub-tree of the top node is palindrome
    void findPartition(vector<vector<string>>& res, vector<string>& p, string s, int index) {
        int n = s.size();

        // whenever the index hit the last node, record the result
        if (index == n)
        {
            res.push_back(p);
            return;
        }

        string str("");
        for (int i = index; i < n; i++)
        {
            str += s[i]; 
        
            if (isPalindrome(str) == false)    // if not palindrome, check next possible sub-tree
            {
                continue;
            }

            p.push_back(str);    // if true, push the current string onto stack, continue recursion
            findPartition(res, p, s, i+1);
            p.pop_back();    // pop out the current node from top of the stack
        }
    }

    vector<vector<string>> partition(string s) {
        vector<vector<string>> res;
        vector<string> p;
        findPartition(res, p, s, 0);
        return res;
    }
};

// this is the solution using dynamic programming
// it will maintain a 2D array of palindrome status instead of checking it every time
class Solution_DP {
public:

    // use backtracking to find partition
    // maintain a stack of palindrome and keep checking if the sub-tree of the top node is palindrome
    void findPartition(vector<vector<string>>& res, vector<string>& p, string s, int index, vector<vector<bool>> bPalin) {
        int n = s.size();

        // whenever the index hit the last node, record the result
        if (index == n)
        {
            res.push_back(p);
            return;
        }

        string str("");
        for (int i = index; i < n; i++)
        {
            str += s[i]; 
        
            if (bPalin[index][i] == false)    // if not palindrome, check next possible sub-tree
            {
                continue;
            }

            p.push_back(str);    // if true, push the current string onto stack, continue recursion
            findPartition(res, p, s, i+1, bPalin);
            p.pop_back();    // pop out the current node from top of the stack
        }
    }

    vector<vector<string>> partition(string s) {
        vector<vector<string>> res;
        vector<string> p;

        // bPalin[i][j] corresponds to whether substring s[i..j] is palindrome or not
        int n = s.size();
        vector<vector<bool>> bPalin(n, vector<bool>(n, false));
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                // if s[j + 1, i - 1] is palindrome, and s[j] == s[i], then s[j, i] is a palindrome
                if ((s[j] == s[i]) && ((i - j < 2) || bPalin[j + 1][i - 1]))
                {
                    bPalin[j][i] = true;
                }
            }
        }

        findPartition(res, p, s, 0, bPalin);
        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string s("ababaaba");

    Solution_DP mySolution;
    vector<vector<string>> result = mySolution.partition(s);

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

