import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.concurrent.*;
public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    @Test
    public void partialTest() throws ExecutionException, InterruptedException, TimeoutException {
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
                int s = 5;
                int b = s ^ s + 5 - 66 + 6 ;
                Thread.sleep(5);
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = (int)sumTask.get(55, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            Thread.sleep(5);
            return sb.reverse().toString();
        };
        // var is used to infer the declared type automatically
        var priceTask = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        var reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = (Double) priceTask.get();
            reversed = (String) reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0;i < 10;i++){
            var s = customExecutor.submit(task);
            var p = customExecutor.submit(()-> {
                Thread.sleep(5);
                return 1000 * Math.pow(1.02, 5);
            }, TaskType.IO);
        }

        //Test to see that currentmax func work
        Assertions.assertEquals("2",customExecutor.getCurrentMax());

        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        int size = customExecutor.getQueue().size();
        customExecutor.gracefullyTerminate();

        //Test to see that task cant be added to the queue after gracefullyTerminate
        Assertions.assertEquals(size,customExecutor.getQueue().size());
        Assertions.assertThrows(RejectedExecutionException.class,()->customExecutor.submit(task));
    }
}
