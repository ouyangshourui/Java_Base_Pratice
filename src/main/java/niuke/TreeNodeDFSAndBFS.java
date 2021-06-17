/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yougu
 * @version : TreeNodeDFSAndBFS.java, v 0.1 2021年06月17日 5:17 下午 yougu Exp $
 * https://blog.csdn.net/qq_37638061/article/details/89598413
 */

public class TreeNodeDFSAndBFS {

    public static List<Integer> BFSByQueue(TreeNode root) {
        if(root==null)
            return null;
        Queue queue = new LinkedList();
        queue.add(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode treeNode = (TreeNode) queue.poll();
            result.add(treeNode.val);
            if(treeNode.left!=null)
                queue.add(treeNode.left);
            if(treeNode.right!=null)
                queue.add(treeNode.right);
        }

        return  result;
    }

    public static List<Integer> DFSByStack(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();

            /*
            处理 TreeNode 节点 的逻辑
             */
            result.add(treeNode.val);

            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }

        }
        return result;
    }



    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode12 = new TreeNode(12);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode4.left = treeNode8;
        treeNode5.left = treeNode9;
        treeNode6.left = treeNode10;
        treeNode7.left = treeNode11;
        treeNode7.right = treeNode12;

        List<Integer> result = BFSByQueue(treeNode);

        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.print("\n");

        List<Integer> result1 = DFSByStack(treeNode);
        Iterator<Integer> iterator1 = result1.iterator();
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }


    }
}