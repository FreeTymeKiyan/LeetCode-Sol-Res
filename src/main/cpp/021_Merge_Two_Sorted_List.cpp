// 021. Merge Two Sorted List
/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
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
    ListNode(int x, ListNode *p): val(x), next(p) {}
};

// merge sort, adding while comparing, time O(m+n)
class Solution_MergeSort {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode dummy(0);
        ListNode *curr = &dummy;
        while (l1 && l2)
        {
            if (l1->val < l2->val)
            {
                curr->next = l1;
                curr = l1;
                l1 = l1->next;
            }
            else
            {
                curr->next = l2;
                curr = l2;
                l2 = l2->next;
            }
        }

        if (!l1)
        {
            curr->next = l2;
        }

        if (!l2)
        {
            curr->next = l1;
        }

        return dummy.next;
    }
};

// use a dummy pointer to track the head, add nodes of l2 to l1, time O(m+n)
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode dummy(INT_MIN);
        ListNode *curr = &dummy;
        dummy.next = l1;
        while (l2)
        {
            // move l1
            while ((curr->next) && (curr->next->val < l2->val))
            {
                curr = curr->next;
            }

            // insert l2 node after curr
            ListNode *next = l2->next;
            l2->next = curr->next;
            curr->next = l2;
            l2 = next;
        }

        return dummy.next;
    }
};

// double pointer, time O(m+n)
class Solution_DoublePointer {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode **prev = &l1;

        while (*prev && l2)
        {
            if ((*prev)->val > l2->val)
            {
                // insert l2 node before *prev
                // before insertion:
                //
                //          val     val
                // prev --> next -> next
                //                   |
                //                 *prev
                //
                // after insertion:
                //
                //          val     newval  val
                // prev --> next -> next -> next
                //                   |
                //                 *prev
                ListNode *next = l2->next;
                l2->next = *prev;
                *prev = l2;
                l2 = next;
            }

            prev = &((*prev)->next);
        }

        if (l2)    // the element left in l2
        {
            *prev = l2;   // let the l1's last node->next = l2
        }

        return l1;
    }
};

// recursive solution, time O(m+n), much more space complexity
class Solution_Recursive {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (!l1)
        {
            return l2;
        }

        if (!l2)
        {
            return l1;
        }

        if (l1->val <= l2->val)
        {
            l1->next = mergeTwoLists(l1->next, l2);
            return l1;
        }

        l2->next = mergeTwoLists(l1, l2->next);
        return l2;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    ListNode node1_4(2);
    ListNode node1_3(1, &node1_4);
    ListNode node1_2(0, &node1_3);
    ListNode node1_1(-1, &node1_2);

    ListNode node2_4(4);
    ListNode node2_3(3, &node2_4);
    ListNode node2_2(2, &node2_3);
    ListNode node2_1(1, &node2_2);

    Solution mySolution;
    ListNode *newhead = mySolution.mergeTwoLists(&node2_1, &node1_1);

    for (ListNode *p = newhead; p; p = p->next)
    {
        cout << p->val << " ";
    }
    cout << endl;
    system("pause");

    return 0;
}

