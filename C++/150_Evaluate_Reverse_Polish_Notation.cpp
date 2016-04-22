//150. Evaluate Reverse Polish Notation 
/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
Subscribe to see which companies asked this question

Tag: Stack

Author: Xinyu Liu
*/

#include<iostream>
#include<vector>
#include<string>

using namespace std;

class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        if (tokens.empty())
            return 0;

        string s = tokens.back();
        tokens.pop_back();

        if (s == "+" || s == "-" || s == "*" || s == "/"){
           int r = evalRPN(tokens);
           int l = evalRPN(tokens);
           if (s == "+") return l + r;
           else if (s == "-") return l - r;
           else if (s == "*") return l * r;
           else return l / r;
        }

        else 
            return atoi( s.c_str() );
    }
};

void main(){
    const char* test[] = {"0","3","/"};
    vector<string> test_s(begin(test), end(test));
    Solution sol;
    int result = sol.evalRPN(test_s);
}
