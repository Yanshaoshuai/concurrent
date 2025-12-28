package sync;

import java.util.concurrent.TimeUnit;

public class InstanceMethodLockExample {
    public synchronized void hello(){
        System.out.println("hello");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("release example monitor");
    }

    public static void main(String[] args) {
        InstanceMethodLockExample example = new InstanceMethodLockExample();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                example.hello();
            }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (example){
            System.out.println("main get example monitor");
        }
    }
}
