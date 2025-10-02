package org.maplibre.turf;

import io.sentry.SentryBaseEvent;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.LineString;
import org.maplibre.geojson.Point;
import org.maplibre.turf.models.LineIntersectsResult;

/* loaded from: classes2.dex */
public final class TurfMisc {
    private static final String INDEX_KEY = "index";

    private TurfMisc() {
        throw new AssertionError("No Instances.");
    }

    public static LineString lineSlice(Point point, Point point2, Feature feature) {
        if (feature.geometry() == null) {
            throw new NullPointerException("Feature.geometry() == null");
        }
        if (!feature.geometry().type().equals("LineString")) {
            throw new TurfException("input must be a LineString Feature or Geometry");
        }
        return lineSlice(point, point2, (LineString) feature.geometry());
    }

    public static LineString lineSlice(Point point, Point point2, LineString lineString) {
        List<Point> listCoordinates = lineString.coordinates();
        if (listCoordinates.size() < 2) {
            throw new TurfException("Turf lineSlice requires a LineString made up of at least 2 coordinates.");
        }
        if (point.equals(point2)) {
            throw new TurfException("Start and stop points in Turf lineSlice cannot equal each other.");
        }
        Feature featureNearestPointOnLine = nearestPointOnLine(point, listCoordinates);
        Feature featureNearestPointOnLine2 = nearestPointOnLine(point2, listCoordinates);
        ArrayList arrayList = new ArrayList();
        if (((Integer) featureNearestPointOnLine.getNumberProperty(INDEX_KEY)).intValue() <= ((Integer) featureNearestPointOnLine2.getNumberProperty(INDEX_KEY)).intValue()) {
            arrayList.add(featureNearestPointOnLine);
            arrayList.add(featureNearestPointOnLine2);
        } else {
            arrayList.add(featureNearestPointOnLine2);
            arrayList.add(featureNearestPointOnLine);
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add((Point) ((Feature) arrayList.get(0)).geometry());
        for (int iIntValue = ((Integer) ((Feature) arrayList.get(0)).getNumberProperty(INDEX_KEY)).intValue() + 1; iIntValue < ((Integer) ((Feature) arrayList.get(1)).getNumberProperty(INDEX_KEY)).intValue() + 1; iIntValue++) {
            arrayList2.add(listCoordinates.get(iIntValue));
        }
        arrayList2.add((Point) ((Feature) arrayList.get(1)).geometry());
        return LineString.fromLngLats(arrayList2);
    }

    public static LineString lineSliceAlong(Feature feature, double d, double d2, String str) {
        if (feature.geometry() == null) {
            throw new NullPointerException("Feature.geometry() == null");
        }
        if (!feature.geometry().type().equals("LineString")) {
            throw new TurfException("input must be a LineString Feature or Geometry");
        }
        return lineSliceAlong((LineString) feature.geometry(), d, d2, str);
    }

    public static LineString lineSliceAlong(LineString lineString, double d, double d2, String str) {
        List<Point> listCoordinates = lineString.coordinates();
        if (listCoordinates.size() < 2) {
            throw new TurfException("Turf lineSlice requires a LineString Geometry made up of at least 2 coordinates. The LineString passed in only contains " + listCoordinates.size() + ".");
        }
        if (d == d2) {
            throw new TurfException("Start and stop distance in Turf lineSliceAlong cannot equal each other.");
        }
        ArrayList arrayList = new ArrayList(2);
        int i = 0;
        double dDistance = 0.0d;
        while (i < listCoordinates.size() && (d < dDistance || i != listCoordinates.size() - 1)) {
            if (dDistance > d && arrayList.size() == 0) {
                double d3 = d - dDistance;
                if (d3 == 0.0d) {
                    arrayList.add(listCoordinates.get(i));
                    return LineString.fromLngLats(arrayList);
                }
                arrayList.add(TurfMeasurement.destination(listCoordinates.get(i), d3, TurfMeasurement.bearing(listCoordinates.get(i), listCoordinates.get(i - 1)) - 180.0d, str));
            }
            if (dDistance >= d2) {
                double d4 = d2 - dDistance;
                if (d4 == 0.0d) {
                    arrayList.add(listCoordinates.get(i));
                    return LineString.fromLngLats(arrayList);
                }
                arrayList.add(TurfMeasurement.destination(listCoordinates.get(i), d4, TurfMeasurement.bearing(listCoordinates.get(i), listCoordinates.get(i - 1)) - 180.0d, str));
                return LineString.fromLngLats(arrayList);
            }
            if (dDistance >= d) {
                arrayList.add(listCoordinates.get(i));
            }
            if (i == listCoordinates.size() - 1) {
                return LineString.fromLngLats(arrayList);
            }
            Point point = listCoordinates.get(i);
            i++;
            dDistance += TurfMeasurement.distance(point, listCoordinates.get(i), str);
        }
        if (dDistance < d) {
            throw new TurfException("Start position is beyond line");
        }
        return LineString.fromLngLats(arrayList);
    }

    public static Feature nearestPointOnLine(Point point, List<Point> list) {
        return nearestPointOnLine(point, list, null);
    }

    public static Feature nearestPointOnLine(Point point, List<Point> list, String str) {
        Feature featureFromGeometry;
        if (list.size() < 2) {
            throw new TurfException("Turf nearestPointOnLine requires a List of Points made up of at least 2 coordinates.");
        }
        String str2 = str == null ? "kilometers" : str;
        Feature featureFromGeometry2 = Feature.fromGeometry(Point.fromLngLat(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
        featureFromGeometry2.addNumberProperty(SentryBaseEvent.JsonKeys.DIST, Double.valueOf(Double.POSITIVE_INFINITY));
        int i = 0;
        Feature feature = featureFromGeometry2;
        while (i < list.size() - 1) {
            Feature featureFromGeometry3 = Feature.fromGeometry(list.get(i));
            int i2 = i + 1;
            Feature featureFromGeometry4 = Feature.fromGeometry(list.get(i2));
            featureFromGeometry3.addNumberProperty(SentryBaseEvent.JsonKeys.DIST, Double.valueOf(TurfMeasurement.distance(point, (Point) featureFromGeometry3.geometry(), str2)));
            featureFromGeometry4.addNumberProperty(SentryBaseEvent.JsonKeys.DIST, Double.valueOf(TurfMeasurement.distance(point, (Point) featureFromGeometry4.geometry(), str2)));
            double dMax = Math.max(featureFromGeometry3.properties().get(SentryBaseEvent.JsonKeys.DIST).getAsDouble(), featureFromGeometry4.properties().get(SentryBaseEvent.JsonKeys.DIST).getAsDouble());
            double dBearing = TurfMeasurement.bearing((Point) featureFromGeometry3.geometry(), (Point) featureFromGeometry4.geometry());
            String str3 = str2;
            Feature featureFromGeometry5 = Feature.fromGeometry(TurfMeasurement.destination(point, dMax, dBearing + 90.0d, str3));
            Feature featureFromGeometry6 = Feature.fromGeometry(TurfMeasurement.destination(point, dMax, dBearing - 90.0d, str3));
            LineIntersectsResult lineIntersectsResultLineIntersects = lineIntersects(((Point) featureFromGeometry5.geometry()).longitude(), ((Point) featureFromGeometry5.geometry()).latitude(), ((Point) featureFromGeometry6.geometry()).longitude(), ((Point) featureFromGeometry6.geometry()).latitude(), ((Point) featureFromGeometry3.geometry()).longitude(), ((Point) featureFromGeometry3.geometry()).latitude(), ((Point) featureFromGeometry4.geometry()).longitude(), ((Point) featureFromGeometry4.geometry()).latitude());
            if (lineIntersectsResultLineIntersects != null) {
                featureFromGeometry = Feature.fromGeometry(Point.fromLngLat(lineIntersectsResultLineIntersects.horizontalIntersection().doubleValue(), lineIntersectsResultLineIntersects.verticalIntersection().doubleValue()));
                featureFromGeometry.addNumberProperty(SentryBaseEvent.JsonKeys.DIST, Double.valueOf(TurfMeasurement.distance(point, (Point) featureFromGeometry.geometry(), str2)));
            } else {
                featureFromGeometry = null;
            }
            if (((Double) featureFromGeometry3.getNumberProperty(SentryBaseEvent.JsonKeys.DIST)).doubleValue() < ((Double) feature.getNumberProperty(SentryBaseEvent.JsonKeys.DIST)).doubleValue()) {
                featureFromGeometry3.addNumberProperty(INDEX_KEY, Integer.valueOf(i));
                feature = featureFromGeometry3;
            }
            if (((Double) featureFromGeometry4.getNumberProperty(SentryBaseEvent.JsonKeys.DIST)).doubleValue() < ((Double) feature.getNumberProperty(SentryBaseEvent.JsonKeys.DIST)).doubleValue()) {
                featureFromGeometry4.addNumberProperty(INDEX_KEY, Integer.valueOf(i));
            } else {
                featureFromGeometry4 = feature;
            }
            if (featureFromGeometry == null || ((Double) featureFromGeometry.getNumberProperty(SentryBaseEvent.JsonKeys.DIST)).doubleValue() >= ((Double) featureFromGeometry4.getNumberProperty(SentryBaseEvent.JsonKeys.DIST)).doubleValue()) {
                feature = featureFromGeometry4;
            } else {
                featureFromGeometry.addNumberProperty(INDEX_KEY, Integer.valueOf(i));
                feature = featureFromGeometry;
            }
            i = i2;
        }
        return feature;
    }

    private static LineIntersectsResult lineIntersects(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        LineIntersectsResult lineIntersectsResultBuild = LineIntersectsResult.builder().onLine1(false).onLine2(false).build();
        double d9 = d8 - d6;
        double d10 = d3 - d;
        double d11 = d7 - d5;
        double d12 = d4 - d2;
        double d13 = (d9 * d10) - (d11 * d12);
        if (d13 == 0.0d) {
            if (lineIntersectsResultBuild.horizontalIntersection() == null || lineIntersectsResultBuild.verticalIntersection() == null) {
                return null;
            }
            return lineIntersectsResultBuild;
        }
        double d14 = d2 - d6;
        double d15 = d - d5;
        double d16 = ((d11 * d14) - (d9 * d15)) / d13;
        double d17 = ((d14 * d10) - (d15 * d12)) / d13;
        LineIntersectsResult lineIntersectsResultBuild2 = lineIntersectsResultBuild.toBuilder().horizontalIntersection(Double.valueOf(d + (d10 * d16))).build().toBuilder().verticalIntersection(Double.valueOf(d2 + (d12 * d16))).build();
        if (d16 > 0.0d && d16 < 1.0d) {
            lineIntersectsResultBuild2 = lineIntersectsResultBuild2.toBuilder().onLine1(true).build();
        }
        if (d17 > 0.0d && d17 < 1.0d) {
            lineIntersectsResultBuild2 = lineIntersectsResultBuild2.toBuilder().onLine2(true).build();
        }
        if (lineIntersectsResultBuild2.onLine1() && lineIntersectsResultBuild2.onLine2()) {
            return lineIntersectsResultBuild2;
        }
        return null;
    }
}
