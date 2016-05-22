// 3. Longest Substring Without Repeating Characters
/**
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
 * For "bbbbb" the longest substring is "b", with the length of 1.
 * 
 * Tag: Hash Table, Two Pointers, String
 * 
 * Author: Kuang Qin
 */

#include <iostream>
#include <string>
#include <unordered_map>

using namespace std;

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        // use two pointers to search a string with non-repeating characters
        int l = 0, r = 0, len = 0;
        
        // use a hash map to map current char to its last appeared location
        unordered_map<char, int> map;
        
        // use r to scan the string
        for (r = 0; r < s.size(); r++)
        {
            if (map.find(s[r]) != map.end())   // s[r] appears before
            {
                if (l <= map[s[r]])    // l can only go forward
                {
                    l = map[s[r]] + 1;    // update l to ensure non-repeating
                }
            }
            
            map[s[r]] = r;    // update last apeared location of s[r]
            
            if (len < r - l + 1)
            {
                len = r - l + 1;    // update max length
            }
        }
        
        return len;
    }
};

int main() {
    string s("abcabcbb");
    Solution mySolution;
    int res = mySolution.lengthOfLongestSubstring(s);
    cout << res << endl;
    return 0;
}
