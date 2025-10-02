package com.site360new;

import android.content.SharedPreferences;
import android.os.Build;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import io.sentry.Sentry;
import io.sentry.protocol.Device;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: RNDeviceInfo.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0018\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0007J \u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0011H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/site360new/RNDeviceInfo;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clearJson", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getBuild", "callback", "Lcom/facebook/react/bridge/Callback;", "getDeviceInfo", "getName", "", "getPackageName", "getTimeZone", "readJson", "fileName", "saveJson", "json", "setLanguage", Device.JsonKeys.LANGUAGE, "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNDeviceInfo extends ReactContextBaseJavaModule {
    private static final String TAG = "RNDeviceInfo";
    private final ReactApplicationContext reactContext;
    private final SharedPreferences sharedPreferences;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RNDeviceInfo(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.sharedPreferences = reactContext.getSharedPreferences("sharedPreferences", 0);
    }

    @ReactMethod
    public final void getBuild(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.invoke(Integer.valueOf(BuildConfig.VERSION_CODE));
    }

    @ReactMethod
    public final void getPackageName(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.invoke(this.reactContext.getPackageName());
    }

    @ReactMethod
    public final void getDeviceInfo(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        this.sharedPreferences.edit().putString(ResultKey.DEVICE_ID.getValue(), string).apply();
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(ResultKey.DEVICE_ID.getValue(), string);
        writableNativeMap.putString(ResultKey.DEVICE_MODEL.getValue(), Build.DEVICE);
        writableNativeMap.putString(ResultKey.DEVICE_MANUFACTURER.getValue(), Build.MANUFACTURER);
        callback.invoke(writableNativeMap);
    }

    @ReactMethod
    public final void getTimeZone(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.invoke(TimeZone.getDefault().getID());
    }

    @ReactMethod
    public final void setLanguage(String language) {
        Intrinsics.checkNotNullParameter(language, "language");
        this.sharedPreferences.edit().putString(Device.JsonKeys.LANGUAGE, language).apply();
    }

    @ReactMethod
    public final void saveJson(String json, String fileName, Promise promise) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(promise, "promise");
        FilesKt.writeText$default(new File(this.reactContext.getFilesDir(), fileName), json, null, 2, null);
        promise.resolve(null);
    }

    @ReactMethod
    public final void readJson(String fileName, Promise promise) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(new File(this.reactContext.getFilesDir(), fileName)), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                promise.resolve(text);
            } finally {
            }
        } catch (Exception e) {
            Exception exc = e;
            Sentry.captureException(exc);
            promise.reject(e.getLocalizedMessage(), exc);
        }
    }

    @ReactMethod
    public final void clearJson(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        File[] fileArrListFiles = this.reactContext.getFilesDir().listFiles();
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        for (File file : fileArrListFiles) {
            file.delete();
        }
        promise.resolve(null);
    }
}
