package com.facebook.react.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;

/* loaded from: classes3.dex */
public class CallInvokerHolderImpl implements CallInvokerHolder {
    private final HybridData mHybridData;

    static {
        NativeModuleSoLoader.maybeLoadSoLibrary();
    }

    private CallInvokerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
