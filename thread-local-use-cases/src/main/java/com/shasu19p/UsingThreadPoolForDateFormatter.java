package com.shasu19p;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://youtu.be/sjMe9aecW_A?list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6

// Using lock or synchronized, will slow down the performance of the application.
// So, thread local is better approach.

// Use cases:
// RequestContext handling - Web application flow
// Each thread pool giving some common resource to share

public class UsingThreadPoolForDateFormatter {

	final static ConcurrentHashMap<Integer, Integer> threadIdCount = new ConcurrentHashMap<Integer, Integer>();

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		for (int i = 0; i < 100000; i++) {

			final int id = i;

			// submit also same, but better for callback and future use cases
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println(new UserService3().birthDate(id, threadIdCount));
				}
			});
		}

		Thread.sleep(3000);

		System.out.println("Thread count size : " + threadIdCount.size());
		System.out.println("Thread details : " + threadIdCount);
	}

	private static class UserService3 {

		public String birthDate(int id, ConcurrentHashMap<Integer, Integer> threadIdCount) {

			System.out.println("User details: " + id);

			int idCode = System.identityHashCode(ThreadSafeFormatter.dateFormatter.get());

			if (Objects.isNull(threadIdCount.get(idCode))) {
				threadIdCount.put(idCode, 1);
			} else {
				threadIdCount.put(idCode, threadIdCount.get(idCode) + 1);
			}

			System.out.println(System.identityHashCode(ThreadSafeFormatter.dateFormatter.get()));

			return ThreadSafeFormatter.dateFormatter.get().format(new Date());
		}
	}

	private static class ThreadSafeFormatter {

		public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
			protected SimpleDateFormat initialValue() {
				return new SimpleDateFormat("yyyy-MM-dd");
			}
		};
	}
}