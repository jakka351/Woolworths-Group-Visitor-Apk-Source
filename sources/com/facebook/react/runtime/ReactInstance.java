package com.facebook.react.runtime;

import android.content.res.AssetManager;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.react.ReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.fabric.BindingImpl;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.JavaTimerManager;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.NativeMethodCallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.TurboModuleManager;
import com.facebook.react.uimanager.ComponentNameResolver;
import com.facebook.react.uimanager.ComponentNameResolverManager;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIConstantsProvider;
import com.facebook.react.uimanager.UIConstantsProviderManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
final class ReactInstance {
    private static final String TAG = "ReactInstance";
    private static volatile boolean sIsLibraryLoaded;
    private final BridgelessReactContext mBridgelessReactContext;

    @Nullable
    private ComponentNameResolverManager mComponentNameResolverManager;
    private final ReactHostDelegate mDelegate;
    private final FabricUIManager mFabricUIManager;
    private final HybridData mHybridData;
    private final JavaTimerManager mJavaTimerManager;
    private final ReactQueueConfiguration mQueueConfiguration;
    private final List<ReactPackage> mReactPackages;
    private final TurboModuleManager mTurboModuleManager;

    @Nullable
    private UIConstantsProviderManager mUIConstantsProviderManager;
    private final Map<String, ViewManager> mViewManagers = new ConcurrentHashMap();

    private static native JSTimerExecutor createJSTimerExecutor();

    private native RuntimeExecutor getBufferedRuntimeExecutor();

    private native CallInvokerHolderImpl getJSCallInvokerHolder();

    private native NativeMethodCallInvokerHolderImpl getNativeMethodCallInvokerHolder();

    private native RuntimeScheduler getRuntimeScheduler();

    private native RuntimeExecutor getUnbufferedRuntimeExecutor();

    private native void handleMemoryPressureJs(int i);

    private native HybridData initHybrid(JSEngineInstance jSEngineInstance, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, JavaTimerManager javaTimerManager, JSTimerExecutor jSTimerExecutor, ReactJsExceptionHandler reactJsExceptionHandler, @Nullable BindingsInstaller bindingsInstaller, boolean z);

