
import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolTest {

    public static void main(String[] args) {
        ExecutorService exxc = Executors.newFixedThreadPool(5);
        RateLimiter rateLimiter =RateLimiter.create(3);
        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = 0; i < 15; i++) {
            tasks.add(new UserRequest(i));
        }
        for (Runnable runnable : tasks) {
            System.out.println("等待时间：" + rateLimiter.acquire());
            exxc.execute(runnable);
        }
    }
}
class UserRequest implements Runnable {
    private int id;

    public UserRequest(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println(id);
    }

}
