package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * get 示例：阻塞获取异步任务的结果（可抛出检查异常）
 */
public class GetExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("异步任务执行中...");
                return 100;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            Integer result = cf.get();  // 阻塞等待结果
            System.out.println("获取结果: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("获取失败: " + e.getMessage());
        }
        System.out.println("main end");
    }
}
