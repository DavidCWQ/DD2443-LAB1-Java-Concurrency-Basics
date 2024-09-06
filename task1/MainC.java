import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainC {

	//Shared Integer
	private static int sharedCounter;
	private static final int INCREMENT = 1_000_000;

	public static class Incrementer implements Runnable {

		public void run() {
			for (int i = 0; i < INCREMENT; i++) {
				MainC.incrementSharedCounter();
			}
		}
	}
	private synchronized static void incrementSharedCounter() {
		MainC.sharedCounter++;
	}
	long run_experiment(int n) {
		sharedCounter = 0;
		long startTime = System.nanoTime();
		ArrayList<Thread> threads = new ArrayList<>();

		// Create and start threads
		for (int i = 0; i < n; i++) {
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

		assert sharedCounter==n*INCREMENT;
		return System.nanoTime() - startTime;
	}
	private Data measuringPerformance(int numberOfThreads,  int warmUpIterations, int measurementIterations) {
		Data data = new Data();

		// Warm up phase
		for (int i = 0; i < warmUpIterations; i++) {
			this.run_experiment(numberOfThreads);
		}

		// Measurement phase
		for (int i = 0; i < measurementIterations; i++) {
			data.add(run_experiment(numberOfThreads));
		}
		return data;
	}
	public static void main(String [] args) throws IOException {
		FileWriter writer = new FileWriter("output.txt");
		MainC main = new MainC();
		for (int i = 0; i < 64; i++) {
			Data data = main.measuringPerformance(i+1, 20, 50);

			double x = data.getAverage()/1000000;
			double y = data.getStandardDeviation()/1000000;
			// write the string to the file
			writer.write("" + x + ";" + y + "\n");
			writer.flush();
			// close the writer

		}
		System.out.println("Goodbye");
	}
}
class Data extends ArrayList<Long> {
	public double getAverage() {
		long sum = 0;
		for (long time : this) {
			sum += time;
		}
		return (double) sum / this.size();
	}

	public double getStandardDeviation() {
		double mean = this.getAverage();
		double sum = 0;
		for (long time : this) {
			double diff = time - mean;
			sum += diff * diff;
		}
		return Math.sqrt(sum / this.size());
	}
}