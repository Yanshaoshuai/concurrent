package threadexception;

public class SingleThreadUncaughtExceptionTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int i = 1 / 0;
        });
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println(" thread id "+t.getId()+" exception has been caught");
                e.printStackTrace();
            }
        });
        t1.start();
    }
}
