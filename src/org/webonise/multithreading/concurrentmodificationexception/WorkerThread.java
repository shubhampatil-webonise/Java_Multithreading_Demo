package org.webonise.multithreading.concurrentmodificationexception;

import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerThread implements Runnable {
    private final List<Integer> list;
    private static final Logger logger = Logger.getLogger(WorkerThread.class.getName());

    WorkerThread(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        ListIterator iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            iterator.previous();
            iterator.remove();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
}
