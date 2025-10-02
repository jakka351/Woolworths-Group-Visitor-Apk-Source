package com.maplibre.rctmln.utils;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.maplibre.android.geometry.LatLng;

/* loaded from: classes3.dex */
public class SphericalMercator {
    public static final double D2R = 0.017453292519943295d;
    public static final double R2D = 57.29577951308232d;
    private static Map<String, List<Double>> cache;

    public SphericalMercator() {
        if (cache == null) {
            HashMap map = new HashMap();
            cache = map;
            map.put("Bc", new ArrayList());
            cache.put("Cc", new ArrayList());
            cache.put("zc", new ArrayList());
            cache.put("Ac", new ArrayList());
            double d = 512;
            for (int i = 0; i < 30; i++) {
                cache.get("Bc").add(Double.valueOf(d / 360.0d));
                cache.get("Cc").add(Double.valueOf(d / 6.283185307179586d));
                cache.get("zc").add(Double.valueOf(d / 2.0d));
                cache.get("Ac").add(Double.valueOf(d));
                d *= 2.0d;
            }
        }
    }

    public PointF getPX(LatLng latLng, int i) {
        double dDoubleValue = cache.get("zc").get(i).doubleValue();
        double dMin = Math.min(Math.max(Math.sin(latLng.getLatitude() * 0.017453292519943295d), -0.9999d), 0.9999d);
        double dRound = Math.round((latLng.getLongitude() * cache.get("Bc").get(i).doubleValue()) + dDoubleValue);
        double dRound2 = Math.round(dDoubleValue + (Math.log((dMin + 1.0d) / (1.0d - dMin)) * 0.5d * (-cache.get("Cc").get(i).doubleValue())));
        if (dRound > cache.get("Ac").get(i).doubleValue()) {
            dRound = cache.get("Ac").get(i).doubleValue();
        }
        if (dRound2 > cache.get("Ac").get(i).doubleValue()) {
            dRound2 = cache.get("Ac").get(i).doubleValue();
        }
        return new PointF((float) dRound, (float) dRound2);
    }

    public LatLng getLatLng(PointF pointF, int i) {
        return new LatLng(((Math.atan(Math.exp((pointF.y - cache.get("zc").get(i).doubleValue()) / (-cache.get("Cc").get(i).doubleValue()))) * 2.0d) - 1.5707963267948966d) * 57.29577951308232d, (pointF.x - cache.get("zc").get(i).doubleValue()) / cache.get("Bc").get(i).doubleValue());
    }
}
