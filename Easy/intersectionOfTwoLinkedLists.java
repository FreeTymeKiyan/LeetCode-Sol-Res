package gitLeetCode;
import java.util.*;

/*
 * Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
 */



// get the length of 2 lists
// the common part must in the same length part so set longhead and shorthead
// longhead runs first. When longhead has the same length with the shorthead , shorthead runs
// Time O(n+m+max(n,m)) -> O(n)
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class intersectionOfTwoLinkedLists {
    
	 public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        if(headA == null || headB== null)
	          return null;
	        ListNode l1 = headA;
	        ListNode l2 = headB;
	        int c1 = 0;
	        int c2 = 0;
	        while(l1!= null){
	            l1= l1.next;
	            c1++;
	        }
	        while(l2!= null){
	            l2= l2.next;
	            c2++;
	        }
	       int different = Math.abs(c1-c2);
	       ListNode longHead = c1>=c2? headA:headB;
	       ListNode shortHead = c1>=c2? headB:headA;
	     
	        while(longHead!= null ){
	            if(different!=0){
	                longHead = longHead.next;
	                different--;
	            }
	            else{
	                if(longHead.val == shortHead.val){
	                 return longHead;
	                 }
	                else{
	                    longHead = longHead.next;
	                    shortHead = shortHead.next;
	                 } 
	            }
	           
	        }
	        return null;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode headA = new ListNode(1);
		headA.next =  new ListNode(3);
		headA.next =  new ListNode(4);
		headA.next =  new ListNode(5);
		
		ListNode headB = new ListNode(0);
		headB.next =  new ListNode(2);
		headB.next =  new ListNode(5);
		
		//System.out.print(getIntersectionNode(headA,headB).val);
	}

}


