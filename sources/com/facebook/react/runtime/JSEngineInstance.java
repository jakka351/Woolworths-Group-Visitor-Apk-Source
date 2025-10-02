package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;

/* loaded from: classes3.dex */
public abstract class JSEngineInstance {
    private final HybridData mHybridData;

    static {
        SoLoader.loadLibrary("rninstance");
    }

    protected JSEngineInstance(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
