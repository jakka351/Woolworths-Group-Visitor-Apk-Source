package com.facebook.react.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.JSEngineResolutionAlgorithm;
import com.facebook.react.MemoryPressureRouter;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashBridgeNotAllowedSoftException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.DisabledDevSupportManager;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.runtime.BridgelessAtomicRef;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;
import com.facebook.react.runtime.internal.bolts.TaskCompletionSource;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.BlackHoleEventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes3.dex */
public class ReactHostImpl implements ReactHost {
    private static final int BRIDGELESS_MARKER_INSTANCE_KEY = 1;
    private static final boolean DEV = false;
    private static final String TAG = "ReactHost";
    private static final AtomicInteger mCounter = new AtomicInteger(0);
    private final AtomicReference<Activity> mActivity;
    private final boolean mAllowPackagerServerAccess;
    private final Set<ReactSurfaceImpl> mAttachedSurfaces;
    private final Executor mBGExecutor;
    private final Set<Function0<Unit>> mBeforeDestroyListeners;
    private final BridgelessAtomicRef<BridgelessReactContext> mBridgelessReactContextRef;
    private final BridgelessReactStateTracker mBridgelessReactStateTracker;
    private final ComponentFactory mComponentFactory;
    private final Context mContext;
    private DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
    private Task<Void> mDestroyTask;
    private final DevSupportManager mDevSupportManager;
    private final int mId;
    private JSEngineResolutionAlgorithm mJSEngineResolutionAlgorithm;
    private final AtomicReference<WeakReference<Activity>> mLastUsedActivity;
    private MemoryPressureListener mMemoryPressureListener;
    private final MemoryPressureRouter mMemoryPressureRouter;
    private final QueueThreadExceptionHandler mQueueThreadExceptionHandler;
    private final ReactHostDelegate mReactHostDelegate;
    private final Collection<ReactInstanceEventListener> mReactInstanceEventListeners;
    private final BridgelessAtomicRef<Task<ReactInstance>> mReactInstanceTaskRef;
    private final ReactJsExceptionHandler mReactJsExceptionHandler;
    private final ReactLifecycleStateManager mReactLifecycleStateManager;
    private Task<ReactInstance> mReloadTask;
    private Task<Void> mStartTask;
    private final Executor mUIExecutor;
    private final boolean mUseDevSupport;

    /* JADX INFO: Access modifiers changed from: private */
    interface ReactInstanceTaskUnwrapper {
        ReactInstance unwrap(Task<ReactInstance> task, String str);
    }

