// 151 Reverse Words in a String
/**
* Given an input string, reverse the string word by word.
* 
* For example,
* Given s = "the sky is blue",
* return "blue is sky the".
* 
* Tag:       String
* 
* Author:    Yanbin Lu
*/

#include <stddef.h>
#include <vector>
#include <string.h>
#include <stdio.h>
#include <algorithm> 
#include <iostream>
#include <map>
#include <queue>

using namespace std;

class Solution {
public:
    void reverseWords(string &s) {
        
        // build the new string in reverse order
        string result = "";
        for (int i = 0; i < s.length(); i++) {
            if (s[i] == ' ')        // skip leading spaces
                continue;
                
            int pos = i;            // start of the next word
            // compute the length of teh next word
            while (i < s.length() && s[i] != ' ') 
                i++;                                
            if (result.length() > 0) 
                result = ' ' + result;    
            // apend the next word in the front of the new string
            result = s.substr(pos, i - pos) + result;
            i--;
        }
        
        s = result;
    }
};

int main()
{
    
    string s("the sky is blue");
    
    Solution* sol = new Solution();
    cout << sol->reverseWords(s) << endl;
	
    char c;
    std::cin>>c;

    return 0;
}