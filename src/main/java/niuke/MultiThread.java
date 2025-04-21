package niuke;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @author: ourui
 * @date: 2021/1/12
 * @description:
 * # 队列类型对比总结
 *
 * | 队列类型               | 适用场景                          | 优点                          | 缺点                          |
 * |------------------------|-----------------------------------|-------------------------------|-------------------------------|
 * | **ArrayBlockingQueue** | 固定容量 + FIFO                  | 性能稳定，容量可控            | 初始化时必须指定容量          |
 * | **LinkedBlockingQueue**| 动态容量 + FIFO                  | 容量灵活（可无界）            | 无界时可能内存溢出            |
 * | **SynchronousQueue**   | 直接线程协作                    | 零中间存储开销                | 需动态线程数或拒绝策略        |
 * | **PriorityBlockingQueue** | 优先级任务处理                | 支持优先级排序                | 无界时风险高                  |
 *
 * ---
 *
 * ## 根据你的需求
 * **ArrayBlockingQueue 是最合适的替代方案**，因其固定容量和 FIFO 特性与你的场景完全匹配：
 * - **你的场景**：需要固定容量（100）且任务需按提交顺序执行。
 * - **优势**：容量可控，避免内存溢出，性能稳定。*
 * *
 */
public class MultiThread {
    private static final Object lock = new Object();
    private static int count = 2;

    public static void main(String[] args) {
        // 自定义线程池（2 核心线程，队列大小 100）
        ExecutorService executor = new ThreadPoolExecutor(
            2, 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100), // 限制队列大小
            new ThreadPoolExecutor.CallerRunsPolicy()
        );

        // 提交任务
        executor.submit(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    while (count % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("偶数：" + count);
                    count++;
                    lock.notifyAll();
                }
            }
        });

        executor.submit(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    while (count % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("奇数：" + count);
                    count++;
                    lock.notifyAll();
                }
            }
        });

        // 关闭线程池
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
