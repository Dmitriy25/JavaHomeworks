package org.example;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {

    private final int n;
    private static final AtomicInteger currentNumber = new AtomicInteger(1);
    private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public ConcurrentLinkedQueue<String> getQueue() {
        return queue;
    }

    public boolean checkN() {
        return currentNumber.get() <= n;
    }

    public synchronized void caseFizz() {
        while (checkN()) {
            if (currentNumber.get() % 3 == 0 && currentNumber.get() % 5 != 0) {
                queue.add("Fizz");
                currentNumber.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void caseBuzz() {
        while (checkN()) {
            if (currentNumber.get() % 3 != 0 && currentNumber.get() % 5 == 0) {
                queue.add("Buzz");
                currentNumber.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void caseFizzBuzz() {
        while (checkN()) {
            if (currentNumber.get() % 15 == 0) {
                queue.add("FizzBuzz");
                currentNumber.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void caseNumber() {
        while (checkN()) {
            if (currentNumber.get() % 5 != 0 && currentNumber.get() % 3 != 0) {
                queue.add(String.valueOf(currentNumber.get()));
                currentNumber.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}