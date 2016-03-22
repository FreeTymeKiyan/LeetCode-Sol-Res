//92. Reverse Linked List II
/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

Tag: Linked List

Author: Xinyu Liu
*/

#include <iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};



class Solution {
public:
    ListNode *reverseBetween(ListNode *head, int m, int n) {
        if (m == n) return head;
        n -= m;
        ListNode dumbnode(0);
        dumbnode.next = head;
        ListNode* pre = &dumbnode;
        while (--m)
            pre = pre->next;
        ListNode* start = pre->next;
        while (n--){
            ListNode* p = start->next;
            start->next = p->next;
            p->next = pre->next;
            pre->next = p;
        }
        return dumbnode.next;
    }
};

void main(){
    ListNode n1(1),n2(2),n3(3),n4(4),n5(5);
    n1.next = &n2;
    n2.next = &n3;
    n3.next = &n4;
    n4.next = &n5;
    Solution sol;
    ListNode* head = sol.reverseBetween(&n1, 2, 4);
}
