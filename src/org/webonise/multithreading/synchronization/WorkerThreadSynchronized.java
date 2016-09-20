package org.webonise.multithreading.synchronization;

public class WorkerThreadSynchronized implements Runnable {

    private ThreadPrinterSychronized threadPrinter;

    WorkerThreadSynchronized(ThreadPrinterSychronized threadPrinter){
        this.threadPrinter = threadPrinter;
    }

    @Override
    public void run() {
        threadPrinter.printThreadAccessSequence();
    }
}
