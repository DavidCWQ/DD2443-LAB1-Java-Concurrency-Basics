# Lab 1 - Basic Concurrency in Java
- Group 18
- Simon Dussud and Wenqi Cao

## Task 1: Simple Synchronization

### A. Race Conditions
Source files:
- `task1/MainA.java` (main file)

To compile and execute:
```shell
javac MainA.java
java  Main
```

### B. Synchronized Keyword
Source files:
- `task1/MainB.java` (main file)

To compile and execute:
```shell
javac MainB.java
java  Main
```

### C. Synchronization Performance
Source files:
- `task1/MainC.java` (main file)

To compile and execute:
```shell
javac MainC.java
java  Main <N>
```
> Where `N` is number of threads to execute with.

In figure 1, we see how the execution time scaled with the number of threads:

![My plot for task 1c](data/task2c.png)

## Task 2: Guarded Blocks using wait()/notify()

### A. Asynchronous Sender-Receiver
Source files:
- `task2/MainA.java` (main file)

To compile and execute:
```shell
javac MainA.java
java  Main
```

> Since the print thread runs in parallel with the increment thread without synchronization, it may print a value between 0 and 1,000,000.

### B. Busy-waiting Receiver
Source files:
- `task2/MainB.java` (main file)

To compile and execute:
```shell
javac MainB.java
java  Main
```

> With the while loop to continuously check if 'done' is true, the program works fine and print 1,000,000

### C. Waiting with Guarded Block
Source files:
- `task2/MainC.java` (main file)

To compile and execute:
```shell
javac MainC.java
java  Main
```

> This also works well.
> Let's analyze the performance.

### D. Guarded Block Performance
Source files:
- `task2/MainD.java` (main file)

To compile and execute:
```shell
javac MainD.java
java  Main
```

## Task 3: Producer-Consumer Buffer using Condition Variables


### A. Implementation
Source files:
- `task3/Buffer.java`

To compile and execute:
```shell
javac Buffer.java
```
> The condition variables ensure that producers wait when the buffer is full and consumers wait when it's empty, while maintaining thread safety with the ReentrantLock.

> The order of consumption is FIFO.

> The implementation ensures that the producer will not proceed to add an item after being awakened if the closure happens while it was waiting.

### B. Program using Buffer Class
Source files:
- `task3/Main.java` (main file)
- `task3/Buffer.java`

To compile and execute:
```shell
javac Main.java Buffer.java
java  Main
```

> This program ensures that the producer and consumer work concurrently on the shared buffer, with proper handling of the buffer's closure and thread termination.

## Task 4: Counting Semaphore

### A. Implementation
Source files:
- `task4/CountingSemaphore.java`

To compile and execute:
```shell
javac CountingSemaphore.java
```

> The semaphore’s count can be negative and does not have a minimum value constraint other than the practical constraint of how many threads can be waiting. The negative value represents the number of threads waiting for a resource, with count tracking the balance of available versus used resources.

> Handle spurious wake-ups by using a loop to recheck the condition after being awakened, ensuring that it only proceeds when the condition is truly met.

### B. Program using Counting Semaphore
Source files:
- `task4/Main.java` (main file)
- `task4/CountingSemaphore.java`

To compile and execute:
```shell
javac Main.java CountingSemaphore.java
java  Main
```

> The program demonstrates the use of the semaphore with a producer thread that signals the semaphore and a consumer thread that waits for the semaphore.

> The implementation provide options to adjust task durations of the producer and the consumer.

> When the producer's task duration is set to 1000ms and the consumer's task duration to 250ms, the consumers will operate faster than the producer. This setup naturally results in the consumers frequently waiting for the producer to generate new items, as they will quickly consume all available resources. This configuration is typical in a producer-consumer scenario, where consumers must occasionally wait for production to catch up.

## Task 5: Dining Philosophers

### A. Model the Dining Philosophers
Source files:
- `task5/MainA.java` (main file)

To compile and execute:
```shell
javac MainA.java
java  Main
```

### B. Solution Implementation
Source files:
- `task5/MainB.java` (main file)

To compile and execute:
```shell
javac MainB.java
java  Main
```