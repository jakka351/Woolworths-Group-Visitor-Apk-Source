package com.facebook.react;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/* loaded from: classes.dex */
public class ReactDelegate {
    private final Activity mActivity;
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    private boolean mFabricEnabled;
    private Bundle mLaunchOptions;
    private final String mMainComponentName;
    private ReactHost mReactHost;
    private ReactNativeHost mReactNativeHost;
    private ReactRootView mReactRootView;
    private ReactSurface mReactSurface;

    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, String str, Bundle bundle) {
        this.mFabricEnabled = false;
        this.mActivity = activity;
        this.mMainComponentName = str;
        this.mLaunchOptions = bundle;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mReactNativeHost = reactNativeHost;
    }

    public ReactDelegate(Activity activity, ReactHost reactHost, String str, Bundle bundle) {
        this.mFabricEnabled = false;
        this.mActivity = activity;
        this.mMainComponentName = str;
        this.mLaunchOptions = bundle;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mReactHost = reactHost;
    }

    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, String str, Bundle bundle, boolean z) {
        this.mFabricEnabled = false;
        this.mActivity = activity;
        this.mMainComponentName = str;
        this.mLaunchOptions = composeLaunchOptions(bundle);
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mReactNativeHost = reactNativeHost;
        this.mFabricEnabled = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onHostResume() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            Activity activity = this.mActivity;
            if (activity instanceof DefaultHardwareBackBtnHandler) {
                this.mReactHost.onHostResume(activity, (DefaultHardwareBackBtnHandler) activity);
                return;
            }
            return;
        }
        if (getReactNativeHost().hasInstance()) {
            if (this.mActivity instanceof DefaultHardwareBackBtnHandler) {
                ReactInstanceManager reactInstanceManager = getReactNativeHost().getReactInstanceManager();
                Activity activity2 = this.mActivity;
                reactInstanceManager.onHostResume(activity2, (DefaultHardwareBackBtnHandler) activity2);
                return;
            }
            throw new ClassCastException("Host Activity does not implement DefaultHardwareBackBtnHandler");
        }
    }

    public void onHostPause() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onHostPause(this.mActivity);
        } else if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostPause(this.mActivity);
        }
    }

    public void onHostDestroy() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onHostDestroy(this.mActivity);
            return;
        }
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostDestroy(this.mActivity);
        }
    }

    public boolean onBackPressed() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            this.mReactHost.onBackPressed();
            return true;
        }
        if (!getReactNativeHost().hasInstance()) {
            return false;
        }
        getReactNativeHost().getReactInstanceManager().onBackPressed();
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent, boolean z) {
        if (!ReactFeatureFlags.enableBridgelessArchitecture && getReactNativeHost().hasInstance() && z) {
            getReactNativeHost().getReactInstanceManager().onActivityResult(this.mActivity, i, i2, intent);
        }
    }

    public void loadApp() {
        loadApp(this.mMainComponentName);
    }

    public void loadApp(String str) {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            if (this.mReactSurface == null) {
                ReactSurface reactSurfaceCreateSurface = this.mReactHost.createSurface(this.mActivity, str, this.mLaunchOptions);
                this.mReactSurface = reactSurfaceCreateSurface;
                this.mActivity.setContentView(reactSurfaceCreateSurface.getView());
            }
            this.mReactSurface.start();
            return;
        }
        if (this.mReactRootView != null) {
            throw new IllegalStateException("Cannot loadApp while app is already running.");
        }
        ReactRootView reactRootViewCreateRootView = createRootView();
        this.mReactRootView = reactRootViewCreateRootView;
        reactRootViewCreateRootView.startReactApplication(getReactNativeHost().getReactInstanceManager(), str, this.mLaunchOptions);
    }

    public ReactRootView getReactRootView() {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            return (ReactRootView) this.mReactSurface.getView();
        }
        return this.mReactRootView;
    }

    protected ReactRootView createRootView() {
        ReactRootView reactRootView = new ReactRootView(this.mActivity);
        reactRootView.setIsFabric(isFabricEnabled());
        return reactRootView;
    }

    public boolean shouldShowDevMenuOrReload(int i, KeyEvent keyEvent) {
        if (!ReactFeatureFlags.enableBridgelessArchitecture && getReactNativeHost().hasInstance() && getReactNativeHost().getUseDeveloperSupport()) {
            if (i == 82) {
                getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
                return true;
            }
            if (((DoubleTapReloadRecognizer) Assertions.assertNotNull(this.mDoubleTapReloadRecognizer)).didDoubleTapR(i, this.mActivity.getCurrentFocus())) {
                getReactNativeHost().getReactInstanceManager().getDevSupportManager().handleReloadJS();
                return true;
            }
        }
        return false;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.mReactNativeHost;
    }

    public ReactInstanceManager getReactInstanceManager() {
        return getReactNativeHost().getReactInstanceManager();
    }

    protected boolean isFabricEnabled() {
        return this.mFabricEnabled;
    }

    private Bundle composeLaunchOptions(Bundle bundle) {
        if (isFabricEnabled()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("concurrentRoot", true);
        }
        return bundle;
    }
}
