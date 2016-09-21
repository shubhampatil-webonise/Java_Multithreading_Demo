package org.webonise.multithreading.waitandnotify;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerThread implements Runnable {
    private final SharedItem sharedItem;
    private static final Logger logger = Logger.getLogger(WorkerThread.class.getName());

    WorkerThread(SharedItem sharedItem) {
        this.sharedItem = sharedItem;
    }

    @Override
    public void run() {
        synchronized (sharedItem) {

            logger.log(Level.INFO, "In worker thread. Launched. Starting its job ... ");
            sharedItem.setEntity(0);
            logger.log(Level.INFO, "In worked thread. SharedItem changed.");
            logger.log(Level.INFO, "Press enter to notify main thread :");
            new Scanner(System.in).nextLine();
            logger.log(Level.INFO, "In worked thread. Finished its job. Notifying main thread and exiting ...");
            sharedItem.notify();
        }
    }
}
