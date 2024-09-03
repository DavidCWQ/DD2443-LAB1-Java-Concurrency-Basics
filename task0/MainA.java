public class MainA {
	public static class Incrementer implements Runnable {
		public void run() {
			System.out.println("Hello world");
		}
	}

	public static void main(String [] args) {
		Thread t = new Thread(new Incrementer());
		t.start();

		 try {
		 	t.join();
		 } catch (InterruptedException e) {
		 	e.printStackTrace();
		 }

	}
}
