package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * thenCombine 示例：组合两个独立的异步任务的结果
 */
public class ThenCombineExample {
    public static void main(String[] args) {
        // 任务1：获取用户信息
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return "User-1001";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 任务2：获取订单信息
        CompletableFuture<Integer> orderFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 500;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 组合两个任务的结果
        CompletableFuture<String> result = userFuture.thenCombine(orderFuture, (user, orderId) -> {
            return user + " 的订单金额: " + orderId;
        });

        System.out.println("最终结果: " + result.join());
        System.out.println("main end");
    }
}
