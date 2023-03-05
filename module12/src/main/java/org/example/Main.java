package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FizzBuzz fizzBuzz = new FizzBuzz(25);

        executorService.submit(fizzBuzz::caseFizz);
        executorService.submit(fizzBuzz::caseBuzz);
        executorService.submit(fizzBuzz::caseFizzBuzz);
        executorService.submit(fizzBuzz::caseNumber);
        executorService.submit(() -> fizzBuzzPrint(fizzBuzz));
        executorService.shutdown();
    }

    private static synchronized void fizzBuzzPrint(FizzBuzz fizzBuzz) {
        while (fizzBuzz.checkN()) {
            if (fizzBuzz.getQueue().isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while (!(fizzBuzz.getQueue().isEmpty())) {
                System.out.println(fizzBuzz.getQueue().poll());
            }
        }
    }
}