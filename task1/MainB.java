import java.util.ArrayList;

public class MainB {

	//Shared Volatile Integer
	private static int sharedCounter = 0;

	public static class Incrementer implements Runnable {
		private static final int INCREMENT = 1_000_000;

		public void run() {
			for (int i = 0; i < INCREMENT; i++) {
				MainB.incrementSharedCounter();
			}
		}
	}
	private synchronized static void incrementSharedCounter() {
		MainB.sharedCounter++;
	}
	public static void main(String [] args) {
		int numberOfThreads = 4;

		ArrayList<Thread> threads = new ArrayList<>();

		// Create and start threads
		for (int i = 0; i < numberOfThreads; i++) {
			Thread thread  = new Thread(new Incrementer());
			threads.add(thread);
			thread.start();
		}

		// Wait for all threads to finish
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Final value of sharedCounter: " + MainB.sharedCounter);
	}
}
