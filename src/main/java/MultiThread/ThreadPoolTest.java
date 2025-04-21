package MultiThread;
import java.util.concurrent.*;

/*
 * 队列类型使用场景说明：
 * 1. ArrayBlockingQueue（固定容量队列）：
 *    - 适用于需要严格控制队列大小的场景（如生产者-消费者模型）。
 *    - 避免内存溢出，适合任务量可预测的场景。
 *
 * 2. LinkedBlockingQueue（链表结构队列）：
 *    - 有界模式：与 ArrayBlockingQueue 类似，但支持动态扩容。
 *    - 无界模式：适合需要无限缓冲的场景（如任务处理速度远低于生产速度）。
 *
 * 3. SynchronousQueue（无缓冲队列）：
 *    - 强制生产者与消费者直接协作（生产者必须等待消费者线程可用）。
 *    - 适用于高吞吐场景或需要快速扩展线程池的场景。
 *
 * 4. PriorityBlockingQueue（优先级队列）：
 *    - 任务按优先级排序，优先执行高优先级任务。
 *    - 适用于需要紧急任务优先处理的场景（如任务调度系统）。
 */

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 测试所有拒绝策略与不同队列的组合
        testWithArrayBlockingQueue();
        testWithLinkedBlockingQueue();
        testWithSynchronousQueue();
        testWithPriorityBlockingQueue();
    }

    // --- 1. ArrayBlockingQueue 测试 ---
    private static void testWithArrayBlockingQueue() {
        // 使用固定容量队列，触发经典拒绝场景
        testQueueStrategy(
            new ArrayBlockingQueue<>(2),
            "ArrayBlockingQueue（容量2）",
            2, 3, 60, TimeUnit.SECONDS
        );
    }

    // --- 2. LinkedBlockingQueue 测试 ---
    private static void testWithLinkedBlockingQueue() {
        // 有界队列（容量2）与无界队列对比测试
        testQueueStrategy(
            new LinkedBlockingQueue<>(2),
            "LinkedBlockingQueue（容量2）",
            2, 3, 60, TimeUnit.SECONDS
        );

        // 无界队列测试（需通过线程数限制触发拒绝）
        testQueueStrategy(
            new LinkedBlockingQueue<>(),
            "LinkedBlockingQueue（无界）",
            2, 3, 60, TimeUnit.SECONDS
        );
    }

    // --- 3. SynchronousQueue 测试 ---
    private static void testWithSynchronousQueue() {
        // 无缓冲队列，强制立即执行或创建线程
        testQueueStrategy(
            new SynchronousQueue<>(),
            "SynchronousQueue",
            2, 3, 60, TimeUnit.SECONDS
        );
    }

    // --- 4. PriorityBlockingQueue 测试 ---
    private static void testWithPriorityBlockingQueue() {
        // 需要任务实现 Comparable 接口
        testQueueStrategy(
            new PriorityBlockingQueue<>(),
            "PriorityBlockingQueue（优先级队列）",
            2, 3, 60, TimeUnit.SECONDS
        );
    }

    // --- 核心测试方法（参数化队列和策略）---
    private static void testQueueStrategy(
        BlockingQueue<Runnable> workQueue,
        String queueType,
        int corePoolSize,
        int maxPoolSize,
        long keepAliveTime,
        TimeUnit unit
    ) {
        // 遍历所有4种拒绝策略
        for (RejectedExecutionHandler handler : getHandlers()) {
            String strategyName = getHandlerName(handler);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime, unit,
                workQueue, handler
            );

            System.out.println("\n\n--- 测试配置 ---");
            System.out.printf("队列类型：%s%n", queueType);
            System.out.printf("拒绝策略：%s%n", strategyName);
            System.out.printf("线程池参数：core=%d, max=%d%n", corePoolSize, maxPoolSize);

            // 提交任务数量根据队列特性调整
            int taskCount = getTaskCount(workQueue);
            for (int i = 0; i < taskCount; i++) {
                int taskNum = i;
                try {
                    // 根据队列类型选择任务类型
                    Runnable task = createTask(taskNum, workQueue);
                    executor.execute(task);
                    System.out.println("任务 " + taskNum + " 已提交");
                } catch (RejectedExecutionException e) {
                    System.out.println("❌ 任务 " + taskNum + " 被拒绝！" + e.getMessage());
                }
            }

            // 等待执行并关闭
            executor.shutdown();
            try {
                executor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // --- 辅助方法 ---
    private static RejectedExecutionHandler[] getHandlers() {
        return new RejectedExecutionHandler[]{
            new ThreadPoolExecutor.AbortPolicy(),
            new ThreadPoolExecutor.CallerRunsPolicy(),
            new ThreadPoolExecutor.DiscardPolicy(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
        };
    }

    private static String getHandlerName(RejectedExecutionHandler handler) {
        return handler.getClass().getSimpleName().replace("Policy", "");
    }

    private static int getTaskCount(BlockingQueue<Runnable> queue) {
        if (queue instanceof SynchronousQueue) return 10; // 需更多任务触发拒绝
        if (queue instanceof DelayQueue) return 8;       // 延迟后触发
        return 6;                                       // 默认测试数量
    }

    private static Runnable createTask(int taskNum, BlockingQueue<Runnable> queue) {
        if (queue instanceof PriorityBlockingQueue) {
            return new PrioritizedTask(taskNum); // 实现 Comparable
        } else if (queue instanceof DelayQueue) {
            return new DelayedTask(taskNum, 2000); // 延迟2秒执行
        } else {
            return () -> {
                try {
                    System.out.println("任务 " + taskNum + " 执行中，线程：" + Thread.currentThread().getName());
                    Thread.sleep(1000); // 模拟任务耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
        }
    }

    // --- 特殊任务类 ---
    static class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
        private final int priority;
        private final int taskNum;

        PrioritizedTask(int taskNum) {
            this.taskNum = taskNum;
            this.priority = taskNum % 2 == 0 ? 1 : 5; // 偶数优先级高
        }

        @Override
        public void run() {
            try {
                System.out.println("优先级任务 " + taskNum + " 执行中（优先级：" + priority + "）");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public int compareTo(PrioritizedTask other) {
            return Integer.compare(other.priority, this.priority); // 小值优先
        }
    }

    static class DelayedTask implements Runnable, Delayed {
        private final long triggerTime;
        private final int taskNum;

        DelayedTask(int taskNum, long delay) {
            this.taskNum = taskNum;
            this.triggerTime = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long delay = triggerTime - System.currentTimeMillis();
            return unit.convert(delay, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.triggerTime, ((DelayedTask)o).triggerTime);
        }

        @Override
        public void run() {
            System.out.println("延迟任务 " + taskNum + " 已到期执行");
        }
    }
}
