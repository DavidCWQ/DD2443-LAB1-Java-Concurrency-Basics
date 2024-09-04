public class MainB {
	private static long sharedCounter;
	private static final int INCREMENT = 1_000_000;
	private static volatile boolean done;

	public static class Incrementer implements Runnable {
		private long completionTime;
		private long startTime;

		public void run() {
			this.startTime = System.nanoTime();
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

		public long getStartTime() {
			return this.startTime;
		}
	}

	public static class Printer implements Runnable {
		private long notificationTime;
		private boolean isQuiet;
		public void run() {
			while (!done) {}
			notificationTime = System.nanoTime();
			if (!isQuiet) {
				System.out.println(sharedCounter);
			}
		}
		public long getNotificationTime() {
			return notificationTime;
		}
		public void setQuiet() {
			this.isQuiet = true;
		}
	}
	public static void run(boolean isQuiet, long[] data) {
		MainB.sharedCounter = 0;
		done = false;
		Incrementer incrementer = new Incrementer();
		Printer printer = new Printer();
		if (isQuiet) {
			printer.setQuiet();
		}
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
		data[0] = printer.getNotificationTime()-incrementer.getCompletionTime();
		data[1] = printer.getNotificationTime()-incrementer.getStartTime();
	}

	public static void main(String [] args) {
		run(false, new long[2]);
	}
}
