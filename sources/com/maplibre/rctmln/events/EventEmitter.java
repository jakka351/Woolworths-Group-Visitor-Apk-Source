package com.maplibre.rctmln.events;

import android.util.Log;
import com.facebook.react.ReactApplication;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;

/* loaded from: classes3.dex */
public class EventEmitter {
    public static final String LOG_TAG = "EventEmitter";

    private static ReactContext getCurrentReactContext(ReactApplicationContext reactApplicationContext) {
        if (reactApplicationContext.getApplicationContext() instanceof ReactApplication) {
            return ((ReactApplication) reactApplicationContext.getApplicationContext()).getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
        }
        Log.d(LOG_TAG, "getApplicationContext() application doesn't implement ReactApplication");
        return reactApplicationContext;
    }

    public static RCTNativeAppEventEmitter getModuleEmitter(ReactApplicationContext reactApplicationContext) {
        try {
            return (RCTNativeAppEventEmitter) getCurrentReactContext(reactApplicationContext).getJSModule(RCTNativeAppEventEmitter.class);
        } catch (NullPointerException e) {
            Log.d(LOG_TAG, e.getLocalizedMessage());
            return null;
        }
    }
}
