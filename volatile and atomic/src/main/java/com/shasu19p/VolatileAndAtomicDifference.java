package com.shasu19p;

import java.util.concurrent.atomic.AtomicBoolean;

public class VolatileAndAtomicDifference {

	// Problem: Consider, we are running two thread and one is executing in loop and
	// will end only when
	// another thread running in parallel makes the condition false. It means, both
	// threads are sharing one common resource.
	// As we know, each thread maintains it's own thread cache, so this looks
	// problematic.

	// How can we solve this?
	// We can make common resource as volatile, which means giving instructions to
	// JVM to read latest
	// value of common resource (flag)

	public static void main(String[] args) {

		final AtomicBoolean flag = new AtomicBoolean(true);

		new Thread(new Runnable() {
			public void run() {

				while (flag.get()) {
					System.out.println("Running thread1");
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread 2 making condition false");
				flag.set(false);
			}
		}).start();

	}
}
