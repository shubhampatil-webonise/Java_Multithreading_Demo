package org.webonise.multithreading.producerconsumer;

import java.util.Queue;
import java.util.Random;

public class Consumer implements Runnable{
    private final Queue<Integer> queue;

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
                    e.printStackTrace();
                }
            }
        }

        if(isResourceSet){
            processResource(resource);
        }
    }

    private void processResource(int resource){
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " processing resource :" + resource);

        try {
            Thread.sleep((new Random().nextInt(5))*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
