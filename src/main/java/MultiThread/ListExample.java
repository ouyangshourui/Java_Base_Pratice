package MultiThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListExample {
    private  static ArrayList<String>  list = new ArrayList<>();
    private  static  Object  lock = new Object();
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Thread.currentThread().setName("inputdata");
                synchronized (lock){
                    while (list.size()==0) {
                        list.add("data");
                        System.out.println(Thread.currentThread().getName()+":input data");
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                //Thread.currentThread().setName("remove data");
//                synchronized (lock){
//                    while (list.size()==1){
//                        try {
//                            System.out.println(Thread.currentThread().getName()+":remove  data");
//                            list.remove(0);
//                            lock.notifyAll();
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//
//            }
//        });

        executorService.execute(() -> {
            synchronized (lock){
                while (list.size()==1){
                    try {
                        System.out.println(Thread.currentThread().getName()+":remove  data");
                        list.remove(0);
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        });

    }

}
