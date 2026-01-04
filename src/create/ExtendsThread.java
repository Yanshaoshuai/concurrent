package create;

import java.util.concurrent.TimeUnit;

/**
 * @Author yan
 * @Date 2025/12/28
 */
public class ExtendsThread extends Thread{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Child#run");
    }

    public static void main(String[] args) {
        new ExtendsThread().start();
        System.out.println("main#end");
    }
}
