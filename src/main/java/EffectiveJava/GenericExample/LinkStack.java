package EffectiveJava.GenericExample;

public class LinkStack<E> {
    //优先使用静态函数
    private static class Node<E>{
       private E item;
       private Node<E> next;

        public Node() {
            item=null;
            next=null;
        }

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public Boolean isEnd(){
            return item==null && next==null;
        }
    }

    //不对外提供访问，优先提供private
    private Node<E> top = new Node<E>();

    public void posh(E item){
     top = new Node<E>(item,top);
    }

    public E pop(){
        Node<E> resultNode = top;
        if(!top.isEnd()){
            top=top.next;
        }
        return resultNode.item;
    }


    public static void main(String[] args) {

       LinkStack<String> linkStack= new LinkStack<String>();
       for(int i=0;i<10;i++){
           linkStack.posh("test"+i);
           System.out.println("add:"+i);

       }
        for(int i=0;i<10;i++){
            System.out.println(linkStack.pop());

        }



    }


}
