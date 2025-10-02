package com.swmansion.reanimated;

import android.util.Log;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModuleListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

@ReactModule(name = "ReanimatedModule")
/* loaded from: classes3.dex */
public class ReanimatedModule extends NativeReanimatedModuleSpec implements LifecycleEventListener, UIManagerModuleListener {
    public static final String NAME = "ReanimatedModule";

    @Nullable
    private NodesManager mNodesManager;
    private ArrayList<UIThreadOperation> mOperations;

    private interface UIThreadOperation {
        void execute(NodesManager nodesManager);
    }

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.swmansion.reanimated.NativeReanimatedModuleSpec, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ReanimatedModule";
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public ReanimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mOperations = new ArrayList<>();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        UIManagerModule uIManagerModule = (UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class);
        reactApplicationContext.addLifecycleEventListener(this);
        uIManagerModule.addUIManagerListener(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onHostPause();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onHostResume();
        }
    }

    @Override // com.facebook.react.uimanager.UIManagerModuleListener
    public void willDispatchViewUpdates(UIManagerModule uIManagerModule) {
        if (this.mOperations.isEmpty()) {
            return;
        }
        final ArrayList<UIThreadOperation> arrayList = this.mOperations;
        this.mOperations = new ArrayList<>();
        uIManagerModule.addUIBlock(new UIBlock() { // from class: com.swmansion.reanimated.ReanimatedModule.1
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NodesManager nodesManager = ReanimatedModule.this.getNodesManager();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((UIThreadOperation) it.next()).execute(nodesManager);
                }
            }
        });
    }

    public NodesManager getNodesManager() {
        if (this.mNodesManager == null) {
            this.mNodesManager = new NodesManager(getReactApplicationContext());
        }
        return this.mNodesManager;
    }

    @Override // com.swmansion.reanimated.NativeReanimatedModuleSpec
    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean installTurboModule(String str) {
        Utils.isChromeDebugger = getReactApplicationContext().getJavaScriptContextHolder().get() == 0;
        if (!Utils.isChromeDebugger) {
            getNodesManager().initWithContext(getReactApplicationContext(), str);
            return true;
        }
        Log.w("[REANIMATED]", "Unable to create Reanimated Native Module. You can ignore this message if you are using Chrome Debugger now.");
        return false;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        super.invalidate();
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.invalidate();
        }
    }
}
