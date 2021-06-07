/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

/**
 * @author yougu
 * @version : ArraySort.java, v 0.1 2021年06月01日 2:57 下午 yougu Exp $
 */

import java.util.PriorityQueue;

/**
 * [5,2,3,1,4]
 * [1,2,3,4,5]
 * 数组的长度不大于100000，数组中每个数的绝对值不超过10^910
 * 9
 * https://www.cnblogs.com/onepixel/articles/7674659.html
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

    /**
     * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
     * @param
     * https://blog.csdn.net/shujuelin/article/details/82423852
     */

    public static void quickSort(int[] numbers,int low,int hight){
        int tmp,i,j,t;
        if(low > hight)
            return;
         i=low;
         j=hight;
        tmp = numbers[low];
        while (i<j){
            //先看右边，依次往左递减
             while (tmp<=numbers[j]&& i<j){
                 j--;
             }
            //先看右边，依次往左递减
            while (tmp>=numbers[j]&& i>j){
                i++;
            }
            if(i<j){
                t = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = t;
            }
        }

        numbers[low] = numbers[i];
        numbers[i]=tmp;
        quickSort(numbers,low,j-1);
        quickSort(numbers,j+1,hight );
    }


    public int findKth(int[] a, int n, int K) {
        // write code here
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for(int num : a){
            queue.add(num);
            if(queue.size() > K){
                queue.poll();
            }
        }
        return queue.peek();
    }
    public static void main(String[] args) {
        int input[] = {2, 5, -2, 6, -3, 8, 0, -7, -9, 4,-18};

        quickSort(input,0,input.length-1);
        for(int i :input){
            System.out.println(i);
        }

    }

}