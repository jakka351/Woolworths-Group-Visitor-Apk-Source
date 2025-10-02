package org.maplibre.android.camera;

import kotlin.Metadata;
import org.maplibre.android.maps.MapLibreMap;

/* compiled from: CameraUpdate.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/maplibre/android/camera/CameraUpdate;", "", "getCameraPosition", "Lorg/maplibre/android/camera/CameraPosition;", "maplibreMap", "Lorg/maplibre/android/maps/MapLibreMap;", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CameraUpdate {
    CameraPosition getCameraPosition(MapLibreMap maplibreMap);
}
