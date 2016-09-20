package org.webonise.multithreading.concurrentmodificationexception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {
    private List<Integer> list;

    Application(){
        this.list = new ArrayList<>();

        for(int i=0; i<100; i++){
            list.add(i);
        }
    }

    public void start(){
        iterateListAndLaunchThreadToModify();
    }

    private void iterateListAndLaunchThreadToModify(){

        Iterator iterator = list.iterator();
        Thread thread = new Thread(new WorkerThread(list));
        thread.start();

        try{
            while (iterator.hasNext()){
                System.out.println(iterator.next());
                Thread.sleep(100);
            }

            thread.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
