package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer {
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        long programStartTime = System.currentTimeMillis();
        executorService.scheduleAtFixedRate(
                () -> System.out.println((System.currentTimeMillis() - programStartTime) / 1000),
                1,
                1,
                TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(
                () -> System.out.println("Прошло 5 секунд"),
                5,
                5,
                TimeUnit.SECONDS);
        Thread.sleep(20000);
        executorService.shutdown();
    }
}