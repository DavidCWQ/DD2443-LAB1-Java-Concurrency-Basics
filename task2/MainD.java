import java.util.ArrayList;

public class MainD {

	private Data measuringPerformance(int warmUpIterations, int measurementIterations, Data delayTime,  boolean guardedBlock) {
		Data executionTime = new Data();
		long[] dataTime = new long[2];

		// Warm up phase
		for (int i = 0; i < warmUpIterations; i++) {
			if (guardedBlock) {
				MainC.run(true, dataTime);
			} else {
				MainB.run(true, dataTime);
			}
		}

		// Measurement phase
		for (int i = 0; i < measurementIterations; i++) {
			if (guardedBlock) {
				MainC.run(true, dataTime);
				delayTime.add(dataTime[0]);
				executionTime.add(dataTime[1]);
			} else {
				MainC.run(true, dataTime);
				delayTime.add(dataTime[0]);
				executionTime.add(dataTime[1]);
			}
		}
		return executionTime;
	}
	public static void main(String [] args) {
		MainD main = new MainD();
		Data delayTime = new Data();
		Data dataGuardedBlock = main.measuringPerformance(10000, 20000,  delayTime,true);
		System.out.println("Delay between completion of increments by the incrementingThread, and printingThreads receiving the notification");
		System.out.println("Guarded block : average time " + delayTime.getAverage() + ", standart deviation " +delayTime.getStandardDeviation());
		Data dataBuzyWainting = main.measuringPerformance(10000, 20000, delayTime, false);
		System.out.println("Busy-waiting : average time " + delayTime.getAverage() + ", standart deviation " +delayTime.getStandardDeviation());
		System.out.println();
		System.out.println("Execution time");
		System.out.println("Guarded block : average time " + dataGuardedBlock.getAverage() + ", standart deviation " +dataGuardedBlock.getStandardDeviation());
		System.out.println("Busy-waiting : average time " + dataBuzyWainting.getAverage() + ", standart deviation " +dataBuzyWainting.getStandardDeviation());


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