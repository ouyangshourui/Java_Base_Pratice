/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

/**
 * @author yougu
 * @version : AddUseHash.java, v 0.1 2021年06月01日 2:33 下午 yougu Exp $
 */

import java.util.HashMap;

/**
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 * 例如：
 * 给出的数组为 {20, 70, 110, 150},目标值为90
 * 输出 index1=1, index2=2
 */
public class AddUseHash {
    public static int[] twoSum (int[] numbers, int target) {
        // write code here
        HashMap<Integer,Integer> map = new HashMap();
        int result[] = new int[2];
        for(int i = 0;i<numbers.length;i++){
            if(map.containsKey(target-numbers[i])){
                result[0] =map.get(target-numbers[i]);
                result[1] =i+1;
                }else{
                map.put(numbers[i],i);

            }
        }
        return null;
    }

    public static void main(String[] args) {
        int input[] = {2, 5, 4};
        System.out.println(twoSum(input,9));

    }
}