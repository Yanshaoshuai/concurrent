package juc;

public class TestReOrder {
    static long a=0,b=0,x=0,y=0;

    public static void main(String[] args) throws InterruptedException {
        int count=0;
        while ((x != 0 || y != 0) || count == 0) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread t1 = new Thread(() -> {
                a = System.nanoTime();
                x = b;
            });

            Thread t2 = new Thread(() -> {
                b = System.nanoTime();
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            count++;
        }
        System.out.printf("x=%d,y=%d \n",x,y);
    }
}
