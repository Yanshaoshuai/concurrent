package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * exceptionally 示例：异步任务异常时的恢复处理
 */
public class ExceptionallyExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("supplyAsync end");
                return 1 / 0;  // 模拟异常
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(throwable -> {
            System.out.println("捕获异常: " + throwable.getMessage());
            return -1;  // 返回默认值进行恢复
        });

        Integer result = cf.join();
        System.out.println("最终结果: " + result);
        System.out.println("main end");
    }
}
