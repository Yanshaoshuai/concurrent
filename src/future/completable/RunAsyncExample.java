package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * runAsync 示例：无返回值的异步任务
 */
public class RunAsyncExample {
    public static void main(String[] args) {
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("runAsync end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        cf1.join();
        System.out.println("main end");
    }
}
