package threadexception;

public class GlobalThreadUncaughtExceptionTest {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println(" thread id "+t.getId()+" exception has been caught");
                e.printStackTrace();
            }
        });

        new Thread(()->{
            int i=1/0;
        }).start();
    }
}
