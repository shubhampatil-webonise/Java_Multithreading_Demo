package org.webonise.multithreading.producerconsumer;

import java.util.Queue;

public class Producer implements Runnable {
    private final Queue<Integer> queue;

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
            System.out.println(threadName + " adding resource : " + resource);
            queue.notifyAll();

            try{
                queue.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
