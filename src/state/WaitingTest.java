package state;

import java.util.concurrent.TimeUnit;

public class WaitingTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();

        Object lock = new Object();

        Thread t2 = new Thread(() -> {
            try {
                synchronized (lock) {
                    lock.wait(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t3.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("t1 state is " + t1.getState());
        System.out.println("t2 state is " + t2.getState());
        System.out.println("t3 state is " + t3.getState());
    }
}
