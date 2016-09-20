package org.webonise.multithreading.waitandnotify;

public class Application {

    public void start(){

        Thread thread = new Thread(new WorkerThread());
        System.out.println("In main thread. Launching worker thread ...");
        thread.start();

        synchronized (thread){
            try {
                System.out.println("In main thread. Waiting for notification from worker thread ...");
                thread.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("In main thread. Notification received from worker thread. Exiting ...");
    }
}
