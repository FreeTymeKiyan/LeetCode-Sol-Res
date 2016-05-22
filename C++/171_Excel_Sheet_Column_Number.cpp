/*
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

 * For example:
 
 * A -> 1
   B -> 2
   C -> 3
   ...
   Z -> 26
   AA -> 27
   AB -> 28 

 * Tag: string

 * Author : Dawei Li
*/


#include <string>

using namespace std;

class Solution {
public:
    int titleToNumber(string s) {
        
        const int CharNum = 26;
        
        int iVal = 0;
        for( int iIndex = 0; iIndex < s.size(); iIndex++ )
        {
            iVal = iVal * CharNum + (s[iIndex] - 'A' + 1);
        }
        
        return iVal;
        
    }
};

int main( int argc, char ** argv )
{
    string test = "ABB";
    Solution sln;

    sln.titleToNumber( test );

    return 0;

}
