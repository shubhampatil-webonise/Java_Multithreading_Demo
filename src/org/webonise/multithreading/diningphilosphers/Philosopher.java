package org.webonise.multithreading.diningphilosphers;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

    private final int philosopherId;
    private final ReentrantLock leftChopstick;
    private final ReentrantLock rightChopstick;
    private boolean hasFinishedEating;

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
        System.out.println("Philosopher " + philosopherId + " will now finished eating.");
    }

    private void think() throws InterruptedException{
        System.out.println("Philosopher " + philosopherId + " is now thinking.");
        Thread.sleep(new Random().nextInt(1000));
    }

    private void eat() throws InterruptedException{
        System.out.println("Philosopher " + philosopherId + " is now eating.");
        Thread.sleep(new Random().nextInt(1000));
    }

    private boolean acquireLeftChopstick(){
        if(leftChopstick.tryLock()){
            System.out.println("Philosopher " + philosopherId + " has picked up left chopstick.");
            return true;
        }
        return false;
    }

    private boolean acquireRightChopstick(){
        if(rightChopstick.tryLock()){
            System.out.println("Philosopher " + philosopherId + " has picked up right chopstick.");
            return true;
        }
        return false;
    }

    private void releaseLeftChopstick(){
        if(leftChopstick.isHeldByCurrentThread()){
            leftChopstick.unlock();
            System.out.println("Philosopher " + philosopherId + " has put down left chopstick.");
        }
    }

    private void releaseRightChopstick(){
        if(rightChopstick.isHeldByCurrentThread()){
            rightChopstick.unlock();
            System.out.println("Philosopher " + philosopherId + " has put down right chopstick.");
        }
    }
}
