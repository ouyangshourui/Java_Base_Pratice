```java
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;


import java.util.ArrayList;

/**
 * @author yougu
 * @version : TreeNode.java, v 0.1 2021年06月17日 5:30 下午 yougu Exp $
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    public static void preOrder(TreeNode node, ArrayList list){
        if(node ==null) return;
        list.add(node.val);
        preOrder(node.left,list);
        preOrder(node.right,list);
    }

    public static void postOrder(TreeNode node, ArrayList list){
        if(node ==null) return;
        preOrder(node.left,list);
        preOrder(node.right,list);
        list.add(node.val);
    }

    public static void inOrder(TreeNode node, ArrayList list){
        if(node ==null) return;
        preOrder(node.left,list);
        list.add(node.val);
        preOrder(node.right,list);
    }

    public static void main(String[] args) {
        TreeNode  node1= new TreeNode(1);
        TreeNode  node2= new TreeNode(2);
        TreeNode  node3= new TreeNode(3);
        TreeNode  node4= new TreeNode(4);
        TreeNode  node5= new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        ArrayList<Integer> list = new ArrayList<>();
        preOrder(node1,list);
        for(int value:list){
            System.out.print(value);
        }
        System.out.println("********");
        ArrayList<Integer> list1 = new ArrayList<>();
        inOrder(node1,list1);
        for(int value:list1){
            System.out.print(value);
        }

        System.out.println("********");
        ArrayList<Integer> list2 = new ArrayList<>();
        postOrder(node1,list2);
        for(int value:list2){
            System.out.print(value);
        }

    }
}

```
