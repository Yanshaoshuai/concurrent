package future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * handle 示例：同时处理正常结果和异常，无论成功失败都会执行
 */
public class HandleExample {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("supplyAsync end");
                return 1 / 0;  // 模拟异常
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).handle((result, throwable) -> {
            if (throwable != null) {
                System.out.println("捕获异常: " + throwable.getMessage());
                return "默认值";
            } else {
                System.out.println("正常结果: " + result);
                return "处理后的值: " + result;
            }
        });

        String result = cf.join();
        System.out.println("最终结果: " + result);
        System.out.println("main end");
    }
}
