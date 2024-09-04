import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

	private final Deque<Integer> buffer;
	private final Integer capacity;
	private final Lock lock;
	private final Condition notFull;
	private final Condition notEmpty;
	private Boolean isClosed;

	public Buffer(int size) {
		this.capacity = size;
        this.buffer = new LinkedList<>();
		this.lock = new ReentrantLock();
		this.notFull = lock.newCondition();
		this.notEmpty = lock.newCondition();
		this.isClosed = false;
    }

	void add(int i) {
		lock.lock();
		try{
			// Initial check if buffer is closed
			if (isClosed) {
				throw new IllegalStateException("Buffer is closed, add request rejected.");
			}
			// Wait if the buffer is full
			while (buffer.size() == capacity) {
				notFull.await(); // Wait until space becomes available
				// Recheck if buffer has been closed while waiting
				if (isClosed) {
					throw new IllegalStateException("Buffer is closed, awaiting add request rejected.");
				}
			}
			// Add the item to the buffer
			buffer.addLast(i); // FIFO (Queue behavior), use addFirst(i) for LIFO
			notEmpty.signal(); // Signal that the buffer is not empty
		} catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
			lock.unlock();
		}
	}

	public int remove() {
		lock.lock();
		try{
			while (buffer.isEmpty()) { // If the buffer is empty
				if (isClosed) {
					throw new IllegalStateException("Buffer is closed & empty, remove request rejected.");
				}
				notEmpty.await(); // Wait until an item becomes available
			}
			// Remove the item from the buffer
			int val = buffer.removeFirst(); // FIFO (Queue behavior), use removeLast() for LIFO
			notFull.signal(); // Signal that the buffer is not full
			return val;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
	}

	public void close() {
		lock.lock();
		try{
			if (isClosed) {
				throw new IllegalStateException("Buffer is already closed.");
			}
			// Set the flag to true if the buffer is not already closed
			isClosed = true;
			// Inform waiting threads of the change (the buffer is now closed)
			notFull.signalAll(); // Wake up all threads waiting to add
			notEmpty.signalAll(); // Wake up all threads waiting to remove
		} finally {
			lock.unlock();
		}
	}
}
