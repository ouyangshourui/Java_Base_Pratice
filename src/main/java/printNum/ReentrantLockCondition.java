package printNum;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class ReentrantLockCondition {
    private  static  int count=2;
    private static  final ReentrantLock lock = new ReentrantLock();
    private  static final  Condition condition1= lock.newCondition();
    private  static final  Condition condition2= lock.newCondition();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count<=100){
                    try {
                        lock.lock();
                        System.out.println("偶："+count--);
                        condition1.await();
                        condition2.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count<=100){
                    try {
                        lock.lock();
                        System.out.println("奇："+count);
                        count=count+3;
                        condition1.signal();
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        executorService.shutdown();

    }
}





