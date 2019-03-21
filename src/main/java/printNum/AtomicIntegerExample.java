package printNum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    private  static AtomicInteger count = new AtomicInteger(2);
    private  volatile static boolean flag = true;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count.get()<=100){
                    if(flag){
                        System.out.println("偶："+count.getAndDecrement());
                        flag=true;
                    }
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count.get()<=100){
                    if(flag){
                        System.out.println("奇数："+count.getAndIncrement());
                        count.getAndIncrement();
                        count.getAndDecrement();
                        flag=false;
                    }
                }
            }
        });


    }
}
