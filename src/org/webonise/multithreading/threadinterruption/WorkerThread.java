package org.webonise.multithreading.threadinterruption;

public class WorkerThread implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.println("Worker thread is running.");
            } catch (InterruptedException e) {
                System.out.print("Interrupting worker thread.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
