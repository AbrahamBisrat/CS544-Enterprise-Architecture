package edu.miu.cs.cs544.scheduling.jdk5;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelloWorldTask implements Runnable {
	public void run() {
		Date date = Calendar.getInstance().getTime();
		DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
		String currenttime = timeFormatter.format(date);
		System.out.println("This task ran at " + currenttime);
	}
}
