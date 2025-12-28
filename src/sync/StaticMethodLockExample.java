package sync;

import java.util.concurrent.TimeUnit;

public class StaticMethodLockExample {
    public static synchronized void hello(){
        System.out.println("hello");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("release example class lock");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StaticMethodLockExample.hello();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        synchronized (StaticMethodLockExample.class){
            System.out.println("main get example class lock");
        }
    }
}
