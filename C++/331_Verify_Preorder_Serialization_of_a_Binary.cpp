// 331. Verify Preorder Serialization of a Binary Tree
/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false

Tag:

Author: suifeng

*/

// Main thought:
//     basic situation, leaf node: 1,#,#
//     advance situation 1, left child is leaf: 2,1,#,#,#
//     advance situation 2, right child is leaf: 2,#,1,#,#
// repalce node to `#`, so we got:
//     advance situation 1: 2,#,#
//     advance situation 2: 2,#,#
// the leaf node is a pattern.
// the ideas is iteratively repalce leaf node to `#` until no pattern to find。


#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    bool isValidSerialization(string preorder) {
        string& s = preorder;
        while (s.size() >= 5) {
            bool find_pattern = false;
            for (int i = s.size()-1; i>= 4; i--) {
                if (s[i] == '#' && s[i-2] == '#' && s[i-4] != '#') {
                    find_pattern = true;
                    int j = i-4-1;
                    /* find the start place of pattern */
                    while (j > 0 && s[j] != ',') j--; 
                    s.replace(j+1, i-j, "#"); /* s[j+1, i] 替换为 # */
                    break;  /* start a trun search from the end */
                }
            }
            if (!find_pattern) break;
        }
        
        /* boundary: empty tree */
        return (s.size() == 1 && s[0] == '#');
    }
};


void test(string s, bool result) {
    Solution so;
    bool ret = so.isValidSerialization(s);
    if (ret != result) {
        cout << "failed test case: " << endl;
        cout << s << endl;
        cout << "true result: " << result<< endl;
        cout << "false retsult: " << ret << endl;
    }
}

int main()
{
    test("#", true); // empty tree

    // true cases
    test("1,#,#", true);
    test("1,2,#,#,#", true);
    test("9,3,4,#,#,1,#,#,2,#,6,#,#", true);

    // false cases
    test("1,#", false);
    test("1,#,#,#,#", false);
    test("9,#,#,1", false);
    test("9,3,4,#,#,1,#,#,#,2,#,6,#,#", false);
    return 0;
}
