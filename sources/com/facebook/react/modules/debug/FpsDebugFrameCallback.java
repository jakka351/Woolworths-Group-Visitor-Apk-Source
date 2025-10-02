package com.facebook.react.modules.debug;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Map;
import java.util.TreeMap;
import kotlin.time.DurationKt;

/* loaded from: classes3.dex */
public class FpsDebugFrameCallback extends ChoreographerCompat.FrameCallback {
    private static final double EXPECTED_FRAME_TIME = 16.9d;
    private ChoreographerCompat mChoreographer;
    private final ReactContext mReactContext;
    private TreeMap<Long, FpsInfo> mTimeToFps;
    private final UIManagerModule mUIManagerModule;
    private long mFirstFrameTime = -1;
    private long mLastFrameTime = -1;
    private int mNumFrameCallbacks = 0;
    private int mExpectedNumFramesPrev = 0;
    private int m4PlusFrameStutters = 0;
    private int mNumFrameCallbacksWithBatchDispatches = 0;
    private boolean mIsRecordingFpsInfoAtEachFrame = false;
    private final DidJSUpdateUiDuringFrameDetector mDidJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();

    public static class FpsInfo {
        public final double fps;
        public final double jsFps;
        public final int total4PlusFrameStutters;
        public final int totalExpectedFrames;
        public final int totalFrames;
        public final int totalJsFrames;
        public final int totalTimeMs;

        public FpsInfo(int i, int i2, int i3, int i4, double d, double d2, int i5) {
            this.totalFrames = i;
            this.totalJsFrames = i2;
            this.totalExpectedFrames = i3;
            this.total4PlusFrameStutters = i4;
            this.fps = d;
            this.jsFps = d2;
            this.totalTimeMs = i5;
        }
    }

    public FpsDebugFrameCallback(ReactContext reactContext) {
        this.mReactContext = reactContext;
        this.mUIManagerModule = (UIManagerModule) Assertions.assertNotNull((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class));
    }

    @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
    public void doFrame(long j) {
        if (this.mFirstFrameTime == -1) {
            this.mFirstFrameTime = j;
        }
        long j2 = this.mLastFrameTime;
        this.mLastFrameTime = j;
        if (this.mDidJSUpdateUiDuringFrameDetector.getDidJSHitFrameAndCleanup(j2, j)) {
            this.mNumFrameCallbacksWithBatchDispatches++;
        }
        this.mNumFrameCallbacks++;
        int expectedNumFrames = getExpectedNumFrames();
        if ((expectedNumFrames - this.mExpectedNumFramesPrev) - 1 >= 4) {
            this.m4PlusFrameStutters++;
        }
        if (this.mIsRecordingFpsInfoAtEachFrame) {
            Assertions.assertNotNull(this.mTimeToFps);
            this.mTimeToFps.put(Long.valueOf(System.currentTimeMillis()), new FpsInfo(getNumFrames(), getNumJSFrames(), expectedNumFrames, this.m4PlusFrameStutters, getFPS(), getJSFPS(), getTotalTimeMS()));
        }
        this.mExpectedNumFramesPrev = expectedNumFrames;
        ChoreographerCompat choreographerCompat = this.mChoreographer;
        if (choreographerCompat != null) {
            choreographerCompat.postFrameCallback(this);
        }
    }

    public void start() {
        this.mReactContext.getCatalystInstance().addBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback.1
            @Override // java.lang.Runnable
            public void run() {
                FpsDebugFrameCallback.this.mChoreographer = ChoreographerCompat.getInstance();
                FpsDebugFrameCallback.this.mChoreographer.postFrameCallback(this);
            }
        });
    }

    public void startAndRecordFpsAtEachFrame() {
        this.mTimeToFps = new TreeMap<>();
        this.mIsRecordingFpsInfoAtEachFrame = true;
        start();
    }

    public void stop() {
        this.mReactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(null);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback.2
            @Override // java.lang.Runnable
            public void run() {
                FpsDebugFrameCallback.this.mChoreographer = ChoreographerCompat.getInstance();
                FpsDebugFrameCallback.this.mChoreographer.removeFrameCallback(this);
            }
        });
    }

    public double getFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        return (getNumFrames() * 1.0E9d) / (this.mLastFrameTime - this.mFirstFrameTime);
    }

    public double getJSFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        return (getNumJSFrames() * 1.0E9d) / (this.mLastFrameTime - this.mFirstFrameTime);
    }

    public int getNumFrames() {
        return this.mNumFrameCallbacks - 1;
    }

    public int getNumJSFrames() {
        return this.mNumFrameCallbacksWithBatchDispatches - 1;
    }

    public int getExpectedNumFrames() {
        return (int) ((getTotalTimeMS() / EXPECTED_FRAME_TIME) + 1.0d);
    }

    public int get4PlusFrameStutters() {
        return this.m4PlusFrameStutters;
    }

    public int getTotalTimeMS() {
        return ((int) (this.mLastFrameTime - this.mFirstFrameTime)) / DurationKt.NANOS_IN_MILLIS;
    }

    public FpsInfo getFpsInfo(long j) {
        Assertions.assertNotNull(this.mTimeToFps, "FPS was not recorded at each frame!");
        Map.Entry<Long, FpsInfo> entryFloorEntry = this.mTimeToFps.floorEntry(Long.valueOf(j));
        if (entryFloorEntry == null) {
            return null;
        }
        return entryFloorEntry.getValue();
    }

    public void reset() {
        this.mFirstFrameTime = -1L;
        this.mLastFrameTime = -1L;
        this.mNumFrameCallbacks = 0;
        this.m4PlusFrameStutters = 0;
        this.mNumFrameCallbacksWithBatchDispatches = 0;
        this.mIsRecordingFpsInfoAtEachFrame = false;
        this.mTimeToFps = null;
    }
}
