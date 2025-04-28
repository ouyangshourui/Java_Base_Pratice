## 作用
### 阿里巴巴编程规范在什么场景必须使用inner class
- ​​访问外部类私有成员​​
  ```java
  public class Outer {
    private String name = "Outer";
    
    class Inner {
        void print() {
            System.out.println(name); // 直接访问外部类私有成员
        }
    }
}```

- ​​事件处理与回调机制​​
- ​​实现线程安全单例模式​​
- ​​迭代器模式或数据结构封装​​
- ​​临时性接口实现（替代Lambda）​​	
### 逻辑封装


## 经典用法
### ​​访问外部类私有成员​​
在物流系统开发中，内部类访问外部类私有成员的场景非常适合需要紧密协作但保持数据封装的模块。以下是物流领域的典型应用场景和代码示例：

#### **场景 1：订单与订单项处理**
需求：  
订单（`Order`）类包含私有订单项（`OrderItem`）列表，需要内部类直接操作这些私有数据，避免暴露给外部模块。

```java
public class Order {
    private List<OrderItem> items = new ArrayList<>();  // 私有订单项列表

    // 内部类：直接访问外部类的私有 items 进行校验和计算
    public class OrderValidator {
        public boolean validateTotalWeight() {
            double totalWeight = 0;
            for (OrderItem item : items) {  // 直接访问外部类的私有成员
                totalWeight += item.getWeight();
            }
            return totalWeight <= 1000;  // 校验订单总重量是否超限
        }
    }

    // 外部类方法调用内部类
    public boolean isValid() {
        return new OrderValidator().validateTotalWeight();
    }
}
```
优势：  
• 订单项列表（`items`）保持私有，外部无法直接修改。  
• 内部类 `OrderValidator` 直接访问数据，无需通过 `public getItems()` 暴露数据，防止被误操作。

---

**场景 2：仓库库存管理**
需求：  
仓库（`Warehouse`）需要内部类 `InventoryUpdater` 直接更新私有库存数据，并记录操作日志。

```java
public class Warehouse {
    private Map<String, Integer> stock = new HashMap<>();  // 私有库存数据（商品ID → 数量）

    // 内部类：直接操作库存并记录日志
    public class InventoryUpdater {
        public void addStock(String productId, int quantity) {
            int current = stock.getOrDefault(productId, 0);
            stock.put(productId, current + quantity);  // 直接修改外部类私有成员
            log("库存增加: " + productId + ", 数量+" + quantity);
        }

        private void log(String message) {
            System.out.println("[Warehouse] " + message);
        }
    }

    // 对外提供库存操作入口
    public InventoryUpdater getUpdater() {
        return new InventoryUpdater();
    }
}
```

优势：  
• 库存数据（`stock`）对外完全隐藏，只有 `InventoryUpdater` 能直接修改，确保数据一致性。  
• 日志记录和库存更新逻辑封装在内部类中，**避免外部代码侵入**。


---

**场景 3：运输路线规划**
需求：  
运输路线（`TransportRoute`）需要内部类 `RouteOptimizer` 直接访问私有路径节点数据，进行动态优化。

```java
public class TransportRoute {
    private List<Location> nodes = new ArrayList<>();  // 私有路径节点

    // 内部类：直接访问 nodes 进行路径优化
    public class RouteOptimizer {
        public void optimizeForTime() {
            // 直接操作外部类的私有 nodes 列表（例如重新排序节点）
            Collections.sort(nodes, Comparator.comparing(Location::getArrivalTime));
            System.out.println("按到达时间优化路径完成");
        }

        public void optimizeForDistance() {
            // 直接操作外部类的私有 nodes 列表（例如计算最短路径）
            System.out.println("按最短距离优化路径完成");
        }
    }

    // 对外提供优化器
    public RouteOptimizer createOptimizer() {
        return new RouteOptimizer();
    }
}
```
优势：  
• 路径节点（`nodes`）对外不可见，防止外部代码意外修改。  
• 优化逻辑和路径数据强绑定，代码高内聚。

---
**适用场景总结**
在物流系统中，内部类访问外部类私有成员的场景通常具备以下特征：  
1. 数据敏感性强：如订单、库存、路径节点等核心数据需要严格保护。  
2. 逻辑紧密耦合：内部类的功能与外部类数据强相关（如校验、优化、更新）。  
3. 封装性要求高：避免通过 `public` 方法暴露私有字段，减少外部依赖。  
通过内部类实现，既能保证数据安全，又能简化代码结构，是物流系统中模块化设计的常见实践。
