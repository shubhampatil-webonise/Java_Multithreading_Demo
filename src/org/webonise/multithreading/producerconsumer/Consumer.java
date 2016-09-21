package org.webonise.multithreading.producerconsumer;

import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable{
    private final Queue<Integer> queue;
    private static final Logger logger = Logger.getLogger(Consumer.class.getName());

    Consumer(Queue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            fetchResourceFromQueue();
        }
    }

    private void fetchResourceFromQueue(){
        boolean isResourceSet = false;
        int resource = 0;

        synchronized (queue){
            if(!queue.isEmpty()){
                resource = queue.remove();
                isResourceSet = true;
                queue.notify();
            }else{
                try {
                    queue.wait();
                }catch (InterruptedException e){
                    logger.log(Level.SEVERE, e.getMessage());
                }
            }
        }

        if(isResourceSet){
            processResource(resource);
        }
    }

    private void processResource(int resource){
        String threadName = Thread.currentThread().getName();
        logger.log(Level.INFO, threadName + " processing resource :" + resource);

        try {
            Thread.sleep((new Random().nextInt(5))*1000);
        }catch (InterruptedException e){
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
