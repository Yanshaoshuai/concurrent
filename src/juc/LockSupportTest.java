package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    private static volatile boolean flag;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
        });
        Thread t2 = new Thread(() -> {
            while (true){
                if(flag){
                    System.out.println("flag is "+flag);
                    break;
                }else {
                    LockSupport.park();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        LockSupport.unpark(t2);
        t2.join();
    }
}
