/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package niuke;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yougu
 * @version : MultiThread.java, v 0.1 2021年06月09日 10:38 上午 yougu Exp $
 */
public class MultiThread {
    private  static  Object lock = new Object();
    private  static  int count = 2;
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count<=100){
                    synchronized (lock){
                        System.out.println("偶数："+count);
                        count = count -1;
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
                while (count<=100){
                    synchronized (lock){
                        System.out.println("奇数："+count);
                        count = count +3;
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


      /*  Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.hashCode());
            }
        });*/

    }
}