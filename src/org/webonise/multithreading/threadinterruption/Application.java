package org.webonise.multithreading.threadinterruption;

public class Application {

    public void start() {
        Thread thread = new Thread(new WorkerThread());
        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
