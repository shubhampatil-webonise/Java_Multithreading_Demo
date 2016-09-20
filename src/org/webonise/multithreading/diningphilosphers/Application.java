package org.webonise.multithreading.diningphilosphers;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class Application {

    private static final int NUMBER_OF_PHILOSOPHERS = 5;

    private final Philosopher[] philosophers;
    private final ReentrantLock[] chopsticks;
    private final Thread[] threads;

    Application(){
        this.philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
        this.chopsticks = new ReentrantLock[NUMBER_OF_PHILOSOPHERS];
        this.threads = new Thread[NUMBER_OF_PHILOSOPHERS];
        Arrays.fill(chopsticks, new ReentrantLock());
    }


    public void start(){
        assignChopsticksToPhilosophersAndStartThreads();
    }

    private void assignChopsticksToPhilosophersAndStartThreads(){
        for(int i=0; i < NUMBER_OF_PHILOSOPHERS; i++){
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1)%NUMBER_OF_PHILOSOPHERS]);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }

        try{
            Thread.sleep(10000);
            for (int i=0; i < NUMBER_OF_PHILOSOPHERS; i++){
                philosophers[i].finishEating();
                threads[i].join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
