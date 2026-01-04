package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * complete 示例：手动设置 CompletableFuture 的结果
 * 如果任务未完成，complete 会设置结果并返回 true
 * 如果任务已完成，complete 不会改变结果并返回 false
 */
public class CompleteExample {
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

        // 主线程立即手动设置结果
        boolean success = cf.complete(100);
        System.out.println("手动设置结果成功: " + success);

        Integer result = cf.join();
        System.out.println("最终结果: " + result);
        System.out.println("main end");
    }
}
