package niuke;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

public class LowestCommonAncestor {


   public static  int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        if(root==null) return -1;
        if(o1==root.val || o2==root.val) return root.val;
        int left = lowestCommonAncestor(root.left, o1, o2);
        int right = lowestCommonAncestor(root.right,o1,o2);
        if(left ==-1) return right;
        if(right ==-1)  return left;
        return root.val;
    }

        public static void main(String[] args) {
         TreeNode node1 = new TreeNode(1);
         TreeNode node2 = new TreeNode(2);
         TreeNode node3 = new TreeNode(3);
            TreeNode node4 = new TreeNode(4);
            TreeNode node5 = new TreeNode(5);
         node1.left = node2;
         node1.right = node3;
         node3.left = node4;
         node3.right = node5;
         System.out.println(lowestCommonAncestor(node1,4,5));

        }
}
