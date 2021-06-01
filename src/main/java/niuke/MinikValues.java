/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yougu
 * @version : MinikValues.java, v 0.1 2021年06月01日 12:16 下午 yougu Exp $
 */
public class MinikValues {
    public static   ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input==null || input.length<k || k<=0)
            return list;

        PriorityQueue<Integer> heap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int i=0; i<input.length; i++){
            if(heap.size()<k){
                heap.offer(input[i]);
            }else if(heap.peek()>input[i]){
                heap.remove();
                heap.add(input[i]);
            }
        }
        for(int i: heap)
            list.add(i);
        return list;
    }

    public static void main(String[] args) {
        int input[] = {2, 5, -2, 6, -3, 8, 0, -7, -9, 4,-18};
        GetLeastNumbers_Solution(input,4).forEach(k->{
            System.out.println(k);
        });

    }
}