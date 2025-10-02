package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;

/* loaded from: classes3.dex */
public class JSCInstance extends JSEngineInstance {
    protected static native HybridData initHybrid();

    static {
        SoLoader.loadLibrary("jscinstance");
    }

    public JSCInstance() {
        super(initHybrid());
    }
}
