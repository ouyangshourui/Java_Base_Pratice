package niuke;
import java.util.*;
import java.util.PriorityQueue;
public class QuickSork {
    public static int[] MySort (int[] arr) {
        int[] result = new int[arr.length];
        PriorityQueue queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int a:arr){
            queue.add(a);
        }
        int i=0;
        while(!queue.isEmpty()){
            result[i++] = (int)queue.poll();
        }

        return result;
        // write code here
    }


    public  static  int[] QuickSorkTest(int[] arr){
       return quickSort(arr,0,arr.length-1)  ;
    }

    public static  int[] quickSort(int[] arr,int low,int high){
        if(low>high )
            return arr;
        int i= low;
        int j =high;
        int tmp = arr[low];
        while (i<j){
            while (i<j&&arr[j]>tmp){
                j--;
            }
            if(i<j){
                arr[i++] = arr[j];
            }
            while (i<j&& arr[i]<tmp){
                i++;
            }
            if(i<j){
                i--;
            }

        }
        arr[i] = tmp;
        arr =quickSort(arr,low,i-1);
        arr= quickSort(arr,i+1,high);
        return arr;
    }



    public static void main(String[] args) {
        int[]  a = {8,2,3,4};
       int[] result= MySort(a);

       for(int aa:result){
           System.out.println(aa);
       }
    }
}
