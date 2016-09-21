package org.webonise.multithreading.diningphilosphers;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable {

    private final int philosopherId;
    private final ReentrantLock leftChopstick;
    private final ReentrantLock rightChopstick;
    private boolean hasFinishedEating;
    private static final Logger logger = Logger.getLogger(Philosopher.class.getName());

    Philosopher(int philosopherId, ReentrantLock leftChopstick, ReentrantLock rightChopstick){
        this.philosopherId = philosopherId;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.hasFinishedEating = false;
    }

    @Override
    public void run() {
        try{
            while(!hasFinishedEating){
                think();

                if(acquireLeftChopstick()){
                    if(acquireRightChopstick()){
                        eat();
                        releaseRightChopstick();
                    }

                    releaseLeftChopstick();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void finishEating(){
        hasFinishedEating = true;
        logger.log(Level.INFO, "Philosopher " + philosopherId + " will now finish eating.");
    }

    private void think() throws InterruptedException{
        logger.log(Level.INFO, "Philosopher " + philosopherId + " is now thinking.");
        Thread.sleep(new Random().nextInt(1000));
    }

    private void eat() throws InterruptedException{
        logger.log(Level.INFO, "Philosopher " + philosopherId + " is now eating.");
        Thread.sleep(new Random().nextInt(1000));
    }

    private boolean acquireLeftChopstick(){
        if(leftChopstick.tryLock()){
            logger.log(Level.INFO, "Philosopher " + philosopherId + " has picked up left chopstick.");
            return true;
        }
        return false;
    }

    private boolean acquireRightChopstick(){
        if(rightChopstick.tryLock()){
            logger.log(Level.INFO, "Philosopher " + philosopherId + " has picked up right chopstick.");
            return true;
        }
        return false;
    }

    private void releaseLeftChopstick(){
        if(leftChopstick.isHeldByCurrentThread()){
            leftChopstick.unlock();
            logger.log(Level.INFO, "Philosopher " + philosopherId + " has put down left chopstick.");
        }
    }

    private void releaseRightChopstick(){
        if(rightChopstick.isHeldByCurrentThread()){
            rightChopstick.unlock();
            logger.log(Level.INFO, "Philosopher " + philosopherId + " has put down right chopstick.");
        }
    }
}
