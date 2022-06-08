package multithreading;

import java.util.Arrays;
import java.util.MissingResourceException;

public class ThreadPool<T> {
    private static final int remainingThread = 2;
    private int numThreads;
    private Thread[] threads;
    private ParamGetter<T> getter;
    private Runnable<T> target;

    public interface ParamGetter<T> {
        T get();
    }

    public interface Runnable<T> {
        boolean run(T params);
    }

    public ThreadPool() {
        this.numThreads = Runtime.getRuntime().availableProcessors() - remainingThread;
        if (numThreads < 2) numThreads = 1;
    }

    public int getNumThreads() {
        return this.numThreads;
    }

    public boolean isRunning() {
        if (threads == null)
            return false;

        return Arrays.stream(threads).anyMatch(Thread::isAlive);
    }

    public ThreadPool<T> setNumThreads(int numT) {
        if (numT < 0)
            throw new IllegalArgumentException("Invalid number of threads (below 0)");

        this.numThreads = numT;
        return this;
    }

    public ThreadPool<T> setTarget(Runnable<T> target) {
        if (target == null)
            throw new IllegalArgumentException("target can't be null");

        this.target = target;
        return this;
    }

    public ThreadPool<T> setGetter(ParamGetter<T> getter) {
        if (getter == null)
            throw new IllegalArgumentException("getter can't be null");

        this.getter = getter;
        return this;
    }

    public void execute() {
        if (isRunning())
            throw new UnsupportedOperationException("Thread is already running");

        try {
            if (this.getter == null)
                throw new MissingResourceException("Missing Resource", ParamGetter.class.getName(), "");
            if (this.target == null)
                throw new MissingResourceException("Missing Resource", Runnable.class.getName(), "");

            threads = new Thread[numThreads];
            for (int i = 0; i < numThreads; i++) {
                this.threads[i] = new Thread(() -> {
                    while (target.run(getter.get())) ;
                });
            }

            for (Thread thread : threads) {
                thread.start();
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("This resource is missing" + e.getClassName());
        }
    }

    public void join() {
        if (!isRunning()) {
            this.threads = null;
            return;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            }
            catch (Exception e) {
            }
        }
        this.threads = null;
    }


}
