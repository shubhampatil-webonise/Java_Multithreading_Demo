package org.webonise.multithreading.concurrentmodificationresolution;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    private final List<Integer> list;
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public Application() {
        list = new CopyOnWriteArrayList<>();
    }

    public void start() {
        populateList();
        generateConcurrentModificationException();
    }

    private void populateList() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
    }

    private void generateConcurrentModificationException() {
        Thread thread = launchThread();
        iterateList();
        waitForThreadToJoin(thread);
        logger.log(Level.INFO, "Main thread is exiting now !");
    }

    private Thread launchThread() {
        Thread thread = new Thread(new WorkerThread(list));
        thread.start();
        return thread;
    }

    private void iterateList() {
        Iterator iterator = list.iterator();

        try {
            while (iterator.hasNext()) {
                logger.log(Level.INFO, iterator.next().toString());
                Thread.sleep(100);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void waitForThreadToJoin(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
