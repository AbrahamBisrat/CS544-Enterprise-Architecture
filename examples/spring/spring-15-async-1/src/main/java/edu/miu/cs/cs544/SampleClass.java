package edu.miu.cs.cs544;

import org.springframework.scheduling.annotation.Async;

public class SampleClass {
	@Async
	public void longRunningMethod() {
		System.out.println("Started longRunningMethod");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished longRunningMethod");
	}
}