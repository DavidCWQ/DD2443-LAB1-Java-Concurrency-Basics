import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainB {
	public static class Incrementer implements Runnable {
		public void run() {
			System.out.println("Thread " + (new Thread(this)).getId() + " started, hello world!");
		}
	}

	long run_experiments(int n) {
		long startTime = System.nanoTime();

		ArrayList<Thread> threads = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			Thread thread = new Thread(new Incrementer());
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
		MainB mainB = new MainB();
		System.out.println(mainB.run_experiments(5));
		System.out.println("Goodbye");
	}
}
