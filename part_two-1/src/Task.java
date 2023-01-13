import java.util.concurrent.*;

/***
 * Task class to recieve callable object For using the tasks inside the customExecutor
 *
 */
public class Task<T> implements Callable<T> {
    public Callable<T> operation;
    public TaskType taskType;

    public Task(Callable<T> operation) {
        this.operation = operation;
        this.taskType = TaskType.OTHER;
    }
    public Task(Callable<T> operation, TaskType taskType) {
        this.operation = operation;
        this.taskType = taskType;
    }


    public static <T> Task<T> createTask(Callable<T> operation, TaskType taskType) {
        return new Task<>(operation, taskType);
    }

    public T call() throws Exception {
        return operation.call();
    }


}