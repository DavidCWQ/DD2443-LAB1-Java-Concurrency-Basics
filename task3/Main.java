public class Main {
	public static class Producer implements Runnable {
		private final Buffer buffer;

		public Producer(Buffer buffer) {
			this.buffer = buffer;
		}

		public void run() {
			try {
				for (int i = 0; i < 1_000_000; i++) {
					buffer.add(i);
				}
			} finally {
				buffer.close(); // Close the buffer after producing all items
				System.out.println("Producer has finished producing and closed the buffer.");
			}
		}
	}

	public static class Consumer implements Runnable {
		private final Buffer buffer;

		public Consumer(Buffer buffer) {
			this.buffer = buffer;
		}

		public void run() {
            while (true) {
                try {
                    int value = buffer.remove();
                    System.out.println("Consumed: " + value);
                } catch (IllegalStateException e) {
                    // Handle buffer closed exception
                    System.out.println("Buffer closed, consumer terminating...");
                    break;
                }
            }
        }
	}

	public static void main(String [] args) {
		// Initialize shared buffer with a capacity of 200
		Buffer buffer = new Buffer(200);

		// Create producer and consumer threads
		Thread pThread = new Thread(new Producer(buffer));
		Thread cThread = new Thread(new Consumer(buffer));

		// Start the threads
		pThread.start();
		cThread.start();

		// Wait for both threads to finish
		try {
			pThread.join();
			cThread.join();
		} catch (InterruptedException e) {
			System.out.println("Program interrupted.");
		}

		System.out.println("Program finished.");
	}
}
