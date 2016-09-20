package org.webonise.multithreading.threadcreationexamples;

public class Application {

    public void start(){
        createMultipleThreadsAndStart();
    }

    private void createMultipleThreadsAndStart(){
        Thread t1 = new Thread(new WorkerThreadUsingRunnable(), "t1");
        Thread t2 = new WorkerThreadUsingThreadClass("t2");

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
