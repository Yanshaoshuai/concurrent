package threadlocal;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class ThreadLocalLeakMemory {
    static class MyClass{
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyClass has been clear");
        }
    }
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        ThreadLocal<MyClass> threadLocal=new ThreadLocal<>();
        threadLocal.set(new MyClass());
        threadLocal=null;

        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.gc();
        TimeUnit.SECONDS.sleep(1);

        Thread t1 = Thread.currentThread();
        Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
        threadLocalsField.setAccessible(true);
        Object o = threadLocalsField.get(t1);
    }
}
