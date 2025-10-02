package org.maplibre.turf;

import java.util.ArrayList;
import org.maplibre.geojson.Point;
import org.maplibre.geojson.Polygon;

/* loaded from: classes2.dex */
public final class TurfTransformation {
    private static final int DEFAULT_STEPS = 64;

    private TurfTransformation() {
    }

    public static Polygon circle(Point point, double d) {
        return circle(point, d, 64, "kilometers");
    }

    public static Polygon circle(Point point, double d, String str) {
        return circle(point, d, 64, str);
    }

    public static Polygon circle(Point point, double d, int i, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(TurfMeasurement.destination(point, d, (i2 * 360.0d) / i, str));
        }
        if (arrayList.size() > 0) {
            arrayList.add(arrayList.get(0));
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(arrayList);
        return Polygon.fromLngLats(arrayList2);
    }
}
