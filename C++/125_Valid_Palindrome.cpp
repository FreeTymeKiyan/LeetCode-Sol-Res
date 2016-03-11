//125. Valid Palindrome
/*Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Author: Xinyu Liu
*/

#include <string>
using namespace std;

class Solution {
public:
    bool isPalindrome(string s) {
        string::iterator start = s.begin();
        string::iterator end = s.end() - 1;
        while(start < end){
            while (start < end && !isalnum(*start))
                start ++;
            while (start < end && !isalnum(*end))
                end --;       
            if (tolower(*start )!= tolower(*end))
                return false;
            start ++;
            end--;
        }
         return true;
        
    }
};

void main(){
    string str_test = "test";
    Solution sol;
    bool bol = sol.isPalindrome(str_test);
}
