package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * thenRun 示例：任务完成后执行操作，不依赖前一个任务的结果
 */
public class ThenRunExample {
    public static void main(String[] args) {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("supplyAsync end");
                return 100;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenRun(() -> {
            System.out.println("thenRun: 任务完成后的清理工作");
        });

        cf.join();
        System.out.println("main end");
    }
}
