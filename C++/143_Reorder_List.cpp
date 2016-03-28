// 143_Reorder_List.cpp : Defines the entry point for the console application.
/**
 * Given a singly linked list L: L0->L1->бн->Ln-1->Ln,
 * reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->бн
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 *
 * Tags: Linked List
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <iostream>

using namespace std;

/**
 * Definition for singly-linked list.
 */
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
    ListNode(int x, ListNode *p) : val(x), next(p) {}
};

class Solution {
public:
    void reorderList(ListNode* head) {
        if ((head == NULL) || (head->next == NULL))
        {
            return;
        }

        // cut the list into two halves
        ListNode *slow = head, *fast = head->next;
        while (fast && fast->next)
        {
            slow = slow->next;
            fast = fast->next->next;
        }

        // reverse the second list
        ListNode *curr = slow->next, *prev = NULL;
        while (curr)
        {
            ListNode *next = curr->next;
            curr->next = prev;
            prev = curr;
            curr = next;
        }

        // terminate the list at the end of first half
        slow->next = NULL;

        // merge two lists
        while (prev)
        {
            ListNode *next1 = head->next, *next2 = prev->next;
            head->next = prev;
            prev->next = next1;
            head = next1;
            prev = next2;
        }
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    ListNode node5(5), node4(4, &node5), node3(3, &node4), node2(2, &node3), node1(1, &node2);
    ListNode *head = &node1, *curr = head;
    cout << "Before Reorder:" << endl;
    while (curr)
    {
        cout << curr->val << " ";
        curr = curr->next;
    }
    cout << endl;

    Solution mySolution;
    mySolution.reorderList(head);
    curr = head;
    cout << "After Reorder:" << endl;
    while (curr)
    {
        cout << curr->val << " ";
        curr = curr->next;
    }
    cout << endl;
    system("pause");
    return 0;
}

