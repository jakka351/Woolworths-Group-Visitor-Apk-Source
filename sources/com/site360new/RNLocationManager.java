package com.site360new;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import io.sentry.Sentry;
import io.sentry.protocol.App;
import io.sentry.protocol.MetricSummary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNLocationManager.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 52\u00020\u00012\u00020\u0002:\u00015B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007J\b\u0010\u0019\u001a\u00020\u0016H\u0003J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0007H\u0007J,\u0010\u001b\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\u001e\u001a\u00020\u0018H\u0016J-\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000f2\u000e\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180#2\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0002\u0010&J\u0017\u0010'\u001a\u00020\u00162\b\u0010(\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020\u0016H\u0007J \u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u0018H\u0007J\b\u0010/\u001a\u00020\u0016H\u0007J\b\u00100\u001a\u00020\u0016H\u0007J)\u00101\u001a\u00020\u0016*\u00020\u00072\u0016\u00102\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001030#\"\u0004\u0018\u000103H\u0002¢\u0006\u0002\u00104R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/site360new/RNLocationManager;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "Lcom/facebook/react/modules/core/PermissionListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "errorCallback", "Lcom/facebook/react/bridge/Callback;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "getFusedLocationClient", "()Lcom/google/android/gms/location/FusedLocationProviderClient;", "fusedLocationClient$delegate", "Lkotlin/Lazy;", "locationAccuracy", "", "permissionCallback", "polygon", "", "", "successCallback", "addListener", "", "eventName", "", "checkLocationPermission", "checkPermission", "getLocation", "Lcom/facebook/react/bridge/ReadableArray;", "accuracy", "getName", "onRequestPermissionsResult", "", "requestCode", App.JsonKeys.APP_PERMISSIONS, "", "grantResults", "", "(I[Ljava/lang/String;[I)Z", "removeListeners", MetricSummary.JsonKeys.COUNT, "(Ljava/lang/Integer;)V", "startEvacuation", "startTrackingLocation", "baseUrl", "locationAccuracyThreshold", "jsonVisit", "stopEvacuation", "stopTrackingLocation", "invokeAndClear", "args", "", "(Lcom/facebook/react/bridge/Callback;[Ljava/lang/Object;)V", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNLocationManager extends ReactContextBaseJavaModule implements PermissionListener {
    public static final int LOCATION_PERMISSION = 1;
    private static final String TAG = "RNLocationManager";
    private Callback errorCallback;

    /* renamed from: fusedLocationClient$delegate, reason: from kotlin metadata */
    private final Lazy fusedLocationClient;
    private int locationAccuracy;
    private Callback permissionCallback;
    private List<? extends List<Double>> polygon;
    private final ReactApplicationContext reactContext;
    private Callback successCallback;

    @ReactMethod
    public final void addListener(String eventName) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public final void removeListeners(Integer count) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RNLocationManager(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.locationAccuracy = 102;
        this.fusedLocationClient = LazyKt.lazy(new Function0<FusedLocationProviderClient>() { // from class: com.site360new.RNLocationManager$fusedLocationClient$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FusedLocationProviderClient invoke() {
                Activity currentActivity = this.this$0.getCurrentActivity();
                if (currentActivity != null) {
                    return LocationServices.getFusedLocationProviderClient(currentActivity);
                }
                return null;
            }
        });
    }

    private final FusedLocationProviderClient getFusedLocationClient() {
        return (FusedLocationProviderClient) this.fusedLocationClient.getValue();
    }

    @ReactMethod
    public final void checkPermission(Callback permissionCallback) {
        Intrinsics.checkNotNullParameter(permissionCallback, "permissionCallback");
        this.permissionCallback = permissionCallback;
        checkLocationPermission();
    }

    @ReactMethod
    public final void getLocation(ReadableArray polygon, String accuracy, Callback successCallback, Callback errorCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
        if (polygon != null) {
            ArrayList<Object> arrayList = polygon.toArrayList();
            Intrinsics.checkNotNull(arrayList, "null cannot be cast to non-null type java.util.ArrayList<kotlin.collections.List<kotlin.Double>>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.collections.List<kotlin.Double>> }");
            this.polygon = arrayList;
        }
        if (Intrinsics.areEqual(accuracy, "low")) {
            this.locationAccuracy = 104;
        } else {
            this.locationAccuracy = 102;
        }
        checkLocationPermission();
    }

    @ReactMethod
    public final void startTrackingLocation(String baseUrl, int locationAccuracyThreshold, String jsonVisit) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(jsonVisit, "jsonVisit");
        ApiClient.INSTANCE.setBaseUrl(baseUrl + "/");
        LocationService.INSTANCE.setVisit((Visit) new Gson().fromJson(jsonVisit, Visit.class));
        LocationService.INSTANCE.setAccuracyThreshold(locationAccuracyThreshold);
        checkLocationPermission();
    }

    @ReactMethod
    public final void stopTrackingLocation() {
        this.reactContext.stopService(new Intent(this.reactContext, (Class<?>) LocationService.class));
    }

    @ReactMethod
    public final void startEvacuation() {
        LocationService.INSTANCE.setEvacuate(true);
    }

    @ReactMethod
    public final void stopEvacuation() {
        LocationService.INSTANCE.setEvacuate(false);
    }

    private final void checkLocationPermission() {
        FusedLocationProviderClient fusedLocationClient;
        Task<Location> currentLocation;
        PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity) getCurrentActivity();
        boolean z = false;
        if (permissionAwareActivity != null && permissionAwareActivity.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        }
        if (!z || this.permissionCallback != null) {
            if (permissionAwareActivity != null) {
                permissionAwareActivity.requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1, this);
                return;
            }
            return;
        }
        if (this.successCallback != null && (fusedLocationClient = getFusedLocationClient()) != null && (currentLocation = fusedLocationClient.getCurrentLocation(this.locationAccuracy, (CancellationToken) null)) != null) {
            final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: com.site360new.RNLocationManager.checkLocationPermission.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                    invoke2(location);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Location location) {
                    if (location == null) {
                        Callback callback = RNLocationManager.this.errorCallback;
                        if (callback != null) {
                            RNLocationManager.this.invokeAndClear(callback, ErrorKey.LOCATION.getValue());
                            return;
                        }
                        return;
                    }
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble(ResultKey.LATITUDE.getValue(), location.getLatitude());
                    writableNativeMap.putDouble(ResultKey.LONGITUDE.getValue(), location.getLongitude());
                    writableNativeMap.putDouble(ResultKey.ACCURACY.getValue(), location.getAccuracy());
                    if (Build.VERSION.SDK_INT >= 31) {
                        writableNativeMap.putBoolean(ResultKey.MOCK_LOCATION.getValue(), location.isMock());
                    } else {
                        writableNativeMap.putBoolean(ResultKey.MOCK_LOCATION.getValue(), location.isFromMockProvider());
                    }
                    List list = RNLocationManager.this.polygon;
                    if (list != null) {
                        writableNativeMap.putBoolean(ResultKey.INSIDE_POLYGON.getValue(), LocationHelperKt.isLocationInPolygon(list, new Pair(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())), location.getAccuracy()));
                    }
                    Callback callback2 = RNLocationManager.this.successCallback;
                    if (callback2 != null) {
                        RNLocationManager.this.invokeAndClear(callback2, writableNativeMap);
                    }
                }
            };
            Task<Location> taskAddOnSuccessListener = currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.site360new.RNLocationManager$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    RNLocationManager.checkLocationPermission$lambda$1(function1, obj);
                }
            });
            if (taskAddOnSuccessListener != null) {
                taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.site360new.RNLocationManager$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        RNLocationManager.checkLocationPermission$lambda$2(this.f$0, exc);
                    }
                });
            }
        }
        if (LocationService.INSTANCE.getVisit() == null || LocationService.INSTANCE.isRunning()) {
            return;
        }
        Intent intent = new Intent(this.reactContext, (Class<?>) LocationService.class);
        intent.setAction("start");
        try {
            this.reactContext.startForegroundService(intent);
        } catch (SecurityException e) {
            Sentry.captureException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkLocationPermission$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkLocationPermission$lambda$2(RNLocationManager this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Sentry.captureException(it);
        Callback callback = this$0.errorCallback;
        if (callback != null) {
            this$0.invokeAndClear(callback, ErrorKey.LOCATION.getValue());
        }
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if ((!(grantResults.length == 0)) && grantResults[0] != -1) {
            Callback callback = this.permissionCallback;
            if (callback != null) {
                invokeAndClear(callback, new Object[0]);
            }
            checkLocationPermission();
        } else {
            Callback callback2 = this.permissionCallback;
            if (callback2 != null) {
                invokeAndClear(callback2, ErrorKey.LOCATION_PERMISSION.getValue());
            }
            Callback callback3 = this.errorCallback;
            if (callback3 != null) {
                invokeAndClear(callback3, ErrorKey.LOCATION_PERMISSION.getValue());
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invokeAndClear(Callback callback, Object... objArr) {
        callback.invoke(Arrays.copyOf(objArr, objArr.length));
        if (Intrinsics.areEqual(callback, this.permissionCallback)) {
            this.permissionCallback = null;
        } else if (Intrinsics.areEqual(callback, this.successCallback)) {
            this.successCallback = null;
        } else if (Intrinsics.areEqual(callback, this.errorCallback)) {
            this.errorCallback = null;
        }
    }
}
