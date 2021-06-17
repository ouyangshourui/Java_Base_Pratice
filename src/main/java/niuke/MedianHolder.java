/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yougu
 * @version : MedianHolder.java, v 0.1 2021年06月15日 2:32 下午 yougu Exp $
 */
public class MedianHolder {
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });

    static PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    });



    public double[] flowmedian (int[][] operations) {
        // write code here
        int rows =  operations.length;
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0;i<rows;i++){
            if(operations[i][0]==1){
                int num = operations[i][1];
                addToHeap(num);
            }else{
                double res = getMedianFromHeap();
                result.add(res);
            }

        }
        return  result.stream().mapToDouble(x->(Double)x).toArray();

    }

    public static void addToHeap(int num){
        if(maxHeap.isEmpty()){
            maxHeap.add(num);
            return;
        }
        if(maxHeap.peek()>num){
            maxHeap.add(num);
        } else {
            if(minHeap.isEmpty()){
                minHeap.add(num);
                return;
            }
            if(minHeap.peek()>num){
               maxHeap.add(num);
            }else{
                minHeap.add(num);
            }
        }
        modifyTwoHeapsSize();
    }
    private  static void modifyTwoHeapsSize() {
      while (maxHeap.size()-minHeap.size()>=2){
         minHeap.add(maxHeap.poll()) ;
      }
        while (minHeap.size()-maxHeap.size()>=2){
            maxHeap.add(minHeap.poll()) ;
        }
    }

    public  static  double getMedianFromHeap(){
        int maxheapSize = maxHeap.size();
        int minheapSize = minHeap.size();
        if(maxheapSize+minheapSize==0)
                return -1;
        Integer maxHeapHead = maxHeap.peek();
        Integer minHeapHead = minHeap.peek();
        if (((maxheapSize + minheapSize) & 1) == 0) {
            return (maxHeapHead + minHeapHead) / 2.0;
        }
        return maxheapSize > minheapSize ? maxHeapHead : minHeapHead;
    }

    public static void main(String[] args) {
        maxHeap.add(3);
        maxHeap.add(100);
        maxHeap.add(200);
        minHeap.add(3);
        minHeap.add(100);
        minHeap.add(200);
        while (!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
        }
        while (!minHeap.isEmpty()){
            System.out.println(minHeap.poll());
        }
    }
}