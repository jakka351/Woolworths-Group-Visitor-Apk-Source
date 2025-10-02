package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UnstableReactNativeAPI
/* loaded from: classes.dex */
public abstract class LazyTurboModuleManagerDelegate extends ReactPackageTurboModuleManagerDelegate {
    private final Map<String, TurboModule> mModules = new HashMap();
    private final List<ReactPackage> mPackages;
    private final ReactApplicationContext mReactContext;

    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate, com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public boolean unstable_isLegacyModuleRegistered(String str) {
        return false;
    }

    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate, com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public boolean unstable_shouldEnableLegacyModuleInterop() {
        return false;
    }

    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate, com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public boolean unstable_shouldRouteTurboModulesThroughLegacyModuleInterop() {
        return false;
    }

    public LazyTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<ReactPackage> list) {
        this.mPackages = list;
        this.mReactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate, com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public TurboModule getModule(String str) {
        TurboModule turboModule = this.mModules.get(str);
        if (turboModule == null) {
            for (ReactPackage reactPackage : this.mPackages) {
                if (reactPackage instanceof TurboReactPackage) {
                    try {
                        turboModule = (TurboModule) ((TurboReactPackage) reactPackage).getModule(str, this.mReactContext);
                    } catch (IllegalArgumentException unused) {
                    }
                    if (turboModule != null) {
                        this.mModules.put(str, turboModule);
                        turboModule.initialize();
                        return turboModule;
                    }
                } else {
                    throw new IllegalArgumentException("ReactPackage must be an instance of TurboReactPackage");
                }
            }
        }
        return turboModule;
    }

    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate, com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public boolean unstable_isModuleRegistered(String str) {
        return getModule(str) != null;
    }

    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate, com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public NativeModule getLegacyModule(String str) {
        throw new UnsupportedOperationException("Legacy Modules are not supported");
    }
}
