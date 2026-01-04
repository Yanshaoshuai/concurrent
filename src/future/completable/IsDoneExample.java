package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * isDone 示例：检查 CompletableFuture 是否已完成
 */
public class IsDoneExample {
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

        System.out.println("任务是否完成: " + cf.isDone());  // false

        cf.join();  // 等待任务完成

        System.out.println("任务是否完成: " + cf.isDone());  // true
        System.out.println("main end");
    }
}
