package threadpool;

import java.util.LinkedList;

public class ThreadPool implements Pool {

    private LinkedList<Runnable> pool;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;
    private final int MAX_CAPACITY = 30;
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

    @Override
    public synchronized void execute() {
        Runnable task = pool.remove();
        task.run();
    }

    @Override
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

    @Override
    public synchronized Runnable delete() {
        return pool.remove();
    }

    @Override
    public void shutdown() {
    }

    @Override
    public int size() {
        return pool.size();
    }

    @Override
    public synchronized void resize(int newSize) {
        if (newSize > pool.size()) {
            if (newSize > MAX_CAPACITY) {
                throw new IllegalArgumentException("The size is greater than the max capacity");
            }
            capacity = newSize;
        } else {
            throw new IllegalArgumentException("This size you want to resize is smaller than the size of queue now");
        }
    }

    @Override
    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

}
