package create;

import java.util.concurrent.TimeUnit;

/**
 * @Author YSS
 * @Date 2020/6/11 23:59
 */
public class LambdaTest {
    public static void main(String[] args) {
        Thread thread=new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Child lambda runnable");
        });
        thread.start();
        System.out.println("main#end");
    }
}
