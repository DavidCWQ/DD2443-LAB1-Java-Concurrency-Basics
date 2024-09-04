# Lab 1 - Basic Concurrency in Java
- Group 18
- Simon Dussud & Wenqi Cao

## Task 1: Simple Synchronization

### Task 1a: Race Conditions
Source files:
- `task1/MainA.java` (main file)

To compile and execute:
```shell
javac MainA.java
java Main
```

### Task 1b: Synchronized Keyword
Source files:
- `task1/MainB.java` (main file)

To compile and execute:
```shell
javac MainB.java
java Main
```

### Task 1c: Synchronization Performance
Source files:
- `task1/MainC.java` (main file)

To compile and execute:
```shell
javac MainC.java
java Main <N>
```
> Where `N` is number of threads to execute with.

In figure 1, we see how the execution time scaled with the number of threads:

![My plot for task 1c](data/task2c.png)

## Task 2: Guarded Blocks using `wait()/notify()`

### Task 2a: Asynchronous Sender-Receiver
Source files:
- `task2/MainA.java` (main file)

To compile and execute:
```shell
javac MainA.java
java Main
```

### Task 2b: Busy-waiting Receiver
Source files:
- `task2/MainB.java` (main file)

To compile and execute:
```shell
javac MainB.java
java Main
```

### Task 2c: Waiting with Guarded Block
Source files:
- `task2/MainC.java` (main file)

To compile and execute:
```shell
javac MainC.java
java Main
```

### Task 2d: Guarded Block Performance
Source files:
- `task2/MainD.java` (main file)

To compile and execute:
```shell
javac MainD.java
java Main
```

## Task 3: Producer-Consumer Buffer using Condition Variables

### Task 3a: Implementation
Source files:
- `task3/Buffer.java`

To compile and execute:
```shell
javac Buffer.java
```

### Task 3b: Program using Buffer Class
Source files:
- `task3/Main.java` (main file)
- `task3/Buffer.java`

To compile and execute:
```shell
javac Main.java Buffer.java
java Main
```

## Task 4: Counting Semaphore

### Task 4a: Implementation
Source files:
- `task4/CountingSemaphore.java`

To compile and execute:
```shell
javac CountingSemaphore.java
```

### Task 4b: Program using Counting Semaphore
Source files:
- `task4/Main.java` (main file)
- `task4/CountingSemaphore.java`

To compile and execute:
```shell
javac Main.java CountingSemaphore.java
java Main
```

## Task 5: Dining Philosophers

### Task 5a: Model the Dining Philosophers
Source files:
- `task5/MainA.java` (main file)

To compile and execute:
```shell
javac MainA.java
java Main
```

### Task 5b: Solution Implementation
Source files:
- `task5/MainB.java` (main file)

To compile and execute:
```shell
javac MainB.java
java Main
```