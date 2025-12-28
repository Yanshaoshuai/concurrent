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
        if(n<=1){
            return n;
        }
        Fibonacci fibonacci1 = new Fibonacci(n - 1);
        //fork子线程去执行子任务
        fibonacci1.fork();
        Fibonacci fibonacci2 = new Fibonacci(n - 2);
        //fork子线程去执行子任务
        fibonacci2.fork();
        //汇总子任务
        return fibonacci2.join()+fibonacci1.join();
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci(10);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(fibonacci));
    }
}
