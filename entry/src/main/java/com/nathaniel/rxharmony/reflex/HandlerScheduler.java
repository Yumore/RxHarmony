package com.nathaniel.rxharmony.reflex;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.plugins.RxJavaPlugins;
import ohos.eventhandler.EventHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
final class HandlerScheduler extends Scheduler {
    private final EventHandler handler;

    HandlerScheduler(EventHandler handler) {
        this.handler = handler;
    }

    @Override
    public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
        if (run == null) {
            throw new NullPointerException("run == null");
        }
        if (unit == null) {
            throw new NullPointerException("unit == null");
        }
        run = RxJavaPlugins.onSchedule(run);
        ScheduledRunnable scheduled = new ScheduledRunnable(handler, run);
        handler.postTask(scheduled, unit.toMillis(delay));
        return scheduled;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(handler);
    }

    private static final class HandlerWorker extends Worker {
        private final EventHandler handler;
        private volatile boolean disposed;

        HandlerWorker(EventHandler handler) {
            this.handler = handler;
        }

        @Override
        public Disposable schedule(Runnable run, long delay, TimeUnit unit) {
            if (run == null) throw new NullPointerException("run == null");
            if (unit == null) throw new NullPointerException("unit == null");
            if (disposed) {
                return Disposables.disposed();
            }
            run = RxJavaPlugins.onSchedule(run);
            ScheduledRunnable scheduled = new ScheduledRunnable(handler, run);
            handler.postTask(scheduled, unit.toMillis(delay));
            if (disposed) {
                handler.removeAllEvent();
                return Disposables.disposed();
            }
            return scheduled;
        }

        @Override
        public void dispose() {
            disposed = true;
            handler.removeAllEvent();
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }
    }

    private static final class ScheduledRunnable implements Runnable, Disposable {
        private final EventHandler handler;
        private final Runnable delegate;
        private volatile boolean disposed;

        ScheduledRunnable(EventHandler handler, Runnable delegate) {
            this.handler = handler;
            this.delegate = delegate;
        }

        @Override
        public void run() {
            try {
                delegate.run();
            } catch (Throwable t) {
                RxJavaPlugins.onError(t);
            }
        }

        @Override
        public void dispose() {
            disposed = true;
            handler.removeAllEvent();
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }
    }
}
