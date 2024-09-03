import java.util.ArrayList;

public class MainD {

	private Data measuringPerformance(int warmUpIterations, int measurementIterations, boolean guardedBlock) {
		Data data = new Data();

		// Warm up phase
		for (int i = 0; i < warmUpIterations; i++) {
			if (guardedBlock) {
				MainC.run();
			} else {
				MainB.run();
			}

		}

		// Measurement phase
		for (int i = 0; i < measurementIterations; i++) {
			if (guardedBlock) {
				data.add(MainC.run());
			} else {
				data.add(MainC.run());
			}

		}
		return data;
	}
	public static void main(String [] args) {
		MainD main = new MainD();
			Data dataGuardedBlock = main.measuringPerformance(1000, 1000, true);
			Data dataBuzyWainting = main.measuringPerformance(1000, 1000, false);
			System.out.println("average time " + dataGuardedBlock.getAverage() + ", standart deviation " +dataGuardedBlock.getStandardDeviation());
			System.out.println("average time " + dataBuzyWainting.getAverage() + ", standart deviation " +dataBuzyWainting.getStandardDeviation());
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