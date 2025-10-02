package com.site360new;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.SentryEvent;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNLogger.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J,\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/site360new/RNLogger;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "logDateFormatter", "Ljava/text/SimpleDateFormat;", SentryEvent.JsonKeys.LOGGER, "Lcom/site360new/Logger;", "clearLogs", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getLogFilePath", "getLogs", "getName", "", "log", "message", "level", "category", "setLoggingEnabled", ViewProps.ENABLED, "", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNLogger extends ReactContextBaseJavaModule {
    private static final String TAG = "RNLogger";
    private final SimpleDateFormat logDateFormatter;
    private final Logger logger;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RNLogger(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.logger = Logger.INSTANCE.getInstance(reactContext);
        this.logDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    }

    public static /* synthetic */ void log$default(RNLogger rNLogger, String str, String str2, String str3, Promise promise, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "INFO";
        }
        if ((i & 4) != 0) {
            str3 = "General";
        }
        rNLogger.log(str, str2, str3, promise);
    }

    @ReactMethod
    public final void log(String message, String level, String category, Promise promise) {
        LogLevel logLevel;
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            String upperCase = level.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            switch (upperCase.hashCode()) {
                case 2251950:
                    if (!upperCase.equals("INFO")) {
                        logLevel = LogLevel.INFO;
                        break;
                    } else {
                        logLevel = LogLevel.INFO;
                        break;
                    }
                case 64921139:
                    if (!upperCase.equals("DEBUG")) {
                        logLevel = LogLevel.INFO;
                        break;
                    } else {
                        logLevel = LogLevel.DEBUG;
                        break;
                    }
                case 66247144:
                    if (!upperCase.equals("ERROR")) {
                        logLevel = LogLevel.INFO;
                        break;
                    } else {
                        logLevel = LogLevel.ERROR;
                        break;
                    }
                case 1842428796:
                    if (!upperCase.equals("WARNING")) {
                        logLevel = LogLevel.INFO;
                        break;
                    } else {
                        logLevel = LogLevel.WARNING;
                        break;
                    }
                default:
                    logLevel = LogLevel.INFO;
                    break;
            }
            Logger.log$default(this.logger, message, logLevel, category, null, null, 0, 56, null);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject("LOG_ERROR", e.getMessage(), e);
        }
    }

    @ReactMethod
    public final void getLogs(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            promise.resolve(this.logger.getLogs());
        } catch (Exception e) {
            promise.reject("GET_LOGS_ERROR", e.getMessage(), e);
        }
    }

    @ReactMethod
    public final void getLogFilePath(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            promise.resolve(this.logger.getLogFilePath());
        } catch (Exception e) {
            promise.reject("GET_LOG_FILE_PATH_ERROR", e.getMessage(), e);
        }
    }

    @ReactMethod
    public final void clearLogs(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            this.logger.resetLogs();
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject("CLEAR_LOGS_ERROR", e.getMessage(), e);
        }
    }

    @ReactMethod
    public final void setLoggingEnabled(boolean enabled, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            getReactApplicationContext().getSharedPreferences("sharedPreferences", 0).edit().putBoolean("logsEnabled", enabled).apply();
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject("SET_LOGGING_ERROR", e.getMessage(), e);
        }
    }
}
