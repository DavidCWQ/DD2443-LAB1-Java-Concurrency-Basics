public class MainC {
	private static long sharedCounter;
	private static final int INCREMENT = 1_000_000;
	private static boolean done=false;
	private static final Object lock = new Object();

	public static class Incrementer implements Runnable {
		private long completionTime;

		public void run() {
			for (int i = 0; i < INCREMENT; i++) {
				MainC.sharedCounter++;
			}
			done = true;
			completionTime = System.nanoTime();
			synchronized (lock) {
				lock.notify();
			}
		}
		public long getCompletionTime() {
			return completionTime;
		}
	}

	public static class Printer implements Runnable {
		private long notificationTime;
		public void run() {
			synchronized (lock) {
				while(!done) {
					try {
						lock.wait(); // Wait until notified by Incrementer thread
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				notificationTime = System.nanoTime();
			}
			System.out.println(sharedCounter);
		}
		public long getNotificationTime() {
			return notificationTime;
		}
	}
	public static long run() {
		MainC.sharedCounter = 0;
		done = false;
		Incrementer incrementer = new Incrementer();
		Printer printer = new Printer();
		Thread workingThread  = new Thread(incrementer);
		Thread waitingThread  = new Thread(printer);

		workingThread.start();
		waitingThread.start();

		try {
			workingThread.join();
			waitingThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return printer.getNotificationTime()-incrementer.getCompletionTime();
	}

	public static void main(String [] args) {
		MainC.run();
		System.out.println(sharedCounter);
	}
}
