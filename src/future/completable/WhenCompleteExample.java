package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * whenComplete 示例：任务完成（成功或异常）时的回调
 */
public class WhenCompleteExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("异步任务执行中...");
                return 100;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).whenComplete((result, throwable) -> {
            if (throwable != null) {
                System.out.println("whenComplete 捕获异常: " + throwable.getMessage());
            } else {
                System.out.println("whenComplete 正常完成，结果: " + result);
            }
        });

        Integer result = cf.join();
        System.out.println("最终结果: " + result);
        System.out.println("main end");
    }
}
