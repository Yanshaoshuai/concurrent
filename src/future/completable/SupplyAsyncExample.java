package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * supplyAsync 示例：有返回值的异步任务
 */
public class SupplyAsyncExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("supplyAsync end");
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Integer result = cf1.join();
        System.out.println("result: " + result);
        System.out.println("main end");
    }
}
