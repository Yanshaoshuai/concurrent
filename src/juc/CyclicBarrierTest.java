package juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author yan
 * @Date 2025/12/30
 * 多个线程相互等待彼此都到达某一状态再一起往下执行
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        Random random = new Random();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Runnable runnable = () -> {
            int randomInt = random.nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(randomInt);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread() + " step1 completed");

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + " step2 completed");
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
