// 58. Length of Last Word
/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * 
 * For example, 
 * Given s = "Hello World",
 * return 5.
 * 
 * Tags: String
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>

using namespace std;

class Solution {
public:
    int lengthOfLastWord(string s) {
        int n = s.size();
        if (n == 0)
        {
            return 0;
        }

        // remove trailing spaces
        int last = n - 1;
        while (last >= 0 && s[last] == ' ')
        {
            last--;
        }

        // start to count of the last word
        int len = 0;
        while (last >= 0 && s[last] != ' ')
        {
            len++;
            last--;
        }

        return len;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}
