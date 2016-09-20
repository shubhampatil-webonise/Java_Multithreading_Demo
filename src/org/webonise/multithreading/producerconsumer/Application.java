package org.webonise.multithreading.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class Application {

    private final Queue<Integer> queue;

    Application(){
        queue = new LinkedList<>();
    }

    public void start(){

        Thread producer = new Thread(new Producer(queue), "Producer");
        Thread firstConsumer = new Thread(new Consumer(queue), "Consumer 1");
        Thread secondConsumer = new Thread(new Consumer(queue), "Consumer 2");

        producer.start();
        firstConsumer.start();
        secondConsumer.start();

        try {
            producer.join();
            firstConsumer.join();
            secondConsumer.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
