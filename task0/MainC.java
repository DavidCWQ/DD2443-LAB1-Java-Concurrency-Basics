import java.util.ArrayList;

public class MainC {
	public static class Incrementer implements Runnable {
		private final int threadNumber;

        public Incrementer(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        public void run() {
			System.out.println("Thread n°" + this.threadNumber + "(ThreadID " + (new Thread(this)).getId()  + ") started, hello world!");
		}
	}

	long run_experiments(int n) {
		long startTime = System.nanoTime();

		ArrayList<Thread> threads = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			Thread thread = new Thread(new Incrementer(i));
			threads.add(thread);
			thread.start();
		}

		for (Thread thread : threads) {
			try {
				thread.join(); // Attend la fin du thread
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.nanoTime();
		return endTime - startTime;

	}

	public static void main(String [] args) {
		MainC mainc = new MainC();
		System.out.println(mainc.run_experiments(5));
		System.out.println("Goodbye");
	}
}
