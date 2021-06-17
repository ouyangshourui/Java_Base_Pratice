/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.Stack;

/**
 * @author yougu
 * @version : AddTwoList.java, v 0.1 2021年06月07日 11:07 上午 yougu Exp $
 */
public class AddTwoList {
    class ListNode {
        int                   val   = 0;
        ListNode next  = null;
    }
    public ListNode addInList (ListNode head1, ListNode head2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p1!=null){
            stack1.push(p1);
            p1=p1.next;
        }
        while (p2!=null){
            stack2.push(p1);
            p2=p2.next;
        }
        int plus = 0;
        int number = 0;
        ListNode resultNode = new ListNode();
        ListNode tmpNode = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            int left = stack1.isEmpty() ?0:stack1.pop().val;
            int right = stack2.isEmpty() ?0:stack2.pop().val;
            if(left+right+plus>10){
                number = (left+right+plus)/10;
                plus = 1;
            }else {
                number = (left+right+plus);
                plus = 0;
            }
            if(resultNode.next==null){
                tmpNode.val = number;
                tmpNode.next = null;
                resultNode = tmpNode;
            }else {
                tmpNode.val = number;
                tmpNode.next = null;
                resultNode.next = tmpNode;
            }

        }
        if(plus == 1){
            tmpNode.val = 1;
            tmpNode.next = null;
            resultNode.next = tmpNode;
        }
        return  resultNode;
    }
}