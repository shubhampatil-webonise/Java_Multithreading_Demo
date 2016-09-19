package concurrent_modification_exception_resolution_demo;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Application {
    private List<Integer> list;

    Application(){
        list = new CopyOnWriteArrayList<>();

        for (int i=0; i<100; i++){
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
