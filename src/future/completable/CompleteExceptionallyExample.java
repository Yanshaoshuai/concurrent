package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * completeExceptionally 示例：手动设置 CompletableFuture 的异常
 */
public class CompleteExceptionallyExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);  // 模拟耗时操作
                System.out.println("异步任务执行中...");
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 主线程立即手动设置异常
        cf.completeExceptionally(new RuntimeException("手动设置的异常"));

        try {
            cf.join();
        } catch (Exception e) {
            System.out.println("捕获异常: " + e.getCause().getMessage());
        }
        System.out.println("main end");
    }
}
