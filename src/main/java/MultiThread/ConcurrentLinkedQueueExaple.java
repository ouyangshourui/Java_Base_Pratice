package MultiThread;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueExaple {
    private  static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    private  static  Object  lock = new Object();
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Thread.currentThread().setName("inputdata");
                synchronized (lock){
                    while (queue.isEmpty()) {
                        queue.add("data");
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

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Thread.currentThread().setName("remove data");
                synchronized (lock){
                    while (!queue.isEmpty()){
                        try {
                            System.out.println(Thread.currentThread().getName()+":remove  data");
                            queue.poll();
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        });

    }
    }

