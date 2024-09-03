public class MainB {
	private static long sharedCounter;
	private static final int INCREMENT = 1_000_000;
	private static volatile boolean done;

	public static class Incrementer implements Runnable {
		private long completionTime;
		public void run() {
			done = false;
			MainB.sharedCounter = 0;
			for (int i = 0; i < INCREMENT; i++) {
				MainB.sharedCounter++;
			}
			done = true;
			completionTime = System.nanoTime();
		}

		public long getCompletionTime() {
			return completionTime;
		}
	}

	public static class Printer implements Runnable {
		private long notificationTime;
		public void run() {
			while (!done) {}
			notificationTime = System.nanoTime();
			System.out.println(sharedCounter);
		}
		public long getNotificationTime() {
			return notificationTime;
		}
	}
	public static long run() {
		MainB.sharedCounter = 0;
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
		return printer.getNotificationTime() - incrementer.getCompletionTime();
	}

	public static void main(String [] args) {
		run();
	}
}
