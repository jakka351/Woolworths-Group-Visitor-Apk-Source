package com.facebook.react.fabric;

import com.facebook.react.bridge.JSIModuleProvider;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.systrace.Systrace;

/* loaded from: classes.dex */
public class FabricJSIModuleProvider implements JSIModuleProvider<UIManager> {
    private final ComponentFactory mComponentFactory;
    private final ReactNativeConfig mConfig;
    private final ReactApplicationContext mReactApplicationContext;
    private final ViewManagerRegistry mViewManagerRegistry;

    public FabricJSIModuleProvider(ReactApplicationContext reactApplicationContext, ComponentFactory componentFactory, ReactNativeConfig reactNativeConfig, ViewManagerRegistry viewManagerRegistry) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mComponentFactory = componentFactory;
        this.mConfig = reactNativeConfig;
        this.mViewManagerRegistry = viewManagerRegistry;
    }

    @Override // com.facebook.react.bridge.JSIModuleProvider
    public UIManager get() {
        Systrace.beginSection(0L, "FabricJSIModuleProvider.get");
        EventBeatManager eventBeatManager = new EventBeatManager();
        FabricUIManager fabricUIManagerCreateUIManager = createUIManager(eventBeatManager);
        Systrace.beginSection(0L, "FabricJSIModuleProvider.registerBinding");
        new BindingImpl().register(this.mReactApplicationContext.getCatalystInstance().getRuntimeExecutor(), this.mReactApplicationContext.getCatalystInstance().getRuntimeScheduler(), fabricUIManagerCreateUIManager, eventBeatManager, this.mComponentFactory, this.mConfig);
        Systrace.endSection(0L);
        Systrace.endSection(0L);
        return fabricUIManagerCreateUIManager;
    }

    private FabricUIManager createUIManager(EventBeatManager eventBeatManager) {
        Systrace.beginSection(0L, "FabricJSIModuleProvider.createUIManager");
        FabricUIManager fabricUIManager = new FabricUIManager(this.mReactApplicationContext, this.mViewManagerRegistry, eventBeatManager);
        Systrace.endSection(0L);
        return fabricUIManager;
    }
}
