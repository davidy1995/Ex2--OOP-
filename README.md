
# Ex2  OOP Java - Threads

Third project in OOP course at Ariel Univeristy using java. This project was split into 2 parts.



## Part I - Ex2_1 Class
This class contains 4 functions: createTextFiles,getNumOfLines,getNumOfLinesThreads and getNumOfLinesThreadPool
In general - We've asked to create several text files and calculate the total
number of lines in these files. We  useed three methods - without using threads, using threads and using threadpool.
Lets have a look on the UML diagram we've created for this project.
![UML](https://user-images.githubusercontent.com/84707578/212106322-7317748b-01c3-45cc-bc03-3485956bcb75.png)

 Tests

n = 3000, seed = 15 , bound = 10000 </br>
![test](https://user-images.githubusercontent.com/84707578/212276693-eff00907-a83c-4dcd-a861-8e0ca038459b.png)</br>
We assumed that fastest implementation would likely be using a thread pool. By using a thread pool, we can reuse a fixed number of threads, rather than creating a new thread for each task. This can greatly reduce the overhead of thread creation and management, leading to better performance. Additionally, using a thread pool allows you to control the number of threads running at any given time, which can help prevent overloading the system with too many threads.
</br>
There could be several reasons why using threads was the fastest implementation:</br>

* The number of tasks you were performing was not large enough to justify the use of a thread pool. In cases where there are only a few tasks, using a    thread pool can actually add unnecessary overhead and slow down performance. 

*The tasks you were performing were not computationally intensive enough to take advantage of the parallelism provided by multiple threads.

*Thread pool have additional overhead on the management of the thread and the queue, this could be a reason for the slow performance.
