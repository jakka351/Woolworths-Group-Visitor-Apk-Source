package com.maplibre.rctmln.utils;

import android.graphics.PointF;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.VisibleRegion;

/* loaded from: classes3.dex */
public class GeoViewport {
    private static SphericalMercator sphericalMercator = new SphericalMercator();

    public static VisibleRegion getRegion(LatLng latLng, int i, int i2, int i3) {
        PointF px = sphericalMercator.getPX(latLng, i);
        float f = i2 / 2;
        float f2 = i3 / 2;
        LatLng latLng2 = sphericalMercator.getLatLng(new PointF(px.x - f, px.y - f2), i);
        LatLng latLng3 = sphericalMercator.getLatLng(new PointF(px.x + f, px.y + f2), i);
        return new VisibleRegion(latLng2, new LatLng(latLng2.getLatitude(), latLng3.getLongitude()), new LatLng(latLng3.getLatitude(), latLng2.getLongitude()), latLng3, null);
    }
}
