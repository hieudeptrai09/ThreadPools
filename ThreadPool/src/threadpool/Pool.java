package threadpool;

import java.util.LinkedList;

public interface Pool {

    void execute();
    void add(Runnable task) throws InterruptedException;
    Runnable delete();
    void shutdown();
    int size();
    void resize(int newSize);
    int getMaxCapacity();
    int getCapacity();
    
}
