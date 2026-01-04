package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * isCompletedExceptionally 示例：检查 CompletableFuture 是否因异常而完成
 */
public class IsCompletedExceptionallyExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("异步任务执行中...");
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 手动设置异常
        cf.completeExceptionally(new RuntimeException("手动设置的异常"));

        System.out.println("是否因异常完成: " + cf.isCompletedExceptionally());  // true
        System.out.println("main end");
    }
}
