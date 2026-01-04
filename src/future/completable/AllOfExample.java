package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * allOf 示例：等待所有异步任务完成
 */
public class AllOfExample {
    public static void main(String[] args) {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("任务1完成");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("任务2完成");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("任务3完成");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 等待所有任务完成
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);

        allTasks.join();
        System.out.println("所有任务已完成");
        System.out.println("main end");
    }
}
