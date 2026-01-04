package deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 必会产生死锁 - 修复版
 * 使用 tryLock + 重试机制，确保两个线程都能执行业务逻辑
 *
 * @Author YSS
 * @Date 2020/6/14 21:38
 */
public class MustDeadLockFix {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            int retryCount = 0;

            while (true) {
                try {
                    if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "获取到了lock1");

                        if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "获取到了lock2");
                            System.out.println(Thread.currentThread().getName() + "获取到了两把锁，执行业务逻辑");

                            TimeUnit.MILLISECONDS.sleep(100);

                            System.out.println(Thread.currentThread().getName() + "业务执行完成");
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + "尝试获取lock2失败，重试中...");
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "尝试获取lock1失败，稍后重试...");
                    }

                    retryCount++;

                } catch (InterruptedException e) {
                    throw new RuntimeException("线程被中断", e);
                } finally {
                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                }
            }

            System.out.println(Thread.currentThread().getName() + "总共重试了 " + retryCount + " 次");
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            int retryCount = 0;

            while (true) {
                try {
                    if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "获取到了lock2");

                        if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "获取到了lock1");
                            System.out.println(Thread.currentThread().getName() + "获取到了两把锁，执行业务逻辑");

                            TimeUnit.MILLISECONDS.sleep(100);

                            System.out.println(Thread.currentThread().getName() + "业务执行完成");
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + "尝试获取lock1失败，重试中...");
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "尝试获取lock2失败，稍后重试...");
                    }

                    retryCount++;

                } catch (InterruptedException e) {
                    throw new RuntimeException("线程被中断", e);
                } finally {
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                }
            }

            System.out.println(Thread.currentThread().getName() + "总共重试了 " + retryCount + " 次");
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
