//10. Regular Expression Matching
/*
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

Tag: Dynamic Programming, Backtracking, String

Author: Xinyu Liu
*/

#include <iostream>
#include <string>
using namespace std;

class Solution{
public:
    bool isMatch(string s, string p){
        if (p.empty()) return s.empty();

        if (p[1] == '*')
            return ((!s.empty() && (s[0] == p[0] || p[0] == '.') && isMatch(s.substr(1),p))|| isMatch(s,p.substr(2)));
        else
            return (!s.empty() && (s[0] == p[0] || p[0] == '.') && isMatch(s.substr(1),p.substr(1)));
    }
};



void main(){
    string s ("aaa");
    string p ("a*");
    Solution sol;
    bool b = sol.isMatch(s,p);
}
