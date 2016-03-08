//147. Insertion Sort List
/*
Sort a linked list using insertion sort.

Author: Xinyu Liu
*/

#include <iostream>
using namespace std;

//Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};


class Solution{
public:
    ListNode* insertionSortList(ListNode* head){
        ListNode* L = new ListNode(INT_MIN);
        while (head){
            ListNode* i = L;
            while (i ->next && i ->next ->val < head ->val){
                i = i->next;
            }
            ListNode* insert = new ListNode(head -> val);
            insert ->next = i ->next;
            i ->next = insert;
            head = head ->next;
        }
        return L->next;

    }
};

void main(){

    ListNode* n1 = new ListNode(6);
    n1 ->next = new ListNode(5);
    n1 ->next ->next = new ListNode(3);
    Solution sol;
    ListNode* n_sort = sol.insertionSortList(n1);
}
