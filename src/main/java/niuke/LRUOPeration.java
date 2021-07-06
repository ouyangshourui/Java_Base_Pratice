package niuke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUOPeration {
    class LRUcache{
        class Node{
            int key,value;
            Node pre,next;
            public Node(int key,int value){
                this.key=key;
                this.value = value;
            }
        }

        Map<Integer,Node> map = new HashMap<>();
        Node head = new Node(-1,-1);
        Node tail = new Node(-1,-1);
        int capicity;
        int size;

        public LRUcache(int capicity) {
            this.capicity = capicity;
            head.next = tail ;
            tail.pre = head;
            tail.next = null;
        }

        public int get(int key){
            Node node = map.get(key);
            if(node ==null) return -1;
            moveToHead(node);
            return node.value;
        }


        public  void put(int key,int value){
            Node node = map.get(key);
            if(node ==null){
                Node iNode=new Node(key,value);
                if(size< capicity){
                    insertNode(node);
                }else{
                    deleteNode(node);
                    insertNode(node);
                }


            }else{
                node.value = value;
                moveToHead(node);
            }
        }

        public void moveToHead(Node node){
            deleteNode(node);

            insertNode(node);
        }

        public void deleteNode(Node node){
            map.get(node.key);
            node.pre.next = node.next;
            node.next.pre= node.pre;
            size--;
        }

        public void insertNode(Node node){

            map.put(node.key,node);
            node.next = head.next;
            head.next.pre=node;
            head.next = node;
            node.pre = head;
            size++;

        }

    public int[] LRU (int[][] operators, int k) {
        LRUcache lrUcache = new LRUcache(k);
        List<Integer> list = new ArrayList<>();
        for(int i =0;i<operators.length;i++){
            if(operators[i][0]==1){
                lrUcache.put(operators[i][1],operators[i][2]);
            }else{
               list.add(lrUcache.get(operators[i][1]));
            }
        }
        int[] res = new int[list.size()];
        for(int i =0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }

}


}

