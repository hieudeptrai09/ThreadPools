package threadpool;

import java.util.LinkedList;

public class ThreadPool {
    private LinkedList<Runnable> pool;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;

    public ThreadPool(int capacity) {
        pool = new LinkedList<>();
        this.capacity = capacity;
    }
    
    public ThreadPool() {
        pool = new LinkedList<>();
        this.capacity = DEFAULT_CAPACITY;
    }
    
    public void execute () {
        Runnable mission = pool.remove();
        mission.run();
    }
    
    public void add (Runnable mission) {
        if (pool.size() < capacity) {
            pool.add(mission);
        } else {
            throw new IllegalStateException("Queue is overloading");
        }
    }
    
    public Runnable delete () {
        return pool.remove();
    }
    
    public void shutdown () {
        
    }
    
    public int size() {
        return pool.size();
    }
    
    public void resize (int newSize) {
        capacity = newSize;
    }
}
