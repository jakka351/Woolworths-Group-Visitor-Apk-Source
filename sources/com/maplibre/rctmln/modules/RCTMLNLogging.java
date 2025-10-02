package com.maplibre.rctmln.modules;

import android.util.Log;
import app.notifee.core.event.LogEvent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import org.maplibre.android.log.Logger;
import org.maplibre.android.log.LoggerDefinition;

@ReactModule(name = RCTMLNLogging.REACT_CLASS)
/* loaded from: classes3.dex */
public class RCTMLNLogging extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "RCTMLNLogging";
    private ReactApplicationContext mReactContext;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public RCTMLNLogging(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mReactContext = reactApplicationContext;
        Logger.setVerbosity(5);
        Logger.setLoggerDefinition(new LoggerDefinition() { // from class: com.maplibre.rctmln.modules.RCTMLNLogging.1
            @Override // org.maplibre.android.log.LoggerDefinition
            public void v(String str, String str2) {
                Log.v(str, str2);
                RCTMLNLogging.this.onLog(LogEvent.LEVEL_VERBOSE, str, str2, null);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void v(String str, String str2, Throwable th) {
                Log.v(str, str2, th);
                RCTMLNLogging.this.onLog(LogEvent.LEVEL_VERBOSE, str, str2, th);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void d(String str, String str2) {
                Log.d(str, str2);
                RCTMLNLogging.this.onLog(LogEvent.LEVEL_DEBUG, str, str2, null);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void d(String str, String str2, Throwable th) {
                Log.d(str, str2, th);
                RCTMLNLogging.this.onLog(LogEvent.LEVEL_DEBUG, str, str2, th);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void i(String str, String str2) {
                Log.i(str, str2);
                RCTMLNLogging.this.onLog(LogEvent.LEVEL_INFO, str, str2, null);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void i(String str, String str2, Throwable th) {
                Log.i(str, str2, th);
                RCTMLNLogging.this.onLog(LogEvent.LEVEL_INFO, str, str2, th);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void w(String str, String str2) {
                Log.w(str, str2);
                RCTMLNLogging.this.onLog("warning", str, str2, null);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void w(String str, String str2, Throwable th) {
                Log.w(str, str2, th);
                RCTMLNLogging.this.onLog("warning", str, str2, th);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void e(String str, String str2) {
                Log.e(str, str2);
                RCTMLNLogging.this.onLog("error", str, str2, null);
            }

            @Override // org.maplibre.android.log.LoggerDefinition
            public void e(String str, String str2, Throwable th) {
                Log.e(str, str2, th);
                RCTMLNLogging.this.onLog("error", str, str2, th);
            }
        });
    }

    @ReactMethod
    public void setLogLevel(String str) {
        int i;
        str.hashCode();
        i = 4;
        switch (str) {
            case "info":
                break;
            case "debug":
                i = 3;
                break;
            case "error":
                i = 6;
                break;
            case "verbose":
                i = 2;
                break;
            case "warning":
                i = 5;
                break;
            default:
                i = 99;
                break;
        }
        Logger.setVerbosity(i);
    }

    public void onLog(String str, String str2, String str3, Throwable th) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("message", str3);
        writableMapCreateMap.putString("tag", str2);
        writableMapCreateMap.putString("level", str);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("LogEvent", writableMapCreateMap);
    }
}
