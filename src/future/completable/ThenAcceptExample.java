package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * thenAccept 示例：消费异步任务的结果
 */
public class ThenAcceptExample {
    public static void main(String[] args) {
        CompletableFuture<Void> supplyAndPrint = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("supplyAsync end");
                return 100;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenAccept(result -> {
            System.out.println("消费结果: " + result);
        });

        supplyAndPrint.join();
        System.out.println("main end");
    }
}
