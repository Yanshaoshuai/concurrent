package state;

import java.util.concurrent.TimeUnit;

public class BlockedTest {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                synchronized (lock) {
                    TimeUnit.SECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("t1 state is "+t1.getState());
        System.out.println("t2 state is "+t2.getState());
    }
}
