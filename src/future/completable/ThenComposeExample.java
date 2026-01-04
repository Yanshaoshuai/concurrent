package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * thenCompose 示例：串联两个异步操作，避免嵌套 CompletableFuture
 */
public class ThenComposeExample {
    public static void main(String[] args) {
        // 第一个异步操作：获取用户ID
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("获取用户ID: 1001");
                return 1001;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenCompose(userId -> {
            // 第二个异步操作：根据ID获取用户名（依赖第一个结果）
            return CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("根据ID获取用户名");
                    return "User-" + userId;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        System.out.println("最终结果: " + result.join());
        System.out.println("main end");
    }
}
