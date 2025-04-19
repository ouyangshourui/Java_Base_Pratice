# 数据结构分类
# Java数据结构分类（并发与非并发对比）

| 分类          | 接口/抽象类          | 非并发实现类                          | 并发实现类                                      | 核心特性与区别                                                                 | 典型使用场景                                                                 |
|---------------|---------------------|--------------------------------------|-----------------------------------------------|-----------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| **线性结构**  |                     |                                      |                                               |                                                                             |                                                                             |
| 数组          | -                   | `ArrayList`                          | `CopyOnWriteArrayList`<br>`Vector`（旧版）    | **非并发**：动态扩容，尾部操作高效<br>**并发**：写时复制（读多写少优化）或全同步（已废弃） | 高频读取的多线程环境<br>遗留系统兼容                                          |
| 链表          | `List`              | `LinkedList`                         | -                                             | **非并发**：双向链表，增删O(1)，随机访问慢                                    | 高频插入/删除操作<br>实现栈/队列                                              |
| 栈            | `Deque`             | `ArrayDeque`                         | `ConcurrentLinkedDeque`                       | **非并发**：数组实现，LIFO操作高效<br>**并发**：无界非阻塞，CAS保证线程安全          | 线程安全的栈操作<br>并行任务调度                                              |
| 队列          | `Queue`<br>`Deque`  | `LinkedList`<br>`ArrayDeque`         | `LinkedBlockingQueue`<br>`ArrayBlockingQueue`<br>`ConcurrentLinkedQueue`<br>`SynchronousQueue` | **阻塞队列**：生产者-消费者模型<br>**无界队列**：CAS实现高吞吐<br>**直接传递**：无容量任务交接 | 线程池任务队列<br>事件驱动架构<br>即时任务传递                                |
| **树形结构**  | `NavigableSet`<br>`NavigableMap` | `TreeSet`<br>`TreeMap`               | `ConcurrentSkipListSet`<br>`ConcurrentSkipListMap` | **非并发**：红黑树有序结构<br>**并发**：跳表+CAS，无锁读，写操作概率性成功            | 高并发有序存储<br>范围查询密集型场景                                          |
| **堆**        | `Queue`             | `PriorityQueue`                      | `PriorityBlockingQueue`                       | **非并发**：基于堆的优先级排序<br>**并发**：线程安全的阻塞优先队列                    | 多线程任务优先级调度<br>定时任务管理系统                                      |
| **哈希结构**  | `Set`<br>`Map`      | `HashSet`<br>`HashMap`               | `ConcurrentHashMap`<br>`ConcurrentSkipListMap` | **非并发**：哈希冲突链地址法<br>**并发**：分段锁(JDK7)/CAS+synchronized(JDK8+)<br>**跳表Map**：有序键的并发替代 | 高并发缓存系统<br>有序键并发存储                                            |
| **其他结构**  | -                   | `BitSet`<br>`EnumSet/EnumMap`        | `CopyOnWriteArraySet`                         | **非并发**：位向量操作<br>**枚举优化**：紧凑存储，高效枚举键<br>**并发**：写时复制，适合监听器模式 | 位标志存储<br>枚举常量映射<br>事件监听器多线程注册                            |

### 补充说明：
1. **现代并发容器**：
   - **`ConcurrentLinkedDeque`**：作为栈/队列的并发替代方案，支持无界非阻塞操作
   - **`SynchronousQueue`**：直接传递任务的特殊队列，适用于即时任务交接场景
   - **`StampedLock`关联结构**：如`ConcurrentHashMap`在JDK8+使用乐观锁优化

2. **特性增强**：
   - **跳表结构**：`ConcurrentSkipListMap/Set`提供可预测的性能（O(log n)），适合读多写少的有序场景
   - **写时复制**：`CopyOnWriteArrayList/Set`通过复制数组实现线程安全，适合读多写少的监听器列表
   - **CAS优化**：现代并发容器普遍采用CAS+volatile保证可见性，减少锁竞争

3. **典型场景扩展**：
   - **缓存系统**：`ConcurrentHashMap` + `SoftReference`实现内存敏感缓存
   - **限流器**：`ArrayBlockingQueue`固定容量控制请求速率
   - **分布式计数器**：`LongAdder`（非数据结构但属于并发工具类）替代`AtomicLong`提升吞吐量
  

# 实现线程安全的跳表与海量数据Top K频繁项

## 一、线程安全跳表实现
### 1. 跳表(Skip List)定义
跳表是一种基于多层有序链表的随机化数据结构，通过建立多级索引实现快速查找（时间复杂度O(log n)）。其核心特性：
- **多层链表结构**：底层包含所有元素，上层为索引层
- **随机层级**：插入节点时随机生成层级（通常概率p=0.5）
- **跳跃查询**：从高层索引逐层向下查找

### 2. 线程安全实现关键点
```java
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSkipList {
    private static final int MAX_LEVEL = 32;
    private final Node head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
    private final ReentrantLock[] levelLocks = new ReentrantLock[MAX_LEVEL];
    
    // 节点定义
    private static class Node {
        int key;
        Node[] next;
        ReentrantLock lock = new ReentrantLock();
        
        public Node(int key, int level) {
            this.key = key;
            this.next = new Node[level];
        }
    }

    // 插入操作（细粒度锁实现）
    public void insert(int key) {
        Node[] update = new Node[MAX_LEVEL];
        Node curr = head;
        
        // 1. 查找插入位置并记录路径
        for (int i = MAX_LEVEL-1; i >= 0; i--) {
            while (curr.next[i] != null && curr.next[i].key < key) {
                curr = curr.next[i];
            }
            update[i] = curr;
        }
        
        // 2. 生成随机层级
        int level = randomLevel();
        
        // 3. 自底向上加锁插入
        Node newNode = new Node(key, level);
        for (int i = 0; i < level; i++) {
            update[i].lock.lock();
            try {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            } finally {
                update[i].lock.unlock();
            }
        }
    }
    
    private int randomLevel() {
        int level = 1;
        while (Math.random() < 0.5 && level < MAX_LEVEL)
            level++;
        return level;
    }
}
```
### 海量数据Top K频繁项:分治+哈希+堆方案

```java
public class TopKFrequent {
    // 分治处理海量数据
    public List<String> topKFrequent(String[] data, int k) {
        // 1. 分片统计频率
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String item : data) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }
        
        // 2. 构建最小堆
        PriorityQueue<Map.Entry<String, Integer>> heap = 
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        
        // 3. 输出结果
        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll().getKey());
        }
        Collections.reverse(result);
        return result;
    }
}
```

