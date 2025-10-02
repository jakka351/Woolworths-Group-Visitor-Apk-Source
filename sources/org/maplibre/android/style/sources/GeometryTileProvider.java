package org.maplibre.android.style.sources;

import kotlin.Metadata;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.geojson.FeatureCollection;

/* compiled from: GeometryTileProvider.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'¨\u0006\b"}, d2 = {"Lorg/maplibre/android/style/sources/GeometryTileProvider;", "", "getFeaturesForBounds", "Lorg/maplibre/geojson/FeatureCollection;", "bounds", "Lorg/maplibre/android/geometry/LatLngBounds;", "zoomLevel", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface GeometryTileProvider {
    FeatureCollection getFeaturesForBounds(LatLngBounds bounds, int zoomLevel);
}
