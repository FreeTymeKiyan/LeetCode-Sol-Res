// 013. Roman to Integer
/**
 * Given a roman numeral, convert it to an integer.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * Tags: Math, String
 *
 * Similar Problems: (M) Integer to Roman
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    int romanToInt(string s) {
        int n = s.size();
        int res = 0;

        // assume all capital letters
        int val[128];
        val['I'] = 1;
        val['V'] = 5;
        val['X'] = 10;
        val['L'] = 50;
        val['C'] = 100;
        val['D'] = 500;
        val['M'] = 1000;

        for (int i = 0; i < n - 1; i++)
        {
            // if current letter is smaller than the next letter, do substraction
            if (val[s[i]] < val[s[i + 1]])
            {
                res -= val[s[i]];
            }
            else
            {
                res += val[s[i]];
            }
        }

        // the last letter
        res += val[s[n - 1]];

        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string s("XXXI");
    Solution mySolution;
    int res = mySolution.romanToInt(s);
    cout << res << endl;
    system("pause");
	return 0;
}

