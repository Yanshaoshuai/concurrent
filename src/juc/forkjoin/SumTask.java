package juc.forkjoin;

import java.util.Objects;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author yan
 * @Date 2025/12/29
 */
public class SumTask extends RecursiveTask<Integer> {
    private final Integer start;
    private final Integer end;

    public SumTask(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (Objects.equals(start, end)) {
            return start;
        }
        //模拟耗时操作
        business();
        int mid = (start + end) / 2;
        SumTask sumTask1 = new SumTask(start, mid);
        sumTask1.fork();
        SumTask sumTask2 = new SumTask(mid + 1, end);
        sumTask2.fork();
        return sumTask1.join() + sumTask2.join();
    }

    public static void business(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int start = 0;
        int end = 10;
        long startMills = System.currentTimeMillis();
        SumTask sumTask = new SumTask(start, end);
        Integer result = sumTask.compute();
        System.out.println("result=" + result + " time used:" + (System.currentTimeMillis() - startMills) / 1000);
        startMills = System.currentTimeMillis();
        result = 0;
        for (int i = start; i <= end; i++) {
            business();
            result += i;
        }
        System.out.println("result=" + result + " time used:" + (System.currentTimeMillis() - startMills) / 1000);
    }
}
