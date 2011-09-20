package blank.trash;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class TestThread implements Runnable {
	public LinkedList<String> liste = new LinkedList<String>();
	public Semaphore sem = new Semaphore(1);
	public static int dingsbums = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestThread();
	}

	public TestThread() {
		for (int i = 0; i < 1000; i++) {
			liste.add("sdhdfshgajkf" + Math.random());
		}

		for (int i = 0; i < 10000; i++) {
			Thread t = new Thread(this);
			dingsbums = i;
			t.start();
		}

	}

	@Override
	public void run() {
		int wurst = TestThread.dingsbums;
		while (true) {

			try {
				sem.acquire();
				liste.add("sdhdfshgajkf" + Math.random());
				liste.remove(0);
				sem.release();
				System.out.println(wurst + ": " + liste.size());
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
