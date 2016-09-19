package synchronization_demo;

public class WorkerThread implements Runnable {

    private ThreadPrinter threadPrinter;

    WorkerThread(ThreadPrinter threadPrinter){
        this.threadPrinter = threadPrinter;
    }

    @Override
    public void run() {
        threadPrinter.printThreadAccessSequence();
    }
}
