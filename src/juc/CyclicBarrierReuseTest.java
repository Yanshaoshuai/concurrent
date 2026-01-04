package juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author yan
 * @Date 2025/12/30
 */
public class CyclicBarrierReuseTest {
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
                if (cyclicBarrier.isBroken()) {
                    cyclicBarrier.reset();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + " step2 completed");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + " step3 completed");
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
