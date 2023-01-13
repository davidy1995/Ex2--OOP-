
# Ex2  OOP Java - Threads

Third project in OOP course at Ariel Univeristy using java. This project was split into 2 parts.



## Part I - Ex2_1 Class
This class contains 4 functions: createTextFiles,getNumOfLines,getNumOfLinesThreads and getNumOfLinesThreadPool
In general - We've asked to create several text files and calculate the total
number of lines in these files. We  useed three methods - without using threads, using threads and using threadpool.
Lets have a look on the UML diagram we've created for this project.
![UML](https://user-images.githubusercontent.com/84707578/212106322-7317748b-01c3-45cc-bc03-3485956bcb75.png)

 ## Part I - Performance Tests:
 
 We will compare 3 scenarios with different amount of txt files we've created.


!
We assumed that fastest implementation would likely be using a thread pool. By using a thread pool, we can reuse a fixed number of threads, rather than creating a new thread for each task. This can greatly reduce the overhead of thread creation and management, leading to better performance. Additionally, using a thread pool allows you to control the number of threads running at any given time, which can help prevent overloading the system with too many threads.
</br>
1) n = 10, seed = 4 , bound = 10000 </br>
![test1](https://user-images.githubusercontent.com/84707578/212296677-7e5b6a51-97f7-47e9-84dc-cbc42477aab5.png)

2) n = 150, seed = 4 , bound = 10000 </br>
![test2](https://user-images.githubusercontent.com/84707578/212297408-5cc7dd8a-c509-4205-9008-c2996b0fd86a.png)


3) n = 3000, seed = 4 , bound = 10000 </br>
![test3](https://user-images.githubusercontent.com/84707578/212276693-eff00907-a83c-4dcd-a861-8e0ca038459b.png)

We can clearly see that using threads is the fastes way and has the best performance. On the first 2 tests using thread pool was faster then using single thread but in the last example we can see that single thread is faster than threadpool.

There could be several reasons why using threads was the fastest implementation:</br>

* The number of tasks you were performing was not large enough to justify the use of a thread pool. In cases where there are only a few tasks, using a    thread pool can actually add unnecessary overhead and slow down performance. 
* The tasks you were performing were not computationally intensive enough to take advantage of the parallelism provided by multiple threads.
* Thread pool have additional overhead on the management of the thread and the queue, this could be a reason for the slow performance.


 ## Part I - How To Use:
In order to run / test the program navigate to Driver class and change the value of n.

```bash
 String[] files = Ex2_1.createTextFiles(CHANGE THIS, 1, 10000);
```

Please do not remove files folder which initialized with 1 txt file (Off course you can add more)

## Part 2 - Classes
UML diagram for this part-</br>
![UML](https://user-images.githubusercontent.com/84707578/212321939-82f13bee-7c5e-441a-ba28-9e23db0ae26c.jpeg)


Task class:</br>
recieve a callable object and is prioriy number and cast the a Task cllas.
the class can create more instance with the createtask method.
Ft class
addapter for make a object of the form of task to a form of runnable that can be applied inside the threadpool.
its contain also the compar func for decide the priority of each object in the pool.

Customerexecutur class:</br>

contain a threadpool that have all the task casted to runnable and take the according to the max priority in the queue.
the functions of the class
submit - take a task execute it and add the task to the priority queue.
max - iterate over all the queue and return the max value of priority,this func get a call when we add/remove a task from the queue.
gracefullyterminate - finish the executer while not recive new task and end all the previus task.

## Part 2 - Tests 
we use the tests that we recieve at the assigment and add tests to test the gracefullyterminate func and the max priority func
