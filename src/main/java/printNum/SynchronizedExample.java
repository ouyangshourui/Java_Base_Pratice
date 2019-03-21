package printNum;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class SynchronizedExample {
    private static int count = 2;
    private static Object lock = new Object();
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count <= 100) {
                    synchronized (lock){
                        System.out.println("偶: " + count);
                        //count++;
                        count--;
                        lock.notifyAll();
                        if(count<=100){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });


        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count <= 100) {
                    synchronized (lock){
                        System.out.println("奇: " + count);
                        count=count+3;
                        lock.notifyAll();
                        if(count<=100){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });



    }
}
