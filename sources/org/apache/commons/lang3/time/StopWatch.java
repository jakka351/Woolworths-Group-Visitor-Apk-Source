package org.apache.commons.lang3.time;

/* loaded from: classes2.dex */
public class StopWatch {
    private static final long NANO_2_MILLIS = 1000000;
    private static final int STATE_RUNNING = 1;
    private static final int STATE_SPLIT = 11;
    private static final int STATE_STOPPED = 2;
    private static final int STATE_SUSPENDED = 3;
    private static final int STATE_UNSPLIT = 10;
    private static final int STATE_UNSTARTED = 0;
    private int runningState = 0;
    private int splitState = 10;
    private long startTime;
    private long startTimeMillis;
    private long stopTime;

    public void start() {
        int i = this.runningState;
        if (i == 2) {
            throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        }
        if (i != 0) {
            throw new IllegalStateException("Stopwatch already started. ");
        }
        this.startTime = System.nanoTime();
        this.startTimeMillis = System.currentTimeMillis();
        this.runningState = 1;
    }

    public void stop() {
        int i = this.runningState;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        if (i == 1) {
            this.stopTime = System.nanoTime();
        }
        this.runningState = 2;
    }

    public void reset() {
        this.runningState = 0;
        this.splitState = 10;
    }

    public void split() {
        if (this.runningState != 1) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        this.stopTime = System.nanoTime();
        this.splitState = 11;
    }

    public void unsplit() {
        if (this.splitState != 11) {
            throw new IllegalStateException("Stopwatch has not been split. ");
        }
        this.splitState = 10;
    }

    public void suspend() {
        if (this.runningState != 1) {
            throw new IllegalStateException("Stopwatch must be running to suspend. ");
        }
        this.stopTime = System.nanoTime();
        this.runningState = 3;
    }

    public void resume() {
        if (this.runningState != 3) {
            throw new IllegalStateException("Stopwatch must be suspended to resume. ");
        }
        this.startTime += System.nanoTime() - this.stopTime;
        this.runningState = 1;
    }

    public long getTime() {
        return getNanoTime() / NANO_2_MILLIS;
    }

    public long getNanoTime() {
        long jNanoTime;
        long j;
        int i = this.runningState;
        if (i == 2 || i == 3) {
            jNanoTime = this.stopTime;
            j = this.startTime;
        } else {
            if (i == 0) {
                return 0L;
            }
            if (i == 1) {
                jNanoTime = System.nanoTime();
                j = this.startTime;
            } else {
                throw new RuntimeException("Illegal running state has occured. ");
            }
        }
        return jNanoTime - j;
    }

    public long getSplitTime() {
        return getSplitNanoTime() / NANO_2_MILLIS;
    }

    public long getSplitNanoTime() {
        if (this.splitState != 11) {
            throw new IllegalStateException("Stopwatch must be split to get the split time. ");
        }
        return this.stopTime - this.startTime;
    }

    public long getStartTime() {
        if (this.runningState == 0) {
            throw new IllegalStateException("Stopwatch has not been started");
        }
        return this.startTimeMillis;
    }

    public String toString() {
        return DurationFormatUtils.formatDurationHMS(getTime());
    }

    public String toSplitString() {
        return DurationFormatUtils.formatDurationHMS(getSplitTime());
    }
}
