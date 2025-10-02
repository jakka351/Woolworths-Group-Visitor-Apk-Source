package org.maplibre.android.location.engine;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationEngineDefault.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/maplibre/android/location/engine/LocationEngineDefault;", "", "<init>", "()V", "getDefaultLocationEngine", "Lorg/maplibre/android/location/engine/LocationEngine;", "context", "Landroid/content/Context;", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocationEngineDefault {
    public static final LocationEngineDefault INSTANCE = new LocationEngineDefault();

    private LocationEngineDefault() {
    }

    public final LocationEngine getDefaultLocationEngine(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new LocationEngineProxy(new MapLibreFusedLocationEngineImpl(context.getApplicationContext()));
    }
}
