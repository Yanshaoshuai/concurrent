package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author yan
 * @Date 2025/12/30
 * 用来控制可以同时执行的代码块的线程数
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        Runnable runnable = () -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread() + " acquire 1 permits");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread() + " release 1 permits");
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        for (int i = 0; i < 9; i++) {
            new Thread(runnable).start();
        }
    }
}
