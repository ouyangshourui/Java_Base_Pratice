/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package MultiThread.Daemon;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yougu
 * @version : Mytest.java, v 0.1 2021年06月09日 3:13 下午 yougu Exp $
 */
public class Mytest {

   static class   myRunnableTread implements Runnable{
        private int ticket=10;
        @Override
        public void run() {
            for(int i =0 ;i<20;i++){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new myRunnableTread());
        executorService.execute(new myRunnableTread());
        executorService.shutdown();

    }

}