package synchronization_demo;

public class ThreadPrinterSychronized {

    synchronized public void printThreadAccessSequence(){
        String threadName = Thread.currentThread().getName();
        System.out.println("[ Enter : " + threadName);

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println(" Interrupted : " + threadName + " ]");
        }

        System.out.println(" Exit : " + threadName + " ]");
    }
}
