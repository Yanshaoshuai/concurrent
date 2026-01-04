package juc.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        //防止子任务拆分过小
        if (n <= 10) {
            return fib(n);
        }
        Fibonacci fibonacci1 = new Fibonacci(n - 1);
        //fork 提交子任务
        fibonacci1.fork();
        Fibonacci fibonacci2 = new Fibonacci(n - 2);
        //fork 提交子任务
        fibonacci2.fork();
        //汇总子任务
        return fibonacci2.join() + fibonacci1.join();
    }


    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 2) + fib(n - 1);
    }

    public static void main(String[] args) {
        long startMills = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(45);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        System.out.println(forkJoinPool.invoke(fibonacci) + " time used:" + (System.currentTimeMillis() - startMills));
        startMills = System.currentTimeMillis();
        int fib = fib(45);
        System.out.println(fib + " time used:" + (System.currentTimeMillis() - startMills));
    }
}
