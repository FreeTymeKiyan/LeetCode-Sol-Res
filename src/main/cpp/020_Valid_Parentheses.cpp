//20. Valid Parentheses
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Tag: Stack, String

Author: Xinyu Liu
*/

#include <iostream>
#include <string>
#include <stack>
using namespace std;

class Solution {
public:
    bool isValid(string s) {
        if (s.empty()) return true;
        stack<char> cha;
        for (int i = 0; i < s.size(); i++){
            if (s[i] == '(' || s[i] == '[' || s[i] == '{')
                cha.push(s[i]);
            else{
                if(cha.empty()) return false;
                if((s[i] == ')' && cha.top() == '(') || (s[i] == ']' && cha.top() == '[') || (s[i] == '}' && cha.top() == '{'))
                    cha.pop();
                else return false;
            }
        }
        return(cha.empty());
    }
};

void main(){
    string s = "([])";
    Solution sol;
    bool b = sol.isValid(s);
}
