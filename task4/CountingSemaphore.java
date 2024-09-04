public class CountingSemaphore {

	private Integer count;
	private final Object lock = new Object(); // Lock object for synchronization

	public CountingSemaphore(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Initial count must be non-negative.");
		}
		count = n;
	}

	public void signal() throws InterruptedException {
		synchronized (lock) {
			count++;  // Increment the semaphore count
			// If count was negative, notify a waiting thread
			if (count <= 0) {
				lock.notify(); // Wake up a waiting thread
			}
		}
	}

	public void s_wait() throws InterruptedException {
		synchronized (lock) {
			count--; // Decrement the semaphore count
			// DEBUG: if 'count--' is the last step, then 'count <= 0"
			while (count < 0) {
				// If the count < 0, the thread will call wait(),
				// releasing the lock and entering a waiting state.
				lock.wait(); // Wait until a resource is available
			}
		}
	}

	public int getCount() {
		synchronized (lock) {
			return count;
		}
	}
}
