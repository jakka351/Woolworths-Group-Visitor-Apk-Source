package com.zoontek.rnpermissions;

import android.app.AlarmManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.App;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNPermissionsModuleImpl.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u001e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J,\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J \u0010%\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010&\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0011J4\u0010'\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020)2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010*\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J4\u0010+\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020)2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010,\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001e\u0010-\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/zoontek/rnpermissions/RNPermissionsModuleImpl;", "", "()V", "BLOCKED", "", "DENIED", "ERROR_INVALID_ACTIVITY", "GRANTED", "NAME", "UNAVAILABLE", "requestCode", "", "canScheduleExactAlarms", "", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "check", "permission", "checkLocationAccuracy", "checkMultiple", App.JsonKeys.APP_PERMISSIONS, "Lcom/facebook/react/bridge/ReadableArray;", "checkNotifications", "getPermissionAwareActivity", "Lcom/facebook/react/modules/core/PermissionAwareActivity;", "isPermissionAvailable", "", "context", "onRequestPermissionsResult", "callbacks", "Landroid/util/SparseArray;", "Lcom/facebook/react/bridge/Callback;", "grantResults", "", "openPhotoPicker", "openSettings", "type", SentryBaseEvent.JsonKeys.REQUEST, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/modules/core/PermissionListener;", "requestLocationAccuracy", "requestMultiple", "requestNotifications", "shouldShowRequestRationale", "react-native-permissions_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNPermissionsModuleImpl {
    private static final String BLOCKED = "blocked";
    private static final String DENIED = "denied";
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    private static final String GRANTED = "granted";
    public static final RNPermissionsModuleImpl INSTANCE = new RNPermissionsModuleImpl();
    public static final String NAME = "RNPermissions";
    private static final String UNAVAILABLE = "unavailable";
    private static int requestCode;

    private RNPermissionsModuleImpl() {
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x007e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0033 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isPermissionAvailable(com.facebook.react.bridge.ReactApplicationContext r7, java.lang.String r8) throws java.lang.NoSuchFieldException, android.content.pm.PackageManager.NameNotFoundException {
        /*
            r6 = this;
            java.lang.String r0 = "android.permission."
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r0 = kotlin.text.StringsKt.removePrefix(r8, r0)
            java.lang.String r1 = "com.android.voicemail.permission."
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.String r0 = kotlin.text.StringsKt.removePrefix(r0, r1)
            r1 = 1
            java.lang.Class<android.Manifest$permission> r2 = android.Manifest.permission.class
            r2.getField(r0)     // Catch: java.lang.NoSuchFieldException -> L17
            return r1
        L17:
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            r0 = 0
            java.util.List r2 = r7.getAllPermissionGroups(r0)
            java.lang.String r3 = "manager.getAllPermissionGroups(0)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.toMutableList(r2)
            r3 = 0
            r2.add(r3)
            java.util.Iterator r2 = r2.iterator()
        L33:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L7f
            java.lang.Object r4 = r2.next()
            android.content.pm.PermissionGroupInfo r4 = (android.content.pm.PermissionGroupInfo) r4
            if (r4 == 0) goto L44
            java.lang.String r4 = r4.name     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            goto L45
        L44:
            r4 = r3
        L45:
            java.util.List r4 = r7.queryPermissionsByGroup(r4, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            java.lang.String r5 = "manager.queryPermissionsByGroup(group?.name, 0)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            boolean r5 = r4 instanceof java.util.Collection     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            if (r5 == 0) goto L5f
            r5 = r4
            java.util.Collection r5 = (java.util.Collection) r5     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            boolean r5 = r5.isEmpty()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            if (r5 == 0) goto L5f
        L5d:
            r4 = r0
            goto L7c
        L5f:
            java.util.Iterator r4 = r4.iterator()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
        L63:
            boolean r5 = r4.hasNext()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            if (r5 == 0) goto L5d
            java.lang.Object r5 = r4.next()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            android.content.pm.PermissionInfo r5 = (android.content.pm.PermissionInfo) r5     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            if (r5 == 0) goto L74
            java.lang.String r5 = r5.name     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            goto L75
        L74:
            r5 = r3
        L75:
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L33
            if (r5 == 0) goto L63
            r4 = r1
        L7c:
            if (r4 == 0) goto L33
            return r1
        L7f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zoontek.rnpermissions.RNPermissionsModuleImpl.isPermissionAvailable(com.facebook.react.bridge.ReactApplicationContext, java.lang.String):boolean");
    }

    public final void openSettings(ReactApplicationContext reactContext, String type, Promise promise) {
        Intent intent;
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            String packageName = reactContext.getPackageName();
            if (Build.VERSION.SDK_INT >= 31 && Intrinsics.areEqual(type, "alarms")) {
                intent = new Intent();
                intent.setAction("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
                intent.setData(Uri.parse("package:" + packageName));
            } else if (Intrinsics.areEqual(type, "notifications")) {
                intent = new Intent();
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
            } else {
                intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + packageName));
            }
            intent.addFlags(268435456);
            reactContext.startActivity(intent);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    public final void canScheduleExactAlarms(ReactApplicationContext reactContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (Build.VERSION.SDK_INT < 31) {
            promise.resolve(true);
            return;
        }
        Object systemService = reactContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        if (alarmManager == null) {
            promise.resolve(false);
        } else {
            promise.resolve(Boolean.valueOf(alarmManager.canScheduleExactAlarms()));
        }
    }

    public final void check(ReactApplicationContext reactContext, String permission, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (!isPermissionAvailable(reactContext, permission)) {
            promise.resolve(UNAVAILABLE);
        } else if (reactContext.getBaseContext().checkSelfPermission(permission) == 0) {
            promise.resolve(GRANTED);
        } else {
            promise.resolve(DENIED);
        }
    }

    public final void checkNotifications(ReactApplicationContext reactContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        boolean zAreNotificationsEnabled = NotificationManagerCompat.from(reactContext).areNotificationsEnabled();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("status", zAreNotificationsEnabled ? GRANTED : DENIED);
        writableMapCreateMap.putMap("settings", Arguments.createMap());
        promise.resolve(writableMapCreateMap);
    }

    public final void checkMultiple(ReactApplicationContext reactContext, ReadableArray permissions, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Context baseContext = reactContext.getBaseContext();
        int size = permissions.size();
        for (int i = 0; i < size; i++) {
            String string = permissions.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "permissions.getString(i)");
            if (!isPermissionAvailable(reactContext, string)) {
                writableNativeMap.putString(string, UNAVAILABLE);
            } else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, GRANTED);
            } else {
                writableNativeMap.putString(string, DENIED);
            }
        }
        promise.resolve(writableNativeMap);
    }

    public final void request(ReactApplicationContext reactContext, PermissionListener listener, SparseArray<Callback> callbacks, final String permission, final Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (!isPermissionAvailable(reactContext, permission)) {
            promise.resolve(UNAVAILABLE);
            return;
        }
        if (reactContext.getBaseContext().checkSelfPermission(permission) == 0) {
            promise.resolve(GRANTED);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactContext);
            callbacks.put(requestCode, new Callback() { // from class: com.zoontek.rnpermissions.RNPermissionsModuleImpl$$ExternalSyntheticLambda0
                @Override // com.facebook.react.bridge.Callback
                public final void invoke(Object[] objArr) {
                    RNPermissionsModuleImpl.request$lambda$7(promise, permission, objArr);
                }
            });
            permissionAwareActivity.requestPermissions(new String[]{permission}, requestCode, listener);
            requestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void request$lambda$7(Promise promise, String permission, Object[] objArr) {
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(permission, "$permission");
        Object obj = objArr[0];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.IntArray");
        int[] iArr = (int[]) obj;
        Object obj2 = objArr[1];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
        PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity) obj2;
        if ((true ^ (iArr.length == 0)) && iArr[0] == 0) {
            promise.resolve(GRANTED);
        } else if (permissionAwareActivity.shouldShowRequestPermissionRationale(permission)) {
            promise.resolve(DENIED);
        } else {
            promise.resolve(BLOCKED);
        }
    }

    public final void requestNotifications(ReactApplicationContext reactContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        boolean zAreNotificationsEnabled = NotificationManagerCompat.from(reactContext).areNotificationsEnabled();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("status", zAreNotificationsEnabled ? GRANTED : BLOCKED);
        writableMapCreateMap.putMap("settings", Arguments.createMap());
        promise.resolve(writableMapCreateMap);
    }

    public final void requestMultiple(ReactApplicationContext reactContext, PermissionListener listener, SparseArray<Callback> callbacks, ReadableArray permissions, final Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList arrayList = new ArrayList();
        Context baseContext = reactContext.getBaseContext();
        int size = permissions.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String string = permissions.getString(i2);
            Intrinsics.checkNotNullExpressionValue(string, "permissions.getString(i)");
            if (!isPermissionAvailable(reactContext, string)) {
                writableNativeMap.putString(string, UNAVAILABLE);
            } else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, GRANTED);
            } else {
                arrayList.add(string);
            }
            i++;
        }
        if (permissions.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactContext);
            callbacks.put(requestCode, new Callback() { // from class: com.zoontek.rnpermissions.RNPermissionsModuleImpl$$ExternalSyntheticLambda1
                @Override // com.facebook.react.bridge.Callback
                public final void invoke(Object[] objArr) {
                    RNPermissionsModuleImpl.requestMultiple$lambda$10(arrayList, promise, writableNativeMap, objArr);
                }
            });
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), requestCode, listener);
            requestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestMultiple$lambda$10(ArrayList permissionsToCheck, Promise promise, WritableMap output, Object[] objArr) {
        Intrinsics.checkNotNullParameter(permissionsToCheck, "$permissionsToCheck");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(output, "$output");
        Object obj = objArr[0];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.IntArray");
        int[] iArr = (int[]) obj;
        Object obj2 = objArr[1];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
        PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity) obj2;
        int i = 0;
        for (Object obj3 : permissionsToCheck) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj3;
            if ((!(iArr.length == 0)) && iArr[i] == 0) {
                output.putString(str, GRANTED);
            } else if (permissionAwareActivity.shouldShowRequestPermissionRationale(str)) {
                output.putString(str, DENIED);
            } else {
                output.putString(str, BLOCKED);
            }
            i = i2;
        }
        promise.resolve(output);
    }

    public final void shouldShowRequestRationale(ReactApplicationContext reactContext, String permission, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity(reactContext).shouldShowRequestPermissionRationale(permission)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    private final PermissionAwareActivity getPermissionAwareActivity(ReactApplicationContext reactContext) {
        ComponentCallbacks2 currentActivity = reactContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.".toString());
        }
        if (!(currentActivity instanceof PermissionAwareActivity)) {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.".toString());
        }
        return (PermissionAwareActivity) currentActivity;
    }

    public final void openPhotoPicker(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.reject("Permissions:openPhotoPicker", "openPhotoPicker is not supported on Android");
    }

    public final void checkLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.reject("Permissions:checkLocationAccuracy", "checkLocationAccuracy is not supported on Android");
    }

    public final void requestLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.reject("Permissions:requestLocationAccuracy", "requestLocationAccuracy is not supported on Android");
    }

    public final boolean onRequestPermissionsResult(ReactApplicationContext reactContext, SparseArray<Callback> callbacks, int requestCode2, int[] grantResults) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        try {
            Callback callback = callbacks.get(requestCode2);
            if (callback != null) {
                callback.invoke(grantResults, getPermissionAwareActivity(reactContext));
                callbacks.remove(requestCode2);
            } else {
                FLog.w("PermissionsModule", "Unable to find callback with requestCode %d", Integer.valueOf(requestCode2));
            }
            return callbacks.size() == 0;
        } catch (IllegalStateException e) {
            FLog.e("PermissionsModule", e, "Unexpected invocation of `onRequestPermissionsResult`", new Object[0]);
            return false;
        }
    }
}
