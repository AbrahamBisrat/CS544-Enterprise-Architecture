package edu.miu.cs.cs544.exercise15_1;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class BillingServiceImpl implements BillingService {

    @Override
    @Scheduled(cron="0/7 * * * * *")
    public void printBills() {
        Date date = Calendar.getInstance().getTime();
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        String currentTime = timeFormatter.format(date);

        System.out.println("Print : currentTime = " + currentTime);
    }

    @Override
    @Scheduled(cron = "0/10 * * * * *")
    public void generateBillingReport() {
        Date date = Calendar.getInstance().getTime();
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        String currentTime = timeFormatter.format(date);

        System.out.println("Report : currentTime = " + currentTime);
    }

}
