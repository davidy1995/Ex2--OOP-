import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Iterator;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    private static final Logger logger = LoggerFactory.getLogger(CustomExecutor.class);
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() / 2;
    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() - 1;
    private static final long KEEP_ALIVE_TIME = 300L;
    int maxPriority = 0;

    public CustomExecutor() {
        super(CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME,TimeUnit.SECONDS,new PriorityBlockingQueue<>());


    }

    /***
     * A function that is activated every time an operation is added to the queue or an operation ends
     * @param it - iterator on the task in the queue
     * @param <T>
     * @return - the max priority of the task in the queue
     */
    public <T> int max(Iterator<Runnable> it){
        int max = 0;
        while (it.hasNext()) {
            var a = it.next();
            if (a instanceof Ft) {
                if (((Ft<?>) a).priority > max) {
                    max = ((Ft<?>) a).priority;
                }
            }
        }
        return max;
    }

    /***
     *
     * @param operation
     * @param <T>
     * @return
     */
    public <T> Future submit(Callable<T> operation) {
        Task<T> task = new Task<T>(operation);
        return this.submit(task);

    }

    /***
     *
     * @param operation
     * @param taskType
     * @param <T>
     * @return
     */
    public <T> Future submit(Callable<T> operation, TaskType taskType) {
        Task<T> task = new Task<T>(operation, taskType);
        return this.submit(task);
    }

    /***
     * use the ft addapter to make the task runnable and activate the max func
     * @param task
     * @param <T>
     * @return
     */
    public <T>Future submit(Task<T> task) {
        Ft<T> ft = new Ft<>(task);
        maxPriority = ft.priority;
        if(getQueue().size()>1)
            maxPriority = max(getQueue().iterator());
        this.execute(ft);
        return ft;
    }

    /***
     * override the afterexecute func that after the task end the max func activated
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t){
        Ft ft = (Ft) r;
        if(getQueue().size()>0)
            maxPriority = max(getQueue().iterator());
        else maxPriority = 0;
        super.afterExecute(ft,t);
    }

    /***
     *  use the maxvalue var off the class
     * @return max value
     */
    public String getCurrentMax() {
        return  Integer.toString(maxPriority);
    }

    /***
     * shutdown the class while finish all the task in the queue and not recieve new tasks
     */
    public void gracefullyTerminate() {
        this.shutdown();
    }
}

