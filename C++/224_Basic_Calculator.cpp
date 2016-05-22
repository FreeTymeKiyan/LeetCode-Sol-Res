//224. Basic Calculator
/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

Tag: Stack, Math

Author: Xinyu Liu
*/

#include <iostream>
#include <string>
#include <stack>

using namespace std;

class Solution {
public:
    int calculate(string s) {
        int res = 0;
        int num = 0;
        int lastsign = 1;
        stack<int> sign;
        sign.push(lastsign);

        for(int i = 0; i < s.length() ; i++ ){
            char c = s.at(i);
            if (c == '(')
                sign.push(lastsign);
            else if ( c == ')'){
                sign.pop();
            }
            else if ( c >= '0' && c <= '9')
                num = 10 * num + c - '0';
            else if( c == '+' || c == '-'){
                res += num * lastsign;
                lastsign = (c == '+' ? 1 : -1) * sign.top();
                num = 0;
            }
        }
        res += num * lastsign;
        return res;
    }
};

void main(){
    string s = "1";
    Solution sol;
    int res = sol.calculate(s);
}
