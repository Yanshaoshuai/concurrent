package threadlocal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUseThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal=new ThreadLocal<>();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,100, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 5; i++) {
            final int j=i;
            threadPoolExecutor.execute(() -> {
                    try {
                        if(threadLocal.get()==null){
                            threadLocal.set(Thread.currentThread().getId()+Thread.currentThread().getName()+" task:"+j);
                            System.out.println("set thread local");
                        }else {
                            System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+" task:"+j+"=="+threadLocal.get());
                        }
                    }finally {
                        threadLocal.remove();
                    }
            });
        }
    }
}
