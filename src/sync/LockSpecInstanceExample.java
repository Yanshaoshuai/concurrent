package sync;

import java.util.concurrent.TimeUnit;

public class LockSpecInstanceExample {
    public final Object monitor=new Object();

    public void hello(){
        synchronized (monitor){
            System.out.println("hello");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("release spec instance lock");
        }
    }

    public static void main(String[] args) {
        LockSpecInstanceExample example = new LockSpecInstanceExample();
        new Thread(new Runnable() {
            @Override
            public void run() {
                example.hello();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (example.monitor){
            System.out.println("main get spec instance lock");
        }
    }
}
