---
title: "Report Lab1 - Parallel and Distributed Computing"
author: "Dussud Simon"
date: "04/09/2024"
output:
  html_document: default
  pdf_document: default
---

# Lab 1 - Basic Concurrency in Java
- Group 18
- Simon Dussud and Wenqi Cao

## Task 1: Simple Synchronization

### Task 1a: Race conditions
Source files:

- `task2/MainA.java` (main file)

To compile and execute:
```
javac MainA.java
java Main
```

### Task 1b: Synchronized keyword
Source files:

- `task2/MainB.java` (main file)

To compile and execute:
```
javac MainB.java
java Main
```

### Task 1c: Synchronization performance
Source files:

- `task2/MainC.java` (main file)

To compile and execute:
```
javac MainC.java
java Main <N>
```
Where `N` is number of threads to execute with.

In figure 1, we see how the execution time scaled with the number of threads
...

![My plot for task 2c](data/task2c.png)


## Task 2: Guarded blocks using wait()/notify()
### A. asynchronous sender-receiver
Source files:

- `task2/MainA.java`

To compile and execute:
```
javac MainA.java
java MainA <N>
```

Since the print thread runs in parallel with the increment thread without synchronization, it may print a value between 0 and 1,000,000.

### B. busy-waiting receiver 
Source files:

- `task2/MainB.java`

To compile and execute:=
```
javac MainB.java
java MainB <N>
```

With the while loop to continuously check if 'done' is true, the program works fine and print 1,000,000

### C. waiting with guarded block 
Source files:

- `task2/MainC.java`

To compile and execute:
```
javac MainC.java
java MainC <N>
```

This also works well.
Let's analyse the performance.

### D. Effects on performance


## Task 3: Producer-Consumer Buffer using Condition Variables

## Task 4: Counting Semaphore

## Task 5: Dining Philosophers
