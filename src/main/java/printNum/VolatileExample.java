package printNum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileExample {
    private  volatile  static  int count=2;
    private  volatile  static boolean flag = true;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(count<=100){
                    if(flag){
                        System.out.println("偶数："+count);
                        count=count-1;
                    }
                    flag=false;
                }

            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(count<=100){
                    if(!flag){
                        System.out.println("奇数："+count);
                        count=count+1;
                    }
                    flag=true;
                }

            }
        });

    }


}
