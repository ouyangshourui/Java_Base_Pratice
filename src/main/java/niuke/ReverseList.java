package niuke;


public class ReverseList {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode reverseList(ListNode head){
        if(head==null|| head.next==null)
            return head;
        ListNode pre = null;
        ListNode next = null;
        while (head!=null){
            next = head.next;
            head.next=pre;
            pre =head;
            head = next;
        }
        return  pre;


    }

    public static void main(String[] args) {

    }
}
