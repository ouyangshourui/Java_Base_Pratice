//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.Stack;
//
//public class Test {
//    public boolean IsPopOrder(int [] pushA,int [] popA)  throws Exception {
//        Boolean flag=true;
//        int pushAlength=pushA.length;
//        int popAlength=popA.length;
//        if(pushAlength==0){
//            return false;
//
//        }
//        if(popAlength==0){
//            return false;
//        }
//
//        if(pushAlength!=popAlength){
//            return false;
//        }
//
//        for(int i=0;i<pushAlength;i++){
//            if(pushA[0]!=popA[popAlength-i-1]){
//                flag=false;
//            }
//        }
//        return flag;
//
//    }
//
//    private static class People {
//        String name;
//        int age;
//        public People(String name, int age){
//            this.name = name;
//            this.age = age;
//        }
//        public String toString() {
//            return "姓名："+name + " 年龄：" + age;
//        }
//    }
//
//    public class ListNode {
//        int val;
//        ListNode next = null;
//
//                ListNode(int val) {
//            this.val = val;
//        }
//    }
//
//    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        ArrayList<Integer> arrayList = new ArrayList<>();
//
//        Stack<Integer> stack= new Stack<>();
//        while(listNode!=null){
//            stack.push(listNode.val);
//            listNode=listNode.next;
//        }
//
//        while (!stack.empty())
//            arrayList.add(stack.peek());
//
//        return arrayList;
//
//    }
//
//
//
//    public static void main(String[] args) {
//        int[][] array= {{1,2,3,4},{2,3,4,5},{3,4,5,6}};
//
//
//                PriorityQueue<People> queue = new PriorityQueue<People>(11,
//                        new Comparator<People>() {
//                            public int compare(People p1, People p2) {
//                                return p2.age - p1.age;
//                            }
//                        });
//
//                for (int i = 1; i <= 10; i++) {
//                    queue.add(new People("张"+ i, (new Random().nextInt(100))));
//                }
//                while (!queue.isEmpty()) {
//                    System.out.println(queue.poll().toString());
//                }
//            }
//        }
//
//
//
