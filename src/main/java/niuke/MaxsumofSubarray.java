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