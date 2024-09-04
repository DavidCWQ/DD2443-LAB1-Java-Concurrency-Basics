public class Main {

	private static final CountingSemaphore semaphore = new CountingSemaphore(3); // Initial count of 3

	public static class Producer implements Runnable {

		private final Integer time;

		public Producer() {
			this.time = 1000;
		}

		public Producer(int workload) {
			this.time = workload;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 5; i++) {
					Thread.sleep(time); // Simulate production work
					semaphore.signal(); // Signal (release) a resource
					System.out.println("Produced item " + i + ". " +
							"Semaphore count: " + semaphore.getCount());
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Producer thread was interrupted.");
			}
		}
	}

	public static class Consumer implements Runnable {

		private final Integer time;

		public Consumer() {
			this.time = 2000;
		}

		public Consumer(int workload) {
			this.time = workload;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 5; i++) {
					semaphore.s_wait(); // Wait for a resource (to be produced)
					Thread.sleep(time); // Simulate consumption work
					System.out.println("Consumed item " + i + ". " +
							"Semaphore count: " + semaphore.getCount());
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Consumer thread was interrupted.");
			}
		}
	}

	public static void main(String[] args) {
		// Create producer and consumer tasks
		Producer producer = new Producer(1000);
		Consumer consumer = new Consumer(2000);

		// Create producer and consumer threads
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);

		// Start the threads
		producerThread.start();
		consumerThread.start();

		// Wait for both threads to finish
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			System.out.println("Program interrupted.");
		}

		System.out.println("Program finished.");
	}
}
