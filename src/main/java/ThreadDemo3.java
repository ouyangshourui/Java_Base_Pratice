import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo3 {
    private static volatile boolean flag = true;
    private static AtomicInteger num = new AtomicInteger(1);

    public static void main(String []args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num.get() <= 100) {
                    if (!flag) {
                        System.out.println(Thread.currentThread().getName()+ num.getAndIncrement());
                        flag = true;
                    }
                }
            }
        });
        t1.setName("奇数:");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int value = num.get();
                while (num.get() <= 100) {
                    if (flag) {
                        System.out.println(Thread.currentThread().getName()+ num.getAndIncrement());
                        flag = false;
                    }
                }
            }
        });

        t2.setName("偶数:");
        t1.start();
        t2.start();
    }
}