    private native void installGlobals(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void loadJSBundleFromAssets(AssetManager assetManager, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void loadJSBundleFromFile(String str, String str2);

    private native void registerSegmentNative(int i, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void callFunctionOnModule(String str, String str2, NativeArray nativeArray);

    static {
        loadLibraryIfNeeded();
    }

    ReactInstance(BridgelessReactContext bridgelessReactContext, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, DevSupportManager devSupportManager, QueueThreadExceptionHandler queueThreadExceptionHandler, ReactJsExceptionHandler reactJsExceptionHandler, boolean z) {
        this.mBridgelessReactContext = bridgelessReactContext;
        this.mDelegate = reactHostDelegate;
        Systrace.beginSection(0L, "ReactInstance.initialize");
        ReactQueueConfigurationImpl reactQueueConfigurationImplCreate = ReactQueueConfigurationImpl.create(ReactQueueConfigurationSpec.builder().setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("v_js")).setNativeModulesQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("v_native")).build(), queueThreadExceptionHandler);
        this.mQueueConfiguration = reactQueueConfigurationImplCreate;
        FLog.d(TAG, "Calling initializeMessageQueueThreads()");
        bridgelessReactContext.initializeMessageQueueThreads(reactQueueConfigurationImplCreate);
        MessageQueueThread jSQueueThread = reactQueueConfigurationImplCreate.getJSQueueThread();
        MessageQueueThread nativeModulesQueueThread = reactQueueConfigurationImplCreate.getNativeModulesQueueThread();
        ReactChoreographer.initialize();
        if (z) {
            devSupportManager.startInspector();
        }
        JSTimerExecutor jSTimerExecutorCreateJSTimerExecutor = createJSTimerExecutor();
        JavaTimerManager javaTimerManager = new JavaTimerManager(bridgelessReactContext, jSTimerExecutorCreateJSTimerExecutor, ReactChoreographer.getInstance(), devSupportManager);
        this.mJavaTimerManager = javaTimerManager;
        bridgelessReactContext.addLifecycleEventListener(new LifecycleEventListener() { // from class: com.facebook.react.runtime.ReactInstance.1
            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostResume() {
                ReactInstance.this.mJavaTimerManager.onHostResume();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostPause() {
                ReactInstance.this.mJavaTimerManager.onHostPause();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostDestroy() {
                ReactInstance.this.mJavaTimerManager.onHostDestroy();
            }
        });
        this.mHybridData = initHybrid(reactHostDelegate.getJsEngineInstance(), jSQueueThread, nativeModulesQueueThread, javaTimerManager, jSTimerExecutorCreateJSTimerExecutor, reactJsExceptionHandler, reactHostDelegate.getBindingsInstaller(), Systrace.isTracing(0L));
        RuntimeExecutor unbufferedRuntimeExecutor = getUnbufferedRuntimeExecutor();
        this.mComponentNameResolverManager = new ComponentNameResolverManager(unbufferedRuntimeExecutor, new ComponentNameResolver() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda0
            @Override // com.facebook.react.uimanager.ComponentNameResolver
            public final String[] getComponentNames() {
                return this.f$0.lambda$new$0();
            }
        });
        Systrace.beginSection(0L, "ReactInstance.initialize#initTurboModules");
        ArrayList arrayList = new ArrayList(reactHostDelegate.getReactPackages());
        this.mReactPackages = arrayList;
        arrayList.add(new CoreReactPackage(bridgelessReactContext.getDevSupportManager(), bridgelessReactContext.getDefaultHardwareBackBtnHandler()));
        TurboModuleManager turboModuleManager = new TurboModuleManager(unbufferedRuntimeExecutor, reactHostDelegate.getTurboModuleManagerDelegateBuilder().setPackages(arrayList).setReactApplicationContext(bridgelessReactContext).build(), getJSCallInvokerHolder(), getNativeMethodCallInvokerHolder());
        this.mTurboModuleManager = turboModuleManager;
        Iterator<String> it = turboModuleManager.getEagerInitModuleNames().iterator();
        while (it.hasNext()) {
            this.mTurboModuleManager.getModule(it.next());
        }
        Systrace.endSection(0L);
        if (ReactFeatureFlags.useNativeViewConfigsInBridgelessMode) {
            this.mUIConstantsProviderManager = new UIConstantsProviderManager(unbufferedRuntimeExecutor, new UIConstantsProvider() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda1
                @Override // com.facebook.react.uimanager.UIConstantsProvider
                public final NativeMap getConstants() {
                    return this.f$0.lambda$new$1();
                }
            });
        }
        Systrace.beginSection(0L, "ReactInstance.initialize#initFabric");
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(new ViewManagerResolver() { // from class: com.facebook.react.runtime.ReactInstance.2
            @Override // com.facebook.react.uimanager.ViewManagerResolver
            @Nullable
            public ViewManager getViewManager(String str) {
                return ReactInstance.this.createViewManager(str);
            }

            @Override // com.facebook.react.uimanager.ViewManagerResolver
            public Collection<String> getViewManagerNames() {
                return ReactInstance.this.getViewManagerNames();
            }
        });
        EventBeatManager eventBeatManager = new EventBeatManager();
        FabricUIManager fabricUIManager = new FabricUIManager(this.mBridgelessReactContext, viewManagerRegistry, eventBeatManager);
        this.mFabricUIManager = fabricUIManager;
        ReactNativeConfig reactNativeConfig = this.mDelegate.getReactNativeConfig(this.mTurboModuleManager);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(this.mBridgelessReactContext);
        new BindingImpl().register(getBufferedRuntimeExecutor(), getRuntimeScheduler(), fabricUIManager, eventBeatManager, componentFactory, reactNativeConfig);
        Systrace.endSection(0L);
        fabricUIManager.initialize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String[] lambda$new$0() {
        Collection<String> viewManagerNames = getViewManagerNames();
        if (viewManagerNames.size() < 1) {
            FLog.e(TAG, "No ViewManager names found");
            return new String[0];
        }
        return (String[]) viewManagerNames.toArray(new String[0]);
    }

    private static synchronized void loadLibraryIfNeeded() {
        if (!sIsLibraryLoaded) {
            SoLoader.loadLibrary("rninstance");
            sIsLibraryLoaded = true;
        }
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mQueueConfiguration;
    }

    public void loadJSBundle(JSBundleLoader jSBundleLoader) {
        Systrace.beginSection(0L, "ReactInstance.loadJSBundle");
        jSBundleLoader.loadScript(new JSBundleLoaderDelegate() { // from class: com.facebook.react.runtime.ReactInstance.3
            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadScriptFromFile(String str, String str2, boolean z) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str2);
                ReactInstance.this.loadJSBundleFromFile(str, str2);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadSplitBundleFromFile(String str, String str2) {
                ReactInstance.this.loadJSBundleFromFile(str, str2);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadScriptFromAssets(AssetManager assetManager, String str, boolean z) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str);
                ReactInstance.this.loadJSBundleFromAssets(assetManager, str);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void setSourceURLs(String str, String str2) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str);
            }
        });
        Systrace.endSection(0L);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return this.mTurboModuleManager.hasModule(reactModule.name());
        }
        return false;
    }

    public Collection<NativeModule> getNativeModules() {
        return new ArrayList(this.mTurboModuleManager.getModules());
    }

    @Nullable
    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return (T) getNativeModule(reactModule.name());
        }
        return null;
    }

    @Nullable
    public NativeModule getNativeModule(String str) {
        NativeModule module;
        synchronized (this.mTurboModuleManager) {
            module = this.mTurboModuleManager.getModule(str);
        }
        return module;
    }

    void prerenderSurface(ReactSurfaceImpl reactSurfaceImpl) {
        Systrace.beginSection(0L, "ReactInstance.prerenderSurface");
        FLog.d(TAG, "call prerenderSurface with surface: " + reactSurfaceImpl.getModuleName());
        this.mFabricUIManager.startSurface(reactSurfaceImpl.getSurfaceHandler(), reactSurfaceImpl.getContext(), null);
        Systrace.endSection(0L);
    }

    void startSurface(ReactSurfaceImpl reactSurfaceImpl) {
        String str = TAG;
        FLog.d(str, "startSurface() is called with surface: " + reactSurfaceImpl.getSurfaceID());
        Systrace.beginSection(0L, "ReactInstance.startSurface");
        ViewGroup view = reactSurfaceImpl.getView();
        if (view == null) {
            throw new IllegalStateException("Starting surface without a view is not supported, use prerenderSurface instead.");
        }
        if (view.getId() != -1) {
            ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("surfaceView's is NOT equal to View.NO_ID before calling startSurface."));
            view.setId(-1);
        }
        if (reactSurfaceImpl.isRunning()) {
            this.mFabricUIManager.attachRootView(reactSurfaceImpl.getSurfaceHandler(), view);
        } else {
            this.mFabricUIManager.startSurface(reactSurfaceImpl.getSurfaceHandler(), reactSurfaceImpl.getContext(), view);
        }
        Systrace.endSection(0L);
    }

    void stopSurface(ReactSurfaceImpl reactSurfaceImpl) {
        FLog.d(TAG, "stopSurface() is called with surface: " + reactSurfaceImpl.getSurfaceID());
        this.mFabricUIManager.stopSurface(reactSurfaceImpl.getSurfaceHandler());
    }

    void destroy() {
        FLog.d(TAG, "ReactInstance.destroy() is called.");
        this.mQueueConfiguration.destroy();
        this.mTurboModuleManager.onCatalystInstanceDestroy();
        this.mFabricUIManager.onCatalystInstanceDestroy();
        this.mHybridData.resetNative();
        this.mComponentNameResolverManager = null;
        this.mUIConstantsProviderManager = null;
    }

    public void handleMemoryPressure(int i) {
        try {
            handleMemoryPressureJs(i);
        } catch (NullPointerException unused) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Native method handleMemoryPressureJs is called earlier than librninstance.so got ready."));
        }
    }

    EventDispatcher getEventDispatcher() {
        return this.mFabricUIManager.getEventDispatcher();
    }

    FabricUIManager getUIManager() {
        return this.mFabricUIManager;
    }

    public void registerSegment(int i, String str) {
        registerSegmentNative(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public ViewManager createViewManager(String str) {
        ViewManager viewManagerCreateViewManager;
        if (this.mViewManagers.containsKey(str)) {
            return this.mViewManagers.get(str);
        }
        List<ReactPackage> list = this.mReactPackages;
        if (this.mDelegate != null && list != null) {
            synchronized (list) {
                for (ReactPackage reactPackage : list) {
                    if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerCreateViewManager = ((ViewManagerOnDemandReactPackage) reactPackage).createViewManager(this.mBridgelessReactContext, str)) != null) {
                        this.mViewManagers.put(str, viewManagerCreateViewManager);
                        return viewManagerCreateViewManager;
                    }
                }
            }
        }
        Iterator<ReactPackage> it = list.iterator();
        while (it.hasNext()) {
            for (ViewManager viewManager : it.next().createViewManagers(this.mBridgelessReactContext)) {
                this.mViewManagers.put(viewManager.getName(), viewManager);
            }
        }
        return this.mViewManagers.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Collection<String> getViewManagerNames() {
        List<ReactPackage> list;
        Collection<String> viewManagerNames;
        HashSet hashSet = new HashSet();
        if (this.mDelegate != null && (list = this.mReactPackages) != null) {
            synchronized (list) {
                for (ReactPackage reactPackage : list) {
                    if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerNames = ((ViewManagerOnDemandReactPackage) reactPackage).getViewManagerNames(this.mBridgelessReactContext)) != null) {
                        hashSet.addAll(viewManagerNames);
                    }
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getUIManagerConstants, reason: merged with bridge method [inline-methods] */
    public NativeMap lambda$new$1() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        List<ReactPackage> list = this.mReactPackages;
        Iterator<ReactPackage> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            if (!(it.next() instanceof ViewManagerOnDemandReactPackage)) {
                z = false;
                break;
            }
        }
        if (z) {
            Iterator<String> it2 = getViewManagerNames().iterator();
            while (it2.hasNext()) {
                arrayList.add(createViewManager(it2.next()));
            }
        } else {
            Iterator<ReactPackage> it3 = list.iterator();
            while (it3.hasNext()) {
                arrayList.addAll(it3.next().createViewManagers(this.mBridgelessReactContext));
            }
        }
        return Arguments.makeNativeMap(UIManagerModule.createConstants(arrayList, new HashMap(), new HashMap()));
    }
}