    interface VeniceThenable<T> {
        void then(T t);
    }

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, boolean z, ReactJsExceptionHandler reactJsExceptionHandler, boolean z2) {
        this(context, reactHostDelegate, componentFactory, Executors.newSingleThreadExecutor(), Task.UI_THREAD_EXECUTOR, reactJsExceptionHandler, z, z2);
    }

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, Executor executor, Executor executor2, ReactJsExceptionHandler reactJsExceptionHandler, boolean z, boolean z2) {
        this.mAttachedSurfaces = Collections.synchronizedSet(new HashSet());
        this.mReactInstanceEventListeners = Collections.synchronizedList(new ArrayList());
        this.mReactInstanceTaskRef = new BridgelessAtomicRef<>(Task.forResult((ReactInstance) Assertions.nullsafeFIXME(null, "forResult parameter supports null, but is not annotated as @Nullable")));
        this.mBridgelessReactContextRef = new BridgelessAtomicRef<>();
        this.mActivity = new AtomicReference<>();
        this.mLastUsedActivity = new AtomicReference<>(new WeakReference(null));
        BridgelessReactStateTracker bridgelessReactStateTracker = new BridgelessReactStateTracker(false);
        this.mBridgelessReactStateTracker = bridgelessReactStateTracker;
        this.mReactLifecycleStateManager = new ReactLifecycleStateManager(bridgelessReactStateTracker);
        this.mId = mCounter.getAndIncrement();
        this.mJSEngineResolutionAlgorithm = null;
        this.mBeforeDestroyListeners = Collections.synchronizedSet(new HashSet());
        this.mStartTask = null;
        this.mReloadTask = null;
        this.mDestroyTask = null;
        this.mContext = context;
        this.mReactHostDelegate = reactHostDelegate;
        this.mComponentFactory = componentFactory;
        this.mBGExecutor = executor;
        this.mUIExecutor = executor2;
        this.mReactJsExceptionHandler = reactJsExceptionHandler;
        this.mQueueThreadExceptionHandler = new QueueThreadExceptionHandler() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda8
            @Override // com.facebook.react.bridge.queue.QueueThreadExceptionHandler
            public final void handleException(Exception exc) {
                this.f$0.handleHostException(exc);
            }
        };
        this.mMemoryPressureRouter = new MemoryPressureRouter(context);
        this.mMemoryPressureListener = new MemoryPressureListener() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda9
            @Override // com.facebook.react.bridge.MemoryPressureListener
            public final void handleMemoryPressure(int i) {
                this.f$0.lambda$new$1(i);
            }
        };
        this.mAllowPackagerServerAccess = z;
        this.mDevSupportManager = new DisabledDevSupportManager();
        this.mUseDevSupport = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(final int i) {
        callWithExistingReactInstance("handleMemoryPressure(" + i + ")", new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda35
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ((ReactInstance) obj).handleMemoryPressure(i);
            }
        });
    }

    @Override // com.facebook.react.ReactHost
    public LifecycleState getLifecycleState() {
        return this.mReactLifecycleStateManager.getLifecycleState();
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> start() {
        if (ReactFeatureFlags.enableBridgelessArchitectureNewCreateReloadDestroy) {
            return newStart();
        }
        return oldStart();
    }

    TaskInterface<Void> prerenderSurface(final ReactSurfaceImpl reactSurfaceImpl) {
        final String str = "prerenderSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        attachSurface(reactSurfaceImpl);
        return callAfterGetOrCreateReactInstance(str, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda10
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                this.f$0.lambda$prerenderSurface$2(str, reactSurfaceImpl, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prerenderSurface$2(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.prerenderSurface(reactSurfaceImpl);
    }

    TaskInterface<Void> startSurface(final ReactSurfaceImpl reactSurfaceImpl) {
        final String str = "startSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        attachSurface(reactSurfaceImpl);
        return callAfterGetOrCreateReactInstance(str, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda20
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                this.f$0.lambda$startSurface$3(str, reactSurfaceImpl, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startSurface$3(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.startSurface(reactSurfaceImpl);
    }

    TaskInterface<Void> stopSurface(final ReactSurfaceImpl reactSurfaceImpl) {
        final String str = "stopSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        detachSurface(reactSurfaceImpl);
        return callWithExistingReactInstance(str, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda52
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                this.f$0.lambda$stopSurface$4(str, reactSurfaceImpl, (ReactInstance) obj);
            }
        }).makeVoid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stopSurface$4(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.stopSurface(reactSurfaceImpl);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDefaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        onHostResume(activity);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostResume(Activity activity) {
        log("onHostResume(activity)");
        setCurrentActivity(activity);
        this.mReactLifecycleStateManager.moveToOnHostResume(getCurrentReactContext(), getCurrentActivity());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostPause(Activity activity) {
        log("onHostPause(activity)");
        ReactContext currentReactContext = getCurrentReactContext();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            Assertions.assertCondition(activity == currentActivity, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + currentActivity.getClass().getSimpleName() + " Paused activity: " + (activity == null ? "null" : activity.getClass().getSimpleName()));
        }
        this.mDefaultHardwareBackBtnHandler = null;
        this.mReactLifecycleStateManager.moveToOnHostPause(currentReactContext, currentActivity);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostPause() {
        log("onHostPause()");
        ReactContext currentReactContext = getCurrentReactContext();
        this.mDefaultHardwareBackBtnHandler = null;
        this.mReactLifecycleStateManager.moveToOnHostPause(currentReactContext, getCurrentActivity());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostDestroy() {
        log("onHostDestroy()");
        moveToHostDestroy(getCurrentReactContext());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostDestroy(Activity activity) {
        log("onHostDestroy(activity)");
        if (getCurrentActivity() == activity) {
            moveToHostDestroy(getCurrentReactContext());
        }
    }

    @Override // com.facebook.react.ReactHost
    public ReactContext getCurrentReactContext() {
        return this.mBridgelessReactContextRef.getNullable();
    }

    @Override // com.facebook.react.ReactHost
    public DevSupportManager getDevSupportManager() {
        return (DevSupportManager) Assertions.assertNotNull(this.mDevSupportManager);
    }

    @Override // com.facebook.react.ReactHost
    public ReactSurface createSurface(Context context, String str, Bundle bundle) {
        ReactSurfaceImpl reactSurfaceImpl = new ReactSurfaceImpl(context, str, bundle);
        reactSurfaceImpl.attachView(new ReactSurfaceView(context, reactSurfaceImpl));
        reactSurfaceImpl.attach(this);
        return reactSurfaceImpl;
    }

    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.mMemoryPressureRouter;
    }

    boolean isInstanceInitialized() {
        return this.mReactInstanceTaskRef.get().getResult() != null;
    }

    @Override // com.facebook.react.ReactHost
    public boolean onBackPressed() {
        DeviceEventManagerModule deviceEventManagerModule;
        UiThreadUtil.assertOnUiThread();
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result == null || (deviceEventManagerModule = (DeviceEventManagerModule) result.getNativeModule(DeviceEventManagerModule.class)) == null) {
            return false;
        }
        deviceEventManagerModule.emitHardwareBackPressed();
        return true;
    }

    @Override // com.facebook.react.ReactHost
    public ReactQueueConfiguration getReactQueueConfiguration() {
        synchronized (this.mReactInstanceTaskRef) {
            Task<ReactInstance> task = this.mReactInstanceTaskRef.get();
            if (task.isFaulted() || task.isCancelled() || task.getResult() == null) {
                return null;
            }
            return task.getResult().getReactQueueConfiguration();
        }
    }

    public void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.add(reactInstanceEventListener);
    }

    public void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.remove(reactInstanceEventListener);
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> reload(final String str) {
        if (ReactFeatureFlags.enableBridgelessArchitectureNewCreateReloadDestroy) {
            return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda15
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$reload$7(str);
                }
            }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda16());
        }
        return oldReload(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$7(final String str) throws Exception {
        Task<Void> taskMakeVoid;
        if (this.mDestroyTask != null) {
            log("reload()", "Waiting for destroy to finish, before reloading React Native.");
            taskMakeVoid = this.mDestroyTask.continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda13
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$reload$5(str, task);
                }
            }, this.mBGExecutor).makeVoid();
        } else {
            taskMakeVoid = newGetOrCreateReloadTask(str).makeVoid();
        }
        return taskMakeVoid.continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda14
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$reload$6(task);
            }
        }, this.mBGExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$5(String str, Task task) throws Exception {
        return newGetOrCreateReloadTask(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$6(Task task) throws Exception {
        if (!task.isFaulted()) {
            return task;
        }
        this.mReactHostDelegate.handleInstanceException(task.getError());
        return newGetOrCreateDestroyTask("Reload failed", task.getError());
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> destroy(final String str, final Exception exc) {
        if (ReactFeatureFlags.enableBridgelessArchitectureNewCreateReloadDestroy) {
            return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda28
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$destroy$9(str, exc);
                }
            }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda16());
        }
        oldDestroy(str, exc);
        return Task.forResult((Void) Assertions.nullsafeFIXME(null, "Empty Destroy Task"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$destroy$9(final String str, final Exception exc) throws Exception {
        if (this.mReloadTask != null) {
            log("destroy()", "Reloading React Native. Waiting for reload to finish before destroying React Native.");
            return this.mReloadTask.continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda24
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$destroy$8(str, exc, task);
                }
            }, this.mBGExecutor);
        }
        return newGetOrCreateDestroyTask(str, exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$destroy$8(String str, Exception exc, Task task) throws Exception {
        return newGetOrCreateDestroyTask(str, exc);
    }

    private MemoryPressureListener createMemoryPressureListener(ReactInstance reactInstance) {
        final WeakReference weakReference = new WeakReference(reactInstance);
        return new MemoryPressureListener() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda55
            @Override // com.facebook.react.bridge.MemoryPressureListener
            public final void handleMemoryPressure(int i) {
                this.f$0.lambda$createMemoryPressureListener$11(weakReference, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createMemoryPressureListener$11(final WeakReference weakReference, final int i) {
        this.mBGExecutor.execute(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda44
            @Override // java.lang.Runnable
            public final void run() {
                ReactHostImpl.lambda$createMemoryPressureListener$10(weakReference, i);
            }
        });
    }

    static /* synthetic */ void lambda$createMemoryPressureListener$10(WeakReference weakReference, int i) {
        ReactInstance reactInstance = (ReactInstance) weakReference.get();
        if (reactInstance != null) {
            reactInstance.handleMemoryPressure(i);
        }
    }

    Activity getCurrentActivity() {
        return this.mActivity.get();
    }

    Activity getLastUsedActivity() {
        WeakReference<Activity> weakReference = this.mLastUsedActivity.get();
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void setCurrentActivity(Activity activity) {
        this.mActivity.set(activity);
        if (activity != null) {
            this.mLastUsedActivity.set(new WeakReference<>(activity));
        }
    }

    EventDispatcher getEventDispatcher() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result == null) {
            return BlackHoleEventDispatcher.get();
        }
        return result.getEventDispatcher();
    }

    FabricUIManager getUIManager() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result == null) {
            return null;
        }
        return result.getUIManager();
    }

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.hasNativeModule(cls);
        }
        return false;
    }

    Collection<NativeModule> getNativeModules() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.getNativeModules();
        }
        return new ArrayList();
    }

    <T extends NativeModule> T getNativeModule(Class<T> cls) {
        if (cls == UIManagerModule.class) {
            ReactSoftExceptionLogger.logSoftExceptionVerbose(TAG, new ReactNoCrashBridgeNotAllowedSoftException("getNativeModule(UIManagerModule.class) cannot be called when the bridge is disabled"));
        }
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return (T) result.getNativeModule(cls);
        }
        return null;
    }

    DefaultHardwareBackBtnHandler getDefaultBackButtonHandler() {
        return new DefaultHardwareBackBtnHandler() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda25
            @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
            public final void invokeDefaultOnBackPressed() {
                this.f$0.lambda$getDefaultBackButtonHandler$12();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getDefaultBackButtonHandler$12() {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    Task<Boolean> loadBundle(final JSBundleLoader jSBundleLoader) {
        log("loadBundle()", "Schedule");
        return callWithExistingReactInstance("loadBundle()", new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda0
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                this.f$0.lambda$loadBundle$13(jSBundleLoader, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBundle$13(JSBundleLoader jSBundleLoader, ReactInstance reactInstance) {
        log("loadBundle()", "Execute");
        reactInstance.loadJSBundle(jSBundleLoader);
    }

    Task<Boolean> registerSegment(final int i, final String str, final Callback callback) {
        final String str2 = "registerSegment(segmentId = \"" + i + "\", path = \"" + str + "\")";
        log(str2, "Schedule");
        return callWithExistingReactInstance(str2, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda11
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                this.f$0.lambda$registerSegment$14(str2, i, str, callback, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerSegment$14(String str, int i, String str2, Callback callback, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.registerSegment(i, str2);
        ((Callback) Assertions.assertNotNull(callback)).invoke(new Object[0]);
    }

    void handleHostException(Exception exc) {
        String str = "handleHostException(message = \"" + exc.getMessage() + "\")";
        log(str);
        destroy(str, exc);
        this.mReactHostDelegate.handleInstanceException(exc);
    }

    Task<Boolean> callFunctionOnModule(final String str, final String str2, final NativeArray nativeArray) {
        return callWithExistingReactInstance("callFunctionOnModule(\"" + str + "\", \"" + str2 + "\")", new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda27
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ((ReactInstance) obj).callFunctionOnModule(str, str2, nativeArray);
            }
        });
    }

    void attachSurface(ReactSurfaceImpl reactSurfaceImpl) {
        log("attachSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")");
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.add(reactSurfaceImpl);
        }
    }

    void detachSurface(ReactSurfaceImpl reactSurfaceImpl) {
        log("detachSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")");
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.remove(reactSurfaceImpl);
        }
    }

    boolean isSurfaceAttached(ReactSurfaceImpl reactSurfaceImpl) {
        boolean zContains;
        synchronized (this.mAttachedSurfaces) {
            zContains = this.mAttachedSurfaces.contains(reactSurfaceImpl);
        }
        return zContains;
    }

    boolean isSurfaceWithModuleNameAttached(String str) {
        synchronized (this.mAttachedSurfaces) {
            Iterator<ReactSurfaceImpl> it = this.mAttachedSurfaces.iterator();
            while (it.hasNext()) {
                if (it.next().getModuleName().equals(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.facebook.react.ReactHost
    public void addBeforeDestroyListener(Function0<Unit> function0) {
        synchronized (this.mBeforeDestroyListeners) {
            this.mBeforeDestroyListeners.add(function0);
        }
    }

    @Override // com.facebook.react.ReactHost
    public void removeBeforeDestroyListener(Function0<Unit> function0) {
        synchronized (this.mBeforeDestroyListeners) {
            this.mBeforeDestroyListeners.remove(function0);
        }
    }

    private Task<Void> oldStart() {
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda47
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$oldStart$17();
            }
        }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda16());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$oldStart$17() throws Exception {
        if (this.mStartTask == null) {
            log("oldStart()", "Schedule");
            this.mStartTask = getOrCreateReactInstanceTask().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda23
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$oldStart$16(task);
                }
            }, this.mBGExecutor).makeVoid();
        }
        return this.mStartTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$oldStart$16(Task task) throws Exception {
        if (task.isFaulted()) {
            destroy("oldStart() failure: " + task.getError().getMessage(), task.getError());
            this.mReactHostDelegate.handleInstanceException(task.getError());
        }
        return task;
    }

    private Task<Void> newStart() {
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda32
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$newStart$20();
            }
        }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda16());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newStart$20() throws Exception {
        if (this.mStartTask == null) {
            log("newStart()", "Schedule");
            this.mStartTask = waitThenCallNewGetOrCreateReactInstanceTask().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda1
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newStart$19(task);
                }
            }, this.mBGExecutor);
        }
        return this.mStartTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newStart$19(final Task task) throws Exception {
        if (task.isFaulted()) {
            this.mReactHostDelegate.handleInstanceException(task.getError());
            return newGetOrCreateDestroyTask("newStart() failure: " + task.getError().getMessage(), task.getError()).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda19
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task2) {
                    return Task.forError(task.getError());
                }
            }).makeVoid();
        }
        return task.makeVoid();
    }

    private void moveToHostDestroy(ReactContext reactContext) {
        this.mReactLifecycleStateManager.moveToOnHostDestroy(reactContext);
        setCurrentActivity(null);
    }

    private void raiseSoftException(String str, String str2) {
        raiseSoftException(str, str2, null);
    }

    private void raiseSoftException(String str, String str2, Throwable th) {
        log(str, str2);
        if (ReactFeatureFlags.enableBridgelessArchitectureSoftExceptions) {
            if (th != null) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(str + ": " + str2, th));
            } else {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(str + ": " + str2));
            }
        }
    }

    private Task<Boolean> callWithExistingReactInstance(String str, final VeniceThenable<ReactInstance> veniceThenable) {
        final String str2 = "callWithExistingReactInstance(" + str + ")";
        return this.mReactInstanceTaskRef.get().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda22
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$callWithExistingReactInstance$21(str2, veniceThenable, task);
            }
        }, this.mBGExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$callWithExistingReactInstance$21(String str, VeniceThenable veniceThenable, Task task) throws Exception {
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        if (reactInstance == null) {
            raiseSoftException(str, "Execute: ReactInstance null. Dropping work.");
            return Boolean.FALSE;
        }
        veniceThenable.then(reactInstance);
        return Boolean.TRUE;
    }

    private Task<Void> callAfterGetOrCreateReactInstance(String str, final VeniceThenable<ReactInstance> veniceThenable) {
        final String str2 = "callAfterGetOrCreateReactInstance(" + str + ")";
        return getOrCreateReactInstanceTask().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda30
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$callAfterGetOrCreateReactInstance$22(str2, veniceThenable, task);
            }
        }, this.mBGExecutor).continueWith((Continuation<TContinuationResult, TContinuationResult>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda31
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$callAfterGetOrCreateReactInstance$23(task);
            }
        }, this.mBGExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$callAfterGetOrCreateReactInstance$22(String str, VeniceThenable veniceThenable, Task task) throws Exception {
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        if (reactInstance == null) {
            raiseSoftException(str, "Execute: ReactInstance is null");
            return null;
        }
        veniceThenable.then(reactInstance);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$callAfterGetOrCreateReactInstance$23(Task task) throws Exception {
        if (!task.isFaulted()) {
            return null;
        }
        handleHostException(task.getError());
        return null;
    }

    private BridgelessReactContext getOrCreateReactContext() {
        return this.mBridgelessReactContextRef.getOrCreate(new BridgelessAtomicRef.Provider() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda12
            @Override // com.facebook.react.runtime.BridgelessAtomicRef.Provider
            public final Object get() {
                return this.f$0.lambda$getOrCreateReactContext$24();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BridgelessReactContext lambda$getOrCreateReactContext$24() {
        log("getOrCreateReactContext()", "Creating BridgelessReactContext");
        return new BridgelessReactContext(this.mContext, this);
    }

    private Task<ReactInstance> getOrCreateReactInstanceTask() {
        if (ReactFeatureFlags.enableBridgelessArchitectureNewCreateReloadDestroy) {
            return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda50
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.waitThenCallNewGetOrCreateReactInstanceTask();
                }
            }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda16());
        }
        return oldGetOrCreateReactInstanceTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<ReactInstance> waitThenCallNewGetOrCreateReactInstanceTask() {
        return waitThenCallNewGetOrCreateReactInstanceTaskWithRetries(0, 4);
    }

    private Task<ReactInstance> waitThenCallNewGetOrCreateReactInstanceTaskWithRetries(final int i, final int i2) {
        if (this.mReloadTask != null) {
            log("waitThenCallNewGetOrCreateReactInstanceTaskWithRetries", "React Native is reloading. Return reload task.");
            return this.mReloadTask;
        }
        if (this.mDestroyTask != null) {
            if (i < i2) {
                log("waitThenCallNewGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down.Wait for teardown to finish, before trying again (try count = " + i + ").");
                return this.mDestroyTask.onSuccessTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda56
                    @Override // com.facebook.react.runtime.internal.bolts.Continuation
                    public final Object then(Task task) {
                        return this.f$0.lambda$waitThenCallNewGetOrCreateReactInstanceTaskWithRetries$25(i, i2, task);
                    }
                }, this.mBGExecutor);
            }
            raiseSoftException("waitThenCallNewGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down. Not wait for teardown to finish: reached max retries.");
        }
        return newGetOrCreateReactInstanceTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$waitThenCallNewGetOrCreateReactInstanceTaskWithRetries$25(int i, int i2, Task task) throws Exception {
        return waitThenCallNewGetOrCreateReactInstanceTaskWithRetries(i + 1, i2);
    }

    private Task<ReactInstance> newGetOrCreateReactInstanceTask() {
        log("newGetOrCreateReactInstanceTask()");
        return this.mReactInstanceTaskRef.getOrCreate(new BridgelessAtomicRef.Provider() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda46
            @Override // com.facebook.react.runtime.BridgelessAtomicRef.Provider
            public final Object get() {
                return this.f$0.lambda$newGetOrCreateReactInstanceTask$29();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReactInstanceTask$29() {
        log("newGetOrCreateReactInstanceTask()", "Start");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_START, 1);
        return getJsBundleLoader().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda53
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$newGetOrCreateReactInstanceTask$27(task);
            }
        }, this.mBGExecutor).onSuccess((Continuation<TContinuationResult, TContinuationResult>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda54
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$newGetOrCreateReactInstanceTask$28(task);
            }
        }, this.mUIExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ C1Result lambda$newGetOrCreateReactInstanceTask$27(Task task) throws Exception {
        JSBundleLoader jSBundleLoader = (JSBundleLoader) task.getResult();
        BridgelessReactContext orCreateReactContext = getOrCreateReactContext();
        DevSupportManager devSupportManager = getDevSupportManager();
        orCreateReactContext.setJSExceptionHandler(devSupportManager);
        log("newGetOrCreateReactInstanceTask()", "Creating ReactInstance");
        ReactInstance reactInstance = new ReactInstance(orCreateReactContext, this.mReactHostDelegate, this.mComponentFactory, devSupportManager, this.mQueueThreadExceptionHandler, this.mReactJsExceptionHandler, this.mUseDevSupport);
        if (ReactFeatureFlags.unstable_bridgelessArchitectureMemoryPressureHackyBoltsFix) {
            this.mMemoryPressureListener = createMemoryPressureListener(reactInstance);
        }
        this.mMemoryPressureRouter.addMemoryPressureListener(this.mMemoryPressureListener);
        log("newGetOrCreateReactInstanceTask()", "Loading JS Bundle");
        reactInstance.loadJSBundle(jSBundleLoader);
        log("newGetOrCreateReactInstanceTask()", "Calling DevSupportManagerBase.onNewReactContextCreated(reactContext)");
        devSupportManager.onNewReactContextCreated(orCreateReactContext);
        orCreateReactContext.runOnJSQueueThread(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda57
            @Override // java.lang.Runnable
            public final void run() {
                ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_END, 1);
            }
        });
        return new C1Result(reactInstance, orCreateReactContext);
    }

    /* renamed from: com.facebook.react.runtime.ReactHostImpl$1Result, reason: invalid class name */
    class C1Result {
        final ReactContext mContext;
        final ReactInstance mInstance;
        final boolean mIsReloading;
        final /* synthetic */ ReactInstance val$instance;
        final /* synthetic */ BridgelessReactContext val$reactContext;

        C1Result(ReactInstance reactInstance, BridgelessReactContext bridgelessReactContext) {
            this.val$instance = reactInstance;
            this.val$reactContext = bridgelessReactContext;
            this.mInstance = reactInstance;
            this.mContext = bridgelessReactContext;
            this.mIsReloading = ReactHostImpl.this.mReloadTask != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$newGetOrCreateReactInstanceTask$28(Task task) throws Exception {
        ReactInstance reactInstance = ((C1Result) task.getResult()).mInstance;
        ReactContext reactContext = ((C1Result) task.getResult()).mContext;
        boolean z = ((C1Result) task.getResult()).mIsReloading;
        boolean z2 = this.mReactLifecycleStateManager.getLifecycleState() == LifecycleState.RESUMED;
        if (z && !z2) {
            this.mReactLifecycleStateManager.moveToOnHostResume(reactContext, getCurrentActivity());
        } else {
            this.mReactLifecycleStateManager.resumeReactContextIfHostResumed(reactContext, getCurrentActivity());
        }
        ReactInstanceEventListener[] reactInstanceEventListenerArr = (ReactInstanceEventListener[]) this.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]);
        log("newGetOrCreateReactInstanceTask()", "Executing ReactInstanceEventListeners");
        for (ReactInstanceEventListener reactInstanceEventListener : reactInstanceEventListenerArr) {
            if (reactInstanceEventListener != null) {
                reactInstanceEventListener.onReactContextInitialized(reactContext);
            }
        }
        return reactInstance;
    }

    private Task<ReactInstance> oldGetOrCreateReactInstanceTask() {
        log("oldGetOrCreateReactInstanceTask()");
        return this.mReactInstanceTaskRef.getOrCreate(new BridgelessAtomicRef.Provider() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda18
            @Override // com.facebook.react.runtime.BridgelessAtomicRef.Provider
            public final Object get() {
                return this.f$0.lambda$oldGetOrCreateReactInstanceTask$33();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$oldGetOrCreateReactInstanceTask$33() {
        log("oldGetOrCreateReactInstanceTask()", "Start");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_START, 1);
        final BridgelessReactContext orCreateReactContext = getOrCreateReactContext();
        final DevSupportManager devSupportManager = getDevSupportManager();
        orCreateReactContext.setJSExceptionHandler(devSupportManager);
        return getJsBundleLoader().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda48
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$oldGetOrCreateReactInstanceTask$31(orCreateReactContext, devSupportManager, task);
            }
        }, this.mBGExecutor).onSuccess((Continuation<TContinuationResult, TContinuationResult>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda49
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return this.f$0.lambda$oldGetOrCreateReactInstanceTask$32(orCreateReactContext, task);
            }
        }, this.mUIExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$oldGetOrCreateReactInstanceTask$31(BridgelessReactContext bridgelessReactContext, DevSupportManager devSupportManager, Task task) throws Exception {
        JSBundleLoader jSBundleLoader = (JSBundleLoader) task.getResult();
        log("oldGetOrCreateReactInstanceTask()", "Creating ReactInstance");
        ReactInstance reactInstance = new ReactInstance(bridgelessReactContext, this.mReactHostDelegate, this.mComponentFactory, devSupportManager, this.mQueueThreadExceptionHandler, this.mReactJsExceptionHandler, this.mUseDevSupport);
        if (ReactFeatureFlags.unstable_bridgelessArchitectureMemoryPressureHackyBoltsFix) {
            this.mMemoryPressureListener = createMemoryPressureListener(reactInstance);
        }
        this.mMemoryPressureRouter.addMemoryPressureListener(this.mMemoryPressureListener);
        log("oldGetOrCreateReactInstanceTask()", "Loading JS Bundle");
        reactInstance.loadJSBundle(jSBundleLoader);
        log("oldGetOrCreateReactInstanceTask()", "Calling DevSupportManagerBase.onNewReactContextCreated(reactContext)");
        devSupportManager.onNewReactContextCreated(bridgelessReactContext);
        bridgelessReactContext.runOnJSQueueThread(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda34
            @Override // java.lang.Runnable
            public final void run() {
                ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_END, 1);
            }
        });
        return reactInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$oldGetOrCreateReactInstanceTask$32(BridgelessReactContext bridgelessReactContext, Task task) throws Exception {
        this.mReactLifecycleStateManager.resumeReactContextIfHostResumed(bridgelessReactContext, getCurrentActivity());
        ReactInstanceEventListener[] reactInstanceEventListenerArr = (ReactInstanceEventListener[]) this.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]);
        log("oldGetOrCreateReactInstanceTask()", "Executing ReactInstanceEventListeners");
        for (ReactInstanceEventListener reactInstanceEventListener : reactInstanceEventListenerArr) {
            if (reactInstanceEventListener != null) {
                reactInstanceEventListener.onReactContextInitialized(bridgelessReactContext);
            }
        }
        return (ReactInstance) task.getResult();
    }

    private Task<JSBundleLoader> getJsBundleLoader() {
        log("getJSBundleLoader()");
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda26
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$getJsBundleLoader$35();
            }
        });
    }

    private /* synthetic */ Task lambda$getJsBundleLoader$34(Task task) throws Exception {
        if (((Boolean) task.getResult()).booleanValue()) {
            return loadJSBundleFromMetro();
        }
        return Task.forResult(this.mReactHostDelegate.getJsBundleLoader());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ JSBundleLoader lambda$getJsBundleLoader$35() throws Exception {
        return this.mReactHostDelegate.getJsBundleLoader();
    }

    private Task<Boolean> isMetroRunning() {
        log("isMetroRunning()");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        getDevSupportManager().isPackagerRunning(new PackagerStatusCallback() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda17
            @Override // com.facebook.react.devsupport.interfaces.PackagerStatusCallback
            public final void onPackagerStatusFetched(boolean z) {
                this.f$0.lambda$isMetroRunning$36(taskCompletionSource, z);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$isMetroRunning$36(TaskCompletionSource taskCompletionSource, boolean z) {
        log("isMetroRunning()", "Async result = " + z);
        taskCompletionSource.setResult(Boolean.valueOf(z));
    }

    private Task<JSBundleLoader> loadJSBundleFromMetro() {
        log("loadJSBundleFromMetro()");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final DevSupportManagerBase devSupportManagerBase = (DevSupportManagerBase) getDevSupportManager();
        final String devServerBundleURL = devSupportManagerBase.getDevServerHelper().getDevServerBundleURL((String) Assertions.assertNotNull(devSupportManagerBase.getJSAppBundleName()));
        devSupportManagerBase.reloadJSFromServer(devServerBundleURL, new BundleLoadCallback() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda21
            @Override // com.facebook.react.devsupport.interfaces.BundleLoadCallback
            public final void onSuccess() {
                this.f$0.lambda$loadJSBundleFromMetro$37(devServerBundleURL, devSupportManagerBase, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadJSBundleFromMetro$37(String str, DevSupportManagerBase devSupportManagerBase, TaskCompletionSource taskCompletionSource) {
        log("loadJSBundleFromMetro()", "Creating BundleLoader");
        taskCompletionSource.setResult(JSBundleLoader.createCachedBundleFromNetworkLoader(str, devSupportManagerBase.getDownloadedJSBundleFile()));
    }

    private void log(String str, String str2) {
        this.mBridgelessReactStateTracker.enterState("ReactHost{" + this.mId + "}." + str + ": " + str2);
    }

    private void log(String str) {
        this.mBridgelessReactStateTracker.enterState("ReactHost{" + this.mId + "}." + str);
    }

    private void stopAttachedSurfaces(String str, ReactInstance reactInstance) {
        log(str, "Stopping all React Native surfaces");
        synchronized (this.mAttachedSurfaces) {
            for (ReactSurfaceImpl reactSurfaceImpl : this.mAttachedSurfaces) {
                reactInstance.stopSurface(reactSurfaceImpl);
                reactSurfaceImpl.clear();
            }
        }
    }

    private void startAttachedSurfaces(String str, ReactInstance reactInstance) {
        log(str, "Restarting previously running React Native Surfaces");
        synchronized (this.mAttachedSurfaces) {
            Iterator<ReactSurfaceImpl> it = this.mAttachedSurfaces.iterator();
            while (it.hasNext()) {
                reactInstance.startSurface(it.next());
            }
        }
    }

    private ReactInstanceTaskUnwrapper createReactInstanceUnwraper(final String str, final String str2, final String str3) {
        return new ReactInstanceTaskUnwrapper() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda33
            @Override // com.facebook.react.runtime.ReactHostImpl.ReactInstanceTaskUnwrapper
            public final ReactInstance unwrap(Task task, String str4) {
                return this.f$0.lambda$createReactInstanceUnwraper$38(str, str3, str2, task, str4);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$createReactInstanceUnwraper$38(String str, String str2, String str3, Task task, String str4) {
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        String str5 = "Stage: " + str4;
        String str6 = str + " reason: " + str2;
        if (task.isFaulted()) {
            raiseSoftException(str3, str + ": ReactInstance task faulted. " + str5 + ". " + ("Fault reason: " + task.getError().getMessage()) + ". " + str6);
            return result;
        }
        if (task.isCancelled()) {
            raiseSoftException(str3, str + ": ReactInstance task cancelled. " + str5 + ". " + str6);
            return result;
        }
        if (reactInstance == null) {
            raiseSoftException(str3, str + ": ReactInstance task returned null. " + str5 + ". " + str6);
            return result;
        }
        if (result != null && reactInstance != result) {
            raiseSoftException(str3, str + ": Detected two different ReactInstances. Returning old. " + str5 + ". " + str6);
        }
        return reactInstance;
    }

    private Task<ReactInstance> newGetOrCreateReloadTask(final String str) {
        log("newGetOrCreateReloadTask()");
        raiseSoftException("newGetOrCreateReloadTask()", str);
        final ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapperCreateReactInstanceUnwraper = createReactInstanceUnwraper("Reload", "newGetOrCreateReloadTask()", str);
        if (this.mReloadTask == null) {
            this.mReloadTask = this.mReactInstanceTaskRef.get().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda36
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateReloadTask$39(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, str, task);
                }
            }, this.mUIExecutor).continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda37
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateReloadTask$40(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda38
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateReloadTask$41(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda39
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateReloadTask$42(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda40
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateReloadTask$43(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda41
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateReloadTask$44(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda42
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateReloadTask$45(str, task);
                }
            }, this.mBGExecutor);
        }
        return this.mReloadTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReloadTask$39(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        log("newGetOrCreateReloadTask()", "Starting React Native reload");
        ReactInstance reactInstanceUnwrap = reactInstanceTaskUnwrapper.unwrap(task, "1: Starting reload");
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("newGetOrCreateReloadTask()", "ReactContext is null. Reload reason: " + str);
        }
        if (nullable != null && this.mReactLifecycleStateManager.getLifecycleState() == LifecycleState.RESUMED) {
            log("newGetOrCreateReloadTask()", "Calling ReactContext.onHostPause()");
            nullable.onHostPause();
        }
        return Task.forResult(reactInstanceUnwrap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReloadTask$40(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance reactInstanceUnwrap = reactInstanceTaskUnwrapper.unwrap(task, "2: Surface shutdown");
        if (reactInstanceUnwrap == null) {
            raiseSoftException("newGetOrCreateReloadTask()", "Skipping surface shutdown: ReactInstance null");
            return task;
        }
        stopAttachedSurfaces("newGetOrCreateReloadTask()", reactInstanceUnwrap);
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReloadTask$41(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        HashSet hashSet;
        reactInstanceTaskUnwrapper.unwrap(task, "3: Executing Before Destroy Listeners");
        synchronized (this.mBeforeDestroyListeners) {
            hashSet = new HashSet(this.mBeforeDestroyListeners);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReloadTask$42(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        reactInstanceTaskUnwrapper.unwrap(task, "4: Destroying ReactContext");
        log("newGetOrCreateReloadTask()", "Removing memory pressure listener");
        this.mMemoryPressureRouter.removeMemoryPressureListener(this.mMemoryPressureListener);
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable != null) {
            log("newGetOrCreateReloadTask()", "Destroying ReactContext");
            nullable.destroy();
        }
        if (this.mUseDevSupport && nullable != null) {
            log("newGetOrCreateReloadTask()", "Calling DevSupportManager.onReactInstanceDestroyed(reactContext)");
            this.mDevSupportManager.onReactInstanceDestroyed(nullable);
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReloadTask$43(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance reactInstanceUnwrap = reactInstanceTaskUnwrapper.unwrap(task, "5: Destroying ReactInstance");
        if (reactInstanceUnwrap == null) {
            raiseSoftException("newGetOrCreateReloadTask()", "Skipping ReactInstance.destroy(): ReactInstance null");
        } else {
            log("newGetOrCreateReloadTask()", "Destroying ReactInstance");
            reactInstanceUnwrap.destroy();
        }
        log("newGetOrCreateReloadTask()", "Resetting ReactContext ref");
        this.mBridgelessReactContextRef.reset();
        log("newGetOrCreateReloadTask()", "Resetting ReactInstance task ref");
        this.mReactInstanceTaskRef.reset();
        log("newGetOrCreateReloadTask()", "Resetting preload task ref");
        this.mStartTask = null;
        return newGetOrCreateReactInstanceTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReloadTask$44(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance reactInstanceUnwrap = reactInstanceTaskUnwrapper.unwrap(task, "7: Restarting surfaces");
        if (reactInstanceUnwrap == null) {
            raiseSoftException("newGetOrCreateReloadTask()", "Skipping surface restart: ReactInstance null");
            return task;
        }
        startAttachedSurfaces("newGetOrCreateReloadTask()", reactInstanceUnwrap);
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateReloadTask$45(String str, Task task) throws Exception {
        if (task.isFaulted()) {
            raiseSoftException("newGetOrCreateReloadTask()", "Error during reload. ReactInstance task faulted. Fault reason: " + task.getError().getMessage() + ". Reload reason: " + str, task.getError());
        }
        if (task.isCancelled()) {
            raiseSoftException("newGetOrCreateReloadTask()", "Error during reload. ReactInstance task cancelled. Reload reason: " + str);
        }
        log("newGetOrCreateReloadTask()", "Resetting reload task ref");
        this.mReloadTask = null;
        return task;
    }

    private Task<Void> newGetOrCreateDestroyTask(final String str, Exception exc) {
        log("newGetOrCreateDestroyTask()");
        raiseSoftException("newGetOrCreateDestroyTask()", str, exc);
        final ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapperCreateReactInstanceUnwraper = createReactInstanceUnwraper("Destroy", "newGetOrCreateDestroyTask()", str);
        if (this.mDestroyTask == null) {
            this.mDestroyTask = this.mReactInstanceTaskRef.get().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda2
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateDestroyTask$46(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, str, task);
                }
            }, this.mUIExecutor).continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda3
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateDestroyTask$47(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda4
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateDestroyTask$48(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda5
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateDestroyTask$49(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, str, task);
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda6
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateDestroyTask$50(reactInstanceTaskUnwrapperCreateReactInstanceUnwraper, task);
                }
            }, this.mBGExecutor).continueWith(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda7
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return this.f$0.lambda$newGetOrCreateDestroyTask$51(str, task);
                }
            });
        }
        return this.mDestroyTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateDestroyTask$46(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        log("newGetOrCreateDestroyTask()", "Starting React Native destruction");
        ReactInstance reactInstanceUnwrap = reactInstanceTaskUnwrapper.unwrap(task, "1: Starting destroy");
        if (this.mUseDevSupport) {
            log("newGetOrCreateDestroyTask()", "DevSupportManager cleanup");
            this.mDevSupportManager.stopInspector();
        }
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("newGetOrCreateDestroyTask()", "ReactContext is null. Destroy reason: " + str);
        }
        log("newGetOrCreateDestroyTask()", "Move ReactHost to onHostDestroy()");
        this.mReactLifecycleStateManager.moveToOnHostDestroy(nullable);
        return Task.forResult(reactInstanceUnwrap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateDestroyTask$47(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance reactInstanceUnwrap = reactInstanceTaskUnwrapper.unwrap(task, "2: Stopping surfaces");
        if (reactInstanceUnwrap == null) {
            raiseSoftException("newGetOrCreateDestroyTask()", "Skipping surface shutdown: ReactInstance null");
            return task;
        }
        stopAttachedSurfaces("newGetOrCreateDestroyTask()", reactInstanceUnwrap);
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateDestroyTask$48(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        HashSet hashSet;
        reactInstanceTaskUnwrapper.unwrap(task, "3: Executing Before Destroy Listeners");
        synchronized (this.mBeforeDestroyListeners) {
            hashSet = new HashSet(this.mBeforeDestroyListeners);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateDestroyTask$49(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        reactInstanceTaskUnwrapper.unwrap(task, "4: Destroying ReactContext");
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("newGetOrCreateDestroyTask()", "ReactContext is null. Destroy reason: " + str);
        }
        log("newGetOrCreateDestroyTask()", "Destroying MemoryPressureRouter");
        this.mMemoryPressureRouter.destroy(this.mContext);
        if (nullable != null) {
            log("newGetOrCreateDestroyTask()", "Destroying ReactContext");
            nullable.destroy();
        }
        setCurrentActivity(null);
        ResourceDrawableIdHelper.getInstance().clear();
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$newGetOrCreateDestroyTask$50(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance reactInstanceUnwrap = reactInstanceTaskUnwrapper.unwrap(task, "5: Destroying ReactInstance");
        if (reactInstanceUnwrap == null) {
            raiseSoftException("newGetOrCreateDestroyTask()", "Skipping ReactInstance.destroy(): ReactInstance null");
        } else {
            log("newGetOrCreateDestroyTask()", "Destroying ReactInstance");
            reactInstanceUnwrap.destroy();
        }
        log("newGetOrCreateDestroyTask()", "Resetting ReactContext ref ");
        this.mBridgelessReactContextRef.reset();
        log("newGetOrCreateDestroyTask()", "Resetting ReactInstance task ref");
        this.mReactInstanceTaskRef.reset();
        log("newGetOrCreateDestroyTask()", "Resetting Preload task ref");
        this.mStartTask = null;
        log("newGetOrCreateDestroyTask()", "Resetting destroy task ref");
        this.mDestroyTask = null;
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$newGetOrCreateDestroyTask$51(String str, Task task) throws Exception {
        if (task.isFaulted()) {
            raiseSoftException("newGetOrCreateDestroyTask()", "React destruction failed. ReactInstance task faulted. Fault reason: " + task.getError().getMessage() + ". Destroy reason: " + str, task.getError());
        }
        if (!task.isCancelled()) {
            return null;
        }
        raiseSoftException("newGetOrCreateDestroyTask()", "React destruction failed. ReactInstance task cancelled. Destroy reason: " + str);
        return null;
    }

    private Task<Void> oldReload(String str) {
        Task<Void> taskCallAfterGetOrCreateReactInstance;
        log("oldReload()");
        raiseSoftException("oldReload()", str);
        synchronized (this.mReactInstanceTaskRef) {
            this.mMemoryPressureRouter.removeMemoryPressureListener(this.mMemoryPressureListener);
            oldDestroyReactInstanceAndContext("oldReload()", str);
            taskCallAfterGetOrCreateReactInstance = callAfterGetOrCreateReactInstance("oldReload()", new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda29
                @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
                public final void then(Object obj) {
                    this.f$0.lambda$oldReload$52((ReactInstance) obj);
                }
            });
        }
        return taskCallAfterGetOrCreateReactInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$oldReload$52(ReactInstance reactInstance) {
        log("oldReload()", "Restarting Surfaces");
        synchronized (this.mAttachedSurfaces) {
            Iterator<ReactSurfaceImpl> it = this.mAttachedSurfaces.iterator();
            while (it.hasNext()) {
                reactInstance.startSurface(it.next());
            }
        }
    }

    private void oldDestroy(String str, Exception exc) {
        log("oldDestroy()");
        raiseSoftException("oldDestroy()", str, exc);
        synchronized (this.mReactInstanceTaskRef) {
            if (this.mReactInstanceTaskRef.get().getResult() == null) {
                return;
            }
            final ReactContext currentReactContext = getCurrentReactContext();
            if (currentReactContext != null) {
                this.mMemoryPressureRouter.destroy(currentReactContext);
            }
            oldDestroyReactInstanceAndContext("oldDestroy()", str);
            log("oldDestroy()", "Clearing attached surfaces");
            synchronized (this.mAttachedSurfaces) {
                this.mAttachedSurfaces.clear();
            }
            Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda51
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$oldDestroy$53(currentReactContext);
                }
            }, this.mUIExecutor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$oldDestroy$53(ReactContext reactContext) throws Exception {
        moveToHostDestroy(reactContext);
        return null;
    }

    private void oldDestroyReactInstanceAndContext(String str, String str2) {
        final String str3 = "oldDestroyReactInstanceAndContext(" + str + ")";
        log(str3);
        synchronized (this.mReactInstanceTaskRef) {
            Task<ReactInstance> andReset = this.mReactInstanceTaskRef.getAndReset();
            if (!andReset.isFaulted() && !andReset.isCancelled()) {
                final ReactInstance result = andReset.getResult();
                if (result == null) {
                    log(str3, "ReactInstance is null");
                    return;
                }
                log(str3, "Stopping surfaces");
                synchronized (this.mAttachedSurfaces) {
                    for (ReactSurfaceImpl reactSurfaceImpl : this.mAttachedSurfaces) {
                        result.stopSurface(reactSurfaceImpl);
                        reactSurfaceImpl.clear();
                    }
                }
                ReactContext currentReactContext = getCurrentReactContext();
                if (currentReactContext != null) {
                    log(str3, "DevSupportManager.onReactInstanceDestroyed()");
                    getDevSupportManager().onReactInstanceDestroyed(currentReactContext);
                    log(str3, "Destroy ReactContext");
                    this.mBridgelessReactContextRef.reset();
                }
                this.mBGExecutor.execute(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda43
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$oldDestroyReactInstanceAndContext$54(str3, result);
                    }
                });
            } else {
                raiseSoftException(str3, "Not cleaning up ReactInstance: task.isFaulted() = " + andReset.isFaulted() + ", task.isCancelled() = " + andReset.isCancelled() + ". Reason: " + str2);
                this.mBGExecutor.execute(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda45
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$oldDestroyReactInstanceAndContext$55(str3);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$oldDestroyReactInstanceAndContext$54(String str, ReactInstance reactInstance) {
        log(str, "Destroy ReactInstance");
        reactInstance.destroy();
        log(str, "Resetting Preload task ref");
        this.mStartTask = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$oldDestroyReactInstanceAndContext$55(String str) {
        log(str, "Resetting Preload task ref");
        this.mStartTask = null;
    }

    public void setJSEngineResolutionAlgorithm(JSEngineResolutionAlgorithm jSEngineResolutionAlgorithm) {
        this.mJSEngineResolutionAlgorithm = jSEngineResolutionAlgorithm;
    }

    public JSEngineResolutionAlgorithm getJSEngineResolutionAlgorithm() {
        return this.mJSEngineResolutionAlgorithm;
    }
}
