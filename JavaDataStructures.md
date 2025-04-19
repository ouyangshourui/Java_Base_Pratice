# 数据结构分类
# Java数据结构分类
# Java数据结构分类（并发与非并发对比）

| 分类          | 接口/抽象类                  | 非并发实现类                                | 并发实现类                                      | 核心特性与区别                                                                 | 典型使用场景                                                                 |
|---------------|-----------------------------|-------------------------------------------|-----------------------------------------------|-----------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| **线性结构**  |                             |                                           |                                               |                                                                             |                                                                             |
| 数组          | -                           | `ArrayList`                               | `CopyOnWriteArrayList`<br>`Vector`（旧版）    | **非并发**：动态扩容，尾部操作高效<br>**并发**：写时复制（读多写少优化）或全同步（已废弃）                     | 高频读取的多线程环境<br>旧系统兼容                                            |
| 链表          | `List`                      | `LinkedList`                              | -                                             | **非并发**：双向链表，增删O(1)，随机访问慢                                                                 | 高频插入/删除操作<br>实现栈/队列                                              |
| 栈            | `Deque`                     | `ArrayDeque`                              | `ConcurrentLinkedDeque`                       | **非并发**：数组实现，LIFO操作高效<br>**并发**：无界非阻塞，CAS保证线程安全                                  | 线程安全的栈操作<br>并行任务调度                                              |
| 队列          | `Queue`<br>`Deque`          | `LinkedList`<br>`ArrayDeque`              | `LinkedBlockingQueue`<br>`ArrayBlockingQueue`<br>`ConcurrentLinkedQueue`<br>`SynchronousQueue` | **非并发**：FIFO或双端操作<br>**并发**：阻塞队列（生产者-消费者）、CAS高吞吐队列、无容量直接传递队列         | 线程池任务队列<br>事件驱动架构<br>即时任务交接                                 |
| **树形结构**  | `NavigableSet`<br>`NavigableMap` | `TreeSet`<br>`TreeMap`                    | `ConcurrentSkipListSet`<br>`ConcurrentSkipListMap` | **非并发**：红黑树有序结构<br>**并发**：跳表+CAS，无锁读，写操作概率性成功                                   | 高并发有序存储<br>范围查询密集型场景                                          |
| **堆**        | `Queue`                     | `PriorityQueue`                           | `PriorityBlockingQueue`                       | **非并发**：基于堆的优先级排序<br>**并发**：线程安全的阻塞优先队列                                            | 多线程任务优先级调度<br>定时任务管理系统                                       |
| **哈希结构**  | `Set`<br>`Map`              | `HashSet`<br>`HashMap`                    | `ConcurrentHashMap`<br>`ConcurrentSkipListMap` | **非并发**：哈希冲突链地址法<br>**并发**：分段锁(JDK7)/CAS+synchronized(JDK8+)，跳表Map替代有序键场景         | 高并发缓存系统<br>有序键并发存储                                              |
| **其他结构**  | -                           | `BitSet`<br>`EnumSet/EnumMap`             | `CopyOnWriteArraySet`                         | **非并发**：位向量操作/枚举优化存储<br>**并发**：写时复制（监听器模式）                                       | 位标志存储<br>事件监听器多线程注册                                            |

---

### 补充说明

#### 现代并发容器
1. **`ConcurrentLinkedDeque`**  
   - 替代`Stack`的线程安全方案，支持无界非阻塞操作。
2. **`SynchronousQueue`**  
   - 直接传递任务的特殊队列，零容量设计，适用于即时任务交接场景。
3. **`StampedLock`关联结构**  
   - 如`ConcurrentHashMap`在JDK8+中使用乐观锁（StampedLock）优化读性能。

#### 特性增强
| 技术          | 代表类                      | 优势                                                                 |
|---------------|----------------------------|----------------------------------------------------------------------|
| **跳表结构**  | `ConcurrentSkipListMap/Set` | 提供O(log n)的可预测性能，适合读多写少的有序场景                     |
| **写时复制**  | `CopyOnWriteArrayList/Set`  | 通过数组复制保证线程安全，读操作完全无锁                            |
| **CAS优化**   | 大多数现代并发容器           | 采用CAS+volatile保证可见性，减少锁竞争（如`ConcurrentHashMap`）      |

#### 典型场景扩展
| 场景                | 推荐方案                                    | 实现要点                                                                 |
|---------------------|--------------------------------------------|--------------------------------------------------------------------------|
| **内存敏感缓存**    | `ConcurrentHashMap` + `SoftReference`      | 自动回收内存，防止OOM                                                   |
| **请求限流器**      | `ArrayBlockingQueue`固定容量               | 控制并发请求速率                                                        |
| **分布式计数器**    | `LongAdder`（并发工具类）                   | 比`AtomicLong`更高的吞吐量，适合统计场景                                |

#### 已废弃类替代方案
| 废弃类       | 替代方案                                      | 选择条件                                                                 |
|--------------|-----------------------------------------------|--------------------------------------------------------------------------|
| `Vector`     | `CopyOnWriteArrayList`<br>`Collections.synchronizedList` | 读多写少用前者，写多读少用后者                                           |
| `Stack`      | `ConcurrentLinkedDeque`                       | 线程安全的栈操作                                                        |

---

### 选择建议
1. **高并发优先**  
   - 无锁设计：`ConcurrentLinkedQueue`、`ConcurrentHashMap`（JDK8+）
   - 低锁竞争：`ConcurrentSkipListMap`、`StampedLock`结构
2. **读多写少场景**  
   - `CopyOnWriteArrayList/Set`（写时复制）
   - `ConcurrentSkipListMap`（范围查询）
3. **强一致性需求**  
   - `LinkedBlockingQueue`（阻塞队列）
   - `Collections.synchronizedMap`（全同步锁）
