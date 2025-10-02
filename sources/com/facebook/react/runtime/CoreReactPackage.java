package com.facebook.react.runtime;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.LogBoxModule;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.debug.DevSettingsModule;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
class CoreReactPackage extends TurboReactPackage {
    private DevSupportManager mDevSupportManager;
    private DefaultHardwareBackBtnHandler mHardwareBackBtnHandler;

    static /* synthetic */ Map lambda$getReactModuleInfoProvider$0(Map map) {
        return map;
    }

    public CoreReactPackage(DevSupportManager devSupportManager, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDevSupportManager = devSupportManager;
        this.mHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
    }

    @Override // com.facebook.react.TurboReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        switch (str) {
            case "LogBox":
                return new LogBoxModule(reactApplicationContext, this.mDevSupportManager);
            case "DevSettings":
                return new DevSettingsModule(reactApplicationContext, this.mDevSupportManager);
            case "DeviceInfo":
                return new DeviceInfoModule(reactApplicationContext);
            case "DeviceEventManager":
                return new DeviceEventManagerModule(reactApplicationContext, this.mHardwareBackBtnHandler);
            case "PlatformConstants":
                return new AndroidInfoModule(reactApplicationContext);
            case "ExceptionsManager":
                return new ExceptionsManagerModule(this.mDevSupportManager);
            case "SourceCode":
                return new SourceCodeModule(reactApplicationContext);
            default:
                throw new IllegalArgumentException("In BridgelessReactPackage, could not find Native module for " + str);
        }
    }

    @Override // com.facebook.react.TurboReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.facebook.react.CoreModulesPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            Class[] clsArr = {AndroidInfoModule.class, DeviceInfoModule.class, SourceCodeModule.class, DevSettingsModule.class, DeviceEventManagerModule.class, LogBoxModule.class, ExceptionsManagerModule.class};
            final HashMap map = new HashMap();
            for (int i = 0; i < 7; i++) {
                Class cls = clsArr[i];
                ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                if (reactModule != null) {
                    map.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), TurboModule.class.isAssignableFrom(cls)));
                }
            }
            return new ReactModuleInfoProvider() { // from class: com.facebook.react.runtime.CoreReactPackage$$ExternalSyntheticLambda0
                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public final Map getReactModuleInfos() {
                    return CoreReactPackage.lambda$getReactModuleInfoProvider$0(map);
                }
            };
        } catch (IllegalAccessException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e2);
        }
    }
}
