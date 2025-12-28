package future;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletionServiceTest {
    static class FutureResult{
        String name;
        Integer result;

        public FutureResult(String name, Integer result) {
            this.name = name;
            this.result = result;
        }
    }
    static class CallableTask implements Callable<FutureResult>{
        int state;
        String name;
        public CallableTask(String name,int state) {
            this.name=name;
            this.state = state;
        }

        @Override
        public FutureResult call() throws Exception {
            Random random =new Random(3);
            TimeUnit.SECONDS.sleep(random.nextInt(10));
            return new FutureResult(name,state*100);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,2,3,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(20));
        ExecutorCompletionService<FutureResult> completionService=new ExecutorCompletionService<>(executor,new ArrayBlockingQueue<>(20));
        for (int i = 0; i < 10; i++) {
            completionService.submit(new CallableTask("task:"+i,i));
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Future<FutureResult> result;
        while ((result=completionService.poll(10,TimeUnit.SECONDS))!=null){
            FutureResult futureResult = result.get();
            System.out.println(futureResult.name+" finished! result is "+ futureResult.result);
        }
        System.out.println("main finished");
        executor.shutdown();
    }
}