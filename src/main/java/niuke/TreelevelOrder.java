/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.ArrayList;

/**
 * @author yougu
 * @version : TreelevelOrder.java, v 0.1 2021年06月03日 5:05 下午 yougu Exp $
 */
public class TreelevelOrder {
    class TreeNode {
        int val = 0;
        TreeNode left;
        TreeNode right;
    }
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root==null){
            return result;
        }
        DFS(root,result,0);
        return result;

    }

    public void DFS(TreeNode root,ArrayList<ArrayList<Integer>> list,int depth){
        if(root ==null)
            return ;
        if(list.size()==depth)
            list.add(new ArrayList<>());
        DFS(root.left,list,depth+1);
        list.get(depth).add(root.val);
        DFS(root.right,list,depth+1);
    }

}