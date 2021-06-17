/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package MultiThread;

/**
 * @author yougu
 * @version : RunnableTicket.java, v 0.1 2021年06月09日 4:26 下午 yougu Exp $
 */
class MyThread implements Runnable{
    private int ticket=10;
    public void run(){
        for(int i=0;i<20;i++){
            if(this.ticket>0){
                System.out.println(Thread.currentThread().getName()+"卖票：ticket"+this.ticket--);
            }
        }
    }
}
public class RunnableTicket {
    public static void main(String[] args) {
        MyThread mt=new MyThread();
        new Thread(mt).start();//同一个mt，但是在Thread中就不可以，如果用同一
        new Thread(mt).start();//个实例化对象mt，就会出现异常
        new Thread(mt).start();
    }
};