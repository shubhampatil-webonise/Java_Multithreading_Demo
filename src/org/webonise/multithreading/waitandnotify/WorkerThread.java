package org.webonise.multithreading.waitandnotify;

import java.util.Scanner;

public class WorkerThread implements Runnable {

    @Override
    public void run() {
        synchronized (this){
            System.out.println("In worker thread. Launched. Starting its job ... ");

            System.out.println("Press enter to notify main thread :");
            new Scanner(System.in).nextLine();

            System.out.println("In worked thread. Finished its job. Notifying main thread and exiting ...");
            notify();
        }
    }
}
