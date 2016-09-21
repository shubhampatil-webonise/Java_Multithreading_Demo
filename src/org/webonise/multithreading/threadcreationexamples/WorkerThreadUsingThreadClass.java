package org.webonise.multithreading.threadcreationexamples;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerThreadUsingThreadClass extends Thread {
    private static final Logger logger = Logger.getLogger(WorkerThreadUsingThreadClass.class.getName());
    WorkerThreadUsingThreadClass(String threadName){
        super(threadName);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for(int i=0; i < 100; i++){
            logger.log(Level.INFO, "Thread " + threadName + " says :" + i);
        }
    }
}
