package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * anyOf 示例：等待任意一个异步任务完成
 */
public class AnyOfExample {
    public static void main(String[] args) {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return "任务1结果";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return "任务2结果";  // 最快完成
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return "任务3结果";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 等待任意一个任务完成
        CompletableFuture<Object> anyTask = CompletableFuture.anyOf(task1, task2, task3);

        Object result = anyTask.join();
        System.out.println("最先完成的任务结果: " + result);
        System.out.println("main end");
    }
}
