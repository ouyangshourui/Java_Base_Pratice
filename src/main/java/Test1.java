import java.util.HashMap;

import java.util.PriorityQueue;
import java.util.Scanner;
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<Character,Integer>  map = new HashMap<>();
        PriorityQueue  queue  =  new PriorityQueue<Character>((o1, o2) -> (o1.compareTo(o2)));
        while (in.hasNext()){
            StringBuffer result = new StringBuffer();
            String a = in.nextLine();
            char[]  aArray = a.toCharArray();
            for(char ch:aArray){
                map.put(ch,map.getOrDefault(ch,0)+1);
            }

            for(char key:map.keySet()){
                if(map.get(key)==1){
                   queue.add(key) ;
                }
            }

            while (!queue.isEmpty()){
                result.append(queue.poll());
            }

            System.out.println(result.toString());

        }
    }
}