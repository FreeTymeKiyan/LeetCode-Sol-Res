// 17. Letter Combinations of a Phone Number
/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * 1()     2(abc)  3(def)
 * 4(ghi)  5(jkl)  6(mno)
 * 7(pqrs) 8(tuv)  9(wxyz)
 * *()     0()     #()
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * 
 * Tags: Backtracking, String
 *
 * Similar Problems: (M) Generate Parentheses, (M) Combination Sum
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <string>
#include <unordered_map>
#include <iostream>

using namespace std;

class Solution {
    unordered_map<char, string> tel;
    void backtrack(string& digits, vector<string>& res, string& str, int index) {
        int n = digits.size();

        // record current result at the last button
        if (index == n && !str.empty())
        {
            res.push_back(str);
            return;
        }

        // invalid string, return
        if (tel.find(digits[index]) == tel.end())
        {
            res.clear();
            return;
        }

        // start to backtrack corresponding string on each button
        string s = tel[digits[index]];
        int len = s.size();
        for (int i = 0; i < len; i++)
        {
            str += s[i];
            backtrack(digits, res, str, index + 1);
            str.pop_back();
        }

        return;
    }
public:
    vector<string> letterCombinations(string digits) {
        vector<string> res;
        int n = digits.size();
        if (n == 0)
        {
            return res;
        }

        // initialize hash map
        tel['2'] = "abc";
        tel['3'] = "def";
        tel['4'] = "ghi";
        tel['5'] = "jkl";
        tel['6'] = "mno";
        tel['7'] = "pqrs";
        tel['8'] = "tuv";
        tel['9'] = "wxyz";

        string str;

        backtrack(digits, res, str, 0);
        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string digits("731");
    Solution mySolution;
    vector<string> res = mySolution.letterCombinations(digits);

    for (int i = 0; i < res.size(); i++)
    {
        cout << res[i] << endl;
    }
    system("pause");
    return 0;
}

