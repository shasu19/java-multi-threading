package com.shasu19p;

import java.util.concurrent.CompletableFuture;

public class UsingCompletableFuture {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("****** Main Thread Start *******");
		for (int i = 0; i < 10000; i++) {
			CompletableFuture.runAsync(new MyThread(i));
		}

		Thread.sleep(3000);
		System.out.println("****** Main Thread End *******");
	}

	static class MyThread implements Runnable {

		int id = 0;

		public MyThread(int counter) {
			id = counter;
		}

		public void run() {
			System.out.println(id);
		}
	}
}
