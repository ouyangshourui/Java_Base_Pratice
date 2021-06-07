/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

/**
 * @author yougu
 * @version : MergeSortedListNode.java, v 0.1 2021年06月03日 6:13 下午 yougu Exp $
 */
public class MergeSortedListNode {
    public class ListNode {
        int val;
        ListNode next = null;
      }

    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        // write code here
        ListNode node =null;
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;

        if(l1.val<l2.val){
            node = l1;
            node.next = mergeTwoLists(l1.next,l2);
        }else{
            node = l2;
            node.next = mergeTwoLists(l1,l2.next);
        }
        return node;
    }
}
