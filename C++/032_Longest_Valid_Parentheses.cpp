// 032 Longest Valid Parentheses
/**
* Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
* 
* For "(()", the longest valid parentheses substring is "()", which has length = 2.
* 
* Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
* 
* Tag:      Dynamic Programming, String
* 
* Author:   Yanbin Lu
*/

#include <stddef.h>
#include <vector>
#include <string.h>
#include <stdio.h>
#include <algorithm> 
#include <iostream>
#include <map>
#include <queue>
#include <stack>

using namespace std;

class Solution {
public:
    int longestValidParentheses(string s) {
        
        stack<int> stk;
        stk.push(-1);
        int maxlen=0;

        for(int i = 0; i < s.size(); i++)
        {
            int t = stk.top();
            
            //pop stack and update lenth only when matching
            if(t != -1 && s[i] == ')' && s[t] == '('){
                stk.pop();
                maxlen = max(maxlen, i - stk.top());
            }
            // push the last elemnet that is not matched yet
            else
                stk.push(i);
        }
        return maxlen;
    }
};

int main()
{
    Solution* sol = new Solution();
    string test1("(())");
    string test2("()()");
    string test3(")))(((");
    cout << sol->longestValidParentheses(test1) << endl;
    cout << sol->longestValidParentheses(test2) << endl;
    cout << sol->longestValidParentheses(test3) << endl;
	
    char c;
    std::cin>>c;

    return 0;
}

