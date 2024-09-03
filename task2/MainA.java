public class MainA {

	//Shared Integer
	private static int sharedCounter;
	private static final int INCREMENT = 1_000_000;

	public static class Incrementer implements Runnable {
		public void run() {
			for (int i = 0; i < INCREMENT; i++) {
				MainA.sharedCounter++;
			}
		}
	}

	public static class Printer implements Runnable {
		public void run() {
				System.out.println(sharedCounter);
		}
	}

	private static void run() {
		sharedCounter = 0;
		Thread incrementer  = new Thread(new Incrementer());
		Thread printer  = new Thread(new Printer());

		incrementer.start();
		printer.start();

		try {
			incrementer.join();
			printer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String [] args) {
		run();
	}
}
