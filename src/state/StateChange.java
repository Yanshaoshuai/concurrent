package state;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StateChange {
    private static volatile int version;

    public static int sum() {
        Random random = new Random(100);
        int sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum += random.nextInt();
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            try {
                sum();
                version++;
                TimeUnit.SECONDS.sleep(3);
                synchronized (lock) {
                    version++;
                    lock.wait(3000);
                }
                synchronized (lock) {
                    version++;
                    lock.wait();
                }
                TimeUnit.SECONDS.sleep(1);
                version++;
                synchronized (lock) {
                    TimeUnit.SECONDS.sleep(1);
                }
                version++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        int mainVersion = version;
        System.out.println("t1 state is " + t1.getState());
        t1.start();
        System.out.println("t1 state is " + t1.getState());
        while (true) {
            if (mainVersion < version) {
                mainVersion = version;
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t1 state is " + t1.getState());
                if (t1.getState().equals(Thread.State.WAITING)) {
                    synchronized (lock) {
                        lock.notify();
                    }
                    t2.start();
                } else if (t1.getState().equals(Thread.State.TERMINATED)) {
                    break;
                }
            }
        }
    }
}
