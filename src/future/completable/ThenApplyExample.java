package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * thenApply 示例：对异步任务的结果进行同步转换
 */
public class ThenApplyExample {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("获取用户ID...");
                return 1001;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenApply(id -> {
            // 同步转换：将ID转换为用户名
            System.out.println("ID转换用户名...");
            return "User-" + id;
        });

        System.out.println("最终结果: " + cf.join());
        System.out.println("main end");
    }
}
