import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/***
 * addapter for Task class to take the callable and make it runnable
 */

public class Ft<T> extends FutureTask<T> implements Comparable<Ft<T>>{
    public Task<T> ta;
    int priority;


    public Ft(Task<T> task) {
        super(task);
        ta = task;
        priority = task.taskType.getPriorityValue();
    }

    @Override
    public int compareTo(Ft o) {
        return Integer.compare(priority,o.priority);
    }
}