package com.facebook.react.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;

/* loaded from: classes3.dex */
public class NativeMethodCallInvokerHolderImpl implements NativeMethodCallInvokerHolder {
    private final HybridData mHybridData;

    static {
        NativeModuleSoLoader.maybeLoadSoLibrary();
    }

    private NativeMethodCallInvokerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
