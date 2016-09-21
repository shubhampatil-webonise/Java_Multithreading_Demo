package org.webonise.multithreading.producerconsumer;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {
    private final Queue<Integer> queue;
    private static final Logger logger = Logger.getLogger(Producer.class.getName());

    Producer(Queue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        int resource = 0;

        while(true){
            putResourceIntoQueue(resource++);
        }
    }

    private void putResourceIntoQueue(int resource){

        String threadName = Thread.currentThread().getName();

        synchronized (queue){
            queue.add(resource);
            logger.log(Level.INFO, threadName + " adding resource : " + resource);
            queue.notifyAll();

            try{
                queue.wait(10000);
            }catch (InterruptedException e){
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
}
