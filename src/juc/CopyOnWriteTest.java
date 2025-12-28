package juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteTest {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList=new CopyOnWriteArrayList<>();
        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                copyOnWriteArrayList.add(copyOnWriteArrayList.get(i)+1);
            }
        }).start();

        new Thread(()->{
            for (int i = 10000; i < 20000; i++) {
                copyOnWriteArrayList.add(copyOnWriteArrayList.get(i)+1);
            }
        }).start();

        Iterator<Integer> iterator = copyOnWriteArrayList.iterator();

        TimeUnit.SECONDS.sleep(5);
        for (Integer str:
             copyOnWriteArrayList) {
            System.out.println(str);
        }
    }
}
