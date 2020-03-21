package com.shasu19p;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class UsingCompletableFuture2 {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		System.setOut(new PrintStream("Data.txt"));

		System.out.println("****** Main Thread Start *******");
		for (int i = 0; i < 10; i++) {
			CompletableFuture.runAsync(new MyThread(i), new MyThread(i));
		}

		Thread.sleep(3000);
		System.out.println("****** Main Thread End *******");
	}

	static class MyThread implements Runnable, Executor {

		int id = 0;

		public MyThread(int counter) {
			id = counter;
		}

		public void run() {
			System.out.println("Running: " + id);
		}

		public void execute(Runnable command) {
			System.out.println("Executing: " + id);
		}
	}
}
