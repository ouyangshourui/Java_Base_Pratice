package niuke;
import java.util.LinkedList;
public class MaxSlidingWindow {
    /**
     * 思路：
     * 1、双端队列, 最大值放在队列头部
     * 2、如果一个值比它前面的值要大，那么它前面的值就永远不可能成为最大值
     * 2、因为当前元素比前面的元素大，则当前的窗口最大值为当前元素，否则为窗口中目前的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k <= 0) {
            return new int[0];
        }
        int len = nums.length;
        // k有效不需要校验k
        int[] res = new int[len - k + 1];

        // 双端队列
        LinkedList<Integer> cache = new LinkedList<>();

        for (int i=0; i<len; i++) {
            // 添加元素到队列，保证队列递增,比当前元素小的都弹出
            while (!cache.isEmpty() && nums[cache.peekLast()] < nums[i]) {
                // 队列中加入元素索引
                cache.removeLast();
            }
            // 队列中加入元素索引
            cache.addLast(i);
            // 需要移除队列中过期的元素
            if (i - cache.peekFirst() >= k) {
                cache.removeFirst();
            }
            // 如果队列中i>=k-1, 记录当前队列中的最大值
            if (i >= k - 1) {
                res[i-k+1] = nums[cache.peekFirst()];
            }
        }

        return res;
    }

    public static void main(String[] args)
    {
        int[] nums = {1,3,1,2,0,5};
        int k = 3;
       int[] result = new MaxSlidingWindow().maxSlidingWindow(nums, k);
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.println(result[i]);
        }

        int[] nums1 = {1,-1};
        int k1 = 1;
        System.out.println(new MaxSlidingWindow().maxSlidingWindow(nums1, k1));
    }
}
