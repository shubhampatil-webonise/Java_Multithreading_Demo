package concurrent_modification_exception_demo;
import java.util.List;

public class WorkerThread implements Runnable {
    private List<Integer> list;

    WorkerThread(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        while(!list.isEmpty()){
            list.remove(list.size() - 1);

            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
