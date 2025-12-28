package juc.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        // Runnable runnable = () -> {
        //   queue.add("hello");
        // };
        // Thread thread = new Thread(runnable);
        // thread.start();

        //非阻塞
        // queue.add("hello");
        //阻塞
        queue.put("hello");

        //非阻塞 成功返回true 失败返回false
        System.out.println(queue.offer("hello"));
        //阻塞指定时间
        // System.out.println(queue.offer("hello",1, TimeUnit.SECONDS));

        //非阻塞 获取不到返回null
        System.out.println(queue.poll());

        //阻塞指定时间 获取不到返回null
        System.out.println(queue.poll(1, TimeUnit.SECONDS));

        //阻塞 获取头节点
        System.out.println(queue.take());
    }
}
