package org.maplibre.turf;

import java.util.List;
import org.maplibre.geojson.Point;

/* loaded from: classes2.dex */
public class TurfClassification {
    private TurfClassification() {
    }

    public static Point nearestPoint(Point point, List<Point> list) {
        if (list.isEmpty()) {
            return point;
        }
        Point point2 = list.get(0);
        double d = Double.POSITIVE_INFINITY;
        for (Point point3 : list) {
            double dDistance = TurfMeasurement.distance(point, point3);
            if (dDistance < d) {
                point2 = point3;
                d = dDistance;
            }
        }
        return point2;
    }
}
