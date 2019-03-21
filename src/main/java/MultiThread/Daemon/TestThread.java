package MultiThread.Daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {
    public static void main(String[] args) {
        final TestSync testSync = new TestSync();
         ExecutorService executorService= Executors.newCachedThreadPool();
         executorService.execute(new Runnable() {
            @Override
            public void run() {

                testSync.run1();
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                testSync.run2();
            }
        });
    }
}
