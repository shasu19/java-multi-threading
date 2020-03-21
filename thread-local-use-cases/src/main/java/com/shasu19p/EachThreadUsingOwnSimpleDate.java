package com.shasu19p;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

//https://youtu.be/sjMe9aecW_A?list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6
public class EachThreadUsingOwnSimpleDate {

	public static void main(String[] args) {
		for (final AtomicInteger i = new AtomicInteger(0); i.intValue() < 100000; i.incrementAndGet()) {
			new Thread(new Runnable() {

				public void run() {
					System.out.println(new UserService().birthDate("userId" + i.intValue()));
				}
			}).start();
		}
	}

	private static class UserService {

		public String birthDate(String userId) {

			System.out.println("User details: " + userId);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			return sf.format(new Date());
		}
	}

}