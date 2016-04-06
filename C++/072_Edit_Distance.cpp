//72. Edit Distance
/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character

Tag: Dynamic Programming, String

Author: Xinyu Liu
*/

#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    int minDistance(string word1, string word2) {
        int m = word1.size();
        int n = word2.size();
        vector<vector<int>> vec(m+1, vector<int>(n+1,0));
        for (int i = 0; i <= m; i++){
            vec[i][0] = i;
        }
        for (int j = 0; j <= n; j++){
            vec[0][j] = j;
        }

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (word1[i - 1] == word2[j - 1])
                    vec[i][j] = vec[i - 1][j - 1];
                else
                    vec[i][j] = min(vec[i - 1][j] + 1, min(vec[i][j - 1] + 1,vec[i - 1][j - 1] + 1));
            }
        }

        return vec[m][n];
    }
};

void main(){

    string word1 = "a";
    string word2 = "a";
    Solution sol;
    int num = sol.minDistance(word1,word2);
}
