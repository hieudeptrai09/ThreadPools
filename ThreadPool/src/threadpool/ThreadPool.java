package threadpool;

import java.util.LinkedList;

public class ThreadPool {

    private LinkedList<Runnable> pool;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;
    private final int MAX_CAPACITY = 3000;
    private int waitingAddCount = 0;
    private int waitingRemoveCount = 0;

    public ThreadPool(int capacity) throws MaxCapacityException {
        pool = new LinkedList<>();
        if (capacity <= MAX_CAPACITY) {
            this.capacity = capacity;
        } else {
            throw new MaxCapacityException();
        }
    }

    public ThreadPool() {
        pool = new LinkedList<>();
        this.capacity = DEFAULT_CAPACITY;
    }

    public synchronized void execute() {
        Runnable task = pool.remove();
        task.run();
    }

    public synchronized void add(Runnable task) throws InterruptedException {
        if (pool.size() < capacity) {
            pool.add(task);
        } else if (pool.size() == capacity) {
            wait();
            ++waitingAddCount;
        } else if (pool.size() == capacity - waitingAddCount) {
            notifyAll();
        }
    }

    public synchronized Runnable delete() {
        return pool.remove();
    }

    public void shutdown() {
        
    }

    public int size() {
        return pool.size();
    }

    public void resize(int newSize) {
        capacity = newSize;
    }
}
