package edu.miu.cs.cs544.scheduling.jdk;

import java.util.Timer;

public class Application {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new HelloWorldTask(), 5000, 5000);
	}
}
