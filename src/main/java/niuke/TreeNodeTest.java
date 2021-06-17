
package niuke;

import java.util.ArrayList;
/**
public class TreeNodeTest {
 class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
     TreeNode next = null;
      }

    ArrayList<Integer> pre=new ArrayList<>();
    ArrayList<Integer> mid=new ArrayList<>();
    ArrayList<Integer> pos=new ArrayList<>();
    public int[][] threeOrders (TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        preOrder(root);
        midOrder(root);
        posOrder(root);
        list.add(pre);
        list.add(mid);
        list.add(pos);
        int[][] arr=new int[list.size()][list.get(0).size()];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                arr[i][j]=list.get(i).get(j);
            }
        }
        return arr;
    }
    private void preOrder(TreeNode root){
        if(root==null){
            return;
        }
        pre.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
    private void midOrder(TreeNode root){
        if(root==null){
            return;
        }
        midOrder(root.left);
        mid.add(root.val);
        midOrder(root.right);
    }
    private void posOrder(TreeNode root){
        if(root==null){
            return;
        }
        posOrder(root.left);
        posOrder(root.right);
        pos.add(root.val);
    }

    TreeNode invertTree(TreeNode root){
        if(root == null){
            return  null;
        }

        TreeNode tmp = root.left;
        root.left = root.left;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
    }
    TreeNode connectNode(TreeNode root){
        if(root==null) return null;
        connectNode(root.left,root.right) ;
        return root;
    }

    public void connectTwoNode(TreeNode node1,TreeNode node2){
        if(node1 ==null || node2 ==null){
            return ;
        }
         node1.next= node2;
        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }

    public boolean isValidBST(TreeNode root){

        return isValidBST( root,null,null);
    }

    public boolean isValidBST(TreeNode root,TreeNode min,TreeNode max){
        if(root ==null) return true;
        if(min!=null && root.val <=min.val)
            return false;
        if(max!=null && root.val <=max.val)
            return false;
        return isValidBST( root.left,min,root)|| isValidBST(root.right, min, max);
    }


}
**/