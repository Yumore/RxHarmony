package com.nathaniel.rxharmony.reflex;

import io.reactivex.Scheduler;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;

import java.util.concurrent.Callable;

/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public final class HmOSSchedulers {
    private HmOSSchedulers() {
        throw new AssertionError("No instances.");
    }

    private static final Scheduler MAIN_THREAD = RxHmOSPlugins.initMainThreadScheduler(
            new Callable<Scheduler>() {
                @Override
                public Scheduler call() throws Exception {
                    return MainHolder.DEFAULT;
                }
            });

    public static Scheduler mainThread() {
        return RxHmOSPlugins.onMainThreadScheduler(MAIN_THREAD);
    }

    public static Scheduler from(EventRunner eventRunner) {
        if (eventRunner == null) throw new NullPointerException("eventRunner == null");
        return new HandlerScheduler(new EventHandler(eventRunner));
    }

    private static final class MainHolder {
        static final Scheduler DEFAULT = new HandlerScheduler(new EventHandler(EventRunner.getMainEventRunner()));
    }
}
