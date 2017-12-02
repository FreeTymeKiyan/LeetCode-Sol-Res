// 043. Multiply Strings
/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 *
 * Note: The numbers can be arbitrarily large and are non-negative
 *
 * Tags: Math, String
 *
 * Similar Problems: (M) Add Two Numbers, (E) Plus One, (E) Add Binary
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    string multiply(string num1, string num2) {
        if ((num1 == "0") || (num2 == "0"))
        {
            return "0";
        }

        // use an integer array to store multiplication products
        int len1 = num1.size(), len2 = num2.size(), len = len1 + len2;
        int *product = new int[len]();
        for (int i = len1 - 1; i >= 0; i--)
        {
            for (int j = len2 - 1; j >= 0; j--)
            {
                product[i + j + 1] += (num1[i] - '0') * (num2[j] - '0');
            }
        }

        // gather carry info to make each digit valid
        for (int i = len - 1; i > 0; i--)
        {
            product[i - 1] += product[i] / 10;    // add carry to previous digit
            product[i] = product[i] % 10;         // set current digit
        }

        // convert int into string
        string res("");
        if (product[0] != 0)    // first position could be zero
        {
            res += product[0] + '0';
        }

        for (int i = 1; i < len; i++)
        {
            res += product[i] + '0';
        }

        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string num1("8354"), num2("7890");
    Solution mySolution;
    string res = mySolution.multiply(num1, num2);
    cout << num1 << " x " << num2 << " = " << res << endl;
    system("pause");
    return 0;
}

