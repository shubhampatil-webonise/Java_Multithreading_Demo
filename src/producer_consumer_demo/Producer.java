package producer_consumer_demo;

import java.util.Queue;

public class Producer implements Runnable {
    Queue<Integer> queue;

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
                System.out.println(e.getMessage());
            }
        }
    }
}
