package org.webonise.multithreading.synchronization;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public void start(){
        runThreads();
    }

    private void runThreads(){
        runThreadsWithoutSynchronization();
        runThreadsWithSynchronization();
    }

    private void runThreadsWithoutSynchronization(){
        Thread[] threads = new Thread[5];
        ThreadPrinter threadPrinter = new ThreadPrinter();

        logger.log(Level.INFO, "\nResult of access without synchronization :");

        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(new WorkerThread(threadPrinter), "Thread " + i);
            threads[i].start();
        }

        for (int i=0; i<threads.length; i++){
            try {
                threads[i].join();
            }catch (InterruptedException e){
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    private void runThreadsWithSynchronization(){
        Thread[] threads = new Thread[5];
        ThreadPrinterSychronized threadPrinter = new ThreadPrinterSychronized();
        logger.log(Level.INFO, "\nResult of access with synchronization :");

        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(new WorkerThreadSynchronized(threadPrinter), "Thread " + i);
            threads[i].start();
        }

        for (int i=0; i<threads.length; i++){
            try {
                threads[i].join();
            }catch (InterruptedException e){
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
}
