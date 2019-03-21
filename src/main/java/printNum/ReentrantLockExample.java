    package printNum;

    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.locks.ReentrantLock;

    public class ReentrantLockExample {
        //检查是否打印
        private static int count = 2;
        private static volatile boolean flag = false;
        private static  final ReentrantLock lock = new ReentrantLock();
        public static void main(String[] args) {
            ExecutorService executorService = Executors.newCachedThreadPool();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (count<=100){
                            lock.lock();
                            System.out.println("偶："+count);
                            count--;
                            lock.unlock();

                        }

                }
            });

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (count<=100) {

                        lock.lock();
                        System.out.println("奇：" + count);
                        count = count + 3;
                        lock.unlock();
                    }
                }
            });

        }
    }
