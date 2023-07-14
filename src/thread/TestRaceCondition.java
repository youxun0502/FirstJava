package thread;

import java.util.ArrayList;
import java.util.List;

import collection.Employee;

public class TestRaceCondition {

	public static int counter = 0;

	public static void main(String[] args) {

		List<Thread> threads = new ArrayList<>();
		// 產生threads，加入ArrayList
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public synchronized void run() {
					//這裡的synchronized,鎖頭是Runnable物件,10個Runnable物件，沒有作用
					for (int i = 0; i < 1000; i++) {
						TestRaceCondition.vote();
					}
				}
			};
			
			Thread t1 = new Thread(runnable, "T" + i);
			threads.add(t1);
		}
		// 一起發動所有的thread
		for (Thread thread : threads) {
			thread.start();
		}
		try {
			// main thread 等待所有執行緒結束
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 印出最後的值
		System.out.println("finally counter = " + counter);

	}

	public static Object Lock = new Object();
	public static Employee emp = new Employee("001");

	public /*synchronized*/ static void vote() {
		// synchronized (TestRaceCondition.class) {
		counter = counter + 1;
	}

}
