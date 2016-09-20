package org.webonise.multithreading.synchronization;

public class ThreadPrinter {

    public void printThreadAccessSequence(){
        String threadName = Thread.currentThread().getName();
        System.out.println("[ Enter : " + threadName);

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println(" Interrupted : " + threadName + " ]");
        }

        System.out.println(" Exit : " + threadName + " ]");
    }
}
