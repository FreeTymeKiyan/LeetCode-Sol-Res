//71. Simplify Path
/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Tag: stack, string

Author: Xinyu Liu
*/

#include <iostream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

class Solution{
public:
    string simplifyPath(string path) {
        string ss, tmp;
        vector<string> stk;
        stringstream str(path);
        while(getline(str,tmp,'/')){
            if (tmp == "" || tmp == ".") continue;
            if (tmp == ".." && !stk.empty()) stk.pop_back();
            if (tmp != "..") stk.push_back(tmp);
        }
        for(int i = 0; i != stk.size(); i++)
            ss += "/" + stk[i];
        return stk.empty() ? "/" : ss;
    }
};

void main(){
    string s = "/a/./b/../../c/";
    Solution sol;
    string ss = sol.simplifyPath(s);
}
