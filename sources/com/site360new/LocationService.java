package com.site360new;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import androidx.core.content.ContextCompat;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.ReactApplication;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.oblador.keychain.KeychainModule;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.net.ssl.SSLHandshakeException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: LocationService.kt */
@Metadata(d1 = {"\u0000Á\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t*\u0001\u0007\u0018\u0000 e2\u00020\u00012\u00020\u0002:\u0001eB\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\n\u0010/\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u00100\u001a\u00020\u000eH\u0002J\u0010\u00101\u001a\u00020,2\u0006\u00102\u001a\u00020\u0014H\u0002J\b\u00103\u001a\u00020\u000eH\u0002J\b\u00104\u001a\u00020\u000eH\u0002J\b\u00105\u001a\u00020\u000eH\u0002J\b\u00106\u001a\u000207H\u0002J \u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020>H\u0002J\b\u0010?\u001a\u00020,H\u0002J\b\u0010@\u001a\u00020>H\u0002J\u0010\u0010A\u001a\u00020,2\u0006\u0010B\u001a\u00020;H\u0002J&\u0010C\u001a\u00020,2\u0006\u0010B\u001a\u00020;2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010D\u001a\u00020\u000eH\u0002J(\u0010E\u001a\u00020,2\u0006\u0010F\u001a\u00020;2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010#H\u0002J\b\u0010H\u001a\u00020,H\u0002J\u0010\u0010I\u001a\u00020,2\u0006\u0010J\u001a\u00020KH\u0002J\u0014\u0010L\u001a\u0004\u0018\u00010M2\b\u0010N\u001a\u0004\u0018\u00010OH\u0016J\b\u0010P\u001a\u00020,H\u0016J\b\u0010Q\u001a\u00020,H\u0016J\u0010\u0010R\u001a\u00020,2\u0006\u00102\u001a\u00020\u0014H\u0016J\u0010\u0010S\u001a\u00020,2\u0006\u0010T\u001a\u00020;H\u0016J\u0010\u0010U\u001a\u00020,2\u0006\u0010T\u001a\u00020;H\u0016J\"\u0010V\u001a\u00020W2\b\u0010N\u001a\u0004\u0018\u00010O2\u0006\u0010X\u001a\u00020W2\u0006\u0010Y\u001a\u00020WH\u0016J$\u0010Z\u001a\u00020,2\b\u0010T\u001a\u0004\u0018\u00010;2\u0006\u0010[\u001a\u00020W2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0017J\u0012\u0010^\u001a\u00020,2\b\u0010_\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010`\u001a\u00020,H\u0002J\u0012\u0010a\u001a\u00020,2\b\u00102\u001a\u0004\u0018\u00010\u0014H\u0002J\u0018\u0010b\u001a\u00020,2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0002J\b\u0010c\u001a\u00020,H\u0003J\b\u0010d\u001a\u00020,H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006f"}, d2 = {"Lcom/site360new/LocationService;", "Landroid/app/Service;", "Landroid/location/LocationListener;", "()V", "apiClient", "Lcom/site360new/ApiClient;", "handleUpdate", "com/site360new/LocationService$handleUpdate$1", "Lcom/site360new/LocationService$handleUpdate$1;", "handler", "Landroid/os/Handler;", "handlerThread", "Landroid/os/HandlerThread;", "hasLocation", "", "hasLocationPermission", "hasNewLocationDataSinceOutOfBounds", "keychainManager", "Lcom/site360new/KeychainManager;", "lastLocation", "Landroid/location/Location;", "lastUpdateTime", "", "Ljava/lang/Long;", "locationBuffer", "Ljava/util/concurrent/CopyOnWriteArrayList;", "locationManager", "Landroid/location/LocationManager;", SentryEvent.JsonKeys.LOGGER, "Lcom/site360new/Logger;", "notificationManager", "Landroid/app/NotificationManager;", "outOfBounds", "outOfBoundsStartTime", "pendingCheckoutData", "Lcom/site360new/CheckoutData;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "sharedPreferences", "Landroid/content/SharedPreferences;", "updateInterval", "getUpdateInterval", "()J", "attachBaseContext", "", Constants.SENSITIVITY_BASE, "Landroid/content/Context;", "calculateWeightedAverageLocation", "checkAutoCheckoutTime", "checkLocationBounds", "location", "checkLocationEnabled", "checkLocationPermission", "checkMockLocation", "createNotification", "Landroid/app/Notification;", "createNotificationBuilder", "Landroid/app/Notification$Builder;", KeychainModule.AuthPromptOptions.TITLE, "", "message", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "createNotificationChannel", "createPendingIntent", "emitCheckoutEvent", "time", "finishCheckout", "apiFailed", "handleCheckout", "checkOutMethod", "existingData", "initializeServices", "logApiError", "error", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onLocationChanged", "onProviderDisabled", "provider", "onProviderEnabled", "onStartCommand", "", "flags", "startId", "onStatusChanged", "status", "extras", "Landroid/os/Bundle;", "performUpdateChecks", "locationToUse", "resetState", "sendUpdate", "showNotification", "startLocationService", "stopService", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LocationService extends Service implements LocationListener {
    public static final String CHECKED_IN_CHANNEL = "checkedIn";
    private static final float DEFAULT_ACCURACY_THRESHOLD = 30.0f;
    private static final long DEFAULT_INTERVAL = 120000;
    private static final long EVACUATION_INTERVAL = 30000;
    private static final float LOCATION_UPDATE_DISTANCE = 1.0f;
    private static final long LOCATION_UPDATE_INTERVAL = 1000;
    private static final int MAX_LOCATION_BUFFER_SIZE = 10;
    public static final String NOTIFICATION_CHANNEL = "notification";
    public static final String START_ACTION = "start";
    private static boolean evacuate;
    private static volatile Function0<? extends SharedPreferences> getSharedPreferencesStatic;
    private static boolean isRunning;
    private static Visit visit;
    private ApiClient apiClient;
    private final LocationService$handleUpdate$1 handleUpdate;
    private final Handler handler;
    private final HandlerThread handlerThread;
    private boolean hasLocation;
    private boolean hasLocationPermission;
    private boolean hasNewLocationDataSinceOutOfBounds;
    private KeychainManager keychainManager;
    private Location lastLocation;
    private Long lastUpdateTime;
    private final CopyOnWriteArrayList<Location> locationBuffer;
    private LocationManager locationManager;
    private Logger logger;
    private NotificationManager notificationManager;
    private boolean outOfBounds;
    private Long outOfBoundsStartTime;
    private CheckoutData pendingCheckoutData;
    private final CoroutineScope serviceScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
    private SharedPreferences sharedPreferences;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static float accuracyThreshold = 30.0f;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
    }

    @Override // android.location.LocationListener
    @Deprecated(message = "Deprecated in Java")
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.site360new.LocationService$handleUpdate$1] */
    public LocationService() {
        HandlerThread handlerThread = new HandlerThread("LocationHandlerThread");
        handlerThread.start();
        this.handlerThread = handlerThread;
        this.handler = new Handler(handlerThread.getLooper());
        this.hasLocationPermission = true;
        this.hasLocation = true;
        this.locationBuffer = new CopyOnWriteArrayList<>();
        this.handleUpdate = new Runnable() { // from class: com.site360new.LocationService$handleUpdate$1
            @Override // java.lang.Runnable
            public void run() {
                Logger logger;
                Logger logger2;
                Logger logger3;
                Logger logger4;
                Logger logger5 = this.this$0.logger;
                if (logger5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                    logger = null;
                } else {
                    logger = logger5;
                }
                Logger.log$default(logger, "Handling location update cycle", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
                CheckoutData checkoutData = this.this$0.pendingCheckoutData;
                if (checkoutData != null) {
                    LocationService locationService = this.this$0;
                    Logger logger6 = locationService.logger;
                    if (logger6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger4 = null;
                    } else {
                        logger4 = logger6;
                    }
                    Logger.log$default(logger4, "Attempting to retry pending checkout", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
                    locationService.handleCheckout("Retry", null, checkoutData);
                }
                Location locationCalculateWeightedAverageLocation = this.this$0.calculateWeightedAverageLocation();
                Location location = locationCalculateWeightedAverageLocation == null ? this.this$0.lastLocation : locationCalculateWeightedAverageLocation;
                if (locationCalculateWeightedAverageLocation != null) {
                    Logger logger7 = this.this$0.logger;
                    if (logger7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger3 = null;
                    } else {
                        logger3 = logger7;
                    }
                    Logger.log$default(logger3, "Using averaged location: " + locationCalculateWeightedAverageLocation.getLatitude() + ", " + locationCalculateWeightedAverageLocation.getLongitude() + ", accuracy: " + locationCalculateWeightedAverageLocation.getAccuracy() + "m", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
                } else {
                    Logger logger8 = this.this$0.logger;
                    if (logger8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger2 = null;
                    } else {
                        logger2 = logger8;
                    }
                    Logger.log$default(logger2, "No valid averaged location available", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
                }
                this.this$0.performUpdateChecks(location);
                this.this$0.sendUpdate(location);
                this.this$0.lastUpdateTime = Long.valueOf(System.currentTimeMillis());
                this.this$0.locationBuffer.clear();
                this.this$0.handler.postDelayed(this, this.this$0.getUpdateInterval());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getUpdateInterval() {
        if (evacuate) {
            return 30000L;
        }
        return DEFAULT_INTERVAL;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Logger companion = Logger.INSTANCE.getInstance(this);
        this.logger = companion;
        if (companion == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            companion = null;
        }
        Logger.log$default(companion, "LocationService initialized", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
        initializeServices();
        createNotificationChannel();
    }

    private final void initializeServices() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(\"sh…eferences\", MODE_PRIVATE)");
        this.sharedPreferences = sharedPreferences;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        this.keychainManager = new KeychainManager(applicationContext);
        getSharedPreferencesStatic = new Function0<SharedPreferences>() { // from class: com.site360new.LocationService.initializeServices.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedPreferences invoke() {
                SharedPreferences sharedPreferences2 = LocationService.this.getSharedPreferences("sharedPreferences", 0);
                Intrinsics.checkNotNullExpressionValue(sharedPreferences2, "getSharedPreferences(\"sh…eferences\", MODE_PRIVATE)");
                return sharedPreferences2;
            }
        };
        Object systemService = getSystemService("location");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.locationManager = (LocationManager) systemService;
        Object systemService2 = getSystemService(NOTIFICATION_CHANNEL);
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.app.NotificationManager");
        this.notificationManager = (NotificationManager) systemService2;
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        this.apiClient = new ApiClient(applicationContext2);
    }

    private final void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL, "Site360", 4);
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
            notificationManager = null;
        }
        notificationManager.createNotificationChannel(notificationChannel);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleService.INSTANCE.updateBaseContextLocale(base));
    }

    @Override // android.app.Service
    public void onDestroy() {
        CoroutineScopeKt.cancel$default(this.serviceScope, null, 1, null);
        this.handlerThread.quit();
        stopService();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, "start")) {
            return 1;
        }
        try {
            startLocationService();
            return 1;
        } catch (SecurityException e) {
            Sentry.captureException(e);
            return 2;
        }
    }

    private final void startLocationService() {
        Logger logger;
        Logger logger2;
        Logger logger3;
        LocationManager locationManager;
        Logger logger4 = this.logger;
        if (logger4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger4 = null;
        }
        logger4.resetLogs();
        resetState();
        Logger logger5 = this.logger;
        if (logger5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        } else {
            logger = logger5;
        }
        Logger.log$default(logger, "Starting location service with update interval: " + getUpdateInterval() + "ms", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(1, createNotification(), 8);
        } else {
            startForeground(1, createNotification());
        }
        LocationManager locationManager2 = this.locationManager;
        if (locationManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationManager");
            locationManager2 = null;
        }
        if (locationManager2.isProviderEnabled("gps")) {
            Logger logger6 = this.logger;
            if (logger6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger3 = null;
            } else {
                logger3 = logger6;
            }
            Logger.log$default(logger3, "GPS Provider Enabled: Requesting location updates from GPS", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
            LocationManager locationManager3 = this.locationManager;
            if (locationManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                locationManager = null;
            } else {
                locationManager = locationManager3;
            }
            locationManager.requestLocationUpdates("gps", 1000L, 1.0f, this);
        } else {
            Logger logger7 = this.logger;
            if (logger7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger2 = null;
            } else {
                logger2 = logger7;
            }
            Logger.log$default(logger2, "GPS Provider Disabled: GPS provider is not available", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
        }
        this.lastUpdateTime = Long.valueOf(System.currentTimeMillis());
        isRunning = true;
        this.handler.postDelayed(this.handleUpdate, getUpdateInterval());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void performUpdateChecks(Location locationToUse) {
        Logger logger;
        Logger logger2;
        Logger logger3;
        if (visit == null) {
            Logger logger4 = this.logger;
            if (logger4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger3 = null;
            } else {
                logger3 = logger4;
            }
            Logger.log$default(logger3, "No active visit, skipping update checks", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
            return;
        }
        if (checkMockLocation() && checkLocationPermission() && checkLocationEnabled()) {
            if (evacuate) {
                Logger logger5 = this.logger;
                if (logger5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                    logger2 = null;
                } else {
                    logger2 = logger5;
                }
                Logger.log$default(logger2, "In evacuation mode, skipping bounds checks", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
                return;
            }
            if (checkAutoCheckoutTime()) {
                if (locationToUse != null) {
                    checkLocationBounds(locationToUse);
                    return;
                }
                Logger logger6 = this.logger;
                if (logger6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                    logger = null;
                } else {
                    logger = logger6;
                }
                Logger.log$default(logger, "No location available for bounds checking", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
            }
        }
    }

    private final boolean checkMockLocation() {
        Location location;
        Location location2;
        if (!(Build.VERSION.SDK_INT < 31 ? !((location = this.lastLocation) == null || !location.isFromMockProvider()) : !((location2 = this.lastLocation) == null || !location2.isMock()))) {
            return true;
        }
        handleCheckout$default(this, "Mock Location", getString(R.string.error_mock_location_checked_in), null, 4, null);
        return false;
    }

    private final boolean checkLocationPermission() {
        Logger logger;
        Logger logger2;
        Logger logger3;
        Logger logger4;
        boolean z = ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0;
        if (!z) {
            Logger logger5 = this.logger;
            if (logger5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger2 = null;
            } else {
                logger2 = logger5;
            }
            Logger.log$default(logger2, "Location permission check failed", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
            if (this.hasLocationPermission) {
                this.hasLocationPermission = false;
                Logger logger6 = this.logger;
                if (logger6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                    logger4 = null;
                } else {
                    logger4 = logger6;
                }
                Logger.log$default(logger4, "Location permission lost, showing warning", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
                String string = getString(R.string.warning);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.warning)");
                String string2 = getString(R.string.error_location_permission_checked_in);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.error…on_permission_checked_in)");
                showNotification(string, string2);
            } else if (!evacuate) {
                Logger logger7 = this.logger;
                if (logger7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                    logger3 = null;
                } else {
                    logger3 = logger7;
                }
                Logger.log$default(logger3, "Location permission still denied, initiating checkout", LogLevel.ERROR, "LocationService", null, null, 0, 56, null);
                handleCheckout$default(this, "Location Permission", null, null, 6, null);
            }
        } else {
            if (!this.hasLocationPermission) {
                Logger logger8 = this.logger;
                if (logger8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                    logger = null;
                } else {
                    logger = logger8;
                }
                Logger.log$default(logger, "Location permission restored", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
            }
            this.hasLocationPermission = true;
        }
        return z;
    }

    private final boolean checkLocationEnabled() {
        LocationManager locationManager = this.locationManager;
        if (locationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationManager");
            locationManager = null;
        }
        boolean zIsProviderEnabled = locationManager.isProviderEnabled("gps");
        if (!zIsProviderEnabled) {
            if (this.hasLocation) {
                this.hasLocation = false;
                String string = getString(R.string.warning);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.warning)");
                String string2 = getString(R.string.error_location_checked_in);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.error_location_checked_in)");
                showNotification(string, string2);
            } else if (!evacuate) {
                handleCheckout$default(this, "GPS Provider", null, null, 6, null);
            }
        } else if (!this.hasLocation) {
            this.hasLocation = true;
        }
        return zIsProviderEnabled;
    }

    private final boolean checkAutoCheckoutTime() {
        Logger logger;
        Logger logger2;
        Integer autoCheckoutTime;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        Visit visit2 = visit;
        LocalDateTime localDateTime = LocalDateTime.parse(visit2 != null ? visit2.getCheckinTime() : null, dateTimeFormatter);
        LocalDateTime localDateTime2 = LocalDateTime.parse(Instant.now().toString(), dateTimeFormatter);
        if (localDateTime == null) {
            localDateTime = localDateTime2;
        }
        Duration durationBetween = Duration.between(localDateTime, localDateTime2);
        Visit visit3 = visit;
        int iIntValue = (visit3 == null || (autoCheckoutTime = visit3.getAutoCheckoutTime()) == null) ? 0 : autoCheckoutTime.intValue();
        Logger logger3 = this.logger;
        if (logger3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        } else {
            logger = logger3;
        }
        long seconds = durationBetween.getSeconds();
        Visit visit4 = visit;
        Logger.log$default(logger, "Visit duration: " + seconds + "s, auto checkout time: " + iIntValue + "s, system checkout disabled: " + (visit4 != null ? visit4.getDoNotSystemCheckout() : null), LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
        if (durationBetween.getSeconds() <= iIntValue) {
            return true;
        }
        Visit visit5 = visit;
        if (!(visit5 != null ? Intrinsics.areEqual((Object) visit5.getDoNotSystemCheckout(), (Object) false) : false)) {
            return true;
        }
        Logger logger4 = this.logger;
        if (logger4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger2 = null;
        } else {
            logger2 = logger4;
        }
        Logger.log$default(logger2, "Auto checkout time exceeded, initiating checkout", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
        handleCheckout$default(this, "Auto Checkout Time", null, null, 6, null);
        return false;
    }

    private final void checkLocationBounds(Location location) {
        Unit unit;
        Logger logger;
        List<List<Double>> offsetGeometry;
        Logger logger2;
        Logger logger3;
        Logger logger4;
        Logger logger5;
        Logger logger6;
        Logger logger7;
        Visit visit2 = visit;
        if (visit2 == null || (offsetGeometry = visit2.getOffsetGeometry()) == null) {
            unit = null;
        } else {
            boolean zIsLocationInPolygon = LocationHelperKt.isLocationInPolygon(offsetGeometry, new Pair(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())), location.getAccuracy());
            Logger logger8 = this.logger;
            if (logger8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger2 = null;
            } else {
                logger2 = logger8;
            }
            Logger.log$default(logger2, "Location bounds check: " + (zIsLocationInPolygon ? "IN BOUNDS" : "OUT OF BOUNDS") + " at " + location.getLatitude() + ", " + location.getLongitude(), LogLevel.INFO, "LocationService", null, null, 0, 56, null);
            boolean z = this.outOfBounds;
            boolean z2 = !z;
            if (zIsLocationInPolygon) {
                if (z) {
                    Logger logger9 = this.logger;
                    if (logger9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger7 = null;
                    } else {
                        logger7 = logger9;
                    }
                    Logger.log$default(logger7, "User returned to bounds, resetting out of bounds state", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
                    this.outOfBoundsStartTime = null;
                    this.hasNewLocationDataSinceOutOfBounds = false;
                }
                this.outOfBounds = false;
            } else {
                this.hasNewLocationDataSinceOutOfBounds = true;
                if (!z) {
                    Logger logger10 = this.logger;
                    if (logger10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger6 = null;
                    } else {
                        logger6 = logger10;
                    }
                    Logger.log$default(logger6, "User went out of bounds, starting 120s grace period", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
                    this.outOfBounds = true;
                    this.outOfBoundsStartTime = Long.valueOf(System.currentTimeMillis());
                    String string = getString(R.string.warning);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.warning)");
                    String string2 = getString(R.string.error_out_of_bounds);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.error_out_of_bounds)");
                    showNotification(string, string2);
                } else if (this.outOfBoundsStartTime != null) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    Long l = this.outOfBoundsStartTime;
                    Intrinsics.checkNotNull(l);
                    long jLongValue = jCurrentTimeMillis - l.longValue();
                    Logger logger11 = this.logger;
                    if (logger11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger3 = null;
                    } else {
                        logger3 = logger11;
                    }
                    Logger.log$default(logger3, "Still out of bounds for " + jLongValue + "ms with new location confirmation", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
                    if (jLongValue >= DEFAULT_INTERVAL) {
                        Logger logger12 = this.logger;
                        if (logger12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                            logger5 = null;
                        } else {
                            logger5 = logger12;
                        }
                        Logger.log$default(logger5, "Grace period expired (" + jLongValue + "ms) and confirmed still out of bounds with new location data - initiating checkout", LogLevel.ERROR, "LocationService", null, null, 0, 56, null);
                        handleCheckout$default(this, "Out Of Bounds", null, null, 6, null);
                        return;
                    }
                    Logger logger13 = this.logger;
                    if (logger13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger4 = null;
                    } else {
                        logger4 = logger13;
                    }
                    Logger.log$default(logger4, "Still within grace period (" + jLongValue + "ms/120000ms), continuing to wait", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
                }
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Logger logger14 = this.logger;
            if (logger14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger = null;
            } else {
                logger = logger14;
            }
            Logger.log$default(logger, "No offset geometry available for bounds checking", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
        }
    }

    /* compiled from: LocationService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "com.site360new.LocationService$sendUpdate$1", f = "LocationService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.site360new.LocationService$sendUpdate$1, reason: invalid class name and case insensitive filesystem */
    static final class C00511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Location $location;
        int label;
        final /* synthetic */ LocationService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00511(Location location, LocationService locationService, Continuation<? super C00511> continuation) {
            super(2, continuation);
            this.$location = location;
            this.this$0 = locationService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00511(this.$location, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Logger logger;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Location location = this.$location;
            ApiClient apiClient = null;
            Double dBoxDouble = location != null ? Boxing.boxDouble(location.getLatitude()) : null;
            Location location2 = this.$location;
            Double dBoxDouble2 = location2 != null ? Boxing.boxDouble(location2.getLongitude()) : null;
            Location location3 = this.$location;
            Float fBoxFloat = location3 != null ? Boxing.boxFloat(location3.getAccuracy()) : null;
            Boolean boolBoxBoolean = Boxing.boxBoolean(true);
            SharedPreferences sharedPreferences = this.this$0.sharedPreferences;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                sharedPreferences = null;
            }
            String string = sharedPreferences.getString(ResultKey.DEVICE_ID.getValue(), "");
            SharedPreferences sharedPreferences2 = this.this$0.sharedPreferences;
            if (sharedPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                sharedPreferences2 = null;
            }
            String string2 = sharedPreferences2.getString(ResultKey.DEVICE_ID.getValue(), "");
            Visit visit = LocationService.INSTANCE.getVisit();
            UpdateData updateData = new UpdateData(dBoxDouble, dBoxDouble2, fBoxFloat, boolBoxBoolean, string, string2, BuildConfig.APP_THEME_ID, visit != null ? visit.getUserId() : null);
            Logger logger2 = this.this$0.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger = null;
            } else {
                logger = logger2;
            }
            Object latitude = updateData.getLatitude();
            if (latitude == null) {
                latitude = Boxing.boxInt(0);
            }
            Object longitude = updateData.getLongitude();
            if (longitude == null) {
                longitude = Boxing.boxInt(0);
            }
            Object accuracy = updateData.getAccuracy();
            if (accuracy == null) {
                accuracy = Boxing.boxInt(0);
            }
            Logger.log$default(logger, "Sending location update: lat=" + latitude + ", lng=" + longitude + ", accuracy=" + accuracy, LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
            ApiClient apiClient2 = this.this$0.apiClient;
            if (apiClient2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("apiClient");
            } else {
                apiClient = apiClient2;
            }
            Call<UpdateResponse> callUpdate = apiClient.getApiService().update(updateData);
            final LocationService locationService = this.this$0;
            callUpdate.enqueue(new Callback<UpdateResponse>() { // from class: com.site360new.LocationService.sendUpdate.1.1
                @Override // retrofit2.Callback
                public void onFailure(Call<UpdateResponse> call, Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    Logger logger3 = locationService.logger;
                    if (logger3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger3 = null;
                    }
                    Logger.log$default(logger3, "Failed to send location update: " + t.getMessage(), LogLevel.ERROR, "LocationService", null, null, 0, 56, null);
                    locationService.logApiError(t);
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                    Logger logger3;
                    Logger logger4;
                    Logger logger5;
                    Boolean boolIsCheckedIn;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (!response.isSuccessful()) {
                        Logger logger6 = locationService.logger;
                        if (logger6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                            logger3 = null;
                        } else {
                            logger3 = logger6;
                        }
                        Logger.log$default(logger3, "Empty response from update endpoint", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
                        locationService.logApiError(new Exception("API Error: " + response.code()));
                        return;
                    }
                    UpdateResponse updateResponseBody = response.body();
                    boolean zBooleanValue = (updateResponseBody == null || (boolIsCheckedIn = updateResponseBody.isCheckedIn()) == null) ? true : boolIsCheckedIn.booleanValue();
                    Logger logger7 = locationService.logger;
                    if (logger7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger4 = null;
                    } else {
                        logger4 = logger7;
                    }
                    Logger.log$default(logger4, "Update response: isCheckedIn=" + zBooleanValue, LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
                    if (zBooleanValue) {
                        return;
                    }
                    Logger logger8 = locationService.logger;
                    if (logger8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger5 = null;
                    } else {
                        logger5 = logger8;
                    }
                    Logger.log$default(logger5, "Server indicates user is no longer checked in. Response: " + response.body(), LogLevel.INFO, "LocationService", null, null, 0, 56, null);
                    LocationService locationService2 = locationService;
                    String string3 = Instant.now().toString();
                    Intrinsics.checkNotNullExpressionValue(string3, "now().toString()");
                    LocationService.finishCheckout$default(locationService2, string3, locationService.getString(R.string.dashboard_auto_checked_out_message), false, 4, null);
                }
            });
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendUpdate(Location location) {
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new C00511(location, this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Location calculateWeightedAverageLocation() {
        Logger logger;
        Logger logger2;
        Logger logger3;
        Logger logger4;
        boolean zIsEmpty = this.locationBuffer.isEmpty();
        String str = SentryEvent.JsonKeys.LOGGER;
        if (zIsEmpty) {
            Logger logger5 = this.logger;
            if (logger5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger4 = null;
            } else {
                logger4 = logger5;
            }
            Logger.log$default(logger4, "Location buffer is empty, no average location available", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
            return null;
        }
        Logger logger6 = this.logger;
        if (logger6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        } else {
            logger = logger6;
        }
        Logger.log$default(logger, "Calculating weighted average from " + this.locationBuffer.size() + " locations", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
        double d = 0.0d;
        double latitude = 0.0d;
        double longitude = 0.0d;
        double accuracy = 0.0d;
        for (Iterator it = this.locationBuffer.iterator(); it.hasNext(); it = it) {
            Location location = (Location) it.next();
            double accuracy2 = 1.0d / ((location.getAccuracy() + 1.0d) * (location.getAccuracy() + 1.0d));
            latitude += location.getLatitude() * accuracy2;
            longitude += location.getLongitude() * accuracy2;
            accuracy += location.getAccuracy() * accuracy2;
            d += accuracy2;
            str = str;
        }
        String str2 = str;
        if (d > 0.0d) {
            Location location2 = new Location("weighted");
            location2.setLatitude(latitude / d);
            location2.setLongitude(longitude / d);
            location2.setAccuracy((float) (accuracy / d));
            location2.setTime(System.currentTimeMillis());
            Logger logger7 = this.logger;
            if (logger7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
                logger3 = null;
            } else {
                logger3 = logger7;
            }
            Logger.log$default(logger3, "Calculated average location: " + location2.getLatitude() + ", " + location2.getLongitude() + ", accuracy: " + location2.getAccuracy() + "m", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
            return location2;
        }
        Logger logger8 = this.logger;
        if (logger8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
            logger2 = null;
        } else {
            logger2 = logger8;
        }
        Logger.log$default(logger2, "Total weight is zero, cannot calculate average", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
        return null;
    }

    static /* synthetic */ void handleCheckout$default(LocationService locationService, String str, String str2, CheckoutData checkoutData, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            checkoutData = null;
        }
        locationService.handleCheckout(str, str2, checkoutData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleCheckout(String checkOutMethod, String message, CheckoutData existingData) {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        }
        Logger.log$default(logger, "Initiating checkout with method: " + checkOutMethod, LogLevel.INFO, "LocationService", null, null, 0, 56, null);
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass1(existingData, this, checkOutMethod, message, null), 3, null);
    }

    /* compiled from: LocationService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "com.site360new.LocationService$handleCheckout$1", f = "LocationService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.site360new.LocationService$handleCheckout$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $checkOutMethod;
        final /* synthetic */ CheckoutData $existingData;
        final /* synthetic */ String $message;
        int label;
        final /* synthetic */ LocationService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CheckoutData checkoutData, LocationService locationService, String str, String str2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$existingData = checkoutData;
            this.this$0 = locationService;
            this.$checkOutMethod = str;
            this.$message = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$existingData, this.this$0, this.$checkOutMethod, this.$message, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Logger logger;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final CheckoutData checkoutData = this.$existingData;
            ApiClient apiClient = null;
            if (checkoutData == null) {
                Visit visit = LocationService.INSTANCE.getVisit();
                Integer visitId = visit != null ? visit.getVisitId() : null;
                SharedPreferences sharedPreferences = this.this$0.sharedPreferences;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                    sharedPreferences = null;
                }
                String string = sharedPreferences.getString(ResultKey.DEVICE_ID.getValue(), "");
                SharedPreferences sharedPreferences2 = this.this$0.sharedPreferences;
                if (sharedPreferences2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                    sharedPreferences2 = null;
                }
                checkoutData = new CheckoutData(visitId, string, sharedPreferences2.getString(ResultKey.DEVICE_ID.getValue(), ""), BuildConfig.APP_THEME_ID, "Checked Out", Intrinsics.areEqual(this.$checkOutMethod, "Auto Checkout Time") ? "System Checkout" : "Auto Checkout", this.$checkOutMethod, Boxing.boxInt(0), Instant.now().toString());
            }
            Logger logger2 = this.this$0.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger = null;
            } else {
                logger = logger2;
            }
            Integer visitId2 = checkoutData.getVisitId();
            Logger.log$default(logger, "Checkout data prepared: visitId=" + (visitId2 != null ? visitId2.intValue() : 0) + ", time=" + checkoutData.getCheckOutTime(), LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
            ApiClient apiClient2 = this.this$0.apiClient;
            if (apiClient2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("apiClient");
            } else {
                apiClient = apiClient2;
            }
            Call<Void> callCheckout = apiClient.getApiService().checkout(checkoutData);
            final LocationService locationService = this.this$0;
            final String str = this.$message;
            callCheckout.enqueue(new Callback<Void>() { // from class: com.site360new.LocationService.handleCheckout.1.1
                @Override // retrofit2.Callback
                public void onFailure(Call<Void> call, Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    Logger logger3 = locationService.logger;
                    if (logger3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger3 = null;
                    }
                    Logger.log$default(logger3, "Checkout request failed: " + t.getMessage() + ", will retry on next update cycle", LogLevel.ERROR, "LocationService", null, null, 0, 56, null);
                    locationService.logApiError(t);
                    locationService.pendingCheckoutData = checkoutData;
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Logger logger3;
                    Logger logger4;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        Logger logger5 = locationService.logger;
                        if (logger5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                            logger4 = null;
                        } else {
                            logger4 = logger5;
                        }
                        Logger.log$default(logger4, "Checkout request successful", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
                        locationService.pendingCheckoutData = null;
                        LocationService locationService2 = locationService;
                        String checkOutTime = checkoutData.getCheckOutTime();
                        if (checkOutTime == null) {
                            checkOutTime = "";
                        }
                        LocationService.finishCheckout$default(locationService2, checkOutTime, str, false, 4, null);
                        return;
                    }
                    Logger logger6 = locationService.logger;
                    if (logger6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger3 = null;
                    } else {
                        logger3 = logger6;
                    }
                    Logger.log$default(logger3, "Checkout request failed with status: " + response.code() + ", will retry on next update cycle", LogLevel.ERROR, "LocationService", null, null, 0, 56, null);
                    locationService.logApiError(new Exception("API Error: " + response.code()));
                    locationService.pendingCheckoutData = checkoutData;
                }
            });
            return Unit.INSTANCE;
        }
    }

    static /* synthetic */ void finishCheckout$default(LocationService locationService, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        locationService.finishCheckout(str, str2, z);
    }

    private final void finishCheckout(String time, String message, boolean apiFailed) {
        Logger logger;
        Logger logger2;
        Logger logger3;
        if (visit == null) {
            Logger logger4 = this.logger;
            if (logger4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger3 = null;
            } else {
                logger3 = logger4;
            }
            Logger.log$default(logger3, "No active visit to finish checkout for", LogLevel.WARNING, "LocationService", null, null, 0, 56, null);
            return;
        }
        Logger logger5 = this.logger;
        if (logger5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        } else {
            logger = logger5;
        }
        Logger.log$default(logger, "Finishing checkout at time: " + time, LogLevel.INFO, "LocationService", null, null, 0, 56, null);
        if (isRunning) {
            stopService();
            emitCheckoutEvent(time);
            String string = getString(R.string.dashboard_checked_out_title);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dashboard_checked_out_title)");
            if (message == null) {
                message = getString(R.string.dashboard_auto_checked_out_message);
                Intrinsics.checkNotNullExpressionValue(message, "getString(R.string.dashb…auto_checked_out_message)");
            }
            showNotification(string, message);
            Logger logger6 = this.logger;
            if (logger6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger2 = null;
            } else {
                logger2 = logger6;
            }
            Logger.log$default(logger2, "Checkout completed and notification sent", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
            return;
        }
        emitCheckoutEvent(time);
    }

    private final void emitCheckoutEvent(String time) {
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter;
        Object applicationContext = getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type com.facebook.react.ReactApplication");
        ReactContext currentReactContext = ((ReactApplication) applicationContext).getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
        if (currentReactContext == null || (rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)) == null) {
            return;
        }
        rCTDeviceEventEmitter.emit("checkout", time);
    }

    private final void showNotification(String title, String message) {
        Logger logger;
        Logger logger2;
        Logger logger3;
        Logger logger4 = this.logger;
        if (logger4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        } else {
            logger = logger4;
        }
        Logger.log$default(logger, "Showing notification: '" + title + "' - '" + message + "'", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
        Notification notificationBuild = createNotificationBuilder(title, message, createPendingIntent()).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "createNotificationBuilde…e, pendingIntent).build()");
        try {
            NotificationManager notificationManager = this.notificationManager;
            if (notificationManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
                notificationManager = null;
            }
            notificationManager.notify(1, notificationBuild);
            Logger logger5 = this.logger;
            if (logger5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger3 = null;
            } else {
                logger3 = logger5;
            }
            Logger.log$default(logger3, "Notification scheduled successfully", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
        } catch (Exception e) {
            Logger logger6 = this.logger;
            if (logger6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger2 = null;
            } else {
                logger2 = logger6;
            }
            Logger.log$default(logger2, "Failed to schedule notification: " + e.getMessage(), LogLevel.ERROR, "LocationService", null, null, 0, 56, null);
            Sentry.captureException(e);
        }
    }

    private final PendingIntent createPendingIntent() {
        LocationService locationService = this;
        Intent intent = new Intent(locationService, (Class<?>) MainActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(268435456);
        PendingIntent activity = PendingIntent.getActivity(locationService, 0, intent, 33554432);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(\n           …nt.FLAG_MUTABLE\n        )");
        return activity;
    }

    private final Notification.Builder createNotificationBuilder(String title, String message, PendingIntent pendingIntent) {
        Notification.Builder showWhen = new Notification.Builder(this, NOTIFICATION_CHANNEL).setContentTitle(title).setContentText(message).setSmallIcon(R.mipmap.ic_launcher_round).setContentIntent(pendingIntent).setWhen(System.currentTimeMillis()).setShowWhen(true);
        Intrinsics.checkNotNullExpressionValue(showWhen, "Builder(this, NOTIFICATI…       .setShowWhen(true)");
        return showWhen;
    }

    private final Notification createNotification() {
        PendingIntent pendingIntentCreatePendingIntent = createPendingIntent();
        NotificationChannel notificationChannel = new NotificationChannel(CHECKED_IN_CHANNEL, "Site360", 2);
        notificationChannel.setShowBadge(false);
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
            notificationManager = null;
        }
        notificationManager.createNotificationChannel(notificationChannel);
        String string = getString(R.string.notification_checked_in);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.notification_checked_in)");
        Notification notificationBuild = createNotificationBuilder("Site360", string, pendingIntentCreatePendingIntent).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "createNotificationBuilde…gIntent\n        ).build()");
        return notificationBuild;
    }

    private final void resetState() {
        visit = null;
        accuracyThreshold = 30.0f;
        evacuate = false;
        this.hasLocationPermission = true;
        this.hasLocation = true;
        this.outOfBounds = false;
        this.outOfBoundsStartTime = null;
        this.hasNewLocationDataSinceOutOfBounds = false;
        this.lastLocation = null;
        this.locationBuffer.clear();
        this.lastUpdateTime = null;
        this.pendingCheckoutData = null;
    }

    private final void stopService() {
        Logger logger;
        Logger logger2 = this.logger;
        LocationManager locationManager = null;
        if (logger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        } else {
            logger = logger2;
        }
        Logger.log$default(logger, "Stopping location service", LogLevel.INFO, "LocationService", null, null, 0, 56, null);
        this.handler.removeCallbacks(this.handleUpdate);
        LocationManager locationManager2 = this.locationManager;
        if (locationManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationManager");
        } else {
            locationManager = locationManager2;
        }
        locationManager.removeUpdates(this);
        stopForeground(1);
        stopSelf();
        resetState();
        isRunning = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logApiError(Throwable error) {
        if (error instanceof UnknownHostException ? true : error instanceof SocketException ? true : error instanceof SocketTimeoutException ? true : error instanceof SSLHandshakeException ? true : error instanceof ConnectException) {
            return;
        }
        Sentry.captureException(error);
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        Logger logger;
        Logger logger2;
        Logger logger3;
        Logger logger4;
        Logger logger5;
        Intrinsics.checkNotNullParameter(location, "location");
        if (location.getAccuracy() <= accuracyThreshold) {
            if (this.lastUpdateTime != null) {
                long time = location.getTime();
                Long l = this.lastUpdateTime;
                Intrinsics.checkNotNull(l);
                if (time <= l.longValue()) {
                    Logger logger6 = this.logger;
                    if (logger6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                        logger5 = null;
                    } else {
                        logger5 = logger6;
                    }
                    Logger.log$default(logger5, "Filtered out stale location timestamp", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
                    return;
                }
            }
            this.lastLocation = location;
            this.locationBuffer.add(location);
            Logger logger7 = this.logger;
            if (logger7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger2 = null;
            } else {
                logger2 = logger7;
            }
            Logger.log$default(logger2, "Adding 1 valid locations to buffer", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
            Logger logger8 = this.logger;
            if (logger8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                logger3 = null;
            } else {
                logger3 = logger8;
            }
            Logger.log$default(logger3, "Location: " + location.getLatitude() + ", " + location.getLongitude() + ", accuracy: " + location.getAccuracy() + "m", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
            if (this.locationBuffer.size() > 10) {
                Logger logger9 = this.logger;
                if (logger9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
                    logger4 = null;
                } else {
                    logger4 = logger9;
                }
                Logger.log$default(logger4, "Location buffer exceeded max size, trimming to 10 locations", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
                this.locationBuffer.remove(0);
                return;
            }
            return;
        }
        Logger logger10 = this.logger;
        if (logger10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryEvent.JsonKeys.LOGGER);
            logger = null;
        } else {
            logger = logger10;
        }
        Logger.log$default(logger, "Filtered out location with poor accuracy: " + location.getAccuracy() + "m (threshold: " + accuracyThreshold + "m)", LogLevel.DEBUG, "LocationService", null, null, 0, 56, null);
    }

    /* compiled from: LocationService.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lcom/site360new/LocationService$Companion;", "", "()V", "CHECKED_IN_CHANNEL", "", "DEFAULT_ACCURACY_THRESHOLD", "", "DEFAULT_INTERVAL", "", "EVACUATION_INTERVAL", "LOCATION_UPDATE_DISTANCE", "LOCATION_UPDATE_INTERVAL", "MAX_LOCATION_BUFFER_SIZE", "", "NOTIFICATION_CHANNEL", "START_ACTION", "accuracyThreshold", "getAccuracyThreshold", "()F", "setAccuracyThreshold", "(F)V", "evacuate", "", "getEvacuate", "()Z", "setEvacuate", "(Z)V", "getSharedPreferencesStatic", "Lkotlin/Function0;", "Landroid/content/SharedPreferences;", "getGetSharedPreferencesStatic", "()Lkotlin/jvm/functions/Function0;", "setGetSharedPreferencesStatic", "(Lkotlin/jvm/functions/Function0;)V", "isRunning", "setRunning", "visit", "Lcom/site360new/Visit;", "getVisit", "()Lcom/site360new/Visit;", "setVisit", "(Lcom/site360new/Visit;)V", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Function0<SharedPreferences> getGetSharedPreferencesStatic() {
            return LocationService.getSharedPreferencesStatic;
        }

        public final void setGetSharedPreferencesStatic(Function0<? extends SharedPreferences> function0) {
            LocationService.getSharedPreferencesStatic = function0;
        }

        public final Visit getVisit() {
            return LocationService.visit;
        }

        public final void setVisit(Visit visit) {
            LocationService.visit = visit;
        }

        public final float getAccuracyThreshold() {
            return LocationService.accuracyThreshold;
        }

        public final void setAccuracyThreshold(float f) {
            LocationService.accuracyThreshold = f;
        }

        public final boolean getEvacuate() {
            return LocationService.evacuate;
        }

        public final void setEvacuate(boolean z) {
            LocationService.evacuate = z;
        }

        public final boolean isRunning() {
            return LocationService.isRunning;
        }

        public final void setRunning(boolean z) {
            LocationService.isRunning = z;
        }
    }
}
