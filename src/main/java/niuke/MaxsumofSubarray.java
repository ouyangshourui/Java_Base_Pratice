/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

/**
 * @author yougu
 * @version : MaxsumofSubarray.java, v 0.1 2021年06月04日 5:06 下午 yougu Exp $
 */
public class MaxsumofSubarray {
public int maxsumofSubarray (int[] arr) {
        int prev=0,res = 0;
        for(int num:arr){
            prev =Math.max(prev+num,prev);
            res  = Math.max(prev,res);
        }
        return res;
    }

    /**
     * 动态规划：dp[0]=0
     *         dp[i-1] >0 -> dp[i] = dp[i]+arr[i]
     *         dp[i-1] <0  -> dp[i] = arr[i]
     * @param arr
     * @return
     */
    public int maxsumofSubarray1 (int[] arr) {
        if(arr.length==0) return 0;
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max= dp[0];
        for(int i = 0;i <arr.length;i++){
            if(dp[i-1]>0){
                dp[i] = dp[i]+arr[i];
            }
            else
                dp[i] = arr[i];
            max = Math.max(dp[i],max);
        }
        return max;

    }

    public  static  int maxsumofSubarray11 (int[] arr) {
        // write code here
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int res = arr[0];
        for(int i =1;i<arr.length;i++){
            dp[i] = dp[i-1]>0?(dp[i-1]+arr[i]):arr[i];
            res = Math.max(res,dp[i]);
        }
        return res;

    }

    public static String solve(String str) {
        if(str ==null)
            return str;
        int left = 0;
        int right = str.length()-1;
        char[] newStr = new char[str.length()];
        while (left<=right){
            newStr[left] =str.charAt(right);
            newStr[right] =str.charAt(left);
            left++;
            right--;
        }
       return String.valueOf(newStr);
        // write code here
    }

    public static void main(String[] args) {
        String myString = "myString";
        System.out.println(solve(myString));
    }
}