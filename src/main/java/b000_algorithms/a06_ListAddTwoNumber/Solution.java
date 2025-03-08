package main.java.b000_algorithms.a06_ListAddTwoNumber;

/**
 * LeetCode 2
 * @author Chenyu Liu
 * @since 3/2/25 Sunday
 **/

public class Solution {

    /**
     * Example 1:
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     * <p>
     * Example 2:
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     * <p>
     * Example 3:
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     */

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void loop(ListNode ll1) {
        while (ll1 != null) {
            System.out.print(ll1.val + " ");
            ll1 = ll1.next;
        }
        System.out.println();
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // corner case
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode ll1 = l1;
        ListNode ll2 = l2;
        loop(ll1);
        loop(ll2);
        ListNode pointer1 = l1;
        ListNode pointer2 = l2;
        ListNode ansHead;
        int carry = 0;
        ListNode curr;
        if (pointer1 == null) {
            ansHead = pointer2;
            curr = ansHead;
            pointer2 = pointer2.next;
        } else if (pointer2 == null) {
            ansHead = pointer1;
            curr = ansHead;
            pointer1 = pointer1.next;
        } else {
            ansHead = new ListNode((pointer1.val + pointer2.val + carry )% 10);
            carry = (pointer1.val + pointer2.val + carry) / 10;
            curr = ansHead;
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        while (pointer1 != null && pointer2 != null) {
            System.out.println(curr.val + " carry:" + carry);
            int currSum = (pointer1.val + pointer2.val + carry) % 10;
            curr.next = new ListNode(currSum);
            carry = (pointer1.val + pointer2.val + carry) / 10;
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
            curr = curr.next;
        }
        while (pointer1 != null || pointer2 != null) {
            System.out.println(curr.val + " carry:" + carry);
            if (pointer1 == null) {
                curr.next = new ListNode((pointer2.val + carry) % 10);
                carry = (pointer2.val + carry) / 10;
                pointer2 = pointer2.next;
                curr = curr.next;
            } else {
                curr.next = new ListNode((pointer1.val + carry) % 10);
                carry = (pointer1.val + carry) / 10;
                pointer1 = pointer1.next;
                curr = curr.next;
            }
        }
        if(carry != 0){
            curr.next = new ListNode(1);
        }
        return ansHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(8);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(7);
        ListNode listNode = addTwoNumbers(l1, l2);
        loop(listNode);
    }
}
