package future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // CompletableFuture<CompletionServiceTest.FutureResult> cf1 = CompletableFuture.supplyAsync(() -> {
        //     Random random = new Random(3);
        //     try {
        //         TimeUnit.SECONDS.sleep(random.nextInt(10));
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        //     return new CompletionServiceTest.FutureResult("task:" + 1, 100);
        // });
        // CompletableFuture<CompletionServiceTest.FutureResult> cf2 = CompletableFuture.supplyAsync(() -> {
        //     Random random = new Random(3);
        //     try {
        //         TimeUnit.SECONDS.sleep(random.nextInt(10));
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        //     return new CompletionServiceTest.FutureResult("task:" + 2, 200);
        // });
        // 所有都执行完成
        // CompletableFuture.allOf(cf1,cf2);
        // CompletionServiceTest.FutureResult futureResult1 = cf1.get();
        // System.out.println("task:"+futureResult1.name+" finished! result is "+futureResult1.result);
        // CompletionServiceTest.FutureResult futureResult2 = cf2.get();
        // System.out.println("task:"+futureResult2.name+" finished! result is "+futureResult2.result);

        // 任意一个完成
        // CompletableFuture<Object> future = CompletableFuture.anyOf(cf1, cf2);
        // CompletionServiceTest.FutureResult result = (CompletionServiceTest.FutureResult)future.get();
        // System.out.println("task:"+result.name+" finished! result is "+result.result);

        // 先执行完一个剩下两个并行
        // CompletableFuture<CompletionServiceTest.FutureResult> syncFuture = CompletableFuture.completedFuture(task1());
        // CompletionServiceTest.FutureResult result = syncFuture.get();
        // System.out.println("task:"+result.name+" finished! result is "+result.result);
        // CompletableFuture<CompletionServiceTest.FutureResult> cf2 = CompletableFuture.supplyAsync(() -> {
        //     Random random = new Random(3);
        //     try {
        //         TimeUnit.SECONDS.sleep(random.nextInt(10));
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        //     return new CompletionServiceTest.FutureResult("task:" + 1, 200);
        // });
        // CompletableFuture<CompletionServiceTest.FutureResult> cf3 = CompletableFuture.supplyAsync(() -> {
        //     Random random = new Random(3);
        //     try {
        //         TimeUnit.SECONDS.sleep(random.nextInt(10));
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        //     return new CompletionServiceTest.FutureResult("task:" + 1, 300);
        // });
        // CompletableFuture.allOf(cf2,cf3);
        // CompletionServiceTest.FutureResult futureResult1 = cf2.get();
        // System.out.println("task:"+futureResult1.name+" finished! result is "+futureResult1.result);
        // CompletionServiceTest.FutureResult futureResult2 = cf3.get();
        // System.out.println("task:"+futureResult2.name+" finished! result is "+futureResult2.result);

        // 两个任务链并行执行 第一个一个task 第二个两个task串行
        CompletableFuture<CompletionServiceTest.FutureResult> cf1 = CompletableFuture.supplyAsync(() -> {
            Random random = new Random(3);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new CompletionServiceTest.FutureResult("task:" + 1, 100);
        });
        CompletableFuture<CompletionServiceTest.FutureResult> cf2 = CompletableFuture.supplyAsync(() -> {
            Random random = new Random(3);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new CompletionServiceTest.FutureResult("task:" + 2, 200);
        }).thenApply((result) -> {
            System.out.println("cf2 step one task name is " + result.name+" result is "+result.result);
            Random random = new Random(3);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new CompletionServiceTest.FutureResult("task:" + 3, 300);
        });

        CompletableFuture.allOf(cf1,cf2);
        CompletionServiceTest.FutureResult futureResult1 = cf1.get();
        System.out.println("task:"+futureResult1.name+" finished! result is "+futureResult1.result);
        CompletionServiceTest.FutureResult futureResult2 = cf2.get();
        System.out.println("task:"+futureResult2.name+" finished! result is "+futureResult2.result);

    }

    static CompletionServiceTest.FutureResult task1() {
        Random random = new Random(3);
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CompletionServiceTest.FutureResult("task:" + 1, 100);
    }
}
