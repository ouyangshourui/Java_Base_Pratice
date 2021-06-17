/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 * @author yougu
 * @version : MatriMin.java, v 0.1 2021年06月08日 2:49 下午 yougu Exp $
 */
public class MatriMin {
    /**
     *
     * @param matrix int整型二维数组 the matrix
     * @return int整型
     */
    public static int minPathSum (int[][] matrix) {
        // write code here
        if(matrix == null){
            return 0;
        }
        int  n = matrix.length;
        int  m = matrix[0].length;
        int   [][] dp = new int[n+1][m+1];
        for(int i = 1;i<=n ;i++){
            for(int j = 1;j <= m;j++){
                if(i==1||j == 1){
                  dp[i][j] = dp[i-1][j]>dp[i][j-1]? matrix[i-1][j-1] + dp[i-1][j] :matrix[i-1][j-1] + dp[i][j-1];
                }
                else {
                    dp[i][j] = dp[i-1][j]<dp[i][j-1]? matrix[i-1][j-1] + dp[i-1][j] :matrix[i-1][j-1] + dp[i][j-1];
                }


            }
        }

      return  dp[n][m];
    }

    public   int minmumNumberOfHost(int n,int[][] startEnd){
        Arrays.sort(startEnd, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                if(arr1[0] == arr2[0]){
                    return arr1[1] - arr2[1];
                }
                return  arr1[0]-arr2[0];
            }
        });

        int min = Integer.MIN_VALUE;
        PriorityQueue<Integer>  queue = new PriorityQueue<Integer>();
        queue.add(min);
        for(int i = 0;i<0;i++){
            if(queue.peek()<=startEnd[i][0]){
                queue.poll();
            }
              queue.offer(startEnd[i][1]);

        }
        return queue.size();
    }

    public static void main(String[] args) {
        int[][]  matrix = {{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};

        System.out.println(minPathSum(matrix));
    }
}