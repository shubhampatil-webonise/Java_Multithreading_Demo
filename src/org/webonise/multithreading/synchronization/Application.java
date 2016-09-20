package org.webonise.multithreading.synchronization;

public class Application {

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

        System.out.println("\nResult of access without synchronization :");

        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(new WorkerThread(threadPrinter), "Thread " + i);
            threads[i].start();
        }

        for (int i=0; i<threads.length; i++){
            try {
                threads[i].join();
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void runThreadsWithSynchronization(){
        Thread[] threads = new Thread[5];
        ThreadPrinterSychronized threadPrinter = new ThreadPrinterSychronized();

        System.out.println("\nResult of access with synchronization :");

        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(new WorkerThreadSynchronized(threadPrinter), "Thread " + i);
            threads[i].start();
        }

        for (int i=0; i<threads.length; i++){
            try {
                threads[i].join();
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
