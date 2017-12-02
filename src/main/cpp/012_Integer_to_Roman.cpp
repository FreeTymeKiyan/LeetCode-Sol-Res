// 012. Integer to Roman
/**
 * Given an integer, convert it to a roman numeral.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * Tags: Math, String
 *
 * Similar Problems: (E) Roman to Integer, (H) Integer to English Words
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    string intToRoman(int num) {
        string roman;
        char pattern[4][2] = {{' ', 'M'}, {'D', 'C'}, {'L', 'X'}, {'V', 'I'}};    // the pattern: '5', '1'

        int divisor = 1000, remainder = num;    // start from thousands
        for (int i = 0; i < 4; i++)    // maximum 4 digits
        {
            int digit = remainder / divisor;
            remainder %= divisor;
            divisor /= 10;

            // the pattern is the same for number 1-9
            // the letters are different depending on where the number is
            switch (digit)
            {
            case 1:    // 'I'
                roman += pattern[i][1];
                break;
            case 2:    // 'II'
                roman += pattern[i][1];
                roman += pattern[i][1];
                break;
            case 3:    // 'III'
                roman += pattern[i][1];
                roman += pattern[i][1];
                roman += pattern[i][1];
                break;
            case 4:    // 'IV'
                roman += pattern[i][1];
                roman += pattern[i][0];
                break;
            case 5:    // 'V'
                roman += pattern[i][0];
                break;
            case 6:    // 'VI'
                roman += pattern[i][0];
                roman += pattern[i][1];
                break;
            case 7:    // 'VII'
                roman += pattern[i][0];
                roman += pattern[i][1];
                roman += pattern[i][1];
                break;
            case 8:    // 'VIII'
                roman += pattern[i][0];
                roman += pattern[i][1];
                roman += pattern[i][1];
                roman += pattern[i][1];
                break;
            case 9:    // 'IX'
                roman += pattern[i][1];
                roman += pattern[i - 1][1];
                break;
            default:
                break;
            }
        }
        
        return roman;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    int num = 1111;
    Solution mySolution;
    string res = mySolution.intToRoman(num);
    cout << res << endl;
    system("pause");
    return 0;
}

