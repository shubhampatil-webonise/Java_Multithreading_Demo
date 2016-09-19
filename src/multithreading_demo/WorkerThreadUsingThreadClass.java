package multithreading_demo;

public class WorkerThreadUsingThreadClass extends Thread {

    WorkerThreadUsingThreadClass(String threadName){
        super(threadName);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for(int i=0; i < 100; i++){
            System.out.println("Thread " + threadName + " says :" + i);
        }
    }
}
