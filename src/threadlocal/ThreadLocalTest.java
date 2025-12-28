package threadlocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal=new ThreadLocal<>();
        threadLocal.set("yan");
        new Thread(()->{
            String str = threadLocal.get();
            System.out.println("get main thread thread local from child thread -->"+str);
            threadLocal.set("wang");
            str = threadLocal.get();
            System.out.println("get main thread thread local from child thread after set -->"+str);
        }).start();
        String str = threadLocal.get();
        System.out.println("get main thread thread local from main thread -->"+str);
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        withInitialTest();
    }
    public static void withInitialTest(){
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "xxx");
        System.out.println("withInitialTest "+threadLocal.get());
    }
}
