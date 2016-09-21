package org.webonise.multithreading.waitandnotify;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    private final SharedItem sharedItem;
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public Application() {
        this.sharedItem = new SharedItem();
    }

    public void start(){

        Thread thread = new Thread(new WorkerThread(sharedItem));
        logger.log(Level.INFO, "In main thread. Launching worker thread ...");
        thread.start();

        synchronized (sharedItem) {
            try {
                logger.log(Level.INFO, "In main thread. Waiting for notification from worker thread ...");
                sharedItem.wait();
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }

        logger.log(Level.INFO, "In main thread. Notification received from worker thread. Exiting ...");
    }
}
