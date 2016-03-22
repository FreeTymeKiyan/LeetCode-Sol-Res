// 005. Longest Palindromic Substring
/**
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 *
 * Tags: String
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        if (n < 2)
        {
            return s;
        }

        int start = 0, maxLen = 0, left = 0, right = 0, index = 0;
        // stop if max length is larger than twice the length from index to end
        while ((index < n) && (maxLen < 2 * (n - index)))
        {
            left = right = index;
            while ((right < n - 1) && (s[right + 1] == s[right]))
            {
                right++;    // skip all duplicates
            }

            index = right + 1;    // set next index

            while ((left > 0) && (right < n - 1) && (s[left - 1] == s[right + 1]))
            {
                left--;
                right++;
            }

            // current maxlength at index
            int curLen = right - left + 1;
            if (curLen > maxLen)
            {
                maxLen = curLen;
                start = left;
            }
        }

        return s.substr(start, maxLen);
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string s("abcbababc");
    Solution mySolution;
    string res = mySolution.longestPalindrome(s);
    cout << res << endl;
    system("pause");
    return 0;
}

