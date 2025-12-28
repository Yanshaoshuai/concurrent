package threadexception;

public class ThreadExceptionCaught {
    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(() -> {
                int i = 1 / 0;
            }).start();
        } catch (Exception exception) {
            System.err.println("main thread caught exception");
            exception.printStackTrace();
        }
        Thread.sleep(100);
        new Thread(() -> {
            try {
                int i=1/0;
            } catch (Exception exception) {
                System.err.println(Thread.currentThread().getId()+" thread caught exception--------->");
                exception.printStackTrace();
            }
        }).start();
    }
}
