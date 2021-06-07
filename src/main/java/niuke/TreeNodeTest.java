/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.ArrayList;

/**
 * @author yougu
 * @version : TreeNode.java, v 0.1 2021年06月03日 4:55 下午 yougu Exp $
 */
public class TreeNodeTest {
 class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
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

}