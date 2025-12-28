package threadexception;

import java.util.concurrent.TimeUnit;

public class InterruptedExceptionTest {
    private static volatile boolean flag=false;

    public static void main(String[] args) throws InterruptedException {
        Object source=new Object();
        Thread t1 = new Thread(() -> {
            while (!flag) {
                try {
                    synchronized (source) {
                        source.wait();
                    }
                } catch (InterruptedException e) {//catch捕获中断异常会同时设置中断标志位为false
                    //防止中断被异常吞掉
                    Thread.currentThread().interrupt();
                }
            }
            synchronized (source){
                System.out.println("interrupt flag is "+Thread.currentThread().isInterrupted());
                System.out.println("do something");
            }
        });
        t1.start();
        Thread.interrupted();
        TimeUnit.SECONDS.sleep(1);
        flag=true;
        t1.interrupt();
    }
}
