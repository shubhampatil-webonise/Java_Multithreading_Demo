package org.webonise.multithreading.synchronization;

public class WorkerThread implements Runnable {

    private final ThreadPrinter threadPrinter;

    WorkerThread(ThreadPrinter threadPrinter){
        this.threadPrinter = threadPrinter;
    }

    @Override
    public void run() {
        threadPrinter.printThreadAccessSequence();
    }
}
