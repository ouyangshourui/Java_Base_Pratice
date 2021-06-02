/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

/**
 * @author yougu
 * @version : ArraySort.java, v 0.1 2021年06月01日 2:57 下午 yougu Exp $
 */

/**
 * [5,2,3,1,4]
 * [1,2,3,4,5]
 * 数组的长度不大于100000，数组中每个数的绝对值不超过10^910
 * 9
 */
public class ArraySort {
    public static void bubbleSort(int[] numbers){
        int temp = 0;
        int size = numbers.length;
        for(int i = 0;i < size;i++){
            for(int j = 0;i<size-j-1;j++){
                if(numbers[j]>numbers[j+1]){
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }

    }

    public static void main(String[] args) {
        int input[] = {2, 5, -2, 6, -3, 8, 0, -7, -9, 4,-18};
        for(int i :input){
            System.out.println(i);
        }
    }

}