package org.webonise.multithreading.synchronization;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadPrinter {
    private static final Logger logger = Logger.getLogger(ThreadPrinter.class.getName());

    public void printThreadAccessSequence(){
        String threadName = Thread.currentThread().getName();
        logger.log(Level.INFO, "[ Enter : " + threadName);

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            logger.log(Level.SEVERE, "Interrupted : " + threadName + " ]");
        }

        logger.log(Level.INFO, "Exit : " + threadName + " ]");
    }
}
