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
        ListNode* i ;
        while (head){
            i = L;
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

    ListNode n1(6), n2(5), n3(3);
    n1.next = &n2;
    n2.next = &n3;
    Solution sol;
    ListNode* n_sort = sol.insertionSortList(&n1);
}
