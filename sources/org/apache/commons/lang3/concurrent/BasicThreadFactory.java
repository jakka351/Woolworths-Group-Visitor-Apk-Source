package org.apache.commons.lang3.concurrent;

import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public class BasicThreadFactory implements ThreadFactory {
    private final Boolean daemonFlag;
    private final String namingPattern;
    private final Integer priority;
    private final AtomicLong threadCounter;
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final ThreadFactory wrappedFactory;

    private BasicThreadFactory(Builder builder) {
        if (builder.wrappedFactory == null) {
            this.wrappedFactory = Executors.defaultThreadFactory();
        } else {
            this.wrappedFactory = builder.wrappedFactory;
        }
        this.namingPattern = builder.namingPattern;
        this.priority = builder.priority;
        this.daemonFlag = builder.daemonFlag;
        this.uncaughtExceptionHandler = builder.exceptionHandler;
        this.threadCounter = new AtomicLong();
    }

    public final ThreadFactory getWrappedFactory() {
        return this.wrappedFactory;
    }

    public final String getNamingPattern() {
        return this.namingPattern;
    }

    public final Boolean getDaemonFlag() {
        return this.daemonFlag;
    }

    public final Integer getPriority() {
        return this.priority;
    }

    public final Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler;
    }

    public long getThreadCount() {
        return this.threadCounter.get();
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = getWrappedFactory().newThread(runnable);
        initializeThread(threadNewThread);
        return threadNewThread;
    }

    private void initializeThread(Thread thread) {
        if (getNamingPattern() != null) {
            thread.setName(String.format(getNamingPattern(), Long.valueOf(this.threadCounter.incrementAndGet())));
        }
        if (getUncaughtExceptionHandler() != null) {
            thread.setUncaughtExceptionHandler(getUncaughtExceptionHandler());
        }
        if (getPriority() != null) {
            thread.setPriority(getPriority().intValue());
        }
        if (getDaemonFlag() != null) {
            thread.setDaemon(getDaemonFlag().booleanValue());
        }
    }

    public static class Builder implements org.apache.commons.lang3.builder.Builder<BasicThreadFactory> {
        private Boolean daemonFlag;
        private Thread.UncaughtExceptionHandler exceptionHandler;
        private String namingPattern;
        private Integer priority;
        private ThreadFactory wrappedFactory;

        public Builder wrappedFactory(ThreadFactory threadFactory) {
            if (threadFactory == null) {
                throw new NullPointerException("Wrapped ThreadFactory must not be null!");
            }
            this.wrappedFactory = threadFactory;
            return this;
        }

        public Builder namingPattern(String str) {
            if (str == null) {
                throw new NullPointerException("Naming pattern must not be null!");
            }
            this.namingPattern = str;
            return this;
        }

        public Builder daemon(boolean z) {
            this.daemonFlag = Boolean.valueOf(z);
            return this;
        }

        public Builder priority(int i) {
            this.priority = Integer.valueOf(i);
            return this;
        }

        public Builder uncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            if (uncaughtExceptionHandler == null) {
                throw new NullPointerException("Uncaught exception handler must not be null!");
            }
            this.exceptionHandler = uncaughtExceptionHandler;
            return this;
        }

        public void reset() {
            this.wrappedFactory = null;
            this.exceptionHandler = null;
            this.namingPattern = null;
            this.priority = null;
            this.daemonFlag = null;
        }

        @Override // org.apache.commons.lang3.builder.Builder
        public BasicThreadFactory build() {
            BasicThreadFactory basicThreadFactory = new BasicThreadFactory(this);
            reset();
            return basicThreadFactory;
        }
    }
}
