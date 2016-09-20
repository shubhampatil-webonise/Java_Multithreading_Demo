package org.webonise.multithreading.threadcreationexamples;

public class WorkerThreadUsingRunnable implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for(int i=0; i < 100; i++){
            System.out.println("Thread " + threadName + " says :" + i);
        }
    }
}
