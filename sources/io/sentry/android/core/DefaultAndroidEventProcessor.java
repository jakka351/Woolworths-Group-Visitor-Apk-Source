package io.sentry.android.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import io.sentry.DateUtils;
import io.sentry.EventProcessor;
import io.sentry.Hint;
import io.sentry.IpAddressUtils;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.SentryReplayEvent;
import io.sentry.android.core.ContextUtils;
import io.sentry.android.core.internal.util.AndroidMainThreadChecker;
import io.sentry.android.core.performance.AppStartMetrics;
import io.sentry.android.core.performance.TimeSpan;
import io.sentry.protocol.App;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import io.sentry.util.HintUtils;
import io.sentry.util.Objects;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes3.dex */
final class DefaultAndroidEventProcessor implements EventProcessor {
    private final BuildInfoProvider buildInfoProvider;
    final Context context;
    private final Future<DeviceInfoUtil> deviceInfoUtil;
    private final SentryAndroidOptions options;

    public DefaultAndroidEventProcessor(final Context context, BuildInfoProvider buildInfoProvider, final SentryAndroidOptions sentryAndroidOptions) {
        this.context = (Context) Objects.requireNonNull(context, "The application context is required.");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "The BuildInfoProvider is required.");
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryAndroidOptions, "The options object is required.");
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.deviceInfoUtil = executorServiceNewSingleThreadExecutor.submit(new Callable() { // from class: io.sentry.android.core.DefaultAndroidEventProcessor$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeviceInfoUtil.getInstance(context, sentryAndroidOptions);
            }
        });
        executorServiceNewSingleThreadExecutor.shutdown();
    }

    @Override // io.sentry.EventProcessor
    public SentryEvent process(SentryEvent sentryEvent, Hint hint) {
        boolean zShouldApplyScopeData = shouldApplyScopeData(sentryEvent, hint);
        if (zShouldApplyScopeData) {
            processNonCachedEvent(sentryEvent, hint);
            setThreads(sentryEvent, hint);
        }
        setCommons(sentryEvent, true, zShouldApplyScopeData);
        fixExceptionOrder(sentryEvent);
        return sentryEvent;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void fixExceptionOrder(io.sentry.SentryEvent r4) {
        /*
            java.util.List r4 = r4.getExceptions()
            if (r4 == 0) goto L4d
            int r0 = r4.size()
            r1 = 1
            if (r0 <= r1) goto L4d
            int r0 = r4.size()
            int r0 = r0 - r1
            java.lang.Object r0 = r4.get(r0)
            io.sentry.protocol.SentryException r0 = (io.sentry.protocol.SentryException) r0
            java.lang.String r2 = "java.lang"
            java.lang.String r3 = r0.getModule()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L4d
            io.sentry.protocol.SentryStackTrace r0 = r0.getStacktrace()
            if (r0 == 0) goto L4d
            java.util.List r0 = r0.getFrames()
            if (r0 == 0) goto L4d
            java.util.Iterator r0 = r0.iterator()
        L34:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L4d
            java.lang.Object r2 = r0.next()
            io.sentry.protocol.SentryStackFrame r2 = (io.sentry.protocol.SentryStackFrame) r2
            java.lang.String r3 = "com.android.internal.os.RuntimeInit$MethodAndArgsCaller"
            java.lang.String r2 = r2.getModule()
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L34
            goto L4e
        L4d:
            r1 = 0
        L4e:
            if (r1 == 0) goto L53
            java.util.Collections.reverse(r4)
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.DefaultAndroidEventProcessor.fixExceptionOrder(io.sentry.SentryEvent):void");
    }

    private void setCommons(SentryBaseEvent sentryBaseEvent, boolean z, boolean z2) {
        mergeUser(sentryBaseEvent);
        setDevice(sentryBaseEvent, z, z2);
        setSideLoadedInfo(sentryBaseEvent);
    }

    private boolean shouldApplyScopeData(SentryBaseEvent sentryBaseEvent, Hint hint) {
        if (HintUtils.shouldApplyScopeData(hint)) {
            return true;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Event was cached so not applying data relevant to the current app execution/version: %s", sentryBaseEvent.getEventId());
        return false;
    }

    private void mergeUser(SentryBaseEvent sentryBaseEvent) {
        User user = sentryBaseEvent.getUser();
        if (user == null) {
            user = new User();
            sentryBaseEvent.setUser(user);
        }
        if (user.getId() == null) {
            user.setId(Installation.id(this.context));
        }
        if (user.getIpAddress() == null) {
            user.setIpAddress(IpAddressUtils.DEFAULT_IP_ADDRESS);
        }
    }

    private void setDevice(SentryBaseEvent sentryBaseEvent, boolean z, boolean z2) {
        if (sentryBaseEvent.getContexts().getDevice() == null) {
            try {
                sentryBaseEvent.getContexts().setDevice(this.deviceInfoUtil.get().collectDeviceInformation(z, z2));
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, "Failed to retrieve device info", th);
            }
            mergeOS(sentryBaseEvent);
        }
    }

    private void mergeOS(SentryBaseEvent sentryBaseEvent) {
        OperatingSystem operatingSystem = sentryBaseEvent.getContexts().getOperatingSystem();
        try {
            sentryBaseEvent.getContexts().setOperatingSystem(this.deviceInfoUtil.get().getOperatingSystem());
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to retrieve os system", th);
        }
        if (operatingSystem != null) {
            String name = operatingSystem.getName();
            sentryBaseEvent.getContexts().put((name == null || name.isEmpty()) ? "os_1" : "os_" + name.trim().toLowerCase(Locale.ROOT), operatingSystem);
        }
    }

    private void processNonCachedEvent(SentryBaseEvent sentryBaseEvent, Hint hint) {
        App app2 = sentryBaseEvent.getContexts().getApp();
        if (app2 == null) {
            app2 = new App();
        }
        setAppExtras(app2, hint);
        setPackageInfo(sentryBaseEvent, app2);
        sentryBaseEvent.getContexts().setApp(app2);
    }

    private void setThreads(SentryEvent sentryEvent, Hint hint) {
        if (sentryEvent.getThreads() != null) {
            boolean zIsFromHybridSdk = HintUtils.isFromHybridSdk(hint);
            for (SentryThread sentryThread : sentryEvent.getThreads()) {
                boolean zIsMainThread = AndroidMainThreadChecker.getInstance().isMainThread(sentryThread);
                if (sentryThread.isCurrent() == null) {
                    sentryThread.setCurrent(Boolean.valueOf(zIsMainThread));
                }
                if (!zIsFromHybridSdk && sentryThread.isMain() == null) {
                    sentryThread.setMain(Boolean.valueOf(zIsMainThread));
                }
            }
        }
    }

    private void setPackageInfo(SentryBaseEvent sentryBaseEvent, App app2) {
        PackageInfo packageInfo = ContextUtils.getPackageInfo(this.context, 4096, this.options.getLogger(), this.buildInfoProvider);
        if (packageInfo != null) {
            setDist(sentryBaseEvent, ContextUtils.getVersionCode(packageInfo, this.buildInfoProvider));
            ContextUtils.setAppPackageInfo(packageInfo, this.buildInfoProvider, app2);
        }
    }

    private void setDist(SentryBaseEvent sentryBaseEvent, String str) {
        if (sentryBaseEvent.getDist() == null) {
            sentryBaseEvent.setDist(str);
        }
    }

    private void setAppExtras(App app2, Hint hint) {
        Boolean boolIsInBackground;
        app2.setAppName(ContextUtils.getApplicationName(this.context, this.options.getLogger()));
        TimeSpan appStartTimeSpanWithFallback = AppStartMetrics.getInstance().getAppStartTimeSpanWithFallback(this.options);
        if (appStartTimeSpanWithFallback.hasStarted()) {
            app2.setAppStartTime(DateUtils.toUtilDate(appStartTimeSpanWithFallback.getStartTimestamp()));
        }
        if (HintUtils.isFromHybridSdk(hint) || app2.getInForeground() != null || (boolIsInBackground = AppState.getInstance().isInBackground()) == null) {
            return;
        }
        app2.setInForeground(Boolean.valueOf(!boolIsInBackground.booleanValue()));
    }

    public User getDefaultUser(Context context) {
        User user = new User();
        user.setId(Installation.id(context));
        return user;
    }

    private void setSideLoadedInfo(SentryBaseEvent sentryBaseEvent) {
        try {
            ContextUtils.SideLoadedInfo sideLoadedInfo = this.deviceInfoUtil.get().getSideLoadedInfo();
            if (sideLoadedInfo != null) {
                for (Map.Entry<String, String> entry : sideLoadedInfo.asTags().entrySet()) {
                    sentryBaseEvent.setTag(entry.getKey(), entry.getValue());
                }
            }
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error getting side loaded info.", th);
        }
    }

    @Override // io.sentry.EventProcessor
    public SentryTransaction process(SentryTransaction sentryTransaction, Hint hint) {
        boolean zShouldApplyScopeData = shouldApplyScopeData(sentryTransaction, hint);
        if (zShouldApplyScopeData) {
            processNonCachedEvent(sentryTransaction, hint);
        }
        setCommons(sentryTransaction, false, zShouldApplyScopeData);
        return sentryTransaction;
    }

    @Override // io.sentry.EventProcessor
    public SentryReplayEvent process(SentryReplayEvent sentryReplayEvent, Hint hint) {
        boolean zShouldApplyScopeData = shouldApplyScopeData(sentryReplayEvent, hint);
        if (zShouldApplyScopeData) {
            processNonCachedEvent(sentryReplayEvent, hint);
        }
        setCommons(sentryReplayEvent, false, zShouldApplyScopeData);
        return sentryReplayEvent;
    }
}
